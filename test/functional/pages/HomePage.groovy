package pages

import geb.Page

class HomePage extends Page {
    static url = 'home/index'

    static at = {
        title ==~ "Home"
    }

    def reminderClick(){
        $(".reminded").click()
    }
    def checkReminder(String spot, String type, String sector){
        $("div", class: "message").text() == ("O usuário estacionou na vaga ${spot} do tipo ${type} no setor ${sector}" as String)
    }

    def checkReminder(){
        $("div", class: "message").text() == ("O usuário não estacionou em nenhuma vaga")
    }

    def logout(){
        $("h4[id='logout'").click()
    }

    def sugest() {
        $(".sugest").click()
    }

    def mensagem(String numero, String tipo, String setor) {
        $("div", class: "message").text() == ("É sugerido a vaga ${numero} do tipo ${tipo} no setor ${setor} para reserva" as String)
    }

    def mensagem() {
        $("div", class: "message").text() == ("Não existem vagas disponiveis para reserva")
    }
}