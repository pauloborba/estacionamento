package steps

import cucumber.api.PendingException
import sistemadevagasdeestacionamento.Vaga
import pages.ListaDeVagas
import sistemadevagasdeestacionamento.VagaController

/**
 * Created by Gabriel on 02/06/2017.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

When(~/^passaram mais do que (\d+) segundos da vaga "([^"]*)" do setor "([^"]*)" reservada para "([^"]*)"$/) { int tempo, String numero, String setor, String user ->
    def vaga = Vaga.findByNumeroAndSetor(numero, setor)
    sleep(tempo*1000)
    ReservaTrocaDeVagaTestDataAndOperations.removerReserva(vaga, tempo)
}
Then(~/^a vaga "([^"]*)" do setor "([^"]*)" é desocupada$/) { String numero, String setor ->
    def vaga = Vaga.findByNumeroAndSetor(numero, setor)
    assert !vaga.ocupada
}

When(~/^eu tento remover todas as reservas que esgotoram o limite de reserva de (\d+) segundos$/) { int tempo ->
    sleep(tempo*1000)
    ReservaTrocaDeVagaTestDataAndOperations.removerTodasReservas(tempo)
}
When(~/^eu seleciono a opção de remover todas as reservas que esgotaram o limite de tempo de reserva de (\d+) segundos$/) { int tempo ->
    to ListaDeVagas
    at ListaDeVagas
    sleep(tempo*1000)
    page.desocuparReservasExpiradas()
}