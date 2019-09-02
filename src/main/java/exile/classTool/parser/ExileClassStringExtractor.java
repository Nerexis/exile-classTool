package exile.classTool.parser;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class ExileClassStringExtractor {

    private static final String REGEX_CLASS_PRICE_QUALITY_PRESENT = "class.*\\{.*( (price.*quality.*)|(quality.*price.*)).*};.*";
    private static final String REGEX_EXTRACT_CLASS_NAME = "class ([^\\s{]+)"; // value as group 1
    private static final String REGEX_EXTRACT_PRICE = "price\\s*=\\s*(\\d+)"; // value as group 1
    private static final String REGEX_EXTRACT_QUALITY = "quality\\s*=\\s*(\\d+)"; // value as group 1
    @SuppressWarnings("SpellCheckingInspection")
    private static final String REGEX_EXTRACT_SELLPRICE = "sellPrice\\s*=\\s*(\\d+)"; // value as group 1
    private static final String LINE_VALID_BUT_ELEMENT_NOT_FOUND = "Line valid but element not found";

    private final Pattern classNameExtractPattern;
    private final Pattern priceExtractPattern;
    private final Pattern qualityExtractPattern;
    private final Pattern sellPriceExtractPattern;
    private final Pattern isValidLinePattern;

    ExileClassStringExtractor() {
        this.classNameExtractPattern = Pattern.compile(ExileClassStringExtractor.REGEX_EXTRACT_CLASS_NAME);
        this.priceExtractPattern = Pattern.compile(ExileClassStringExtractor.REGEX_EXTRACT_PRICE);
        this.qualityExtractPattern = Pattern.compile(ExileClassStringExtractor.REGEX_EXTRACT_QUALITY);
        this.sellPriceExtractPattern = Pattern.compile(ExileClassStringExtractor.REGEX_EXTRACT_SELLPRICE);
        this.isValidLinePattern = Pattern.compile(ExileClassStringExtractor.REGEX_CLASS_PRICE_QUALITY_PRESENT);
    }

    String getClassNameFromLine(String line) throws ExileParserException {
        Matcher classNameExtractMatcher = classNameExtractPattern.matcher(line);
        if (classNameExtractMatcher.find() && classNameExtractMatcher.groupCount() >= 1) {
            return classNameExtractMatcher.group(1);
        }
        throw new ExileParserException(ExileClassStringExtractor.LINE_VALID_BUT_ELEMENT_NOT_FOUND + " Line related: '" + line + "'");
    }

    int getPriceFromLine(String line) throws ExileParserException {
        Matcher priceExtractMatcher = priceExtractPattern.matcher(line);
        if (priceExtractMatcher.find() && priceExtractMatcher.groupCount() >= 1) {
            return Integer.parseInt(priceExtractMatcher.group(1));
        }
        throw new ExileParserException(ExileClassStringExtractor.LINE_VALID_BUT_ELEMENT_NOT_FOUND + " Line related: '" + line + "'");
    }

    int getQualityFromLine(String line) throws ExileParserException {
        Matcher qualityExtractMatcher = qualityExtractPattern.matcher(line);
        if (qualityExtractMatcher.find() && qualityExtractMatcher.groupCount() >= 1) {
            return Integer.parseInt(qualityExtractMatcher.group(1));
        }
        throw new ExileParserException(ExileClassStringExtractor.LINE_VALID_BUT_ELEMENT_NOT_FOUND + " Line related: '" + line + "'");
    }

    Optional<Integer> getSellPriceFromLine(String line) {
        Matcher sellPriceExtractMatcher = sellPriceExtractPattern.matcher(line);
        if (sellPriceExtractMatcher.find() && sellPriceExtractMatcher.groupCount() >= 1) {
            return Optional.of(Integer.parseInt(sellPriceExtractMatcher.group(1)));
        }
        return Optional.empty();
    }

    boolean isLineValidClassLine(String line) {
        return isValidLinePattern.matcher(line).matches()
                && !line.startsWith("//")
                && !(line.startsWith("/*") && line.endsWith("*/"));
    }

}
