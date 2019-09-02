package exile.classTool.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RawLinesLoader {

    public List<String> loadRawLines(String filePath) {

        List<String> rawLines = new ArrayList<>();
        File file = new File(filePath);

        Scanner sc = getScanner(file);

        while (sc.hasNextLine()) {
            processLine(rawLines, sc);
        }
        sc.close();
        return rawLines;
    }

    private Scanner getScanner(File file) {
        try {
            return new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }

    private void processLine(List<String> rawLines, Scanner sc) {
        String line = sc.nextLine();
        rawLines.add(line);
    }
}