package org.java17.reader;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class UrlVastReader {
    public String readXmlFromUrl(String url) {
        try {
            URL xmlSource = new URL(url);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(xmlSource.openStream()));

            StringBuilder stringBuilder = new StringBuilder();

            String inputLine;
            while ((inputLine = in.readLine()) != null)
                stringBuilder.append(inputLine);
            in.close();

            return stringBuilder.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
