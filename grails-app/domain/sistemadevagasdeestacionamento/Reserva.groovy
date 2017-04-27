package sistemadevagasdeestacionamento

class Reserva {
    User usuario
    Vaga vaga
    Date entrada
    Date saida

    static constraints = {
        usuario nullable: false, blank: false, unique: true
        vaga nullable: false, blank: false, unique: true
        entrada nullable: false, blank: true
        saida nullable: false, blank: true

    }
}
