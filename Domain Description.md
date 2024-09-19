# Descrição do Domínio


## Entidades principais 

1 -Usuário: 
Um usuário representa uma pessoa que interage com o sistema. Cada usuário tem uma conta, onde pode realizar login e gerenciar suas preferências.
O usuário pode marcar filmes como assistidos e escrever críticas para os filmes que viu.
O usuário pode visualizar filmes no sistema e ver críticas de outros usuários.
O usuário pode indicar quando e onde vai assistir um filme, podendo convidar outros usuários para participar.
O usuário pode criar listas para gerenciar e separar filmes por diferentes categorias.
O usuário pode visualizar as contas de outros usuários vendo as informações públicas da conta
O usuário pode Favoritar um ator ou diretor e recebe uma notificação sempre que ele estiver em um novo filme



2 -Filme: 
Um filme é a entidade central em torno da qual o sistema se organiza. Cada filme possui um título, elenco, sinopse, e outras informações fornecidas pela API do IMDB.
Os filmes podem ser marcados como assistidos por um usuário, e o sistema deve manter uma lista de filmes assistidos por cada usuário.
Os filmes podem ser adicionados em listas personalizadas pelo usuário.
O filme é utilizado na criação de críticas, e cada crítica será associada a um filme específico.

3 -Crítica: 
Uma crítica é uma avaliação escrita por um usuário sobre um filme que ele assistiu.
Cada crítica contém uma nota (geralmente uma escala de 0 a 10), um comentário sobre o filme, e está sempre associada a um usuário e a um filme.
As críticas ficam armazenadas no sistema e podem ser consultadas por outros usuários.

4 -API do IMDB: 
A API do IMDB fornece dados sobre os filmes, como o título, diretor, elenco, classificação, e outras informações relevantes. Esses dados são utilizados pelo sistema para população do catálogo de filmes e exibição de detalhes sobre o filme aos usuários.



## Comportamentos e Regras de negócio


1 -Gerenciamento de conta: 
Cada usuário deve ser capaz de criar uma conta e fazer login para acessar suas informações pessoais e funcionalidades.
O usuário pode marcar um filme como assistido em sua conta. Esse filme será adicionado à sua lista de filmes assistidos, permitindo que ele escreva uma crítica para esse filme.
O usuário pode marcar que irá assistir um filme em um momento futuro e outros usuários podem marcar que irão assistir juntos.
O usuário pode criar listas e adicionar filmes a essas listas e deixá-la privada ou pública. 

2 -Adição e Consulta de filmes: 
O sistema utilizará a API do IMDB para buscar informações sobre novos filmes que não estão no banco de dados local.
Os usuários podem consultar a lista de filmes disponíveis e verificar os detalhes dos filmes, incluindo as críticas de outros usuários.

3 -Criação de Críticas:
Usuários podem criar uma crítica para um filme. Cada crítica será vinculada tanto ao filme quanto ao usuário que a escreveu.
Um filme só pode ser criticado por um usuário que o tenha marcado como assistido.

4 -Exibição de filmes e críticas:
O usuário pode acessar uma lista de filmes que ele assistiu e visualizar as críticas que ele escreveu.
Outros usuários podem consultar as críticas que foram publicadas sobre cada filme.
O usuário pode acessar as listas personalizadas de filmes.


## Linguagem Onipresente

Usuário: Pessoa que interage com o sistema, com uma conta pessoal.
Conta: Área privada do usuário onde ele gerencia seus dados e atividades.
Filme: Entidade que representa um filme no sistema.
Crítica: Avaliação escrita por um usuário sobre um filme.
API do IMDB: Fonte de dados externa para obter informações sobre filmes.
Listas de Filmes: Relação de filmes que o usuário armazenou em listas.

