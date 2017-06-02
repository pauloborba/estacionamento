Feature: Lembrete de vaga
  As a system user
  I want to be informed where I parked my car
  So that I can remember where my car is in the parking space

  #controle
  @ignore
  Scenario: Lembrete de vaga estacionada
    Given o sistema possui o usuário "ala6" armazenado
    And o usuário "ala6" está logado no sistema
    And a vaga "3" tipo "Normal" do setor "CIn" foi reservada pelo usuário "ala6"
    When o usuário "ala6" pedir um lembrete de vaga
    Then o sistema informa a vaga "3" tipo "Normal" do setor "CIn" para o usuário "ala6"
  @ignore
  Scenario: Lembrete de vaga não estacionada
    Given o sistema possui o usuário "divino" armazenado
    And o usuário "divino" está logado no sistema
    And nenhuma vaga foi reservada pelo usuário "divino"
    When o usuário "divino" pedir um lembrete de vaga
    Then o sistema informa para o usuário "divino" que não foi feita nenhuma reserva

  #gui
  @ignore
  Scenario: Lembrete de vaga estacionada web
    Given eu estou logado no sistema como "allan"
    And eu estou na página principal
    And eu reservei a vaga "5" tipo "Normal" do setor "CIn"
    When eu seleciono a opção de lembrar vaga
    Then eu vejo uma mensagem informando vaga "5" tipo "Normal" no setor "CIn"

  @ignore
  Scenario: Lembrete de vaga não estacionada web
    Given eu estou logado no sistema como "lima"
    And eu estou na página principal
    And eu não tenho nenhuma reserva no sistema
    When eu seleciono a opção de lembrar vaga
    Then eu vejo uma mensagem informando que não foi feita uma reserva


