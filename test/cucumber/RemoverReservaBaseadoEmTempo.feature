Feature: Remover Reserva Baseado Em Tempo
As a usuario do sistema
I want to desocupar automaticamente uma vaga apos um certo tempo
So that eu possa evitar problemas de esquecimento de retirar uma reserva

Scenario: Remover vaga baseado em tempo
Given o sistema tem o usuário "Biel" armazenado
And estou logado no sistema como "Biel"
And existe a vaga "100" no setor "CIn" do tipo "Normal"
And a vaga "100" do setor "CIn" já está reservada para "Biel"
When passaram mais do que 10 segundos da vaga "100" do setor "CIn" reservada para "Biel"
Then a vaga "100" do setor "CIn" é desocupada

Scenario: Remover todas as vagas baseada em tempo
Given o sistema tem o usuário "Jogador" armazenado
And o sistema tem o usuário "Silva" armazenado
And estou logado no sistema como "Jogador"
And existe a vaga "400" no setor "CIn" do tipo "Normal"
And a vaga "400" do setor "CIn" já está reservada para "Jogador"
And estou logado no sistema como "Silva"
And existe a vaga "500" no setor "CCEN" do tipo "Normal"
And a vaga "500" do setor "CCEN" já está reservada para "Silva"
When eu tento remover todas as reservas que esgotoram o limite de reserva de 10 segundos
Then a vaga "400" do setor "CIn" é desocupada
And a vaga "500" do setor "CCEN" é desocupada