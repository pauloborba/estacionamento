Feature: Remover Reserva Baseado Em Tempo
As a usuario do sistema
I want to desocupar automaticamente uma vaga apos um certo tempo
So that eu possa evitar problemas de esquecimento de retirar uma reserva

Scenario: Remover reserva baseado em tempo
Given o sistema tem o usuário "Biel" armazenado
And estou logado no sistema como "Biel"
And existe a vaga "100" no setor "CIn" do tipo "Normal"
And a vaga "100" do setor "CIn" já está reservada para "Biel"
When passaram mais do que 10 segundos da vaga "100" do setor "CIn" reservada para "Biel"
Then a vaga "100" do setor "CIn" é desocupada

Scenario: Remover todas as reservas baseada em tempo
Given o sistema tem o usuário "Jogador" armazenado
And o sistema tem o usuário "Silva" armazenado
And estou logado no sistema como "Jogador"
And existe a vaga "400" no setor "CIn" do tipo "Normal"
And a vaga "400" do setor "CIn" já está reservada para "Jogador"
And estou logado no sistema como "Silva"
And existe a vaga "500" no setor "CCEN" do tipo "Normal"
And a vaga "500" do setor "CCEN" já está reservada para "Silva"
And estou logado no sistema como "master"
When eu tento remover todas as reservas que esgotoram o limite de reserva de 10 segundos
Then a vaga "400" do setor "CIn" é desocupada
And a vaga "500" do setor "CCEN" é desocupada

Scenario: Remover todas as vagas baseado em tempo Gui
Given eu estou logado no sistema como "Sheldon"
And eu crio a vaga "1000" do setor "CIn" do tipo "Normal"
And estou na pagina de listagem de vagas
And a vaga "1000" do setor "CIn" do tipo "Normal" está reservada
And eu estou logado no sistema como "Cego"
And eu crio a vaga "2000" do setor "CCEN" do tipo "Normal"
And estou na pagina de listagem de vagas
And a vaga "2000" do setor "CCEN" do tipo "Normal" está reservada
And eu logo no sistema como usuario "master"
When eu seleciono a opção de remover todas as reservas que esgotaram o limite de tempo de reserva de 10 segundos
Then a vaga "1000" do setor "CIn" do tipo "Normal" aparece como diponivel
And a vaga "2000" do setor "CCEN" do tipo "Normal" aparece como diponivel

Scenario: Tentar remover todas as vagas baseado em tempo Gui sem ser o usuário Master
Given eu estou logado no sistema como "Sheldon"
And eu crio a vaga "5000" do setor "CIn" do tipo "Normal"
And estou na pagina de listagem de vagas
And a vaga "1000" do setor "CIn" do tipo "Normal" está reservada
When eu seleciono a opção de remover todas as reservas que esgotaram o limite de tempo de reserva de 10 segundos
Then eu vejo uma mensagem informando que não tenho a permissão necessária para acessar