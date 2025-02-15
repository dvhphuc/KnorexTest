package org.java17.model;

public class MediaFile {
    private String id;
    private String url;
    private String type;
    private int width;
    private int height;

    public MediaFile(String id, String url, String type, int width, int height) {
        this.id = id;
        this.url = url;
        this.type = type;
        this.width = width;
        this.height = height;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return "MediaFile{" +
                "id='" + id + '\'' +
                ", url='" + url + '\'' +
                ", type='" + type + '\'' +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
