package steps

import pages.ListaDeVagas
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.Vaga

/**
 * Created by Allan on 10/06/2017.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

//controller
When(~/^o usuário "([^"]*)" tenta marcar a vaga "([^"]*)" tipo "([^"]*)" do setor "([^"]*)" como indisponível$/) { String username, String spot, String type, String sector ->
    User currentUser = User.findByUsername(username)
    Vaga currentSpot = Vaga.findByNumero(spot)
    MaintenanceTestDataAndOperations.interditarVaga(currentSpot, currentUser)
}
Then(~/^o sistema marca a vaga "([^"]*)" tipo "([^"]*)" do setor "([^"]*)" como indisponível$/) { String spot, String type, String sector ->
    Vaga currentSpot = Vaga.findByNumero(spot)
    if (currentSpot.ocupada){
//        assert currentSpot.maintenance
    }

}

And(~/^a vaga "([^"]*)" do setor "([^"]*)" está indisponível$/) { String spot, String sector ->
    Vaga currentSpot = Vaga.findByNumero(spot)
    if(currentSpot.getSetor() == sector) {
        assert !currentSpot.maintenance
    }
}

//gui
Then(~/^o sistema informa que a vaga "([^"]*)" tipo "([^"]*)" do setor "([^"]*)" está indisponível$/) { String spot, String type, String sector ->
    assert page.readFlashMessage() != null
}

And(~/^a vaga "([^"]*)" do setor "([^"]*)" do tipo "([^"]*)" aparece como indiponível$/) { String spot, String sector, String type ->
    to ListaDeVagas
    at ListaDeVagas
//    page.vagaOcupada(spot, sector, type)
    page.vagaManutencao(spot, sector, type)
}
Then(~/^eu vejo uma mensagem informando que a vaga "([^"]*)" tipo "([^"]*)" do setor "([^"]*)" está indisponível$/) { String spot, String type, String sector ->
    assert page.readFlashMessage() != null
}