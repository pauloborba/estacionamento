package sistemadevagasdeestacionamento



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import sistemadevagasdeestacionamento.AuthHelper

@Transactional(readOnly = true)
class VagaController {

    static allowedMethods = [update: "PUT"]

    def desocupar(Vaga vaga){
        vaga.setOcupada(false)
        vaga.reservas.last().setSaida(new Date())
        vaga.save(flush:true)
    }
    def varreReservas(Vaga, String usuario){
        def reservasDoUsuario = Vaga.all.reservas.each {ita->
            ita.each { ite ->
                ite.usuario.username == usuario && ite.saida != null
            }
        }
        reservasDoUsuario
    }

    def acharEDesocuparVagaAntiga(def reservasDoUsuario){
        if(reservasDoUsuario != null) {
            reservasDoUsuario.each { it ->
                it.each { ita ->
                    desocupar(ita.vaga)
                }
            }
        }
    }

    def reservar(Vaga vaga, User usuario) {
        def usuarioLogado = User.findByUsername(AuthHelper.instance.currentUsername)

        def antigaOcupada = varreReservas(vaga, usuarioLogado.username) //varre as reservas para ver se o usuario já ocupa alguma vaga

        acharEDesocuparVagaAntiga(antigaOcupada)  //encontra a vaga ocupada pelo usuario logado e a desocupa

        if(!vaga.ocupada){
            vaga.setOcupada(true)
            def reserva = new Reserva(usuario: usuarioLogado, vaga: vaga, entrada: new Date())
            vaga.reservas.add(reserva)
            vaga.save(flush:true)
        }
        redirect(action: "show", id: vaga.id)
    }

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Vaga.list(params), model:[vagaInstanceCount: Vaga.count()]
    }

    def show(Vaga vagaInstance) {
        respond vagaInstance
    }


    def create() {
        respond new Vaga(params)
    }

    def save() {
        def vagaInstance = new Vaga(params)
        if (!vagaInstance.save(flush: true)) {
            render(view: "create", model: [vagaInstance: vagaInstance])
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'vaga.label', default: 'Vaga'), vagaInstance.id])
        redirect(action: "show", id: vagaInstance.id)
    }
    @Transactional
    /*  def save(Vaga vagaInstance) {
          if (vagaInstance == null) {
              vagaInstance = new Vaga(params)
              notFound()
              return
          }
          if (vagaInstance.hasErrors()) {
              respond vagaInstance.errors, view:'create'
              return
          }
          vagaInstance.save flush:true
          request.withFormat {
              form multipartForm {
                  flash.message = message(code: 'default.created.message', args: [message(code: 'vaga.label', default: 'Vaga'), vagaInstance.id])
                  redirect vagaInstance
              }
              '*' { respond vagaInstance, [status: CREATED] }
          }
      }
  */
    def edit(Vaga vagaInstance) {
        respond vagaInstance
    }

    @Transactional
    def update(Vaga vagaInstance) {
        if (vagaInstance == null) {
            notFound()
            return
        }

        if (vagaInstance.hasErrors()) {
            respond vagaInstance.errors, view:'edit'
            return
        }

        vagaInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Vaga.label', default: 'Vaga'), vagaInstance.id])
                redirect vagaInstance
            }
            '*'{ respond vagaInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Vaga vagaInstance) {

        if (vagaInstance == null) {
            notFound()
            return
        }

        vagaInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Vaga.label', default: 'Vaga'), vagaInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vaga.label', default: 'Vaga'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}