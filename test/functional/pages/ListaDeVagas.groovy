package pages

import geb.Page
import steps.InternationalizationHelper

class ListaDeVagas extends Page {
    static url = 'vaga/index'

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String pageTitle = helper.getMessage("default.list.label", "Vaga")
        title ==~ pageTitle
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
        InternationalizationHelper helper =  InternationalizationHelper.instance
        String temp = helper.getMessage("default.boolean.true")

        boolean encontrada = acharVaga(numeroVaga, setorVaga, tipoVaga)
        boolean ocupada = $("tr").has("td",text: temp)
        assert (encontrada && ocupada)
    }

    boolean vagaManutencao(String spot, String setorVaga, String tipoVaga){
        InternationalizationHelper helper =  InternationalizationHelper.instance
        String temp = helper.getMessage("default.boolean.true")

        boolean encontrada = acharVaga(spot, setorVaga, tipoVaga)
        boolean ocupada = $("tr").has("td",text: temp)
        boolean manutencao = $("tr").has("td",text: temp)
        assert (encontrada && ocupada && manutencao)
    }

    boolean vagaLimpa(String numeroVaga, String setorVaga, String tipoVaga){
        InternationalizationHelper helper =  InternationalizationHelper.instance
        String temp = helper.getMessage("default.boolean.false")

        boolean encontrada = acharVaga(numeroVaga, setorVaga, tipoVaga)
        boolean naoOcupada = $("tr").has("td",text: temp)
        assert (encontrada && naoOcupada)
    }

    def reservarVaga(String numeroVaga, String setorVaga, String tipoVaga){
        boolean vaga = acharVaga(numeroVaga, setorVaga, tipoVaga)
        if(vaga) {
            $("tr").find("td").has("a", text: "Reservar "+numeroVaga).click()
        }
    }
}