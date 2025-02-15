package org.java17.parser;

import org.java17.model.CompanyBanner;
import org.java17.model.Creative;
import org.java17.model.Impression;
import org.java17.model.MediaFile;
import org.java17.model.TrackingEvent;
import org.java17.model.VastModel;
import org.java17.model.Video;
import org.java17.reader.PathOrUrlDetector;
import org.java17.reader.UrlVastReader;
import org.java17.reader.FileVastReader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class VastParserImpl implements VastParser {

    public static DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    public static DocumentBuilder builder;

    static {
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        }
    }

    private UrlVastReader urlReader;
    private FileVastReader fileReader;
    private PathOrUrlDetector detector;

    public VastParserImpl(UrlVastReader urlReader, FileVastReader fileReader, PathOrUrlDetector detector) {
        this.urlReader = urlReader;
        this.fileReader = fileReader;
        this.detector = detector;
    }


    @Override
    public VastModel parse(String source) {
        String xmlContent = "";
        if (detector.detectType(source).equals("URL")) {
            xmlContent = urlReader.readXmlFromUrl(source);
        }
        else if (detector.detectType(source).equals("File Path")) {
            xmlContent = fileReader.readXmlFromFile(source);
        }
        else {
            throw new RuntimeException("Just support URL or File Path");
        }

        if (xmlContent == null || xmlContent.equals("")) {
            throw new RuntimeException("The source has no content");
        }

        try {
            String vastTagVerson = parseVastTagVersion(xmlContent);
            String id = parseId(xmlContent);
            String title = parseTitle(xmlContent);
            String desc = parseDescription(xmlContent);
            Impression impression = parseImpression(xmlContent);
            List<Creative> creatives = parseCreatives(xmlContent);

            VastModel model = new VastModel();
            model.setVastTagVersion(vastTagVerson);
            model.setId(id);
            model.setDescription(desc);
            model.setImpression(impression);
            model.setTitle(title);
            model.setCreatives(creatives);

            return model;

        } catch (Exception e) {
            throw new RuntimeException("Some error occurs while parsing model");
        }
    }


    // parse tag version
    public String parseVastTagVersion(String xmlString) throws IOException, SAXException, ParserConfigurationException {
        Document document = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));

        NodeList nodeList = document.getElementsByTagName("VAST");

        Element element = (Element) nodeList.item(0);

        return element.getAttribute("version");
    }

    public String parseId(String xmlString) throws IOException, SAXException {
        Document document = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));

        NodeList nodeList = document.getElementsByTagName("Ad");

        Element element = (Element) nodeList.item(0);

        return element.getAttribute("id");
    }

    public String parseTitle(String xmlString) throws IOException, SAXException {
        Document document = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));

        NodeList nodeList = document.getElementsByTagName("AdTitle");

        Element element = (Element) nodeList.item(0);

        return element.getTextContent().strip();
    }

    public String parseDescription(String xmlString) throws IOException, SAXException {
        Document document = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));

        NodeList nodeList = document.getElementsByTagName("Description");

        Element element = (Element) nodeList.item(0);


        return element.getTextContent().strip();
    }

    public Impression parseImpression(String xmlString) throws IOException, SAXException {
        Document document = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));

        NodeList nodeList = document.getElementsByTagName("Impression");

        Element element = (Element) nodeList.item(0);

        String idValue = element.getAttribute("id");

        String urlValue = element.getTextContent();

        return new Impression(idValue, urlValue);
    }

    public List<Creative> parseCreatives(String xmlString) throws IOException, SAXException {
        Document document = builder.parse(new ByteArrayInputStream(xmlString.getBytes()));
        document.getDocumentElement().normalize();
        NodeList creativeNodes = document.getElementsByTagName("Creative");

        List<Creative> creatives = new ArrayList<>();

        for (int i = 0; i < creativeNodes.getLength(); i++) {
            Element creativeElement = (Element) creativeNodes.item(i);
            Creative creative = new Creative();

            // Process Company Banner if present
            NodeList companionNodes = creativeElement.getElementsByTagName("Companion");
            if (companionNodes.getLength() > 0) {
                Element companionElement = (Element) companionNodes.item(0);
                Long id = Long.parseLong(companionElement.getAttribute("id"));
                int width = Integer.parseInt(companionElement.getAttribute("width"));
                int height = Integer.parseInt(companionElement.getAttribute("height"));
                NodeList staticResourceNodes = companionElement.getElementsByTagName("StaticResource");
                Element staticResourceElement = (Element) staticResourceNodes.item(0);
                String type = staticResourceElement.getAttribute("creativeType");
                String imageUrl = companionElement.getElementsByTagName("StaticResource").item(0).getTextContent().trim();
                String clickThroughUrl = companionElement.getElementsByTagName("CompanionClickThrough").item(0).getTextContent().trim();
                creative.setCompanyBanner(new CompanyBanner(id, width, height, null, imageUrl, clickThroughUrl));
            }

            // Process Duration if present
            NodeList durationNodes = creativeElement.getElementsByTagName("Duration");
            if (durationNodes.getLength() > 0) {
                String durationStr = durationNodes.item(0).getTextContent().trim();
                String[] parts = durationStr.split(":");
                int hours = (parts.length > 2) ? Integer.parseInt(parts[0]) : 0;
                int minutes = (parts.length > 1) ? Integer.parseInt(parts[parts.length - 2]) : 0;
                int seconds = Integer.parseInt(parts[parts.length - 1]);
                int duration = (hours * 3600) + (minutes * 60) + seconds;
                creative.setDuration(duration);
            }

            // Process Tracking Events
            NodeList trackingNodes = creativeElement.getElementsByTagName("Tracking");
            List<TrackingEvent> trackingEvents = new ArrayList<>();
            for (int j = 0; j < trackingNodes.getLength(); j++) {
                Element trackingElement = (Element) trackingNodes.item(j);
                String event = trackingElement.getAttribute("event");
                String offset = trackingElement.getAttribute("offset");
                String url = trackingElement.getTextContent().trim();
                trackingEvents.add(new TrackingEvent(event, url, offset));
            }
            creative.setTrackingEvent(trackingEvents);

            // Process Video Clicks
            NodeList clickTrackingNodes = creativeElement.getElementsByTagName("ClickTracking");
            List<Video> videoClicks = new ArrayList<>();
            for (int j = 0; j < clickTrackingNodes.getLength(); j++) {
                Element clickElement = (Element) clickTrackingNodes.item(j);
                String id = clickElement.getAttribute("id");
                String url = clickElement.getTextContent().trim();
                videoClicks.add(new Video(id, url));
            }
            creative.setVideoClicks(videoClicks);

            // Process Media Files
            NodeList mediaFileNodes = creativeElement.getElementsByTagName("MediaFile");
            List<MediaFile> mediaFiles = new ArrayList<>();
            for (int j = 0; j < mediaFileNodes.getLength(); j++) {
                Element mediaElement = (Element) mediaFileNodes.item(j);
                String id = mediaElement.getAttribute("id");
                String url = mediaElement.getTextContent().trim();
                String type = mediaElement.getAttribute("type");
                int width = Integer.parseInt(mediaElement.getAttribute("width"));
                int height = Integer.parseInt(mediaElement.getAttribute("height"));
                mediaFiles.add(new MediaFile(id, url, type, width, height));
            }
            creative.setMediaFiles(mediaFiles);

            creatives.add(creative);
        }
        return creatives;
    }

}
