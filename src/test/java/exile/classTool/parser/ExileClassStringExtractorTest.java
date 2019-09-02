package exile.classTool.parser;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ExileClassStringExtractorTest {

    @Test
    void isLineValidClassLine() {
        ExileClassStringExtractor exileClassStringExtractor = new ExileClassStringExtractor();

        String testLine1 = "class Exile_Uniform_BambiOverall\t\t\t\t{ quality = 1; price = 1; sellPrice = 1; };";
        assertTrue(exileClassStringExtractor.isLineValidClassLine(testLine1));

        String testLine2 = "class Exile_Uniform_BambiOverall\t\t\t\t{ price = 1; quality = 1; sellPrice = 1; };";
        assertTrue(exileClassStringExtractor.isLineValidClassLine(testLine2));

        String testLine3 = "class } price = 1; };";
        assertFalse(exileClassStringExtractor.isLineValidClassLine(testLine3));

        assertFalse(exileClassStringExtractor.isLineValidClassLine("class price = 1;"));

        assertFalse(exileClassStringExtractor.isLineValidClassLine("class { price = 1; };"));

        String testLine4 = "//class Exile_Uniform_BambiOverall\t\t\t\t{ quality = 1; price = 1; sellPrice = 1; };";
        assertFalse(exileClassStringExtractor.isLineValidClassLine(testLine4));

        String testLine5 = "/*class Exile_Uniform_BambiOverall\t\t\t\t{ quality = 1; price = 1; sellPrice = 1; };*/";
        assertFalse(exileClassStringExtractor.isLineValidClassLine(testLine5));

        @SuppressWarnings("SpellCheckingInspection")
        String testLine6 = "class rhs_bmd4_vdv { quality = 5; price = 270000; }; // ppk 30mm i dzialo niszczyciel swiatow :D ";
        assertTrue(exileClassStringExtractor.isLineValidClassLine(testLine6));
    }
}