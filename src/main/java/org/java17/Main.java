package org.java17;

import org.java17.exporter.VastExporter;
import org.java17.model.VastModel;
import org.java17.parser.VastParser;
import org.java17.parser.VastParserImpl;
import org.java17.reader.FileVastReader;
import org.java17.reader.PathOrUrlDetector;
import org.java17.reader.UrlVastReader;

public class Main {
    public static void main(String[] args) {
        // Input:
        // 1. Read xml from file
        // 2. Read xml from url
        // Output:
        // Parse the xml into JSON

        FileVastReader fileVastReader = new FileVastReader();
        UrlVastReader urlVastReader = new UrlVastReader();
        PathOrUrlDetector detector = new PathOrUrlDetector();
        VastParser parser = new VastParserImpl(urlVastReader, fileVastReader, detector); // Dependencies injection

        VastModel parsedModel = parser.parse("D:\\Individual\\Knorex\\src\\main\\resources\\input.xml");

        System.out.println(parsedModel.toString());

        VastExporter exporter = new VastExporter();
        String json = exporter.toJson(parsedModel);

        System.out.println(json);
    }
}