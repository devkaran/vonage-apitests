package com.vonage.api.events;

import com.vonage.api.events.request.CreateEventRequest;
import lombok.experimental.UtilityClass;

@UtilityClass
public class EventsRequestBuilder {

    public CreateEventRequest buildCreateEventRequestBuilder(String toMemberId, String fromMemberId){
        return CreateEventRequest.builder()
                .type("text")
                .to(toMemberId)
                .from(fromMemberId)
                .body(CreateEventRequest.Body.builder().text("My Text").build())
                .build();
    }
}
