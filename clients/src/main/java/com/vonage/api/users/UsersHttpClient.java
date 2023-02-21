package com.vonage.api.users;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vonage.api.env_configuration.ApisList;
import com.vonage.api.env_configuration.Services;
import com.vonage.api.object_mapper_defaults.MapperUtils;
import com.vonage.api.restassured_utils.RestAssuredUtils;
import com.vonage.api.users.request.CreateUserRequest;
import com.vonage.api.users.request.UpdateUserRequest;
import com.vonage.api.users.response.UserResponse;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.Map;

@Log4j2
@Builder(toBuilder = true)
public class UsersHttpClient {
    @Getter
    private Map<String,String> headers;
    @NonNull
    private RequestSpecification requestSpecification;
    @Getter
    private Response response;
    @NonNull
    @Getter
    private Services vonageConfig;
    @Getter
    private String[] pathParameters;
    @Getter
    private Map<String,String> queryParameters;

    public UserResponse createUser(CreateUserRequest createUserRequest){
        response = requestSpecification
                .headers(getHeaders())
                .body(MapperUtils.serialize(createUserRequest))
                .post(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.USERS)));
        return MapperUtils.deserialize(response.asString(), UserResponse.class);
    }

    public UserResponse getUser() {
        response = requestSpecification
                .headers(getHeaders())
                .get(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.USERS), getPathParameters()));
        return MapperUtils.deserialize(response.asString(), UserResponse.class);
    }

    public List<UserResponse> listUsers() {
        response = requestSpecification
                .headers(getHeaders())
                .get(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.USERS)));
        return MapperUtils.deserialize(response.asString(), new TypeReference<>() {});
    }

    public UserResponse updateUser(String userId, UpdateUserRequest updateUserRequest){
        response = requestSpecification
                .headers(getHeaders())
                .body(MapperUtils.serialize(updateUserRequest))
                .put(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.USERS), getPathParameters()));
        return MapperUtils.deserialize(response.asString(), UserResponse.class);
    }

    public void deleteUser() {
        response = requestSpecification
                .headers(getHeaders())
                .delete(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.USERS), getPathParameters()));
    }
}
