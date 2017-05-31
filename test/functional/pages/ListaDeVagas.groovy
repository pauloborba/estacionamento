package pages

import geb.Page

class ListaDeVagas extends Page {
    static url = 'vaga/index'

    static at = {
        
       title ==~ /Vaga List/
    }

    boolean acharVaga(String vaga, String setor, String tipo){
        boolean procuraNumero = $("tr").find("td").has("a",text: vaga)
        boolean procuraSetor = $("tr").has("td",text: setor)
        boolean procuraTipo = $("tr").has("td",text: tipo)
        if(procuraNumero && procuraSetor && procuraTipo){
            true
        }
    }

    boolean vagaOcupada(String numeroVaga, String setorVaga, String tipoVaga){
        boolean encontrada = acharVaga(numeroVaga, setorVaga, tipoVaga)
        //  boolean ocupada = $("tr").has("td",text: "Sim")
        boolean ocupada = $("tr").has("td",text: "True")
        assert (encontrada && ocupada)
    }

    boolean vagaLimpa(String numeroVaga, String setorVaga, String tipoVaga){
        boolean encontrada = acharVaga(numeroVaga, setorVaga, tipoVaga)
        //     boolean naoOcupada = $("tr").has("td",text: "Não")
        boolean naoOcupada = $("tr").has("td",text: "False")
        assert (encontrada && naoOcupada)
    }

    def reservarVaga(String numeroVaga, String setorVaga, String tipoVaga){
        boolean vaga = acharVaga(numeroVaga, setorVaga, tipoVaga)
        if(vaga) {
            $("tr").find("td").has("a", text: "Reservar "+numeroVaga).click()
        }
    }

}