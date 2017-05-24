Feature: Reserva e Troca de vagas
As a usuario do sistema
I want to reservar e trocar de vagas
So that eu possa estacionar na vaga reservada

  Scenario: Troca de vaga
    Given O sistema tem o usuario "herminios2" armazenado
    And eu estou logado no sistema como "herminios2"
    And existe a vaga "1" no setor "CIn" do tipo "Normal"
    And existe a vaga "2" no setor "CCEN" do tipo "Normal"
    And a vaga "1" do setor "CIn" já está reservada para "herminios2"
    And a vaga "2" do setor "CCEN" está disponível
    When o usuario "herminios2" tenta reservar a vaga "2" do setor "CCEN"
    Then o sistema reserva a vaga "2" para o usuário "herminios2"
    And a vaga "1" do setor "CIn" está disponível



Scenario: Fazer a reserva de uma vaga
Given O sistema tem o usuario "Gabriel" armazenado
And eu estou logado no sistema como "Gabriel"
And existe a vaga "1" no setor "CIn" do tipo "Normal"
And a vaga "1" do setor "CIn" está disponível
When o usuario "Gabriel" tenta reservar a vaga "1" do setor "CIn"
Then o sistema reserva a vaga "1" para o usuário "Gabriel"

@ignore
Scenario: Reserva de Vaga GUI
Given eu estou logado no sistema como "Gabriel"
And estou na pagina de listagem de vagas
And a vaga "1" do setor "CIn" aparece como diponível na lista de vagas
When Eu seleciono a opção de reservar a vaga "1"
Then Eu vejo uma mensagem de confirmação
@ignore
Scenario: Troca de Vaga Gui
Given eu estou logado no sistema como "Gabriel"
And estou na pagina de listagem de vagas
And A vaga "1" do setor "CIn" aparece como reservada para "Gabriel"
And a vaga "2" do setor "CCEN" aparece como disponível
When o usuário seleciona a opção de reservar a vaga "2"
Then a vaga "1" do setor "CIn" aparece como disponível
And a vaga "2" do setor "CCEN" aparece como reservada para "Gabriel"
