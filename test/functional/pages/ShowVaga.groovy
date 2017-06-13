package pages

import geb.Page
import steps.InternationalizationHelper

class ShowVaga extends Page {
    static url = 'vaga/show/'

    static at = {
        InternationalizationHelper helper = InternationalizationHelper.instance
        String pageTitle = helper.getMessage("default.show.label", "Vaga")
        title ==~ pageTitle
    }

    boolean checarOcupada(){
        InternationalizationHelper helper =  InternationalizationHelper.instance
        String temp = helper.getMessage("default.boolean.true")
        def s = $("span", id: "t").text()
        return $("span", id: "t").text().equals(temp)
    }

    boolean checarManutencao(){
        InternationalizationHelper helper =  InternationalizationHelper.instance
        String temp = helper.getMessage("default.boolean.true")
        def s = $("span", id: "t").text()
        return $("span", id: "t").text().equals(temp)
    }
}