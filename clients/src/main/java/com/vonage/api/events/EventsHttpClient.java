package com.vonage.api.events;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vonage.api.env_configuration.ApisList;
import com.vonage.api.env_configuration.Services;
import com.vonage.api.events.request.CreateEventRequest;
import com.vonage.api.events.response.CreateEventResponse;
import com.vonage.api.events.response.EventResponse;
import com.vonage.api.object_mapper_defaults.MapperUtils;
import com.vonage.api.restassured_utils.RestAssuredUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

@Log4j2
@Builder(toBuilder = true)
public class EventsHttpClient {
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

    public List<EventResponse> listEvents(String conversationId) {
        response = requestSpecification
                .headers(getHeaders())
                .queryParams(getQueryParameters()) //"conversation_id", conversationId
                .get(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.EVENTS)));
        return MapperUtils.deserialize(response.asString(), new TypeReference<>() {});
    }

    public EventResponse getEvent(String eventId) {
        response = requestSpecification
                .headers(getHeaders())
                .get(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.EVENTS), getPathParameters()));
        return MapperUtils.deserialize(response.asString(), EventResponse.class);
    }

    public CreateEventResponse createEvent(String conversationId, CreateEventRequest textEventRequest) {
        response = requestSpecification
                .headers(getHeaders())
                .body(MapperUtils.serialize(textEventRequest))
                .post(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.EVENTS), getPathParameters()));
        return MapperUtils.deserialize(response.asString(), CreateEventResponse.class);
    }

    public void deleteEvent(String conversationId, String eventid){
        response = requestSpecification
                .headers(getHeaders())
//                .delete(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.DELETE_MEMBER), conversationId, memberId));
                .delete(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.EVENTS), getPathParameters()));
    }
}

