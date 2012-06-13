package quibe.util;

import org.junit.*;
import qiube.exception.*;
import qiube.util.*;

import static org.junit.Assert.*;

public class ConfigurationTest {

    @Test
    public void testNewConfiguration() throws ConfigurationException {
        Configuration configuration = new Configuration("test");
    }

    @Test
    public void testGet() throws ConfigurationException {
        Configuration configuration = new Configuration("main");

        assertEquals("event-manager.log equals true", true, configuration.get("event-manager.log"));
        assertEquals("default value false for event-manager.dispatch is returned", false, configuration.get("event-manager.dispatch", false));
    }

}
