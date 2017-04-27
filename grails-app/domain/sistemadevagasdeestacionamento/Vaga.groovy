package sistemadevagasdeestacionamento

class Vaga {
    User dono
    String numero
    String setor
    boolean tipoDeVaga

    static constraints = {
        dono nullable: true, unique: true
        numero nullable: false, blank: false, unique: true
        setor inList: ["A", "B", "C", "D"]
        tipoDeVaga nullable: false, blank: false
    }
}
