package com.vonage.api.members;

import com.fasterxml.jackson.core.type.TypeReference;
import com.vonage.api.env_configuration.ApisList;
import com.vonage.api.env_configuration.Services;
import com.vonage.api.members.request.CreateMemberRequest;
import com.vonage.api.members.response.CreateMemberResponse;
import com.vonage.api.members.response.ListMembersResponse;
import com.vonage.api.members.response.MemberResponse;
import com.vonage.api.object_mapper_defaults.MapperUtils;
import com.vonage.api.restassured_utils.RestAssuredUtils;
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
public class MembersHttpClient {
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

    public CreateMemberResponse createMember(CreateMemberRequest memberRequest){
        response = requestSpecification
                .headers(getHeaders())
                .body(MapperUtils.serialize(memberRequest))
                .post(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.MEMBERS), getPathParameters()));
        return MapperUtils.deserialize(response.asString(), CreateMemberResponse.class);
    }

    public MemberResponse getMember(String conversationId, String memberId){
        response = requestSpecification
                .headers(getHeaders())
//                .get(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.GET_MEMBER), conversationId, memberId));
                .get(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.MEMBERS), getPathParameters()));
        return MapperUtils.deserialize(response.asString(), MemberResponse.class);
    }

    public List<ListMembersResponse> listMembers(String conversationId){
        response = requestSpecification
                .headers(getHeaders())
                .get(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.MEMBERS), getPathParameters()));
        return MapperUtils.deserialize(response.asString(), new TypeReference<>() {});
    }

    public MemberResponse updateMember(String conversationId, String memberId, CreateMemberRequest memberRequest){
        response = requestSpecification
                .headers(getHeaders())
                .body(MapperUtils.serialize(memberRequest))
//                .put(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.UPDATE_MEMBER), conversationId, memberId));
                .put(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.MEMBERS), getPathParameters()));
        return MapperUtils.deserialize(response.asString(), MemberResponse.class);
    }

    public void deleteMember(String conversationId, String memberId){
        response = requestSpecification
                .headers(getHeaders())
//                .delete(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.DELETE_MEMBER), conversationId, memberId));
                .delete(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.MEMBERS), getPathParameters()));
    }
}

