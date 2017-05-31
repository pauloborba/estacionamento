package pages

import geb.Page

class HomePage extends Page {
    static url = 'home/index'

    static at = {
        title ==~ "Home"
    }

    def sugest() {
        $(".sugest").click()
    }

    def message(String numero, String tipo, String setor) {
        $("div", class: "message").text() == ("É sugerido a vaga ${numero} do tipo ${tipo} no setor ${setor} para reserva" as String)
    }

    def message() {
        $("div", class: "message").text() == ("Não existem vagas disponiveis para reserva")
    }

    def logout() {
        $("h4[id='logout'").click()
    }
}