package org.java17.model;

public class TrackingEvent {
    String eventType;
    String eventUrl;
    String offset;

    public TrackingEvent(String eventType, String eventUrl, String offset) {
        this.eventType = eventType;
        this.eventUrl = eventUrl;
        this.offset = offset;
    }

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getEventUrl() {
        return eventUrl;
    }

    public void setEventUrl(String eventUrl) {
        this.eventUrl = eventUrl;
    }

    @Override
    public String toString() {
        return "TrackingEvent{" +
                "eventType='" + eventType + '\'' +
                ", eventUrl='" + eventUrl + '\'' +
                ", offset='" + offset + '\'' +
                '}';
    }
}
