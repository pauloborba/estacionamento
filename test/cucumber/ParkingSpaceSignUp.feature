Feature: Cadastro no sistema
  As a system user
  I want to sign up
  So that I can book a Parking Space

Scenario: Cadastrar um usuário
  Given o sistema não tem o usuário "jprm" armazenado
  When o usuário tenta se cadastrar com usuário "jprm" com preferencia no setor "CIn"
  Then o sistema armazena o usúario "jprm"

Scenario: Cadastrar um usuário que já existe no sistema
  Given o sistema tem o usuário "jprm" armazenado
  When o usuário tenta se cadastrar com usuário "jprm" com preferencia no setor "CIn"
  Then o sistema não armazena o usúario "jprm" de preferencia no setor "CIn"

Scenario: Cadastrar um usuário web
  Given eu estou na página de cadastro
  When eu tento me cadastrar com usuário "jprm" e preferencia no setor "CIn"
  Then eu sou redirecionado para o sistema

Scenario: Cadastrar um usuário que já existe no sistema web
  Given Eu tenho um cadastro no sistema com o usuário "master"
  And eu estou na página de cadastro
  When eu tento me cadastrar com usuário "jprm" e preferencia no setor "CIn"
  Then eu vejo uma mensagem de erro