package com.vonage.api.users;

import com.vonage.api.users.request.CreateUserRequest;
import com.vonage.api.users.request.UpdateUserRequest;
import lombok.experimental.UtilityClass;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.testng.collections.Lists;


@UtilityClass
public class UsersRequestBuilder {

    public CreateUserRequest buildCreateUserRequest(){
        String randStr = RandomStringUtils.randomAlphabetic(5);
        return CreateUserRequest.builder()
                .name("my_user_name "+randStr)
                .displayName("My User Name "+randStr)
                .imageUrl("https://example.com/image.png")
                .build();
    }

    public UpdateUserRequest buildUpdateUserRequest(String legId){
        return UpdateUserRequest.builder()
                .name("my_user_name")
                .displayName("My User Name")
                .imageUrl("https://example.com/image.png")
                .channels(buildChannels(legId))
                .build();
    }

    public UpdateUserRequest.Channels buildChannels(String legId){
        return UpdateUserRequest.Channels.builder()
                .type("phone")
                .legId(legId)
                .from(UpdateUserRequest.Channels.From.builder().type("app").user("jamie").build())
                .to(UpdateUserRequest.Channels.To.builder().type("app").user("jamie").build())
                .legIds(Lists.newArrayList(UpdateUserRequest.Channels.LegIds.builder().legId(legId).build()))
                .build();
    }
}
