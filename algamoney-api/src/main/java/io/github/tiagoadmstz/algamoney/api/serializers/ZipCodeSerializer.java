package io.github.tiagoadmstz.algamoney.api.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ZipCodeSerializer extends JsonSerializer<String> {

    @Override
    public void serialize(String value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        serializers.defaultSerializeValue(zipCodeFormat(value), gen);
    }

    public static String zipCodeFormat(String zipCode) {
        if (!"".equals(zipCode) && zipCode != null) {
            int counter = 0;
            char[] result = "##.###-##".toCharArray();
            for (char c : zipCode.toCharArray()) {
                if (result[counter] != "#".charAt(0)) counter++;
                result[counter++] = c;
            }
            return String.valueOf(result);
        }
        return zipCode;
    }

}
