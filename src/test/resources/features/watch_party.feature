#Criar uma sessão de Watch Party
Funcionalidade: Criar uma sessão de Watch Party

  Cenário: Usuário cria uma sessão de Watch Party com sucesso
    Dado que o usuário está na página de detalhes do filme "Click"
    Quando o usuário clica no botão "Criar Watch Party"
    E o usuário preenche o campo "nome da sessão" com "Filme com amigos"
    E o usuário define a data e horário da sessão para "25/12/2024 20:00"
    E o usuário seleciona "Amigos" na lista de participantes
    E o usuário clica no botão "Criar"
    Então o sistema deve exibir a mensagem "Watch Party criada com sucesso!"
    E a sessão deve estar listada na página de eventos do usuário
    E a sessão deve exibir o nome "Filme com amigos"
    E a sessão deve exibir a data e hora "25/12/2024 20:00"
    E o número de participantes permitidos deve ser exibido



#------------------------------------------------------------------------------------------------------------------------
#Usuário cancela a criação da Watch Party
Funcionalidade: Criar uma sessão de Watch Party

  Cenário: Usuário cancela a criação da Watch Party
    Dado que o usuário está na página de detalhes do filme "Click"
    Quando o usuário clica no botão "Criar Watch Party"
    E o usuário preenche o campo "nome da sessão" com "Filme com amigos"
    E o usuário define a data e horário da sessão para "25/12/2024 20:00"
    Quando o usuário clica no botão "Cancelar"
    Então o sistema deve exibir a página de detalhes do filme "Click"
    E a Watch Party não deve ser criada



#------------------------------------------------------------------------------------------------------------------------
    #Verificar os detalhes da Watch Party criada
    Funcionalidade: Verificar os detalhes da Watch Party criada

  Cenário: Usuário verifica os detalhes da Watch Party na página de eventos
    Dado que o usuário criou uma Watch Party com sucesso
    E a sessão "Filme com amigos" está listada na página de eventos
    Quando o usuário clica na sessão "Filme com amigos"
    Então o sistema deve exibir os detalhes da sessão
    E os detalhes devem incluir:
      | Nome da Sessão   | Filme com amigos   |
      | Filme            | Click        |
      | Data e Horário   | 25/12/2024 20:00   |
      | Participantes    | Amigos             |
      | Status           | Pendente           |
    E o usuário deve ter a opção de editar ou cancelar a sessão

