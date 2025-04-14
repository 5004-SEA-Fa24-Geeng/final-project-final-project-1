package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Utility class for loading car make and model data from a csv file.
 */
public class MakeAndModelData {

    /**
     * Reads a CSV file and returns a mapping of car makes to their respective models.
     * @return A HashMap where keys are car makes and values are lists of models.
     */
    public static HashMap<String, List<String>> getMakeModelMap() {
        HashMap<String, List<String>> makeModelMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader("data/make_and_model.csv"))) {
            String line;

            // Skip the first line (header)
            br.readLine();

            while ((line = br.readLine()) != null) {
                String[] values = line.split(",");

                if (values.length == 2) {
                    String make = values[0].trim();
                    String model = values[1].trim();

                    // Add model to corresponding make in the map
                    makeModelMap.computeIfAbsent(make, k -> new ArrayList<>()).add(model);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return makeModelMap;
    }
}
