package org.java17.model;

import java.util.List;

public class Creative {

    public Creative() {

    }

    public Creative(CompanyBanner companyBanner, Integer duration, List<TrackingEvent> trackingEvent, List<Video> videoClicks, List<MediaFile> mediaFiles) {
        this.companyBanner = companyBanner;
        this.duration = duration;
        this.trackingEvent = trackingEvent;
        this.videoClicks = videoClicks;
        this.mediaFiles = mediaFiles;
    }

    CompanyBanner companyBanner;
    Integer duration;
    List<TrackingEvent> trackingEvent;
    List<Video> videoClicks;
    List<MediaFile> mediaFiles;

    public CompanyBanner getCompanyBanner() {
        return companyBanner;
    }

    public Integer getDuration() {
        return duration;
    }

    public List<TrackingEvent> getTrackingEvent() {
        return trackingEvent;
    }

    public List<Video> getVideoClicks() {
        return videoClicks;
    }

    public List<MediaFile> getMediaFiles() {
        return mediaFiles;
    }

    public void setCompanyBanner(CompanyBanner companyBanner) {
        this.companyBanner = companyBanner;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setTrackingEvent(List<TrackingEvent> trackingEvent) {
        this.trackingEvent = trackingEvent;
    }

    public void setVideoClicks(List<Video> videoClicks) {
        this.videoClicks = videoClicks;
    }

    public void setMediaFiles(List<MediaFile> mediaFiles) {
        this.mediaFiles = mediaFiles;
    }

    @Override
    public String toString() {
        return "Creative{" +
                "companyBanner=" + companyBanner +
                ", duration=" + duration +
                ", trackingEvent=" + trackingEvent +
                ", videoClicks=" + videoClicks +
                ", mediaFiles=" + mediaFiles +
                '}';
    }
}
