package com.vonage.api;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.vonage.api.env_configuration.TestConfiguration;
import com.vonage.api.object_mapper_defaults.LocalDateTimeDeserializer;
import com.vonage.api.object_mapper_defaults.LocalDateTimeSerializer;
import com.vonage.api.object_mapper_defaults.MapperUtils;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Properties;

@Log4j2
@NoArgsConstructor(force = true, access = AccessLevel.PRIVATE)
public class App {
    @Getter
    private static TestConfiguration configuration;
    private static final String PROPERTIES_FILE_NAME = "../testsettings.properties";
    private static final String TEST_SETTINGS_PATH = System.getProperty("user.dir") +File.separator+ PROPERTIES_FILE_NAME;

    static {
        try {
            log.info("initializing test env config");
            initSetup();
        } catch (IOException e) {
            log.error("error from init setup", e);
        }
    }

    private static void initSetup() throws IOException {
        String configPath = System.getProperty("user.dir") + "/configs/api.json";
        log.info("selected config path : {}", configPath);
        ObjectMapper objectMapper = getDefaultObjectMapper();
        MapperUtils.init(objectMapper);
        configuration = MapperUtils.deserialize(readFile(configPath), TestConfiguration.class);
        log.info("configuration : {}", configuration);

    }

// reads properties from properties files from project root dir
    public static String readProperty(String propertyName) {
        Properties props = new Properties();
        try (InputStream is = new FileInputStream(TEST_SETTINGS_PATH)){
            props.load(is);
        } catch (IOException e) {
            log.error("exception occurred while reading properties file from path : {} error : {}", TEST_SETTINGS_PATH, e);
        }
        return props.getProperty(propertyName);
    }

//  read a file from path
    public static File readFile (String filename) {
        return new File(filename);
    }

//  Default Object Mapper configs
    public static ObjectMapper getDefaultObjectMapper(){
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());
        mapper.registerModule(localDateTimeModule());
        return mapper;
    }

    public static com.fasterxml.jackson.databind.Module localDateTimeModule() {
        SimpleModule module = new SimpleModule();
        module.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer());
        module.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer());
        return module;
    }

}
