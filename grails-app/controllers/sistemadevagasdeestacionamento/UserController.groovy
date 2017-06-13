package sistemadevagasdeestacionamento

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class UserController {
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond User.list(params), model:[userInstanceCount: User.count()]
    }

    def reminder(User user) {
        def booking = Reserva.findByUsuario(user)

        if (booking){
            flash.message = "O usuário estacionou na vaga ${booking.vaga.numero} do tipo ${booking.vaga.preferenceType} no setor ${booking.vaga.setor}"
        } else {
            flash.message = "O usuário não estacionou em nenhuma vaga"
        }

        redirect(controller: "home", action: "index")
    }

    def sugest(User usuario) {
        def setor = usuario.getPreferredSector()
        def tipo = usuario.getPreferenceType()
        def vaga = Vaga.findBySetorAndPreferenceTypeAndOcupada(setor,tipo,false)
        def vagaAux = Vaga.findByOcupada(false)
        if ((vaga == null) && vagaAux == null) {
            flash.message = "Não existem vagas disponíveis para reserva"
        } else if (vaga == null){
            this.mensagem(vagaAux)
        } else {
            this.mensagem(vaga)
        }
        redirect(controller: "vaga", action: "index")
    }
    def mensagem(Vaga vaga) {
        flash.message = "É sugerido a vaga ${vaga.getNumero()} do tipo ${vaga.getPreferenceType()} no setor ${vaga.getSetor()} para reserva"
    }

    def sugestHistoric(String usuario) {
        def controller = new VagaController()
        def vagas = controller.varreReservas(usuario)
        def count = 0
        def retorno = null
        def vagaLivre = Vaga.findByOcupada(false)
        vagas.each { it ->
            it.find { ite ->
                def vagaAux = Vaga.findByNumero(ite.vaga.numero)
                if ((!vagaAux.ocupada) && (count == 0)) {
                    count = 1
                    retorno = vagaAux
                }
            }
        }
        if ((count == 0) && (vagaLivre==null)){
            flash.message = "Não existem vagas disponíveis para reserva"
        } else if (count != 0){
            this.mensagem(vagaLivre)
        } else {
            this.mensagem(retorno)
        }
        redirect(controller: "vaga", action: "index")

    }

    def show(User userInstance) {
        respond userInstance
    }

    def create() {
        respond new User(params)
    }

    def save(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (!userInstance.save(flush:true)) {
            respond userInstance.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'user.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*' { respond userInstance, [status: CREATED] }
        }
    }

    def edit(User userInstance) {
        respond userInstance
    }

    @Transactional
    def update(User userInstance) {
        if (userInstance == null) {
            notFound()
            return
        }

        if (userInstance.hasErrors()) {
            respond userInstance.errors, view:'edit'
            return
        }

        userInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect userInstance
            }
            '*'{ respond userInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(User userInstance) {

        if (userInstance == null) {
            notFound()
            return
        }

        userInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'User.label', default: 'User'), userInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'user.label', default: 'User'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }

}
