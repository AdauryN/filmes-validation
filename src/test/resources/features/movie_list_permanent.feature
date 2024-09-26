#Criar Lista de Filmes
Funcionalidade: Criação de Lista de Filmes

  Cenário: Usuário cria uma lista de filmes com sucesso
    Dado que o usuário está na página inicial do site
    Quando o usuário clica no botão "Criar Nova Lista"
    E o usuário preenche o campo "nome da lista" com "Meus Favoritos"
    E o usuário adiciona o filme "Inception" à lista
    E o usuário clica no botão "Salvar Lista"
    Então o sistema deve exibir a mensagem "Lista criada com sucesso!"
    E a lista "Meus Favoritos" deve aparecer na página de listas do usuário


#------------------------------------------------------------------------------------------------------------------------
#Adicionar um filme a uma lista existente 
Funcionalidade: Adicionar filmes a uma lista

  Cenário: Usuário adiciona um filme a uma lista existente
    Dado que o usuário está na página de detalhes do filme "Inception"
    E o usuário tem uma lista chamada "Meus Favoritos"
    Quando o usuário clica no botão "Adicionar à Lista"
    E o usuário seleciona "Meus Favoritos"
    Então o sistema deve exibir a mensagem "Filme adicionado à lista!"
    E o filme "Inception" deve estar na lista "Meus Favoritos"
