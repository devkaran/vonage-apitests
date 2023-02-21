package com.vonage.api.conversations.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationListResponse {

    @JsonProperty("count")
    private String count;

    @JsonProperty("page_size")
    private int pageSize;

    @JsonProperty("record_index")
    private int recordIndex;

    @JsonProperty("_links")
    private Links links;

    @JsonProperty("_embedded")
    private Embedded embedded;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Links {
        @JsonProperty("self")
        private Self self;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Self {
            @JsonProperty("href")
            private String href;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Embedded {
        @JsonProperty("conversations")
        private List<Conversation> conversations;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        public static class Conversation {
            @JsonProperty("uuid")
            private String uuid;

            @JsonProperty("name")
            private String name;

            @JsonProperty("_links")
            private Links links;
        }
    }
}
