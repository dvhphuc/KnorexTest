package org.java17.model;

public class Impression {
    String id;
    String url;

    public Impression(String id, String url) {
        this.id = id;
        this.url = url;
    }

    @Override
    public String toString() {
        return "Impression{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
