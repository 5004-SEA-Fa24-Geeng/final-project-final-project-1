package utility;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MakeAndModelData {
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

                    makeModelMap.computeIfAbsent(make, k -> new ArrayList<>()).add(model);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return makeModelMap;
    }
}
