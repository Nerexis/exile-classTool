package exile.classTool.service;

public class ExileClassTransformerException extends Exception {
    ExileClassTransformerException(
            @SuppressWarnings("SameParameterValue") /* general mechanism */ String message) {
        super(message);
    }
}
