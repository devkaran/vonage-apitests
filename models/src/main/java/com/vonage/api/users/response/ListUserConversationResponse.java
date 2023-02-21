package com.vonage.api.users.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListUserConversationResponse {
    private Integer count;
    private List<ConversationData> conversations;

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConversationData {
        private String id;
        private String name;
        @JsonProperty("image_url")
        private String imageUrl;
        @JsonProperty("display_name")
        private String displayName;
        private String state;
        @JsonProperty("member_id")
        private String memberId;
        @JsonProperty("sequence_number")
        private String sequenceNumber;
        private String href;
        private Timestamp timestamp;
    }

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Timestamp {
        private String created;
    }
}
