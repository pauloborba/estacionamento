package sistemadevagasdeestacionamento

class Vaga {
    String numero
    String setor
    boolean preferencial
    static hasMany = [reservas:Reserva]
    String tipoVaga

    static constraints = {
        numero nullable: false, blank: false, unique: true
        setor inList: ["A", "B", "C", "D"]
        preferencial nullable: false, blank: false
        tipoVaga inList: ["Normal", "Deficiente", "Idoso"]
    }
}
