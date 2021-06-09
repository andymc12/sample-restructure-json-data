package net.andymc12.restructure.json.sample;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonValue;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.ext.ReaderInterceptor;
import javax.ws.rs.ext.ReaderInterceptorContext;

/**
 * This is the ReaderInterceptor that intercepts the HTTP entity stream after the client process receives the
 * HTTP response from the server but before the stream is converted into a business entity object.
 */
public class DataExtractionReaderInterceptor implements ReaderInterceptor {

    @Override
    public Object aroundReadFrom(ReaderInterceptorContext context) throws IOException, WebApplicationException {
        JsonObject json = Json.createReader(context.getInputStream()).readObject();
        JsonValue data = json.get("data");
        context.setInputStream(new ByteArrayInputStream(data.toString().getBytes()));
        return context.proceed();
    }
}