package steps

import org.apache.tools.ant.taskdefs.WaitFor
import sistemadevagasdeestacionamento.AuthHelper
import sistemadevagasdeestacionamento.User
import pages.*

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

//controller
Given(~/^o sistema não tem o usuário "([^"]*)" armazenado$/) { String username ->
    def user = User.findByUsername(username)
    assert user == null
}
When(~/^o usuário tenta se cadastrar com usuário "([^"]*)" com preferencia no setor "([^"]*)"$/) { String username, String sector ->
    AuthHelper.instance.signup(username, sector, false)
}

Then(~/^o sistema armazena o usúario "([^"]*)"$/) { String username ->
    def currentUser = User.findByUsername(username)
    assert currentUser != null
}
Given(~/^o sistema tem o usuário "([^"]*)" armazenado$/) { String username ->
    AuthHelper.instance.signup(username, "CCEN", false)
    def currentUser = User.findByUsername(username)
    assert currentUser != null
}
Then(~/^o sistema não armazena o usúario "([^"]*)" de preferencia no setor "([^"]*)"$/) { String username, String sector ->
    def user = User.findByUsername(username)
    assert user.preferredSector != sector
}

//gui

Given(~/^eu estou na página de cadastro$/) { ->
    waitFor { to SignUpPage }
    waitFor { at SignUpPage }
}
When(~/^eu tento me cadastrar com usuário "([^"]*)" e preferencia no setor "([^"]*)"$/) { String username, String sector ->
    page.proceed(username, sector, false)
}
Then(~/^eu sou redirecionado para o sistema$/) { ->
    waitFor { at HomePage }
}
Given(~/^Eu tenho um cadastro no sistema com o usuário "([^"]*)"$/) { String username ->
    waitFor { to SignUpPage }
    page.proceed(username, "CIn", false)
}
Then(~/^eu vejo uma mensagem de erro$/) { ->
    assert page.readFlashMessage() != null
}
