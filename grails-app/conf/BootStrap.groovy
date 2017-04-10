import sistemadevagasdeestacionamento.*

class BootStrap {

    def init = { servletContext ->
        // Usuário padrão do sistema, esse login quando você desejar logar no sistema
        // sem ter criado um outro usuário previamente
        def masterUser = new User(username: "master", firstName: "Usuário", lastName: "Master", preferredSector: "CIn")
        masterUser.save(flush: true)

    }

    def destroy = {
    }
}