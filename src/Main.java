import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {

        Path filePath = FileSystems.getDefault().getPath("enable1.txt");

//        readLines(filePath).forEach(System.out::println);

        String firstNineLetterWord = readLines(filePath).stream()
                .filter(entry -> entry.length() > 9)
                .findFirst().get();
        System.out.println(firstNineLetterWord + "\n");

        String containsABCFirst = readLines(filePath).stream()
                .filter(entry -> entry.contains("a") && entry.contains("b") && entry.contains("c"))
                .findFirst().get();
        System.out.println(containsABCFirst + "\n");

        String containsABCFirstCaseInsensitive = readLines(filePath).stream()
                .map(String::toLowerCase)
                .filter(entry -> entry.contains("a") && entry.contains("b") && entry.contains("c"))
                .findFirst().get();
        System.out.println(containsABCFirstCaseInsensitive + "\n");

        String containsNoAELongest = readLines(filePath).stream()
                .map(String::toLowerCase)
                .filter(entry -> !entry.contains("e") && !entry.contains("a"))
                .max(Comparator.comparingInt(String::length)).get();
        System.out.println(containsNoAELongest + "\n");

        String containsQShortest = readLines(filePath).stream()
                .map(String::toLowerCase)
                .filter(entry -> entry.contains("q"))
                .min(Comparator.comparingInt(String::length)).get();
        System.out.println(containsQShortest + "\n");

        // Get words containing 'cool' and 'wow', append an exclamation mark and write them to a file. //

        List<String> containsWowOrCool = readLines(filePath).stream()
                .map(String::toLowerCase)
                .filter(entry -> entry.contains("cool") || entry.contains("wow"))
                .map(entry -> entry + "!")
                .collect(Collectors.toList());
        containsWowOrCool.forEach(System.out::println);

        StringBuilder output = new StringBuilder();
        containsWowOrCool.forEach(entry -> output.append(entry).append("\n"));


        try (BufferedWriter writer = new BufferedWriter(new FileWriter("twitterwords.txt", false))) {
            writer.write(output.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
//
//        try {
//            Files.write(Paths.get("twitterwords.txt"), output.toString().getBytes("UTF-8"));
//        } catch (IOException e) {
//            e.printStackTrace();

        // Print the number of files, including folder, in current project. //

        try {
           long count = Files.walk(Paths.get("")).count();
            System.out.println();
            System.out.println(Paths.get("").toAbsolutePath().toString());
            System.out.printf("%nThere are a total of %d files in this project.", count);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static List<String> readLines(Path path) {
        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
