package com.vonage.api.legs;

import com.vonage.api.env_configuration.ApisList;
import com.vonage.api.env_configuration.Services;
import com.vonage.api.legs.response.ListLegsResponse;
import com.vonage.api.object_mapper_defaults.MapperUtils;
import com.vonage.api.restassured_utils.RestAssuredUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import lombok.Builder;
import lombok.Getter;
import lombok.NonNull;
import lombok.extern.log4j.Log4j2;

import java.util.Map;

@Log4j2
@Builder(toBuilder = true)
public class LegsHttpClient {
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

//    public GetLegResponse getLeg(String legId) {
//        response = requestSpecification
//                .headers(getHeaders())
//                .get(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.GET_LEG), legId));
//        return MapperUtils.deserialize(response.asString(), GetLegResponse.class);
//    }

    public ListLegsResponse listLegs() {
        response = requestSpecification
                .headers(getHeaders())
                .get(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.LEGS)));
        return MapperUtils.deserialize(response.asString(), ListLegsResponse.class);
    }

//    public UpdateLegResponse updateLeg(String legId, UpdateLegRequest updateLegRequest){
//        response = requestSpecification
//                .headers(getHeaders())
//                .body(MapperUtils.serialize(updateLegRequest))
//                .put(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.UPDATE_LEG), legId));
//        return MapperUtils.deserialize(response.asString(), UpdateLegResponse.class);
//    }

    public void deleteLeg(String legId){
        response = requestSpecification
                .headers(getHeaders())
                .delete(RestAssuredUtils.buildUrl(vonageConfig, vonageConfig.getApis().get(ApisList.LEGS), getPathParameters()));
    }
}

