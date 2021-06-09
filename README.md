# Sample for Restructuring JSON data with JAX-RS ReaderInterceptors and JSON-B Magic

This sample is intended to demonstrate how to map JSON into a Java object using JAX-RS and JSON-B, even if the JSON
data doesn't quite "fit". For example, suppose your client accesses a RESTful endpoint that returns the following JSON:
```
{
    "links":{
        "self":"https://api.myhost.com/services/v2/music?titleSearch=10%2C000"
    },
    "data":[
        {
            "type":"Song",
            "id":"56780987",
            "attributes":{
                "admin":"EMI Christian Music Publishing",
                "author":"Jonas Myrin and Matt Redman",
                "ccli_number":6016351,
                "copyright":"2011 Thankyou Music, Said And Done Music, and SHOUT! Publishing",
                "created_at":"2014-11-10T17:31:26Z",
                "hidden":false,
                "last_scheduled_at":"2021-05-30T08:49:00Z",
                "last_scheduled_short_dates":"May 30, 2021",
                "notes":"Vocal Range B - D'",
                "themes":", Adoration, Blessing, Christian Life, Praise",
                "title":"10,000 Reasons (Bless The Lord)"
            },
            "links":{"self":"https://api.myhost.com/services/v2/music/8802060"}
        }
    ],
    "included":[],
    "meta":{
        "total_count":1,
        "count":1,
        "can_order_by":["title","created_at","updated_at","last_scheduled_at"],
        "can_query_by":["arrangement_name","arrangement_rehearsal_mix_id","arrangement_tag_group_ids","arrangement_tag_ids","author","bpm_max","bpm_min","ccli_number","ending_key","ending_key_minor","folder_id","hidden","key_name","lyrics","meter","service_type_id","song_tag_group_ids","song_tag_ids","starting_key","starting_key_minor","themes","title"],
        "parent":{
            "id":"132275",
            "type":"Organization"
        }
    }
}
```

Mapping that to a `Song` Java object is complicated because (1) the data for the song is embeded a few layers down in
the JSON (under the `data` field) and (2) because the fields for the song are split between the root song field and a
child `attributes` field.

The way to overcome these complications is to use a
[ReaderInterceptor](src/main/java/net/andymc12/restructure/json/sample/DataExtractionReaderInterceptor.java) to extract
out only the necessary part of the JSON, and then use some
[JSON-B mapping magic](src/main/java/net/andymc12/restructure/json/sample/Song.java#L47) to _flatten_ the song fields.

To run this sample, first clone this repo, then from the `sample-restructure-json-data` directory, invoke the command:
```
mvn clean package liberty:run
```

Then browse to http://localhost:9080/ - that page has links to invoke the JAX-RS Client and MicroProfile Rest Client.

This sample is part of a blog post that is available at https://andymc12.net/ - enjoy!
