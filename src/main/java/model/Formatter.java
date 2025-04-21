package model;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.io.File;
import java.io.IOException;
import java.util.Set;

public final class Formatter {

private Formatter() { }

    public static class CarRecordListWrapper {
        /**
         * .
         */
        @JacksonXmlElementWrapper(localName = "wishlist")
        @JacksonXmlProperty(localName = "CarRecord")
        private Set<CarRecord> wishlist;

        /**
         * wishlist wrapper.
         */
        public CarRecordListWrapper() { }

        /**
         * CarRecordListWrapper.
         * @param wishlist wishlist.
         */
        public CarRecordListWrapper(Set<CarRecord> wishlist) {
            this.wishlist = wishlist;
        }

        /**
         * getter for wishlist.
         * @return wishlist
         */
        public Set<CarRecord> getWishlist() {
            return wishlist;
        }

        /**
         * setter for wishlist.
         * @param wishlist wishlist
         */
        public void setWishlist(Set<CarRecord> wishlist) {
            this.wishlist = wishlist;
        }
    }

    /**
     * Writes the wishlist to a CSV file.
     * @param wishlist wishlist
     * @param csvPath path
     */
    public static void writeToCSV(Set<CarRecord> wishlist, String csvPath) {
        try {
            CsvMapper csvMapper = new CsvMapper();

            CsvSchema schema = CsvSchema.builder()
                    .addColumn("id")
                    .addColumn("price")
                    .addColumn("zip")
                    .addColumn("mileage")
                    .addColumn("make")
                    .addColumn("model")
                    .addColumn("year")
                    .addColumn("trim")
                    .addColumn("engineInfo")
                    .addColumn("bodyType")
                    .addColumn("numOfCylinders")
                    .addColumn("driveType")
                    .setUseHeader(true)
                    .build()
                    .withoutQuoteChar();

            csvMapper.writer(schema).writeValue(new File(csvPath), wishlist);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while writing to CSV");
        }
    }

    /**
     * Writes the wishlist to a JSON file.
     * @param wishlist wishlist
     * @param jsonPath path
     */
    public static void writeToJSON(Set<CarRecord> wishlist, String jsonPath) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(jsonPath), wishlist);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while writing to JSON");
        }
    }

    /**
     * Writes the wishlist to an XML file.
     * @param wishlist wishlist
     * @param xmlPath path
     */
    public static void writeToXML(Set<CarRecord> wishlist, String xmlPath) {
        try {
            XmlMapper xmlMapper = new XmlMapper();
            xmlMapper.writerWithDefaultPrettyPrinter()
                    .writeValue(new File(xmlPath), new CarRecordListWrapper(wishlist));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error while writing to XML");
        }
    }

    /**
     * write to the file.
     * @param wishlist wishlist
     * @param format format
     * @param path path
     */
    public static void write(Set<CarRecord> wishlist, String format, String path) {
        if (format.equalsIgnoreCase("xml")) {
            writeToXML(wishlist, path);
        } else if (format.equalsIgnoreCase("json")) {
            writeToJSON(wishlist, path);
        } else {
            writeToCSV(wishlist, path);
        }
    }
}
