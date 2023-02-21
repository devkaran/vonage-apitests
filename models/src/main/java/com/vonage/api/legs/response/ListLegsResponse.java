package com.vonage.api.legs.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListLegsResponse {
    private int count;
    @JsonProperty("page_size")
    private int pageSize;
    @JsonProperty("record_index")
    private int recordIndex;
    private Links _links;
    private Embedded _embedded;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Links {
        private Self self;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Self {
            private String href;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Embedded {
        private List<Leg> legs;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Leg {
            private String uuid;
            private String type;
            @JsonProperty("conversation_uuid")
            private String conversationUuid;
            private String status;
            private Endpoint from;
            private Endpoint to;
            @JsonProperty("start_time")
            private String startTime;
            @JsonProperty("end_time")
            private String endTime;
            private Rtc rtc;
            private Links _links;

            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Endpoint {
                private String type;
            }

            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Rtc {
                @JsonProperty("session_id")
                private String sessionId;
                private String state;
            }

            @Data
            @NoArgsConstructor
            @AllArgsConstructor
            public static class Links {
                private Self self;

                @Data
                @NoArgsConstructor
                @AllArgsConstructor
                public static class Self {
                    private String href;
                }
            }
        }
    }
}

