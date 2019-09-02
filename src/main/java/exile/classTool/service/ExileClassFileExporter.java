package exile.classTool.service;

import exile.classTool.model.ExileClass;
import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashSet;
import java.util.TreeSet;
import java.util.logging.Logger;

public class ExileClassFileExporter extends AbstractFileExporter<HashSet<ExileClass>> {

    private static final Logger logger = Logger.getLogger(ExileClassFileExporter.class.getSimpleName());

    public void export(@NotNull String outputPathName, HashSet<ExileClass> dataToExport) throws IOException {
        logger.info("Exporting to " + outputPathName + ", data count: " + dataToExport.size());

        File outputFile = new File(outputPathName);
        if (outputFile.exists() && !outputFile.delete())
            throw new RuntimeException("Couldn't delete output file before export");

        FileWriter fileWriter = new FileWriter(outputPathName, false);
        int wroteCount = 0;

        TreeSet<ExileClass> sortedDataToExport = new TreeSet<>(dataToExport);

        for (ExileClass exileClass : sortedDataToExport) {
            fileWriter.write(getLine(exileClass) + System.lineSeparator());
            logger.finest("wrote line" + wroteCount);
            wroteCount++;
        }
        logger.finest("closing file");
        fileWriter.close();
        logger.info("Export to " + outputPathName + " done, wrote " + wroteCount + " lines");
    }

    private String getLine(ExileClass exileClass) {
        return "class " + exileClass.getClassName() + " { "
                + "price = " + exileClass.getProperties().getPrice() + "; "
                + "quality = " + exileClass.getProperties().getQuality() + "; "
                + (exileClass.getProperties().getSellPrice().isPresent() ? ("sellPrice = " + exileClass.getProperties().getSellPrice().get() + "; ") : "")
                + "};";
    }
}
