package model;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class FetchImage {

    /**
     * a wrapper class
     */
    @JacksonXmlRootElement(localName = "string")
    public static class ImageWrapper {
        @JacksonXmlText
        private String value;
        public String getValue(){
            return value;
        }

    }

    public static String fetchUrl(String make, String model, int year) {
        try {
            String searchTerm = URLEncoder.encode(make + " " + model + " " + year, StandardCharsets.UTF_8);
            String apiUrl = "https://www.carimagery.com/api.asmx/GetImageUrl?searchTerm=" + searchTerm;
            HttpURLConnection connection = (HttpURLConnection) new URL(apiUrl).openConnection();
            connection.setRequestMethod("GET");
            InputStream input = connection.getInputStream();
            XmlMapper xmlMapper = new XmlMapper();
            ImageWrapper result = xmlMapper.readValue(input, ImageWrapper.class);
            return result.getValue();
        } catch (Exception e){
            return "";
        }
    }


}
