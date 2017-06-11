package pages

import geb.Page

class HomePage extends Page {
    static url = 'home/index'

    static at = {
        title ==~ "Home"
    }

    def reminded(){
        $(".reminded").click()
    }
    def message(String spot, String type, String sector){
        $("div", class: "message").text() == ("O usuário estacionou na vaga ${spot} do tipo ${type} no setor ${sector}" as String)
    }

    def message(){ // quando o usuario não reservar e clicar no lembrete
        $("div", class: "message").text() == ("O usuário não estacionou em nenhuma vaga")
    }

    def logout(){
        $("h4[id='logout'").click()
    }
}