package steps

import cucumber.api.PendingException
import sistemadevagasdeestacionamento.AuthHelper
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.Vaga
import sistemadevagasdeestacionamento.VagaController

//import steps.ReservaTrocaDeVagaTestDataAndOperations

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
/*
def criarVaga(String numeroVaga, String setorVaga, String tipoVaga){
    def controlador = new VagaController()
    //controlador.params << [numero: numeroVaga, setor: setorVaga, preferenceType: tipoVaga]
     controlador.save(new Vaga([numero: numeroVaga, setor: setorVaga, preferenceType: tipoVaga]))
   // controlador.save()
    controlador.response.reset()
}

def reservarVaga(Vaga vaga, User usuario){
    def controlador = new VagaController()
    controlador.reservar(vaga, usuario)
    controlador.response.reset()
}
def checarReservaVaga(String numero, String setor, String usuario){
    Vaga criada = Vaga.findByNumero(numero)
    User user = User.findByUsername(usuario)
    if(criada.getSetor() == setor){
        reservarVaga(criada, user)
    }
}
*/
Given(~/^O sistema tem o usuario "([^"]*)" armazenado$/) { String username ->
    AuthHelper.instance.signup(username, "CCEN", "Normal")
    def currentUser = User.findByUsername(username)
    assert currentUser != null
}

And(~/^eu estou logado no sistema como "([^"]*)"$/) { String login ->
    AuthHelper.instance.login(login)
    assert AuthHelper.instance.currentUsername == login
}
And(~/^existe a vaga "([^"]*)" no setor "([^"]*)" do tipo "([^"]*)"$/) { String numero, String setor, String tipo ->
    ReservaTrocaDeVagaTestDataAndOperations.criarVaga(numero, setor, tipo)
//    criarVaga(numero, setor, tipo)
/*
    def controlador = new VagaController()
    controlador.params << [numero: numero, setor: setor, preferenceType: tipo]


    controlador.save()
    controlador.response.reset()
*/
    def vaga = Vaga.findByNumero(numero)
//    assert vaga!=null
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
   // checarReservaVaga(numero, setor, login)
}
//falta completar (depende da logica do metodo reservar)
Then(~/^o sistema reserva a vaga "([^"]*)" para o usuário "([^"]*)"$/) { String numero, String login ->
    Vaga criada = Vaga.findByNumero(numero)
    assert criada.ocupada
    assert criada.reservas.last().entrada != null
}
And(~/^a vaga "([^"]*)" do setor "([^"]*)" já está reservada para "([^"]*)"$/) { String numero, String setor, String usuario->
    ReservaTrocaDeVagaTestDataAndOperations.checarReservaVaga(numero,setor, usuario)
 //   checarReservaVaga(numero, setor, usuario)
    Vaga vaga = Vaga.findByNumero(numero)
    if (vaga.setor == setor){
        assert vaga.ocupada
    }
}
And(~/^estou na página de listagem de vagas$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^a vaga "([^"]*)" do setor "([^"]*)" aparece como diponível na lista de vagas$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
When(~/^Eu seleciono a opção de reservar a vaga "([^"]*)"$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^Eu vejo uma mensagem de confirmação$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^Eu reservei a vaga "([^"]*)" do setor "([^"]*)"$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^a vaga "([^"]*)" do setor "([^"]*)" aparece como disponível$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
When(~/^o usuário seleciona a opção de reservar a vaga "([^"]*)"$/) { String arg1 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^a vaga "([^"]*)" do setor "([^"]*)" aparecerá como disponível$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^a vaga "([^"]*)" do setor "([^"]*)"  como reservada para "([^"]*)"$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}