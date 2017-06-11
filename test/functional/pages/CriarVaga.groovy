package pages

import geb.Page
import steps.InternationalizationHelper

/**
 * Created by Gabriel on 24/05/2017.
 */
class CriarVaga extends Page {
    static url = 'vaga/create'

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String pageTitle = helper.getMessage("default.create.label", "Vaga")
        title ==~ pageTitle
    }
    boolean criarVaga(String numeroVaga, String setorVaga, String tipoVaga){
        $("form").numero = numeroVaga
        $("form").setor = setorVaga
        $("form").preferenceType = tipoVaga
        $("input", name: "create").click()
    }



}