package sistemadevagasdeestacionamento

class Vaga {
    User dono
    String numero
    String setor
    Integer tipoDeVaga

    static constraints = {
        dono nullable: true, unique: true
        numero nullable: false, blank: false, unique: true
        setor inList: ["A", "B", "C", "D"]
        tipoDeVaga inList: [0,1,2] //0 = Normal, 1 = Idoso , 2 = Cadeirante

    }
}
