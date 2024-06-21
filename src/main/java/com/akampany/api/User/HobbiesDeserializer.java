package com.akampany.api.User;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

import io.jsonwebtoken.io.IOException;

public class HobbiesDeserializer extends StdDeserializer<List<String>> {

    public HobbiesDeserializer() {
        this(null);
    }

    public HobbiesDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public List<String> deserialize(JsonParser jsonParser, DeserializationContext deserializationContext)
            throws IOException, JsonProcessingException {
        ObjectCodec codec = jsonParser.getCodec();
        JsonNode node = null;
		try {
			node = codec.readTree(jsonParser);
		} catch (java.io.IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        List<String> hobbies = new ArrayList<>();

        for (JsonNode hobbyNode : node) {
            String hobby = hobbyNode.asText();
            hobbies.add(hobby);
        }

        return hobbies;
    }
}
