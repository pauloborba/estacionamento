package pages

import geb.Page

class HomePage extends Page {
    static url = 'home/index'

    static at = {
        title ==~ "Home"
    }

    def reminded(){
        $(".reminder").click()
    }

    def message(String spot, type, sector){
        $("div.message").text() == ("O usuário estacionou na vaga ${spot} tipo ${type} do setor ${sector}" as String)
    }

    def message(){ // quando o usuario não reservar e clicar no lembrete
        $("div.message").text() == ("O usuário não estacionou em nenhuma vaga")
    }

    def logout(){
        $("h4[id='logout'").click()
    }
}