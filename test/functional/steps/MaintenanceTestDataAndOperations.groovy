package steps

import sistemadevagasdeestacionamento.User
import sistemadevagasdeestacionamento.Vaga
import sistemadevagasdeestacionamento.VagaController

/**
 * Created by Allan on 10/06/2017.
 */
class MaintenanceTestDataAndOperations {

    static interditarVaga(Vaga spot, User user){
        def controller = new VagaController()
        spot.maintenance = true
        controller.reservar(spot, user)
        controller.response.reset()

    }
}
