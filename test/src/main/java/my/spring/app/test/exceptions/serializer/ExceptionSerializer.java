package my.spring.app.test.exceptions.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class ExceptionSerializer extends StdSerializer<Exception> {

    public ExceptionSerializer(Class<Exception> t) {
        super(t);
    }

    public ExceptionSerializer() {
        this(null);
    }
    @Override
    public void serialize(Exception exc, JsonGenerator jgen, SerializerProvider provider) throws IOException {
        jgen.writeStartObject();
        jgen.writeStringField("exception", exc.getClass().getName());
        jgen.writeStringField("message", exc.getMessage());
        jgen.writeEndObject();
    }
}
