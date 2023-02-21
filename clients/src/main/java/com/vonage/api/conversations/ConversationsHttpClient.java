package com.vonage.api.conversations;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vonage.api.conversations.request.ConversationRequest;
import com.vonage.api.conversations.response.ConversationListResponse;
import com.vonage.api.conversations.response.ConversationResponse;
import com.vonage.api.conversations.response.CreateConversationResponse;
import com.vonage.api.env_configuration.ApisList;
import com.vonage.api.env_configuration.Services;
import com.vonage.api.object_mapper_defaults.MapperUtils;
import com.vonage.api.restassured_utils.RestAssuredUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
@Builder(toBuilder = true)
public class ConversationsHttpClient {
    @Getter
    private Map<String,String> headers;
    @NonNull
    private RequestSpecification requestSpecification;
    @Getter
    private Response response;
    @NonNull
    @Getter
    private Services vonageConfig;
    @Getter
    private String[] pathParameters;
    @Getter
    private Map<String,String> queryParameters;

    public CreateConversationResponse createConversation(ConversationRequest conversationRequest){
        response = requestSpecification
                .headers(getHeaders())
                .body(MapperUtils.serialize(conversationRequest))
                .post(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.CONVERSATIONS)));
        return MapperUtils.deserialize(response.asString(), CreateConversationResponse.class);
    }

    public CreateConversationResponse updateConversation(ConversationRequest conversationRequest){
        response = requestSpecification
                .headers(getHeaders())
                .body(MapperUtils.serialize(conversationRequest))
                .put(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.CONVERSATIONS), getPathParameters()));
        return MapperUtils.deserialize(response.asString(), CreateConversationResponse.class);
    }

    public ConversationResponse getAConversation(){
        response = requestSpecification
                .headers(getHeaders())
                .get(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.CONVERSATIONS), getPathParameters()));
        return MapperUtils.deserialize(response.asString(), ConversationResponse.class);
    }

    public ConversationListResponse listAllConversations(){
        response = requestSpecification
                .headers(getHeaders())
                .queryParams(getQueryParameters())
                .get(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.CONVERSATIONS)));
        return MapperUtils.deserialize(response.asString(), ConversationListResponse.class);
    }

    public Map deleteAConversation(){
        response = requestSpecification
                .headers(getHeaders())
                .delete(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.CONVERSATIONS), getPathParameters()));
        return MapperUtils.deserialize(response.asString(), Map.class);
    }
}
