package com.vonage.api.users.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class CreateUserRequest {
    private String name;
    @JsonProperty("display_name")
    private String displayName;
    @JsonProperty("image_url")
    private String imageUrl;
}

