#Criar uma sessão de Watch Party
Feature: Create a Watch Party session

  Scenario: User successfully creates a Watch Party session 
  Given the user is on the movie "Click" details page
  When the user clicks the "Create Watch Party" button
  And the user fills in the "session name" field with "Movie with friends"
  And the user sets the session date and time to "12/25/2024 8:00 PM"
  And the user selects "Friends" from the participants list
  And the user clicks the "Create" button
  Then the system should display the message "Watch Party successfully created!"
  And the session should be listed on the user's events page
  And the session should display the name "Movie with friends"
  And the session should display the date and time "12/25/2024 8:00 PM"
  And the allowed number of participants should be displayed
  
#------------------------------------------------------------------------------------------------------------------------
#Usuário cancela a criação da Watch Party
Feature: User cancels the Watch Party creation
  
  Scenario: User cancels the Watch Party creation
  Given the user is on the movie "Click" details page
  When the user clicks the "Create Watch Party" button
  And the user fills in the "session name" field with "Movie with friends"
  And the user sets the session date and time to "12/25/2024 8:00 PM"
  When the user clicks the "Cancel" button
  Then the system should display the movie "Click" details page
  And the Watch Party should not be created
  
#------------------------------------------------------------------------------------------------------------------------
    #Verificar os detalhes da Watch Party criada
    Feature: Check the details of the created Watch Party
      Scenario: User checks the details of the Watch Party on the events page
      Given the user has successfully created a Watch Party
      And the "Movie with friends" session is listed on the events page
      When the user clicks on the "Movie with friends" session
      Then the system should display the session details

      And the details should include:
        | Session Name     | Movie with friends |
        | Movie            | Click              |
        | Date and Time    | 12/25/2024 8:00 PM |
        | Participants     | Friends            |
        | Status           | Pending            |
      
      
