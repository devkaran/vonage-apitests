package com.vonage.api.members.response;

import java.util.Map;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import com.vonage.api.conversations.response.ConversationResponse;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateMemberResponse {
    @JsonProperty("id")
    private String id;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("state")
    private String state;

    @JsonProperty("timestamp")
    private ConversationResponse.MemberTimestamp timestamp;

    @JsonProperty("channel")
    private Channel channel;

    @JsonProperty("href")
    private String href;

    @JsonProperty("initiator")
    private Initiator initiator;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Channel {
        @JsonProperty("type")
        private String type;

        @JsonProperty("leg_id")
        private String legId;

        @JsonProperty("from")
        private User from;

        @JsonProperty("to")
        private User to;

        @JsonProperty("leg_ids")
        private List<Map<String, String>> legIds;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class User {
        @JsonProperty("type")
        private String type;

        @JsonProperty("user")
        private String user;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Initiator {
        @JsonProperty("joined")
        private Joined joined;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Joined {
        @JsonProperty("isSystem")
        private boolean isSystem;

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("member_id")
        private String memberId;
    }
}
