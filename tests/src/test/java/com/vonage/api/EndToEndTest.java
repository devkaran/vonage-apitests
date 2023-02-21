package com.vonage.api;

import com.vonage.api.conversations.response.ConversationListResponse;
import com.vonage.api.conversations.response.ConversationResponse;
import com.vonage.api.conversations.response.CreateConversationResponse;
import com.vonage.api.members.response.CreateMemberResponse;
import com.vonage.api.users.UsersRequestBuilder;
import com.vonage.api.users.request.CreateUserRequest;
import com.vonage.api.users.response.UserResponse;
import lombok.extern.log4j.Log4j2;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public class EndToEndTest {

    @Test(description = "Create conversation, create two users, join users to conversation")
    public void testCRUDInConversationAndUsers(){
        // create a new conversation
        CreateConversationResponse conversation = TestUtils.createConversation();

        // fetch all conversations
        ConversationListResponse allConversation = TestUtils.listAllConversation();

        // filter the conversation created earlier from the list of all conversations
        ConversationListResponse.Embedded.Conversation filteredConversation = allConversation.getEmbedded().getConversations().stream()
                .filter(conversation1 -> conversation.getId().equals(conversation1.getUuid()))
                .findFirst().get();

        // check if filtered conversation is not null
        Assert.assertNotNull(filteredConversation);

        // check if filtered conversation has same id as the conversation created earlier
        Assert.assertEquals(filteredConversation.getUuid() , conversation.getId());

        // check if filtered conversation has same href as the conversation created earlier
        Assert.assertEquals(filteredConversation.getLinks().getSelf().getHref() , conversation.getHref());

        // fetch the conversation by its id
        ConversationResponse conversationResponse = TestUtils.getAConversation(conversation.getId());

        // check if the fetched conversation has same id as the conversation created earlier
        Assert.assertEquals(conversationResponse.getUuid() , conversation.getId());

        // check if the fetched conversation has same href as the conversation created earlier
        Assert.assertEquals(conversationResponse.getLinks().getSelf().getHref() , conversation.getHref());

        // create a new user one
        CreateUserRequest createUserRequestOne = UsersRequestBuilder.buildCreateUserRequest();
        UserResponse userOne = TestUtils.createUser(createUserRequestOne);

        // create a new user two
        CreateUserRequest createUserRequestTwo = UsersRequestBuilder.buildCreateUserRequest();
        UserResponse userTwo = TestUtils.createUser(createUserRequestTwo);

        // fetch all users
        List<UserResponse> userResponses = TestUtils.listAllUsers();

        // filter the user one created earlier from the list of all users
        UserResponse userResponse1 = userResponses.stream().filter(userResponse -> userOne.getId().equals(userResponse.getId())).findAny().get();

        // check if user one name and href are same as created user one
        Assert.assertEquals(userResponse1.getName(), createUserRequestOne.getName());
        Assert.assertEquals(userResponse1.getHref(), userOne.getHref());

        // filter the user two created earlier from the list of all users
        UserResponse userResponse2 = userResponses.stream().filter(userResponse -> userTwo.getId().equals(userResponse.getId())).findAny().get();

        // check if user two name and href are same as created user two
        Assert.assertEquals(userResponse2.getName(), createUserRequestTwo.getName());
        Assert.assertEquals(userResponse2.getHref(), userTwo.getHref());

        // fetch the user one by its id
        UserResponse userOneGetResponse = TestUtils.getAUser(userOne.getId());

        // check if fetched user one name and href are same as created user one
        Assert.assertEquals(userOneGetResponse.getName(), createUserRequestOne.getName());
        Assert.assertEquals(userOneGetResponse.getHref(), userOne.getHref());

        // delete user one
        TestUtils.deleteAUser(userOne.getId());

        // fetch the user one by its id and check if it returns null, indicating user has been deleted
        userOneGetResponse = TestUtils.getAUser(userOne.getId());
        Assert.assertNull(userOneGetResponse.getId());

        // delete user two
        TestUtils.deleteAUser(userTwo.getId());

        // fetch the conversation by its id and check if it returns null, indicating conversation has been deleted
        TestUtils.deleteAConversation(conversation.getId());
        conversationResponse = TestUtils.getAConversation(conversation.getId());
        Assert.assertNull(conversationResponse.getUuid());

    }

}
