package exile.classTool.service;

import exile.classTool.model.ExileClass;
import org.jetbrains.annotations.NotNull;

import java.util.HashSet;
import java.util.logging.Logger;

public class ApplyDiffTransformer extends AbstractExileClassTransformer<HashSet<ExileClass>> {

    private static final Logger logger = Logger.getLogger(ApplyDiffTransformer.class.getSimpleName());
    private static final String INPUT_DATA_INVALID_EXCEPTION_MSG = "Input data is null, stopping execution";
    private final HashSet<ExileClass> input;
    private final HashSet<ExileClass> diffSet;

    public ApplyDiffTransformer(HashSet<ExileClass> input, HashSet<ExileClass> diffSet) {
        logger.finest("constructor");
        this.input = input;
        this.diffSet = diffSet;
    }

    private boolean validateInputData() {
        logger.finest("validating input data");
        return input != null && diffSet != null;
    }

    @Override
    public HashSet<ExileClass> execute() throws ExileClassTransformerException {
        logger.finest("entered execute");
        if (!validateInputData()) {
            logger.info(INPUT_DATA_INVALID_EXCEPTION_MSG);
            throw new ExileClassTransformerException(INPUT_DATA_INVALID_EXCEPTION_MSG);
        }
        logger.finest("data ok, starting transformation");
        HashSet<ExileClass> output = transform();
        return new HashSet<>(output);
    }

    @NotNull
    private HashSet<ExileClass> transform() {
        logger.finest("entered transform, input count " + input.size() + " diff count " + diffSet.size());
        HashSet<ExileClass> output = new HashSet<>(diffSet);
        output.addAll(input);
        return output;
    }
}
