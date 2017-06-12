package steps

import cucumber.api.PendingException
import pages.ListaDeVagas
import pages.SignUpPage
import sistemadevagasdeestacionamento.AuthHelper
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.UserController
import sistemadevagasdeestacionamento.Vaga
import sistemadevagasdeestacionamento.VagaController

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


And(~/^o usuario "([^"]*)" já reservou a vaga "([^"]*)"$/) { String user, String numeroVaga ->


}
When(~/^o usuario "([^"]*)" solicita a sugestao de vaga baseada no historico$/) { String user ->
    def usuario = User.findByUsername(user)
    assert usuario
    def controler = new UserController ()
    controler.sugestHistoric(user)

}
And(~/^o usuario "([^"]*)" não possui reservas anteriores$/) { String user ->

}
When(~/^eu seleciono a opção de sugestão de vaga baseadas no historico$/) { ->

}
Then(~/^a vaga "([^"]*)" é sugerida para reserva$/) { String numeroVaga ->

}