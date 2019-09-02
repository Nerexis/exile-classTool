package exile.classTool;

import exile.classTool.model.ExileClass;
import exile.classTool.parser.ExileClassParser;
import exile.classTool.service.ApplyDiffTransformer;
import exile.classTool.service.ExileClassFileExporter;
import exile.classTool.service.ExileClassTransformerException;
import exile.classTool.service.RawLinesLoader;
import org.apache.commons.cli.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.logging.LogManager;
import java.util.logging.Logger;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) {
        try {
            LogManager.getLogManager().readConfiguration(Main.class.getClassLoader().getResourceAsStream("logging.properties"));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }

        Options options = new Options();

        Option input = new Option("i", "input", true, "input file path");
        input.setRequired(true);
        options.addOption(input);

        Option diffFile = new Option("d", "diff", true, "difference file / update statements");
        diffFile.setRequired(true);
        options.addOption(diffFile);

        Option output = new Option("o", "output", true, "output file");
        output.setRequired(true);
        options.addOption(output);

        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();
        CommandLine cmd;

        try {
            cmd = parser.parse(options, args);

            String inputFilePath = cmd.getOptionValue(input.getLongOpt());
            String outputFilePath = cmd.getOptionValue(output.getLongOpt());
            String diffFilePath = cmd.getOptionValue(diffFile.getLongOpt());

            logger.info("INPUT: " + inputFilePath);
            logger.info("DIFF:" + diffFilePath);
            logger.info("OUTPUT: " + outputFilePath);

            logger.info("Loading raw input data...");
            RawLinesLoader rawLinesLoader = new RawLinesLoader();
            List<String> inputRawLines = rawLinesLoader.loadRawLines(inputFilePath);
            List<String> diffRawLines = rawLinesLoader.loadRawLines(diffFilePath);
            logger.info("Input raw lines: " + inputRawLines.size());
            logger.info("Diff raw lines: " + diffRawLines.size());

            logger.info("Parsing input data...");
            ExileClassParser exileClassParser = new ExileClassParser();
            HashSet<ExileClass> inputClasses = exileClassParser.parse(inputRawLines);
            HashSet<ExileClass> diffClasses = exileClassParser.parse(diffRawLines);
            logger.info("Parsed input data!");
            logger.info("Input classes count:" + inputClasses.size());
            logger.info("Diff classes count: " + diffClasses.size());

            logger.info("Preparing transformers execution...");
            ApplyDiffTransformer applyDiffTransformer = new ApplyDiffTransformer(inputClasses, diffClasses);

            logger.info("Executing transformers...");
            HashSet<ExileClass> applyDiffTransformerResult = applyDiffTransformer.execute();

            logger.info("Saving output data to " + outputFilePath);
            ExileClassFileExporter exileClassFileExporter = new ExileClassFileExporter();
            exileClassFileExporter.export(outputFilePath, applyDiffTransformerResult);
            logger.info("Done!");

        } catch (ExileClassTransformerException | IOException e) {
            logger.severe("There was problem during transformer process execution: " + e.getMessage());
            e.printStackTrace();
            System.exit(1);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            logger.severe(e.getMessage());
            logger.severe(Arrays.toString(e.getStackTrace()));
            formatter.printHelp("utility-name", options);
            System.exit(1);
        }
    }
}
