package qiube.util.configuration;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class TreeLeaf implements TreeInterface {

    private Object value;

    public TreeLeaf(Object value) {
        this.value = value;
    }

    @Override
    public TreeInterface set(String id, Object value) {
        throw new NotImplementedException();
    }

    @Override
    public TreeInterface set(String id, TreeInterface children) {
        throw new NotImplementedException();
    }

    @Override
    public TreeInterface get(String id) {
        throw new NotImplementedException();
    }

    @Override
    public boolean isLeaf() {
        return true;
    }

    @Override
    public Object value() {
        return this.value;
    }
}
