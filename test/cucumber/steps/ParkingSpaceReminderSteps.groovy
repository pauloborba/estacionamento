package steps

import cucumber.api.PendingException
import pages.HomePage
import pages.SignUpPage
import sistemadevagasdeestacionamento.AuthHelper
import sistemadevagasdeestacionamento.Reserva
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.Vaga


/**
 * Created by Allan on 17/05/2017.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

//controller
Given(~/^o sistema possui o usuário "([^"]*)" armazenado$/) { String username ->
    AuthHelper.instance.signup(username, "CIn", "Normal")
    def currentUser = User.findByUsername(username)
    assert currentUser != null
}
And(~/^o usuário "([^"]*)" está logado no sistema$/) { String username ->
    AuthHelper.instance.login(username)
    assert AuthHelper.instance.currentUsername == username
}
And(~/^a vaga "([^"]*)" tipo "([^"]*)" do setor "([^"]*)" foi reservada pelo usuário "([^"]*)"$/) { String spot, String type, String sector, String username ->
    ReservaTrocaDeVagaTestDataAndOperations.criarVaga(spot, sector, type)
    def currentUser = User.findByUsername(username)
    def currentParkingSpot = Vaga.findByNumero(spot)
    assert currentParkingSpot.numero =="3"
    ReservaTrocaDeVagaTestDataAndOperations.checarReservaVaga(spot, sector, username)
}
And(~/^nenhuma vaga foi reservada pelo usuário "([^"]*)"$/) { String username ->
    def currentUser = User.findByUsername(username)
    Reserva.each { assert it.newInstance().usuario != currentUser    }
}
When(~/^o usuário "([^"]*)" pedir um lembrete de vaga$/) { String username ->
    def currentUser = User.findByUsername(username)
    assert currentUser
    //metodo lembrete de vaga em VagaController
}
Then(~/^o sistema informa a vaga "([^"]*)" tipo "([^"]*)" do setor "([^"]*)" para o usuário "([^"]*)"$/) { String spot, String type, String sector, String username ->
    Vaga currentSpot = Vaga.findByNumero(spot)
    assert currentSpot
    def currentUser = User.findByUsername(username)
    assert currentUser
    ReservaTrocaDeVagaTestDataAndOperations.criarVaga(spot, sector, type)
    ReservaTrocaDeVagaTestDataAndOperations.reservarVaga(currentSpot, currentUser)

    Reserva.findByUsuario(currentUser)
}

Then(~/^o sistema informa para o usuário "([^"]*)" que não foi feita nenhuma reserva$/) { String username ->
    def currentUser = User.findByUsername(username)
    assert currentUser
}


//gui
Given(~/^eu estou logado no sistema como "([^"]*)"$/) { String username ->
    to SignUpPage
    at SignUpPage
    page.proceed(username, "CIn", "Normal")
}
And(~/^eu estou na página principal$/) { ->
    at HomePage
}
And(~/^eu reservei a vaga "([^"]*)" tipo "([^"]*)" do setor "([^"]*)"$/) { String spot, String type, String sector ->
    ReservaTrocaDeVagaTestDataAndOperations.criarVaga(spot, sector, type)
    def currentSpot = Vaga.findByNumero(spot)
    //confirmação da vaga
    assert currentSpot.preferenceType == type
    assert currentSpot.setor == sector

    def aHelper = AuthHelper.instance.currentUsername
    def currentUser = User.findByUsername(aHelper)

//    esperando reserva de Gabriel

//    ReservaTrocaDeVagaTestDataAndOperations.reservarVaga(currentSpot, currentUser)
//    ReservaTrocaDeVagaTestDataAndOperations.checarReservaVaga(spot, sector, aHelper)

//    def booking = Reserva.findByVaga(currentSpot)

//    assert booking.vaga.setor == sector
//    assert booking.vaga.preferenceType == type
}
And(~/^eu não tenho nenhuma reserva no sistema$/) { ->
    def aHelper = AuthHelper.instance.currentUsername
    def currentUser = User.findByUsername(aHelper)
    Reserva.each { assert it.newInstance().usuario != currentUser    }
}
When(~/^eu seleciono a opção de lembrar vaga$/) { ->
    assert page.reminded() != null
}
Then(~/^eu vejo uma mensagem informando vaga "([^"]*)" tipo "([^"]*)" no setor "([^"]*)"$/) { String spot, String type, String sector ->
    assert page.readFlashMessage() != null
}
Then(~/^eu vejo uma mensagem informando que não foi feita uma reserva$/) { ->
    assert page.readFlashMessage() != null
}
