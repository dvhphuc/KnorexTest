package org.java17.model;

public class CompanyBanner {
    private Long id;
    private int width;
    private int height;
    private String type;
    private String source;
    private String clickThroughUrl;

    public CompanyBanner() {
    }

    public CompanyBanner(Long id, int width, int height, String type, String source, String clickThroughUrl) {
        this.id = id;
        this.width = width;
        this.height = height;
        this.type = type;
        this.source = source;
        this.clickThroughUrl = clickThroughUrl;
    }

    public CompanyBanner(String imageUrl, String clickThroughUrl) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getClickThroughUrl() {
        return clickThroughUrl;
    }

    public void setClickThroughUrl(String clickThroughUrl) {
        this.clickThroughUrl = clickThroughUrl;
    }

    // toString method
    @Override
    public String toString() {
        return "CompanyBanner{" +
                "id=" + id +
                ", width=" + width +
                ", height=" + height +
                ", type='" + type + '\'' +
                ", source='" + source + '\'' +
                ", clickThroughUrl='" + clickThroughUrl + '\'' +
                '}';
    }
}
