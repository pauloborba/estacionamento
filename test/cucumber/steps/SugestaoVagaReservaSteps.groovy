package steps


import sistemadevagasdeestacionamento.AuthHelper
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.UserController
import sistemadevagasdeestacionamento.Vaga
import sistemadevagasdeestacionamento.VagaController


this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


And(~/^o usuario "([^"]*)" já reservou a vaga "([^"]*)"$/) { String user, String numeroVaga ->
    def usuario = User.findByUsername(user)
    def vaga = Vaga.findByNumero(numeroVaga)
    def controller = new VagaController()
    ReservaTrocaDeVagaTestDataAndOperations.reservarVaga(vaga,usuario)
    controller.acharEDesocuparVagaAntiga(controller.varreReservas(user),usuario)
    assert  ReservaTrocaDeVagaTestDataAndOperations.compararReservaVaga(usuario,vaga)

}
When(~/^o usuario "([^"]*)" solicita a sugestao de vaga baseada no historico$/) { String user ->
    def usuario = User.findByUsername(user)
    assert usuario
    def controler = new UserController ()
    controler.sugestHistoric(user)

}
And(~/^o usuario "([^"]*)" não possui reservas anteriores$/) { String user ->
    assert !ReservaTrocaDeVagaTestDataAndOperations.compararReservaUser(user)

}
When(~/^eu seleciono a opção de sugestão de vaga baseadas no historico$/) { ->
    assert page.sugestaoReserva() != null

}
Then(~/^a vaga "([^"]*)" é sugerida para reserva$/) { String numeroVaga ->
    assert Vaga.sugestaoVagaHistorico(AuthHelper.instance.currentUsername) == Vaga.findByNumero(numeroVaga)

}
