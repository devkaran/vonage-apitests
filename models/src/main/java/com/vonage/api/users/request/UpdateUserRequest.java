package com.vonage.api.users.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserRequest {
    @JsonProperty("name")
    private String name;

    @JsonProperty("display_name")
    private String displayName;

    @JsonProperty("image_url")
    private String imageUrl;

    @JsonProperty("channels")
    private Channels channels;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Channels {
        @JsonProperty("type")
        private String type;

        @JsonProperty("leg_id")
        private String legId;

        @JsonProperty("from")
        private From from;

        @JsonProperty("to")
        private To to;

        @JsonProperty("leg_ids")
        private List<LegIds> legIds;

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class From {
            @JsonProperty("type")
            private String type;

            @JsonProperty("user")
            private String user;
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class To {
            @JsonProperty("type")
            private String type;

            @JsonProperty("user")
            private String user;
        }

        @Data
        @Builder
        @NoArgsConstructor
        @AllArgsConstructor
        public static class LegIds {
            @JsonProperty("leg_id")
            private String legId;
        }
    }
}
