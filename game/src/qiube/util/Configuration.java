package qiube.util;

import java.io.File;
import java.io.IOException;
import qiube.exception.ConfigurationException;

public class Configuration {

    public static final String FILE_NAME_PATTERN = "config-%s.xml";
    public static final String FILE_PATH = "data";

    protected String environment;
    protected String filename;

    public Configuration() throws ConfigurationException {
        this("main");
    }

    /**
     *
     * @param environment
     * @throws ConfigurationException
     */
    public Configuration(String environment) throws ConfigurationException {
        this.environment = environment;
        String filename = String.format(FILE_NAME_PATTERN, this.environment);
        this.filename = String.format("%s/%s", FILE_PATH, filename);
        this.load();
    }

    /**
     *
     * @throws ConfigurationException
     */
    public void load() throws ConfigurationException {
        File configFile = new File(".", this.filename);

        if (!configFile.exists()) {
            try {
                if (!configFile.createNewFile()) {
                    throw new ConfigurationException(String.format("Config file (%s) already exists", this.filename));
                }
            } catch (IOException exception) {
                throw new ConfigurationException(
                    String.format(
                        "Unable to create config file (%s) : %s",
                        this.filename,
                        exception.getMessage()
                    ),
                    exception
                );
            }
        }
    }


}
