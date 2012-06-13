package quibe.util.tree;

import org.junit.*;

import static org.junit.Assert.*;

import qiube.exception.*;
import qiube.util.tree.*;

import java.io.*;

public class TreeTest {

    protected TreeInterface tree;

    @Before
    public void createTree() {
        this.tree = TreeNode.create()
            .set("key1", true)
            .set("key2", false)
            .set("key3", TreeNode.create()
                .set("subkey1", "lol")
                .set("subkey2", new File(".", "test.text"))
                .set("subkey", 12.54)
            )
            .set("key4", TreeNode.create()
                .set("logger", TreeNode.create()
                    .set("enabled", true)
                )
            )
        ;
    }

    @Test
    public void testGet() throws TreeException {
        assertNotNull("logger is not null", tree.get("key4.logger"));
    }

    @Test(expected=TreeException.class)
    public void testInvalidGet() throws TreeException {
        tree.get("invalid.path");
    }

    @Test
    public void testIsLeaf() throws TreeException {
        assertFalse("root node is not a leaf", tree.isLeaf());
        assertTrue("key1 is a leaf", tree.get("key1").isLeaf());
    }

    @Test
    public void testValue() throws TreeException {
        assertSame("root node value is root", tree.value(), tree);
        assertEquals("key1 equals true", tree.get("key1").value(), true);
        assertEquals("key4.logger.enabled equals true", tree.get("key4.logger.enabled").value(), true);
    }

    @Test
    public void testLength() throws TreeException {
        assertEquals("root node length equals 4", 4, tree.length());
        assertEquals("key2 leaf length equals 0", 0, tree.get("key2").length());
    }

    @Test
    public void testSet() throws TreeException {
        tree.set("key5", 12);
        assertEquals("add the value 12 to key5", 12, tree.get("key5").value());
        tree.set("key1", false);
        assertEquals("change the value of key to false", false, tree.get("key1").value());
    }
}