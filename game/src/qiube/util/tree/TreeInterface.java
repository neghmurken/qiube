package qiube.util.tree;

import qiube.exception.TreeException;

public interface TreeInterface {

    public TreeInterface set(String id, Object value);

    public TreeInterface set(String id, TreeInterface child);

    public TreeInterface get(String id) throws TreeException;

    public boolean isLeaf();

    public Object value();

    public int length();
}
