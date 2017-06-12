package steps
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

    static removerReserva(Vaga vaga, int tempo){
        def controlador = new VagaController()
        controlador.desocuparAposTempo(vaga, tempo)
        controlador.response.reset()
    }
    static removerTodasReservas(int tempo){
        def controlador = new VagaController()
        controlador.desocuparTodasAposTempo(tempo)
        controlador.response.reset()
    }

}