package sistemadevagasdeestacionamento

class Vaga {
    String numero
    String setor
    static hasMany = [reservas:Reserva]
    String preferenceType
    boolean ocupada
    boolean maintenance

    Vaga(){
        reservas = []
    }

    static constraints = {
        numero nullable: false, blank: false, unique: true
        setor inList: ["CIn", "CCEN", "Area II"]
        preferenceType inList: ["Normal", "Deficiente", "Idoso"]
        ocupada nullable: false
        maintenance nullable: false
    }

    static Vaga sugestaoVaga (User usuario) {
        def setor = usuario.preferredSector
        def tipo = usuario.preferenceType
        def vaga = findBySetorAndPreferenceTypeAndOcupada(setor,tipo,false)
        if( vaga == null) {
                vaga = findByOcupada(false)
            }
        return vaga
    }

    static Vaga sugestaoVagaHistorico (String usuario) {
        def retorno = null
        def count = 0
        def controller = new VagaController()
        def vagas = controller.varreReservas(usuario)
        vagas.each {it ->
            it.find {ite ->
                def vagaAux = findByNumero(ite.vaga.numero)
                if ((!vagaAux.ocupada) && (count == 0)) {
                    count = 1
                    retorno = vagaAux
                }
            }            
        }
        if(count == 0){
            sugestaoVaga(User.acharUser(usuario))
        } else {
            return retorno
        }
    }

    def ocupar(User usuarioLogado){
        this.setOcupada(true)
        def reserva = new Reserva(usuario: usuarioLogado, vaga: this, entrada: new Date())
        this.reservas.add(reserva)
    }

    def interditar(User user){
        if (!this.ocupada) { ocupar(user) } // caso não esteja reservada (ocupada)
        this.setMaintenance(true)
    }

    def desinterditar(User user){
        if (this.ocupada) { desocupar(user) }
        this.setMaintenance(false)

    }

    def desocupar(){
        this.setOcupada(false)
        this.reservas.last().setSaida(new Date())
    }

}
