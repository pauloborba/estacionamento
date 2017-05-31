package steps

import cucumber.api.PendingException
import pages.CriarVaga
import pages.ListaDeVagas
import pages.SignUpPage
import sistemadevagasdeestacionamento.AuthHelper
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.Vaga

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


Given(~/^o sistema tem o usuario "([^"]*)" armazenado com preferencia pelo setor "([^"]*)" e tipo de vaga "([^"]*)"$/) { String usuario, String setor, String vaga ->
    AuthHelper.instance.signup(usuario, setor, vaga)
    assert User.findByUsername(usuario) != null
}
And(~/^eu estou logado no sistema como "([^"]*)"$/) { String usuario ->
    AuthHelper.instance.login(usuario)
    assert AuthHelper.instance.currentUsername == usuario
}
And(~/^existe a vaga "([^"]*)" no setor "([^"]*)" do tipo "([^"]*)" disponivel$/) { String numeroVaga, String setor, String tipo ->
    ReservaTrocaDeVagaTestDataAndOperations.criarVaga(numeroVaga,setor,tipo)

    def vaga = Vaga.findByNumero(numeroVaga)
    assert vaga.setor == setor
    assert vaga.preferenceType == tipo
    assert !vaga.ocupada
}
When(~/^o usuario "([^"]*)" solicita a sugestao de vaga$/) { String usuario ->
    def user = User.findByUsername(usuario)
    assert user
    Vaga.sugestaoVaga(user)
}
Then(~/^o sistema sugere a vaga "([^"]*)" para reserva$/) { String numeroVaga ->
    def usuario = User.findByUsername(AuthHelper.instance.currentUsername)
    assert Vaga.sugestaoVaga(usuario) == Vaga.findByNumero(numeroVaga)
}
Given(~/^estou logado no sistema como "([^"]*)", com preferencia pelo setor "([^"]*)" e tipo de vaga "([^"]*)"$/) { String login, String setor, String tipo ->
    to SignUpPage
    at SignUpPage
    page.proceed(login,setor,tipo)

}
And(~/^eu crio a vaga "([^"]*)" do setor "([^"]*)" do tipo "([^"]*)"$/) { String numero, String setor, String tipo ->
    to CriarVaga
    at CriarVaga
    page.criarVaga(numero,setor,tipo)
}
And(~/^estou na pagina de listagem de vagas/) { ->
    to ListaDeVagas
    at ListaDeVagas
}
And(~/^a vaga "([^"]*)" do setor "([^"]*)" do tipo "([^"]*)" aparece como disponivel$/) { String numero, String setor, String tipo ->
    to ListaDeVagas
    at ListaDeVagas
    page.vagaLimpa(numero,setor,tipo)

}
When(~/^eu seleciono a opção de sugestão de vaga$/) { ->
    assert page.sugest() != null

}
Then(~/^eu vejo uma mensagem informando a vaga "([^"]*)" do tipo "([^"]*)" no setor "([^"]*)"$/) { String numero, String tipo, String setor ->
    assert page.readFlashMessage() != null
}