package com.gochinatv.cdn.api.objectmapper;

import lombok.Data;
import org.codehaus.jackson.JsonParser;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.JsonDeserializer;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.annotate.JsonDeserialize;

import java.io.IOException;
import java.util.Map;

public class TestMapper {


    public static void main(String[] args) {
        String json = "{\"id\":\"1\",\"name\":\"zhuhh\",\"address\":\"{\"id\":\"222\",\"name\":\"333\"}\"}";
        ObjectMapper mapper = new ObjectMapper();
        //mapper.configure(DeserializationConfig.Feature.)
        try {
            User user = mapper.readValue(json,User.class);
            System.out.println(user.getName());
            //System.out.println(user.getAddress().getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
        //{"id":"1","name":"zhuhh","address":"{"id":"222","name":"333"}"}
    }


    //SerializationFeature
    @Data
    private final static class User{
        private String id;
        private String name;

        //@JsonDeserialize(contentUsing=StringToObject.class)
        private Address address;

    }

    @Data
    private final static class Address{

        @JsonCreator
        public Address(String id){}

        private String id;
        private String name;

    }


    public static class StringToObject extends JsonDeserializer<Address> {

        @Override
        public Address deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
            Address address = jp.readValueAs(Address.class);

            return address;
        }
    }

}
