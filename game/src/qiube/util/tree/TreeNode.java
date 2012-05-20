package qiube.util.tree;

import qiube.exception.TreeException;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.HashMap;
import java.util.Map;

public class TreeNode implements TreeInterface {

    public static final String PATH_SEPARATOR = ".";

    private Map<String, TreeInterface> children;

    /**
     * @return TreeNode
     */
    public static TreeNode create() {
        return new TreeNode();
    }

    /**
     *
     */
    public TreeNode() {
        this.children = new HashMap<String, TreeInterface>();
    }

    /**
     * @return
     */
    public String toString() {
        String output = "[";
        int i = 0;
        for (String key : this.children.keySet()) {
            if (0 != i) {
                output += ", ";
            }
            output += (key + ": " + this.children.get(key));
            i++;
        }
        return output + "]";
    }

    /**
     * @param id
     * @param value
     * @return TreeNode
     */
    @Override
    public TreeInterface set(String id, Object value) {
        TreeLeaf leaf = new TreeLeaf(value);
        this.set(id, leaf);
        return this;
    }

    /**
     * @param id
     * @param child
     * @return TreeNode
     */
    @Override
    public TreeInterface set(String id, TreeInterface child) {
        this.children.put(id, child);
        return this;
    }

    /**
     * @param id
     * @return TreeNode
     * @throws TreeException
     */
    @Override
    public TreeInterface get(String id) throws TreeException {
        return this.get(id.split("\\" + TreeNode.PATH_SEPARATOR));
    }

    private TreeInterface get(String[] path) throws TreeException {
        if (path.length == 0) {
            throw new TreeException("Null path");
        }

        TreeInterface child = this.children.get(path[0]);
        if (null == child) {
            throw new TreeException(String.format("Invalid path. Non-matching element : %s", path[0]));
        }

        if (path.length > 1) {
            if (child.isLeaf()) {
                throw new TreeException(String.format("Invalid path. Element is a leaf : %s", path[0]));
            }

            String childId = "";
            for (int i = 1; i < path.length; i++) {
                childId += (i != 1 ? TreeNode.PATH_SEPARATOR : "") + path[i];
            }

            return child.get(childId);
        } else {
            return child;
        }
    }

    /**
     * @return boolean
     */
    @Override
    public boolean isLeaf() {
        return false;
    }

    /**
     *
     */
    @Override
    public Object value() {
        return this;
    }

    /**
     * @return
     */
    public int length() {
        return this.children.size();
    }

}
