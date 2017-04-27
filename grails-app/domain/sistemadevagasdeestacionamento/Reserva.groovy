package sistemadevagasdeestacionamento

class Reserva {
    User usuario
    Vaga vaga
    Date entrada
    Date saida

    static constraints = {
        usuario nullable: true, unique: true
        vaga nullable: true, unique: true
        entrada nullable: true
        saida nullable: true

    }
}
