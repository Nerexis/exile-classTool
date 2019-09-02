package exile.classTool.parser;

import exile.classTool.model.ExileClass;

import java.util.HashSet;
import java.util.List;
import java.util.logging.Logger;

public class ExileClassParser {

    private static final Logger logger = Logger.getLogger(ExileClassParser.class.getSimpleName());

    private final ExileClassStringExtractor exileClassStringExtractor;
    private final HashSet<ExileClass> exileClassList;

    public ExileClassParser() {
        this.exileClassList = new HashSet<>();
        this.exileClassStringExtractor = new ExileClassStringExtractor();
    }

    public HashSet<ExileClass> parse(List<String> linesToParse) {
        logger.finest("entered parse(), count: " + linesToParse.size());
        exileClassList.clear();
        deserializeRawLines(linesToParse);
        return new HashSet<>(exileClassList);
    }

    private void deserializeRawLines(List<String> linesToParse) {
        logger.finest("entered deserializeRawLines()");
        linesToParse.stream().sorted().parallel().distinct().forEach(line -> {
            try {
                logger.finest("deserialize line: " + line);
                processLine(line);
            } catch (ExileParserException e) {
                e.printStackTrace();
                throw new RuntimeException(e.getMessage());
            }
        });
    }

    private void processLine(String line) throws ExileParserException {
        logger.finest("processLine: \\'" + line + "\\'");
        if (!exileClassStringExtractor.isLineValidClassLine(line)) {
            logger.finest("processLine: not valid skipping: \\'" + line + "\\'");
            return;
        }
        String classNameFromLine = exileClassStringExtractor.getClassNameFromLine(line);
        if (this.exileClassList.contains(new ExileClass(classNameFromLine))) {
            logger.warning("processLine SKIPPING class " + classNameFromLine + " already exists and won't be added for line: " + line);
            return;
        }

        int price = exileClassStringExtractor.getPriceFromLine(line);
        Integer sellPrice = exileClassStringExtractor.getSellPriceFromLine(line).orElse(null);
        this.exileClassList.add(
                new ExileClass(
                        classNameFromLine,
                        exileClassStringExtractor.getQualityFromLine(line),
                        price,
                        sellPrice
                )
        );
        logger.finest("processLine: line ok added to set");
    }
}
