FilmesValidation

Descrição

CineMeet é um site de filmes que tem o intúito de levar o cinema pra fora da sala de cinema!:
	•	Organizar eventos de Watch Parties
	•	Avaliar os eventos organizados
	•	Determinar o que cada um dos participantes vai levar
	•	Criar amizades com outros participantes dos eventos

O objetivo do projeto é fornecer uma ferramenta divertida e fácil de usar para os amantes de cinema, permitindo que compartilhem suas opiniões e organizem eventos para assistir seus filmes favoritos com amigos.

Design Patterns Utilizados

**Padrão Repository**
Encapsula a lógica de acesso aos dados no banco de dados. Exemplos incluem:
	•	FriendRepository para gerenciar amizades.
	•	EventRepository para lidar com dados relacionados a eventos.
 
**Padrão Strategy:**
Define uma família de algoritmos e encapsula cada um, permitindo sua intercambialidade:
	•	Serviços como FriendService e EventService usam interfaces para flexibilidade e múltiplas implementações.
 
**Padrão Singleton**
Garante que uma classe tenha apenas uma instância e fornece um ponto de acesso global:
	•	Usado na configuração do SimpleCorsFilter para aplicar configurações de CORS de forma consistente.

**Padrão Factory**
Simplifica a criação de objetos e promove a reutilização:
	•	Utilizado em DTOs como UserDTO e EventDTO para converter entidades em objetos transferíveis.

Como Funciona

	1.	Watchparties: Os usuários podem organizar eventos presenciais para assistir filmes com outros usuários da plataforma.
	2.  Resenhas e Avaliações: Usuários logados podem postar resenhas e avaliar eventos, que são exibidos na página principal.
	3.	Amigos: Usuários podem criar e aceitar pedidos de amizade, e fazer eventos exclusivos para aqueles os quais estão adicionados.
 	4.	Comida!: Quando um usuário realizar uma request de participação de um evento, ele pode dizer se vai levar alguma comida.

Protótipo LOFI

Link para o Figma

Tecnologias Utilizadas

	•	Frontend: Angular
	•	Backend: SpringBoot
	•	BDD: Cucumber Gherkin
	•	Outros: Jira

Equipe

A equipe da CESAR School que tornou isso possível e os parceiros de pair programming:
	•	Adaury Oliveira
	•	Demetrius Souza
	•	Israel Erlich
	•	João Pedro Araújo
	•	João Pedro Batista
	•	Lucas Lucena
	•	Mathews Ivo
	•	Rafael Menezes
	•	Théo Moura
	•	Vinícius Gonçalves

Licença

Este projeto está licenciado sob a Licença MIT. Consulte o arquivo LICENSE para mais detalhes.
