package com.vonage.api.conversations.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RecordingRequest {
    private String action;
    @JsonProperty("event_url")
    private List<String> eventUrl;
    @JsonProperty("event_method")
    private String eventMethod;

    private String split;

    private String format;
}
