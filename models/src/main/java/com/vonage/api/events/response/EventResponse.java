package com.vonage.api.events.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {
    private String id;

    private String type;

    private String from;

    private String to;

    private Body body;

    private String state;

    private LocalDateTime timestamp;

    private String href;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Body {
        private String text;
    }
}
