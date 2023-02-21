package com.vonage.api.conversations.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConversationResponse {
    @JsonProperty("uuid")
    private String uuid;

    @JsonProperty("name")
    private String name;

    @JsonProperty("properties")
    private ConversationProperties properties;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("timestamp")
    private ConversationTimestamp timestamp;

    @JsonProperty("sequence_number")
    private String sequenceNumber;

    @JsonProperty("members")
    private List<Member> members;

    @JsonProperty("api_key")
    private String apiKey;

    @JsonProperty("_links")
    private ConversationLinks links;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ConversationProperties {
        @JsonProperty("video")
        private boolean video;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class ConversationTimestamp {
        @JsonProperty("created")
        private LocalDateTime created;

        @JsonProperty("updated")
        private LocalDateTime updated;

        @JsonProperty("destroyed")
        private LocalDateTime destroyed;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class Member {
        @JsonProperty("member_id")
        private String memberId;

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("name")
        private String name;

        @JsonProperty("state")
        private String state;

        @JsonProperty("timestamp")
        private MemberTimestamp timestamp;

        @JsonProperty("initiator")
        private MemberInitiator initiator;

        @JsonProperty("channel")
        private MemberChannel channel;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class MemberTimestamp {
        @JsonProperty("invited")
        private LocalDateTime invited;

        @JsonProperty("joined")
        private LocalDateTime joined;

        @JsonProperty("left")
        private LocalDateTime left;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class MemberInitiator {
        @JsonProperty("joined")
        private MemberInitiatorJoined joined;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class MemberInitiatorJoined {
        @JsonProperty("isSystem")
        private boolean isSystem;

        @JsonProperty("user_id")
        private String userId;

        @JsonProperty("member_id")
        private String memberId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class MemberChannel {
        @JsonProperty("type")
        private String type;

        @JsonProperty("leg_id")
        private String legId;

        @JsonProperty("from")
        private MemberChannelFrom from;

        @JsonProperty("to")
        private MemberChannelTo to;

        @JsonProperty("leg_ids")
        private List<MemberChannelLeg> legIds;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class MemberChannelFrom {
        @JsonProperty("type")
        private String type;

        @JsonProperty("user")
        private String user;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class MemberChannelTo {
        @JsonProperty("type")
        private String type;

        @JsonProperty("user")
        private String user;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class MemberChannelLeg {
        @JsonProperty("leg_id")
        private String legId;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConversationLinks {
        @JsonProperty("self")
        private ConversationLink self;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ConversationLink {
        @JsonProperty("href")
        private String href;
    }
}


