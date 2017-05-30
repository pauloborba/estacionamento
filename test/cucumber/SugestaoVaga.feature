
Feature: Sugestão de Vaga
  As a usuario do sistema de vagas de estacionamento
  I want to vagas de estacionamento sugeridas baseadas em minhas preferencias e necessidades
  So that eu possa estacionar nas vagas de maior comodidade

  Scenario: Sugerir Vaga para Usuario com Necessidade Especial,
    Given o sistema tem o usuario "Irineu" armazenado com preferencia pelo setor "Cin" e tipo de vaga "Deficiente"
    And eu estou logado no sistema como "Irineu"
    And existe a vaga "NE1" no setor "Cin" do tipo "Deficiente" disponivel
    And existe a vaga "NE2" no setor "CCEN" do tipo "Deficiente" disponivel
    When o usuario "Irineu" solicita a sugestao de vaga
    Then o sistema sugere a vaga "NE1" para reserva
  @ignore
  Scenario: Sugerir Vaga para Usuario sem Necessidade Especial
    Given o sistema tem o usuario "Matheus" armazenado com preferencia pelo setor "Cin" e tipo de vaga "Normal"
    And eu estou logado no sistema como "Matheus"
    And existe a vaga "V1" no setor "Cin" do tipo "Deficiente" disponivel
    And existe a vaga "V2" no setor "CCEN" do tipo "Normal" disponivel
    When o usuario "Matheus" solicita a sugestao de vaga
    Then o sistema sugere a vaga "V2" para reserva

  @ignore
  Scenario: Sem Vagas Disponiveis no Setor de Preferencia e sem Necessidade Especial
    Given o sistema tem o usuario "Paulo" armazenado com preferencia pelo setor "Cin" e tipo de vaga "Normal"
    And eu estou logado no sistema como "Paulo"
    And existe a vaga "1" no setor "CCEN" do tipo "Normal"
    When o usuario "Paulo" solicita a sugestao de vaga
    Then o sistema informa que não possui vaga no setor de preferência
    And o sistema sugere a vaga "1" para reserva