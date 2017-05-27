package pages

import geb.Page
/**
 * Created by Gabriel on 24/05/2017.
 */
class CriarVaga extends Page {
    static url = 'vaga/create'

    static at = {
        title ==~ /Criar Vaga/
    }

    boolean criarVaga(String numeroVaga, String setorVaga, String tipoVaga){
        $("form").numero = numeroVaga
        $("form").setor = setorVaga
        $("form").preferenceType = tipoVaga
        $("input", name: "create").click()
    }



}
