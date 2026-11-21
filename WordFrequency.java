import java.io.*;
import java.util.HashMap;
import java.util.Map;

// Assignment 2: Read input.txt, count word frequency, write to output.txt
public class WordFrequency {
    public static void main(String[] args) {
        HashMap<String, Integer> count = new HashMap<>();

        // Read from input.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                for (String word : line.toLowerCase().split("\\W+")) {
                    if (!word.isEmpty()) {
                        count.put(word, count.getOrDefault(word, 0) + 1);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Could not read input.txt: " + e.getMessage());
            return;
        }

        // Write to output.txt
        try (PrintWriter writer = new PrintWriter(new FileWriter("output.txt"))) {
            for (Map.Entry<String, Integer> entry : count.entrySet()) {
                writer.println(entry.getKey() + " : " + entry.getValue());
            }
            System.out.println("Word frequency written to output.txt");
        } catch (IOException e) {
            System.out.println("Could not write output.txt: " + e.getMessage());
        }
    }
}