package exile.classTool.parser;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ExileClassParserTest {

    @Test
    void parse() {
        List<String> linesToParse = new ArrayList<>();
        linesToParse.add("class rhs_m4_carryHandle { price = 12; quality = 3; }; ");
        linesToParse.add("class rhs_ak47_grip { price = 1234; quality = 3; sellPrice = 123; }; ");
        linesToParse.add("class rhs_ak47_grip { price = 1234; quality = 3; sellPrice = 12333; }; ");

        ExileClassParser exileClassParser = new ExileClassParser();
        exileClassParser.parse(linesToParse);
    }
}