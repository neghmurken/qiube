package qiube.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import qiube.exception.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import qiube.util.tree.*;

public class Configuration {

    public static final String FILE_NAME_PATTERN = "config-%s.xml";
    public static final String FILE_PATH = "data";

    protected String environment;
    protected String filename;
    protected TreeInterface tree;

    /**
     * @throws ConfigurationException
     */
    public Configuration() throws ConfigurationException {
        this("main");
    }

    /**
     * @param environment
     * @throws ConfigurationException
     */
    public Configuration(String environment) throws ConfigurationException {
        this.environment = environment;
        String filename = String.format(FILE_NAME_PATTERN, this.environment);
        this.filename = String.format("%s/%s", FILE_PATH, filename);
        this.tree = new TreeNode();
        this.load();
    }

    /**
     * @throws ConfigurationException
     */
    public void load() throws ConfigurationException {
        File configFile = new File(".", this.filename);

        if (!configFile.exists()) {
            try {
                if (!configFile.createNewFile()) {
                    throw new ConfigurationException(String.format("Config file (%s) already exists", this.filename));
                }
                FileOutputStream fileOutputStream = new FileOutputStream(configFile);
                fileOutputStream.write(Configuration.bootstrapXML().getBytes());
                fileOutputStream.flush();
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

        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = documentBuilderFactory.newDocumentBuilder();
            Document document = builder.parse(configFile);

            this.parse(document);
        } catch (Exception exception) {
            throw new ConfigurationException(
                String.format(
                    "Unable to parse config file (%s) : %s",
                    this.filename,
                    exception.getMessage()
                ),
                exception
            );
        }

    }

    /**
     * @param document
     * @throws Exception
     */
    public void parse(Document document) throws Exception {
        Element root = document.getDocumentElement();
        if (!root.getTagName().equals("configuration")) {
            throw new Exception(String.format("Root element must be 'configuration'. Got %s", root.getTagName()));
        }

        NodeList services = root.getElementsByTagName("service");
        if (0 != services.getLength()) {
            for (int i = 0; i < services.getLength(); i++) {
                Element service = (Element) services.item(i);
                TreeInterface serviceConfigTree = this.parseServiceConfiguration(service);
                if (null != serviceConfigTree) {
                    this.tree.set(service.getAttribute("name"), serviceConfigTree);
                }
            }
        }
    }

    /**
     * @param element
     * @return
     */
    public TreeInterface parseServiceConfiguration(Element element) {
        TreeInterface configTree = null;

        if (element.getChildNodes().getLength() > 1) {
            for (int i = 0; i < element.getChildNodes().getLength(); i++) {
                Node child = element.getChildNodes().item(i);

                if (child.getNodeType() == Node.ELEMENT_NODE) {
                    if (null == configTree) {
                        configTree = new TreeNode();
                    }

                    Element childElement = (Element) child;
                    TreeInterface value = this.parseServiceConfiguration(childElement);
                    if (null != value) {
                        configTree.set(childElement.getTagName(), value);
                    }
                }
            }
        } else if (element.getChildNodes().getLength() == 1) {
            configTree = new TreeLeaf(Configuration.parseValue(element.getTextContent()));
        }
        return configTree;
    }

    /**
     *
     * @param raw
     * @return
     */
    public static Object parseValue(String raw) {
        String loweredRaw = raw.toLowerCase();

        if(loweredRaw.equals("true") || loweredRaw.equals("on"))
            return true;

        else if(loweredRaw.equals("false") || loweredRaw.equals("off"))
            return false;

        else if(loweredRaw.equals("null") || loweredRaw.isEmpty())
            return null;

        return raw;
    }

    /**
     * @return
     */
    public static String bootstrapXML() {
        return "<?xml version=\"1.1\" ?>" +
            "<tree></tree>";
    }
}
