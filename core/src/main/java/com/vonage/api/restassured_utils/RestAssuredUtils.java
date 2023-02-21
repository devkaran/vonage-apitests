package com.vonage.api.restassured_utils;


import com.vonage.api.env_configuration.Api;
import com.vonage.api.env_configuration.Services;
import io.restassured.RestAssured;
import io.restassured.filter.log.ErrorLoggingFilter;
import io.restassured.filter.log.LogDetail;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.specification.RequestSpecification;
import lombok.experimental.UtilityClass;
import lombok.extern.log4j.Log4j2;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.io.IoBuilder;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;


@Log4j2
@UtilityClass
public class RestAssuredUtils {
    IoBuilder ioBuilder = IoBuilder.forLogger(log);
    RequestLoggingFilter requestLoggingFilter = new RequestLoggingFilter(LogDetail.ALL,false, ioBuilder.setLevel(Level.INFO).buildPrintStream());
    ResponseLoggingFilter responseLoggingFilter = new ResponseLoggingFilter(LogDetail.BODY,false, ioBuilder.setLevel(Level.INFO).buildPrintStream());
    ErrorLoggingFilter errorLoggingFilter = new ErrorLoggingFilter(ioBuilder.setLevel(Level.ERROR).buildPrintStream());
    public static RequestSpecification getDefaultRequestClient(){
        return RestAssured.given().filters(requestLoggingFilter, responseLoggingFilter, errorLoggingFilter);
    }

    public static URL buildUrl(Services service, Api apiInfo){
        return buildUrl(service, apiInfo, null);
    }

    public static URL buildUrl(Services service, Api apiInfo, String[] pathParameters){
        URL url = null;
        try {
            String path = buildPathParameter(apiInfo, pathParameters);
//            url = new URI(service.getScheme()==null?"https":service.getScheme(), service.getHost(), path, null).toURL();
            url = new URI(service.getScheme() == null ? "https" : service.getScheme(), null, service.getHost(), service.getPort()==null?-1:service.getPort(), path, null, null).toURL();
        } catch (MalformedURLException | URISyntaxException e) {
            log.error("Error occurred while building URL for host: [{}]", service.getHost());
        }
        return url;
    }

    private static String buildPathParameter(Api apiInfo, String[] pathParameters){
        String path = apiInfo.getPath();
        if(pathParameters!=null && pathParameters.length!=0) {
            if (path.toLowerCase().contains("%s")) {
                path = String.format(path, pathParameters);
            } else {
                path = new StringBuilder().append(path).append(path.endsWith("/") ? String.join("/", pathParameters) : ("/" + String.join("/", pathParameters))).toString();
            }
        }
        return path;
    }
}
