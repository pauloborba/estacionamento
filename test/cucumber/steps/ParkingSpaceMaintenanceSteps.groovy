package steps

import cucumber.api.PendingException

/**
 * Created by Allan on 10/06/2017.
 */

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

When(~/^o usuário "([^"]*)" tenta marcar a vaga "([^"]*)" tipo "([^"]*)" do setor "([^"]*)" como indisponível$/) { String arg1, String arg2, String arg3, String arg4 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^o sistema marca a vaga "([^"]*)" tipo "([^"]*)" do setor "([^"]*)" como indisponível$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^a vaga "([^"]*)" do setor "([^"]*)" está indisponível$/) { String arg1, String arg2 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^o sistema informa que a vaga "([^"]*)" tipo "([^"]*)" do setor "([^"]*)" está indisponível$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^a vaga "([^"]*)" do setor "([^"]*)" do tipo "([^"]*)" está indisponível$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
Then(~/^eu vejo uma mensagem informando que a vaga "([^"]*)" tipo "([^"]*)" do setor "([^"]*)" está indisponível$/) { String arg1, String arg2, String arg3 ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}