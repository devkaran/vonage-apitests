package com.vonage.api;

import com.vonage.api.conversations.ConversationsHttpClient;
import com.vonage.api.conversations.ConversationsRequestBuilder;
import com.vonage.api.conversations.request.ConversationRequest;
import com.vonage.api.conversations.response.ConversationListResponse;
import com.vonage.api.conversations.response.ConversationResponse;
import com.vonage.api.conversations.response.CreateConversationResponse;
import com.vonage.api.env_configuration.ServiceList;
import com.vonage.api.env_configuration.Services;
import com.vonage.api.members.MembersHttpClient;
import com.vonage.api.members.MembersRequestBuilder;
import com.vonage.api.members.request.CreateMemberRequest;
import com.vonage.api.members.response.CreateMemberResponse;
import com.vonage.api.restassured_utils.RestAssuredUtils;
import com.vonage.api.users.UsersHttpClient;
import com.vonage.api.users.UsersRequestBuilder;
import com.vonage.api.users.request.CreateUserRequest;
import com.vonage.api.users.response.UserResponse;
import lombok.Getter;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.testng.Assert;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@UtilityClass
public class TestUtils {
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @NonNull String JWT_TOKEN = App.readProperty("jwt");
    @NonNull Map<ServiceList, Services> SERVICES_CONFIG = App.getConfiguration().getServices();


    @Getter
    ConversationsHttpClient conversationsBaseHttpClient = ConversationsHttpClient.builder()
            .headers(Map.of("Authorization", "Bearer "+JWT_TOKEN, "Content-Type", "application/json"))
            .vonageConfig(SERVICES_CONFIG.get(ServiceList.VONAGE))
            .requestSpecification(RestAssuredUtils.getDefaultRequestClient())
            .build();
    @Getter
    UsersHttpClient usersBaseHttpClient = UsersHttpClient.builder()
            .headers(Map.of("Authorization", "Bearer "+JWT_TOKEN, "Content-Type", "application/json"))
            .vonageConfig(SERVICES_CONFIG.get(ServiceList.VONAGE))
            .requestSpecification(RestAssuredUtils.getDefaultRequestClient())
            .build();

    @Getter
    MembersHttpClient membersBaseHttpClient = MembersHttpClient.builder()
            .headers(Map.of("Authorization", "Bearer "+JWT_TOKEN, "Content-Type", "application/json"))
            .vonageConfig(SERVICES_CONFIG.get(ServiceList.VONAGE))
            .requestSpecification(RestAssuredUtils.getDefaultRequestClient())
            .build();

    public CreateConversationResponse createConversation(){
        ConversationRequest conversationRequest = ConversationsRequestBuilder.bulidConversationRequest();
        CreateConversationResponse conversation = getConversationsBaseHttpClient().createConversation(conversationRequest);
        Assert.assertNotNull(conversation);
        return conversation;
    }

    public ConversationListResponse listAllConversation(){
        String startDate = LocalDateTime.now().minusMonths(1).format(formatter);
        String endDate = LocalDateTime.now().plusDays(1).format(formatter); // ha
        Map<String, String> queryParams = Map.of("date_start", startDate,
                "date_end", endDate,
                "page_size", "100",
                "order", "DESC");
        ConversationListResponse conversation = getConversationsBaseHttpClient().toBuilder()
                .requestSpecification(RestAssuredUtils.getDefaultRequestClient())
                .queryParameters(queryParams)
                .build()
                .listAllConversations();
        Assert.assertNotNull(conversation);
        return conversation;
    }

    public ConversationResponse getAConversation(String conversationId){
        ConversationResponse conversation = getConversationsBaseHttpClient().toBuilder()
                .requestSpecification(RestAssuredUtils.getDefaultRequestClient())
                .pathParameters(new String[]{conversationId})
                .build()
                .getAConversation();
        Assert.assertNotNull(conversation);
        return conversation;
    }

    public void deleteAConversation(String conversationId){
        Map response= getConversationsBaseHttpClient().toBuilder()
                .requestSpecification(RestAssuredUtils.getDefaultRequestClient())
                .pathParameters(new String[]{conversationId})
                .build()
                .deleteAConversation();
        Assert.assertTrue(response.isEmpty());
    }

    public UserResponse createUser(CreateUserRequest createUserRequest ){
        UserResponse userResponse = getUsersBaseHttpClient().toBuilder()
                .requestSpecification(RestAssuredUtils.getDefaultRequestClient())
                .build()
                .createUser(createUserRequest);
        Assert.assertNotNull(userResponse);
        return userResponse;
    }

    public UserResponse getAUser(String userId){
        UserResponse userResponse = getUsersBaseHttpClient().toBuilder()
                .requestSpecification(RestAssuredUtils.getDefaultRequestClient())
                .pathParameters(new String[]{userId})
                .build()
                .getUser();
        Assert.assertNotNull(userResponse);
        return userResponse;
    }

    public List<UserResponse> listAllUsers(){
        List<UserResponse> listUsers = getUsersBaseHttpClient().toBuilder()
                .requestSpecification(RestAssuredUtils.getDefaultRequestClient())
                .build()
                .listUsers();
        Assert.assertNotNull(listUsers);
        return listUsers;
    }

    public void deleteAUser(String userId){
        getUsersBaseHttpClient().toBuilder()
                .requestSpecification(RestAssuredUtils.getDefaultRequestClient())
                .pathParameters(new String[]{userId})
                .build()
                .deleteUser();
    }

    public CreateMemberResponse addMembersToConversation(String conversationId, String userId){
        CreateMemberRequest createMemberRequest = MembersRequestBuilder.buildCreateMemberRequestBuilder(userId, null, null);
        CreateMemberResponse createMemberResponse = getMembersBaseHttpClient().toBuilder()
                .requestSpecification(RestAssuredUtils.getDefaultRequestClient())
                .pathParameters(new String[]{conversationId})
                .build()
                .createMember(createMemberRequest);
        Assert.assertNotNull(createMemberResponse);
        return createMemberResponse;
    }






}
