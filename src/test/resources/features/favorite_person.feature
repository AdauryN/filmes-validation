#Favoritar um ator ou diretor 
Funcionalidade: Favoritar um ator ou diretor

  Cenário: Usuário favorita um ator ou diretor com sucesso
    Dado que o usuário está na página de detalhes de um ator ou diretor chamado "Adam Sandler"
    Quando o usuário clica no botão "Favoritar"
    Então o sistema deve exibir a mensagem "Christopher Nolan foi adicionado aos seus favoritos!"
    E o nome "Christopher Nolan" deve aparecer na lista de pessoas favoritas do usuário



#------------------------------------------------------------------------------------------------------------------------
#Notificar novos filmes dos atores ou diretores favoritos do usuário
Funcionalidade: Notificação de novas obras de atores ou diretores favoritos

  Cenário: Usuário recebe notificação quando uma nova obra de um ator ou diretor favorito é adicionada
    Dado que o usuário tem "Christopher Nolan" favoritado
    E uma nova obra de "Christopher Nolan" é adicionada ao catálogo
    Quando o sistema processa a nova obra
    Então o usuário deve receber uma notificação com a mensagem "Uma nova obra de Christopher Nolan foi adicionada: Oppenheimer"
    E a nova obra deve aparecer na lista de notificações do usuário




