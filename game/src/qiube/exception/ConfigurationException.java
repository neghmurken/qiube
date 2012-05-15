package qiube.exception;

public class ConfigurationException extends Exception {

    public ConfigurationException(String explanation) {
        super(explanation);
    }

    public ConfigurationException(String explanation, Throwable cause) {
        super(explanation, cause);
    }
}
