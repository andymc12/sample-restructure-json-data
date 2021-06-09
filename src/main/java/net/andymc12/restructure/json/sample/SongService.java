package net.andymc12.restructure.json.sample;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Remote resource. This is implemented in the same application, server and programming language purely for
 * convenience. The remote resource could just as easily be written in another programming language and hosted
 * half the world apart, and the JSON data restructuring would still work.
 */
@Path("music")
@Produces(MediaType.APPLICATION_JSON)
public class SongService {
    private static String LS = System.lineSeparator();

    @GET
    public String getSong(@QueryParam("titleSearch") String titleString) {
        // yes, this is a bit contrived....
        if ("10,000 Reasons (Bless The Lord)".contains(titleString)) {
            return "{" + LS
                       + "\"links\":{" + LS
                       +     "\"self\":\"https://api.myhost.com/services/v2/music?titleSearch=10%2C000\"" + LS
                       + "}," + LS
                       + "\"data\":[" + LS
                       +      "{" + LS
                       +         "\"type\":\"Song\"," + LS
                       +         "\"id\":\"56780987\"," + LS
                       +         "\"attributes\":{" + LS
                       +             "\"admin\":\"EMI Christian Music Publishing\"," + LS
                       +             "\"author\":\"Jonas Myrin and Matt Redman\"," + LS
                       +             "\"ccli_number\":6016351," + LS
                       +             "\"copyright\":\"2011 Thankyou Music, Said And Done Music, and SHOUT! Publishing\"," + LS
                       +             "\"created_at\":\"2014-11-10T17:31:26Z\"," + LS
                       +             "\"hidden\":false," + LS
                       +             "\"last_scheduled_at\":\"2021-05-30T08:49:00Z\"," + LS
                       +             "\"last_scheduled_short_dates\":\"May 30, 2021\"," + LS
                       +             "\"notes\":\"Vocal Range B - D'\"," + LS
                       +             "\"themes\":\", Adoration, Blessing, Christian Life, Praise\"," + LS
                       +             "\"title\":\"10,000 Reasons (Bless The Lord)\"" + LS
                       +         "}," + LS
                       +         "\"links\":{\"self\":\"https://api.myhost.com/services/v2/music/8802060\"}" + LS
                       +     "}" + LS
                       + "]," + LS
                       + "\"included\":[],"+LS
                       + "\"meta\":{"+LS
                       +     "\"total_count\":1,"+LS
                       +     "\"count\":1,"+LS
                       +     "\"can_order_by\":[\"title\",\"created_at\",\"updated_at\",\"last_scheduled_at\"],"+LS
                       +     "\"can_query_by\":[\"arrangement_name\",\"arrangement_rehearsal_mix_id\",\"arrangement_tag_group_ids\",\"arrangement_tag_ids\",\"author\",\"bpm_max\",\"bpm_min\",\"ccli_number\",\"ending_key\",\"ending_key_minor\",\"folder_id\",\"hidden\",\"key_name\",\"lyrics\",\"meter\",\"service_type_id\",\"song_tag_group_ids\",\"song_tag_ids\",\"starting_key\",\"starting_key_minor\",\"themes\",\"title\"],"+LS
                       +     "\"parent\":{"+LS
                       +         "\"id\":\"132275\","+LS
                       +         "\"type\":\"Organization\""+LS
                       +     "}" + LS
                       + "}" + LS
                 + "}";
        }
        throw new NotFoundException("No song matches your search term: " + titleString);
    }
}