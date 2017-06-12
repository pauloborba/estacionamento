Feature: Sugestão de Vaga baseada no Historico
  As a usuario do sistema de vagas de estacionamento
  I want to vagas de estacionamento sugeridas baseadas em minhas reservas anteriores
  So that eu possa estacionar nas vagas de maior comodidade

  Scenario: Sugerir vaga para usuario que possui reservas anteriores
    Given o sistema tem o usuario "Marcos" armazenado com preferencia pelo setor "CIn" e tipo de vaga "Normal"
    And o usuario "Marcos" está logado no sistema
    And existe a vaga "C1" no setor "CIn" do tipo "Normal" disponivel
    And existe a vaga "C2" no setor "CIn" do tipo "Normal" disponivel
    And o usuario "Marcos" já reservou a vaga "C1"
    When o usuario "Marcos" solicita a sugestao de vaga baseada no historico
    Then a vaga "C1" é sugerida para reserva

  Scenario: Sugerir vaga para usuario que não possui reservas anteriores
    Given o sistema tem o usuario "Robson" armazenado com preferencia pelo setor "CIn" e tipo de vaga "Normal"
    And o usuario "Robson" está logado no sistema
    And existe a vaga "A1" no setor "CIn" do tipo "Normal" disponivel
    And existe a vaga "A2" no setor "CCEN" do tipo "Normal" disponivel
    And o usuario "Robson" não possui reservas anteriores
    When o usuario "Robson" solicita a sugestao de vaga baseada no historico
    Then a vaga "A1" é sugerida para reserva
@ignore
  Scenario: Sugerir vaga para usuario que possui reservas anteriores gui
    Given estou logado no sistema como "mrs", com preferencia pelo setor "CIn" e tipo de vaga "Normal"
    And eu crio a vaga "B1" do setor "CIn" do tipo "Normal"
    And eu crio a vaga "B2" do setor "CCEN" do tipo "Normal"
    And eu estou na página principal
    When eu seleciono a opção de sugestão de vaga baseadas no historico
    Then eu vejo uma mensagem informando a vaga "B1" do tipo "Normal" no setor "CIn"
