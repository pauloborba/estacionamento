package steps

import cucumber.api.PendingException
import sistemadevagasdeestacionamento.AuthHelper
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.UserController
import sistemadevagasdeestacionamento.Vaga
import sistemadevagasdeestacionamento.VagaController

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

static Vaga sugestaoVaga (User usuario) {
    def setor = usuario.preferredSector
    def tipo = usuario.preferenceType
    def vaga = Vaga.findBySetorAndPreferenceTypeAndOcupada(setor,tipo,false)
    if( vaga == null) {
        vaga = Vaga.findByOcupada(false)
    }
    return vaga
}

static User acharUser (String username) {
    def usuario = User.findByUsername(username)
    return usuario
}

Given(~/^o sistema tem o usuario "([^"]*)" armazenado com preferencia pelo setor "([^"]*)" e tipo de vaga "([^"]*)"$/) { String usuario, String setor, String vaga ->
    AuthHelper.instance.signup(usuario, setor, vaga)
    assert acharUser(usuario) != null
   // def user = User.findByUsername(usuario)
    //assert user != null
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
    def user = acharUser(usuario)
    assert user
    sugestaoVaga(user)
}
Then(~/^o sistema sugere a vaga "([^"]*)" para reserva$/) { String numeroVaga ->
    def usuario = acharUser(AuthHelper.instance.currentUsername)
    assert sugestaoVaga(usuario) == Vaga.findByNumero(numeroVaga)
}