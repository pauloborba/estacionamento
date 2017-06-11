package steps

import cucumber.api.PendingException
import pages.CriarVaga
import pages.ListaDeVagas
import pages.SignUpPage
import sistemadevagasdeestacionamento.AuthHelper
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.Vaga
import sistemadevagasdeestacionamento.VagaController

import steps.ReservaTrocaDeVagaTestDataAndOperations

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

//Given(~/^O sistema tem o usuario "([^"]*)" armazenado$/) { String username ->
//    AuthHelper.instance.signup(username, "CCEN", "Normal")
//    def currentUser = User.findByUsername(username)
//    assert currentUser != null
//}
//
//And(~/^eu estou logado no sistema como "([^"]*)"$/) { String login ->
//    to SignUpPage
//    at SignUpPage
//    AuthHelper.instance.login(login)
//    page.proceed(login, "CIn", "Normal")
//    assert AuthHelper.instance.currentUsername == login
//}
And(~/^existe a vaga "([^"]*)" no setor "([^"]*)" do tipo "([^"]*)"$/) { String numero, String setor, String tipo ->
    ReservaTrocaDeVagaTestDataAndOperations.criarVaga(numero, setor, tipo)

    def vaga = Vaga.findByNumero(numero)
    assert vaga?.getSetor() == setor
    assert vaga?.getPreferenceType() == tipo
}
And(~/^a vaga "([^"]*)" do setor "([^"]*)" está disponível$/) { String numero, String setor ->
    Vaga criada = Vaga.findByNumero(numero)
    if(criada.getSetor() == setor) {
        assert !criada.ocupada
    }
}

When(~/^o usuario "([^"]*)" tenta reservar a vaga "([^"]*)" do setor "([^"]*)"$/) { String login, String numero, String setor ->
    ReservaTrocaDeVagaTestDataAndOperations.checarReservaVaga(numero, setor, login)
}

Then(~/^o sistema reserva a vaga "([^"]*)" para o usuário "([^"]*)"$/) { String numero, String login ->
    Vaga criada = Vaga.findByNumero(numero)
    assert criada.ocupada
    assert criada.reservas.last().entrada != null
}
And(~/^a vaga "([^"]*)" do setor "([^"]*)" já está reservada para "([^"]*)"$/) { String numero, String setor, String usuario->
    ReservaTrocaDeVagaTestDataAndOperations.checarReservaVaga(numero,setor, usuario)
    Vaga vaga = Vaga.findByNumero(numero)
    if (vaga.setor == setor){
        assert vaga.ocupada
    }
}
And(~/^estou na pagina de listagem de vagas$/) { ->
    to ListaDeVagas
    at ListaDeVagas
}

And(~/^a vaga "([^"]*)" do setor "([^"]*)" do tipo "([^"]*)" aparece como diponivel$/) { String numeroVaga, String setorVaga, String tipoVaga ->
    to ListaDeVagas
    at ListaDeVagas
    page.vagaLimpa(numeroVaga, setorVaga, tipoVaga)
}

Then(~/^Eu vejo uma mensagem de confirmação$/) { ->
    assert page.readFlashMessage() != null
}

And(~/^a vaga "([^"]*)" do setor "([^"]*)" do tipo "([^"]*)" aparece como reservada$/) { String numeroVaga, String setorVaga, String tipoVaga ->
    page.checarOcupada()
}

When(~/^eu seleciono a opção de reservar a vaga "([^"]*)" do setor "([^"]*)" do tipo "([^"]*)"$/) { String numero, String setor, String tipo ->
    to ListaDeVagas
    at ListaDeVagas
    page.reservarVaga(numero, setor, tipo)
}
And(~/^eu crio a vaga "([^"]*)" do setor "([^"]*)" do tipo "([^"]*)"$/) { String numeroVaga, String setorVaga, String tipoVaga ->
    to CriarVaga
    at CriarVaga
    page.criarVaga(numeroVaga, setorVaga, tipoVaga)
}
And(~/^a vaga "([^"]*)" do setor "([^"]*)" do tipo "([^"]*)" está reservada$/) { String numero, String setor, String tipo ->
    to ListaDeVagas
    at ListaDeVagas
    page.reservarVaga(numero, setor, tipo)

}
And(~/^estou logado no sistema como "([^"]*)"$/) { String login ->
    AuthHelper.instance.login(login)
    assert AuthHelper.instance.currentUsername == login
}