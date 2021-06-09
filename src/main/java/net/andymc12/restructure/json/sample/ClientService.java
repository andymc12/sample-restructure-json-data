package net.andymc12.restructure.json.sample;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.rest.client.RestClientBuilder;

/**
 * This RESTful resource class is where the client code is that invokes the
 * various client APIs (MicroProfile Rest Client and JAX-RS Client) to obtain a
 * list of Songs from the remote service.
 * <p>
 * To invoke, browse or curl to http://localhost:9080/services/v2/client for the
 * JAX-RS Client or http://localhost:9080/services/v2/client?type=mp for the
 * MicroProfile Client.
 */
@Path("client")
public class ClientService {
    private static final String ENDPOINT = "http://localhost:9080/services/v2/";

    @GET
    public String runClient(@QueryParam("type") @DefaultValue("jaxrs") String type) {
        if ("jaxrs".equals(type)) {
            return jaxrsClient();
        }
        return mpRestClient();
    }

    private String jaxrsClient() {
        Client c = ClientBuilder.newClient().register(DataExtractionReaderInterceptor.class);
        WebTarget target = c.target(ENDPOINT).path("music").queryParam("titleSearch", "10,000 Reasons");
        Response r = target.request(MediaType.APPLICATION_JSON).get();
        List<Song> songs = r.readEntity(new GenericType<List<Song>>(){});
        return songs.stream().map(Song::toString).collect(Collectors.joining(System.lineSeparator()));
    }

    private String mpRestClient() {
        String titleString = "10,000";
        SongService client = RestClientBuilder.newBuilder()
                .baseUri(URI.create(ENDPOINT))
                .register(DataExtractionReaderInterceptor.class).property("debug", "true").build(SongService.class);
        List<Song> songs = client.songsByTitle(titleString);
        return songs.stream().map(Song::toString).collect(Collectors.joining(System.lineSeparator()));
    }

    @Path("music")
    public interface SongService {
        @GET
        List<Song> songsByTitle(@QueryParam("titleSearch") String titleString);
    }
}
