# vonage-apitests

### Test cases for https://developer.nexmo.com/api/conversation version 0.1

1. Conversation API - Create a conversation 
   * Verify that a new conversation can be created with valid input parameters such as name, display name, and description.  
   * Verify that the API returns the correct response code (201) and a unique conversation ID. 
   * Verify that the newly created conversation is listed in the dashboard and can be accessed.
      <br><br>
2. Conversation API - List conversations 
   * Verify that all the conversations created under an account are listed.
   * Verify that the API returns the correct response code (200) and a list of conversations.
   * Verify that the list contains the expected conversation details such as conversation ID, name, and display name.
         <br><br>
3. Conversation API - Retrieve a conversation 
   * Verify that an existing conversation can be retrieved using its ID. 
   * Verify that the API returns the correct response code (200) and the details of the conversation. 
   * Verify that the retrieved conversation matches the expected details such as conversation name, display name, and description.
     <br><br>
4. Conversation API - Update a conversation 
   * Verify that an existing conversation can be updated with new details such as name, display name, and description.
   * Verify that the API returns the correct response code (200) and the updated conversation details.
   * Verify that the updated conversation matches the expected details.
     <br><br>
5. Conversation API - Delete a conversation
   * Verify that an existing conversation can be deleted using its ID.
   * Verify that the API returns the correct response code (204) and no conversation is listed in the dashboard.
   * Verify that the deleted conversation cannot be retrieved using its ID.
     <br><br>
6. User API - Create a user 
   * Verify that a new user can be created with valid input parameters such as name, display name, and conversation ID.
   * Verify that the API returns the correct response code (201) and a unique user ID.
   * Verify that the newly created user is listed in the dashboard and can be accessed.
     <br><br>
7. User API - List users 
   * Verify that all the users created under a conversation are listed.
   * Verify that the API returns the correct response code (200) and a list of users.
   * Verify that the list contains the expected user details such as user ID, name, and display name.
     <br><br>
8. User API - Retrieve a user 
   * Verify that an existing user can be retrieved using its ID.
   * Verify that the API returns the correct response code (200) and the details of the user.
   * Verify that the retrieved user matches the expected details such as user name, display name, and conversation ID.
     <br><br>
9. User API - Update a user 
   * Verify that an existing user can be updated with new details such as name, display name, and conversation ID.
   * Verify that the API returns the correct response code (200) and the updated user details.
   * Verify that the updated user matches the expected details.
     <br><br>
10. User API - Delete a user 
    * Verify that an existing user can be deleted using its ID. 
    * Verify that the API returns the correct response code (204) and no user is listed in the dashboard. 
    * Verify that the deleted user cannot be retrieved using its ID.
      <br><br>