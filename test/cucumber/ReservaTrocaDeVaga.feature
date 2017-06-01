Feature: Reserva e Troca de vagas
As a usuario do sistema
I want to reservar e trocar de vagas
So that eu possa estacionar na vaga reservada

Scenario: Troca de vaga
Given O sistema tem o usuario "Henrique" armazenado
And estou logado no sistema como "Henrique"
And existe a vaga "1" no setor "CIn" do tipo "Normal"
And existe a vaga "2" no setor "CCEN" do tipo "Normal"
And a vaga "1" do setor "CIn" já está reservada para "Henrique"
And a vaga "2" do setor "CCEN" está disponível
When o usuario "Henrique" tenta reservar a vaga "2" do setor "CCEN"
Then o sistema reserva a vaga "2" para o usuário "Henrique"
And a vaga "1" do setor "CIn" está disponível

Scenario: Fazer a reserva de uma vaga
Given O sistema tem o usuario "Gabriel" armazenado
And estou logado no sistema como "Gabriel"
And existe a vaga "3" no setor "CIn" do tipo "Normal"
And a vaga "3" do setor "CIn" está disponível
When o usuario "Gabriel" tenta reservar a vaga "3" do setor "CIn"
Then o sistema reserva a vaga "3" para o usuário "Gabriel"


Scenario: Reserva de Vaga GUI
Given eu estou logado no sistema como "gh"
And eu crio a vaga "4" do setor "CIn" do tipo "Normal"
And estou na pagina de listagem de vagas
And a vaga "4" do setor "CIn" do tipo "Normal" aparece como diponivel
When eu seleciono a opção de reservar a vaga "4" do setor "CIn" do tipo "Normal"
Then a vaga "4" do setor "CIn" do tipo "Normal" aparece como reservada

Scenario: Troca de Vaga Gui
Given eu estou logado no sistema como "ghds"
And eu crio a vaga "10" do setor "CIn" do tipo "Normal"
And eu crio a vaga "20" do setor "CCEN" do tipo "Normal"
And estou na pagina de listagem de vagas
And a vaga "10" do setor "CIn" do tipo "Normal" está reservada
And a vaga "20" do setor "CCEN" do tipo "Normal" aparece como diponivel
When eu seleciono a opção de reservar a vaga "20" do setor "CCEN" do tipo "Normal"
Then a vaga "10" do setor "CIn" do tipo "Normal" aparece como diponivel
And a vaga "20" do setor "CCEN" do tipo "Normal" aparece como reservada