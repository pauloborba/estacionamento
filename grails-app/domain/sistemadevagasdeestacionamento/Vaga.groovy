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

    def ocupar(User usuarioLogado){
        this.setOcupada(true)
        def reserva = new Reserva(usuario: usuarioLogado, vaga: this, entrada: new Date())
        this.reservas.add(reserva)
        this.save(flush:true)
    }

    def desocupar(){
        this.setOcupada(false)
        this.reservas.last().setSaida(new Date())
        this.save(flush:true)
    }

}