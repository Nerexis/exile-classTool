package exile.classTool.model;

import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.logging.Logger;

@SuppressWarnings("unused") // model
public final class ExileClass implements Comparable<ExileClass> {

    private static final Logger logger = Logger.getLogger(ExileClass.class.getSimpleName());
    private final String className;

    @NotNull
    private final ExileClassProperties properties;

    public ExileClass(@NotNull String className, int quality, int price, Integer sellPrice) {
        this.className = className;
        this.properties = new ExileClassProperties(quality, price, sellPrice);
        logger.finest("constructor: " + this.toString());
    }

    public ExileClass(String classNameFromLine) {
        this(classNameFromLine, 0, 0, null);
    }

    public String getClassName() {
        return className;
    }

    @Override
    public String toString() {
        return "ExileClass{" +
                "className='" + className + '\'' +
                ", properties=" + properties +
                '}';
    }

    @NotNull
    public ExileClassProperties getProperties() {
        return properties;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExileClass that = (ExileClass) o;
        return className.equals(that.className);
    }

    @Override
    public int hashCode() {
        return Objects.hash(className);
    }

    @Override
    public int compareTo(@NotNull ExileClass o) {
        return this.getClassName().compareTo(o.className);
    }
}
