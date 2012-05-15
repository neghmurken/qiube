package qiube.util;

abstract public class BaseService implements ServiceInterface {

    private ServiceContainer parentContainer;

    public void setParentContainer(ServiceContainer parentContainer) {
        this.parentContainer = parentContainer;
    }
}
