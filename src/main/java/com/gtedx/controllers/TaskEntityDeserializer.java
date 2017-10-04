package com.gtedx.controllers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.gtedx.entities.RequestHeaderEntity;
import com.gtedx.entities.TaskEntity;

import java.io.IOException;

/**
 * Created by lion on 04.10.17.
 */
public class TaskEntityDeserializer extends StdDeserializer<TaskEntity> {

    public TaskEntityDeserializer(){
        this(null);
    }

    public TaskEntityDeserializer(Class < TaskEntity > t) {
            super(t);
        }

    @Override
    public TaskEntity deserialize(JsonParser parser,
                                  DeserializationContext context) throws IOException, JsonProcessingException {
        JsonNode node = parser.getCodec().readTree(parser);

        TaskEntity taskEntity = new TaskEntity();

    //------------------------------------ method --------------------------------------//
        if(node.has("method")){
            taskEntity.setMethod(node.get("method").asText(null));
        }

    //-------------------------------------- url ----------------------------------------//
        if(node.has("url")){
            taskEntity.setUrl(node.get("url").asText(null));
        }

    //------------------------------------- data ----------------------------------------//
        if(node.has("data")){
            taskEntity.setData(node.get("data").toString());
        }

    //------------------------------------ header ---------------------------------------//
        ObjectMapper mapper = new ObjectMapper();
        JsonNode header_node = node.get("headers");
        String jsonHeader = mapper.writeValueAsString(header_node);

        RequestHeaderEntity header =
                mapper.readValue(
                        jsonHeader,
                        mapper.getTypeFactory().constructType(RequestHeaderEntity.class));
        taskEntity.setHeader(header);

        return taskEntity;
    }
}
