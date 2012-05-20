package qiube.util.tree;

import qiube.exception.*;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TreeLeaf implements TreeInterface {

    private Object value;

    /**
     *
     * @param value
     */
    public TreeLeaf(Object value) {
        this.value = value;
    }

    /**
     *
     * @return
     */
    public String toString() {
        return this.value.toString();
    }

    /**
     *
     * @param id
     * @param value
     * @return
     */
    @Override
    public TreeInterface set(String id, Object value) {
        return null;
    }

    /**
     *
     * @param id
     * @param children
     * @return
     */
    @Override
    public TreeInterface set(String id, TreeInterface children) {
        return null;
    }

    /**
     *
     * @param id
     * @return
     */
    @Override
    public TreeInterface get(String id) throws TreeException {
        throw new TreeException("This tree is a leaf");
    }

    /**
     *
     * @return
     */
    @Override
    public boolean isLeaf() {
        return true;
    }

    /**
     *
     * @return
     */
    @Override
    public Object value() {
        return this.value;
    }

    /**
     *
     * @return
     */
    public int length() {
        return 0;
    }
}
