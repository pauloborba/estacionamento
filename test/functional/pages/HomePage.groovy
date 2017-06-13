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

    def sugestHistoric(){
        $(".sugestHistoric").click()
    }
}