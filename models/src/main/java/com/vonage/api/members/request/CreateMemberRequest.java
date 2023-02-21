package com.vonage.api.members.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.vonage.api.users.request.UpdateUserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateMemberRequest {
    @JsonProperty("action")
    private String action;

    @JsonProperty("state")
    private String state;

    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("member_id")
    private String memberId;

    @JsonProperty("channel")
    private Channel channel;

    @JsonProperty("media")
    private Media media;

    @JsonProperty("knocking_id")
    private String knockingId;

    @JsonProperty("member_id_inviting")
    private String memberIdInviting;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class Channel {
        @JsonProperty("type")
        private String type;

        @JsonProperty("leg_id")
        private String legId;

        @JsonProperty("from")
        private From from;

        @JsonProperty("to")
        private To to;

        @JsonProperty("leg_ids")
        private List<UpdateUserRequest.Channels.LegIds> legIds;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder(toBuilder = true)
        public static class From {
            @JsonProperty("type")
            private String type;

            @JsonProperty("user")
            private String user;
        }

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder(toBuilder = true)
        public static class To {
            @JsonProperty("type")
            private String type;

            @JsonProperty("user")
            private String user;
        }
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder(toBuilder = true)
    public static class Media {
        @JsonProperty("audio_settings")
        private AudioSettings audioSettings;

        @Data
        @NoArgsConstructor
        @AllArgsConstructor
        @Builder(toBuilder = true)
        public static class AudioSettings {
            @JsonProperty("enabled")
            private Boolean enabled;

            @JsonProperty("earmuffed")
            private Boolean earmuffed;

            @JsonProperty("muted")
            private Boolean muted;
        }
    }
}

