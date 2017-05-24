Feature: Lembrete de vaga
  As a system user
  I want to be informed where I parked my car
  So that I can remember where my car is in the parking space

  #controle
  Scenario: Lembrete de vaga estacionada
    Given o sistema possui um usuário armazenado com login "ala6"
    And o usuário está logado no sistema
    And vaga "C3" do setor "CIn" foi reservada pelo usuário
    When o usuário pedir um lembrete de vaga
    Then o sistema informa a vaga

  Scenario: Lembrete de vaga estacionada
    Given o sistema possui um usuário armazenado com login "ala6"
    And o usuário está logado no sistema
    And nenhuma vaga foi reservada pelo usuário
    When o usuário pedir um lembrete de vaga
    Then o sistema informa que não foi feita nenhuma reserva

  #gui
  Scenario: Lembrete de vaga estacionada web
    Given eu estou logado no sistema como "ala6"
    And eu estou na página principal
    And eu reservei a vaga "C3" do setor "CIn"
    When eu seleciono a opção de lembrar vaga
    Then eu vejo uma mensagem informando vaga "C3" no setor "CIn"

  Scenario: Lembrete de vaga estacionada web
    Given eu estou logado no sistema como "ala6"
    And eu estou na página principal
    And eu não tenho nenhuma reserva no sistema
    When eu seleciono a opção de lembrar vaga
    Then eu vejo uma mensagem informando que não foi feita uma reserva

