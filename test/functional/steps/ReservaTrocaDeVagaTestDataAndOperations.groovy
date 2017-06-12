package steps

import sistemadevagasdeestacionamento.UserController
import sistemadevagasdeestacionamento.Vaga
import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.VagaController

/**
 * Created by Gabriel on 16/05/2017.
 */

class ReservaTrocaDeVagaTestDataAndOperations{

    static criarVaga(String numeroVaga, String setorVaga, String tipoVaga){
        def controlador = new VagaController()
        controlador.params << [numero: numeroVaga, setor: setorVaga, preferenceType: tipoVaga]
        controlador.save()
        controlador.response.reset()
    }

    static reservarVaga(Vaga vaga, User usuario){
        def controlador = new VagaController()
        controlador.reservar(vaga, usuario)
        controlador.response.reset()
    }

    static checarReservaVaga(String numero, String setor, String usuario){
        Vaga criada = Vaga.findByNumero(numero)
        User user = User.findByUsername(usuario)
        if(criada.getSetor() == setor){
            reservarVaga(criada, user)
        }
    }

    static removerReserva(tempo){
        def controlador = new VagaController()
        controlador.desocuparAposTempo(tempo)
        controlador.response.reset()
    }

    static boolean compararReservaVaga(User usuario, Vaga vaga) {
        def bool = false
        vaga.reservas.each {it ->
            if (it.usuario == usuario){
                bool = true
            }
        }
        bool
    }

    static boolean compararReservaUser (String user){
        def bool = false
        def controller = new VagaController()
        controller.varreReservas(user).each {it ->
            if(it.usuario.username == user){
                bool = true
            }
        }
        bool
    }

}