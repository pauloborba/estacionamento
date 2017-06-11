Feature: Manutenção de vaga
  As a master user
  I want to reserve a parking spot for maintenance
  So that I can alert the users that the parking spot can't be reserved

  #controle
  Scenario: Marcar a vaga como indisponível
    Given o sistema tem o usuário "master" armazenado
    And o usuario "master" está logado no sistema
    And existe a vaga "9" no setor "CIn" do tipo "Normal"
    When o usuário "master" tenta marcar a vaga "9" tipo "Normal" do setor "CIn" como indisponível
    Then o sistema marca a vaga "9" tipo "Normal" do setor "CIn" como indisponível

  Scenario: Alerta de vaga indisponível
    Given o sistema tem o usuário "naix" armazenado
    And o usuario "naix" está logado no sistema
    And existe a vaga "0" no setor "CIn" do tipo "Deficiente"
    And a vaga "0" do setor "CIn" está indisponível
    When o usuario "naix" tenta reservar a vaga "0" do setor "CIn"
    Then o sistema informa que a vaga "0" tipo "Deficiente" do setor "CIn" está indisponível

  Scenario: Alerta de vaga indisponível web
    Given eu estou logado no sistema como "master"
    And estou na pagina de listagem de vagas

    And a vaga "76" do setor "CIn" do tipo "Normal" aparece como indiponível
    When eu seleciono a opção de reservar a vaga "76" do setor "CIn" do tipo "Normal"
    Then eu vejo uma mensagem informando que a vaga "76" tipo "Normal" do setor "CIn" está indisponível

