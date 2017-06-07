package sistemadevagasdeestacionamento

class Reserva {
    User usuario
    Vaga vaga
    Date entrada
    Date saida
    static belongsTo = [vaga:Vaga]

    static constraints = {
        usuario blank: false, unique: false
        vaga blank: false, unique: false
        //Quando a reserva for criada, não tem sainda, então 'saida' pode ser nula, mas a entrada não pode ser nulaa
        entrada nullable: false, blank: true
        saida nullable: true, blank: true

    }
}