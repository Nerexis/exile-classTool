package exile.classTool.service;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

abstract class AbstractFileExporter<TDataToExport> {
    public abstract void export(@NotNull String outputPathName, TDataToExport dataToExport) throws IOException;
}
