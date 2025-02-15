package org.java17.model;

import java.util.List;

public class VastModel {
    String vastTagVersion;
    String id;
    String title;
    String description;
    Impression impression;
    List<Creative> creatives;

    public VastModel() {
    }

    public String getVastTagVersion() {
        return vastTagVersion;
    }

    public void setVastTagVersion(String vastTagVersion) {
        this.vastTagVersion = vastTagVersion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

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

    public Impression getImpression() {
        return impression;
    }

    public void setImpression(Impression impression) {
        this.impression = impression;
    }

    public List<Creative> getCreatives() {
        return creatives;
    }

    public void setCreatives(List<Creative> creatives) {
        this.creatives = creatives;
    }

    @Override
    public String toString() {
        return "VastModel{" +
                "vastTagVersion='" + vastTagVersion + '\'' +
                ", id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", impression=" + impression +
                ", creatives=" + creatives +
                '}';
    }
}
