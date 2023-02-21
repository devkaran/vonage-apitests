package com.vonage.api.env_configuration;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.EnumMap;
import java.util.Map;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TestConfiguration {
    private String name;
    private String version;
    private String environment;

    @JsonProperty("SERVICES")
    public Map<ServiceList, Services> services = new EnumMap<>(ServiceList.class);
}