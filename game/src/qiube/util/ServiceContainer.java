package qiube.util;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class ServiceContainer {

    protected Map<String, ServiceInterface> services;
    protected Configuration configuration;

    /**
     *
     */
    public ServiceContainer(Configuration configuration) {
        this.services = new HashMap<String, ServiceInterface>();
        this.configuration = configuration;
    }

    /**
     * @param service
     */
    public void register(ServiceInterface service) {
        this.services.put(service.getName(), service);
        service.setParentContainer(this);
    }

    /**
     * @param id
     * @return
     */
    public ServiceInterface get(String id) {
        return this.services.get(id);
    }

    /**
     * @param id
     * @return
     */
    public boolean has(String id) {
        return this.services.containsKey(id);
    }
}
