package net.andymc12.restructure.json.sample;

import java.util.Map;

/**
 * Song entity class. This class is the business model. It represents a song that whose data we obtain via the
 * remote RESTful service.
 */
public class Song {
    private String id;
    private String title;
    private String author;
    private String lastScheduledShortDates;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLastScheduledShortDates() {
        return lastScheduledShortDates;
    }

    public void setLastScheduledShortDates(String lastScheduledShortDates) {
        this.lastScheduledShortDates = lastScheduledShortDates;
    }

    public void setAttributes(Map<String, Object> attrs) {
        System.out.println("Attributes: " + attrs);
        setTitle((String) attrs.get("title"));
        setAuthor((String) attrs.get("author"));
        setLastScheduledShortDates((String) attrs.get("last_scheduled_short_dates"));
    }

    @Override
    public String toString() {
        return "Song [id=" + id + ", lastScheduledShortDates=" + lastScheduledShortDates + ", author=" + author
                + ", title=" + title + "]";
    }

}
