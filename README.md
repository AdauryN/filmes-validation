# CineMeet

## Descrição

**CineMeet** é um site de filmes que tem o intuito de levar o cinema para fora da sala de cinema! Com ele, os usuários podem:

- Organizar eventos de **Watch Parties**
- Avaliar os eventos organizados
- Determinar o que cada um dos participantes vai levar
- Criar amizades com outros participantes dos eventos

O objetivo do projeto é fornecer uma ferramenta divertida e fácil de usar para os amantes de cinema, permitindo que compartilhem suas opiniões e organizem eventos para assistir seus filmes favoritos com amigos.

## Design Patterns Utilizados

- **Padrão Repository**  
  Encapsula a lógica de acesso aos dados no banco de dados. Exemplos incluem:  
  - `FriendRepository` para gerenciar amizades.  
  - `EventRepository` para lidar com dados relacionados a eventos.

- **Padrão Strategy**  
  Define uma família de algoritmos e encapsula cada um, permitindo sua intercambialidade:  
  - Serviços como `FriendService` e `EventService` usam interfaces para flexibilidade e múltiplas implementações.

- **Padrão Singleton**  
  Garante que uma classe tenha apenas uma instância e fornece um ponto de acesso global:  
  - Usado, por exemplo, na classe `FriendServiceImpl` que está anotada com @Service. Isso faz com que o Spring crie um único bean dessa classe, garantindo que a mesma instância seja usada em toda a aplicação.

- **Padrão Factory**  
  Simplifica a criação de objetos e promove a reutilização:  
  - Utilizado em DTOs como `UserDTO` e `EventDTO` para converter entidades em objetos transferíveis.

## Como Funciona

1. **Watchparties**: Os usuários podem organizar eventos presenciais para assistir filmes com outros usuários da plataforma.
2. **Resenhas e Avaliações**: Usuários logados podem postar resenhas e avaliar eventos, que são exibidos na página principal.
3. **Amigos**: Usuários podem criar e aceitar pedidos de amizade, além de fazer eventos exclusivos para aqueles que estão adicionados.
4. **Comida!**: Quando um usuário realiza uma solicitação de participação em um evento, ele pode indicar se vai levar alguma comida.

## Tecnologias Utilizadas

- **Frontend**: Angular
- **Backend**: SpringBoot
- **BDD**: Cucumber Gherkin
- **Outros**: Jira

## Instalação e como rodar:

```brew install mysql```

```brew install node```

Iniciar o mySQL:

```brew services start mysql```

Rodar o CineMeetServer após a iniciação do mysql.

Executar o frontend, a pasta CineMeet:

```ng serve```

Após isso, só abrir o localhost e utilizar o site.

## Organização

[Jira](https://cesar-team-je0lvctt.atlassian.net/jira/software/projects/SCRUM/boards/1?selectedIssue=SCRUM-12&atlOrigin=eyJpIjoiN2EwMTc1ZTJmNGVhNDViMDllOWU1MzAzYTNkYTQ1NzgiLCJwIjoiaiJ9)

## LOFI Prototype

[Figma](https://www.figma.com/design/n3PJ0gcBey07Vh1IxvJ0Yc/REQUISITOS-%26-VALIDATION?node-id=0-1&t=2oQCk3nFQhPiw02i-1)

## Equipe

A equipe da CESAR School que tornou isso possível e os parceiros de pair programming:

- Adaury Oliveira  
- Demetrius Souza  
- Israel Erlich  
- João Pedro Araújo  
- João Pedro Batista  
- Lucas Lucena  
- Mathews Ivo  
- Rafael Menezes  
- Théo Moura  
- Vinícius Gonçalves

## Licença

Este projeto está licenciado sob a **Licença MIT**. Consulte o arquivo [LICENSE](./LICENSE) para mais detalhes.
