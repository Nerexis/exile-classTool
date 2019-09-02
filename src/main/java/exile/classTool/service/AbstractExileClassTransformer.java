package exile.classTool.service;

abstract class AbstractExileClassTransformer<TResultType> {
    public abstract TResultType execute() throws ExileClassTransformerException;
}
