
Feature: Sugestão de Vaga
  As a usuario do sistema de vagas de estacionamento
  I want to vagas de estacionamento sugeridas baseadas em minhas preferencias e necessidades
  So that eu possa estacionar nas vagas de maior comodidade

  Scenario: Sugerir Vaga para Usuario com Necessidade Especial
    Given o sistema tem o usuario "Irineu" armazenado com preferencia pelo setor "CIn" e tipo de vaga "Deficiente"
    And eu estou logado no sistema como "Irineu"
    And existe a vaga "NE1" no setor "CIn" do tipo "Deficiente" disponivel
    And existe a vaga "NE2" no setor "CCEN" do tipo "Deficiente" disponivel
    When o usuario "Irineu" solicita a sugestao de vaga
    Then o sistema sugere a vaga "NE1" para reserva

  Scenario: Sugerir Vaga para Usuario sem Necessidade Especial
    Given o sistema tem o usuario "Matheus" armazenado com preferencia pelo setor "CIn" e tipo de vaga "Normal"
    And eu estou logado no sistema como "Matheus"
    And existe a vaga "V1" no setor "CIn" do tipo "Deficiente" disponivel
    And existe a vaga "V2" no setor "CIn" do tipo "Normal" disponivel
    When o usuario "Matheus" solicita a sugestao de vaga
    Then o sistema sugere a vaga "V2" para reserva
 
