package ru.fadesml.di.core;

import ru.fadesml.di.exceptions.BeanException;

import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public final class BeanHolder {
    private static BeanHolder INSTANCE = null;

    private BeanHolder() {}

    public static BeanHolder getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BeanHolder();
        }

        return INSTANCE;
    }

    //qualifier to bean instance map
    private final Map<String, Object> instances = new HashMap<>();

    public void register(String qualifier, Object instance) throws BeanException {
        if (instances.containsKey(qualifier)) throw new BeanException("Bean qualifier already exists!");

        instances.put(qualifier, instance);
    }

    public Object getBean(String qualifier) throws BeanException {

        if (!instances.containsKey(qualifier))  throw new BeanException("Bean does not exists!");

        return instances.get(qualifier);
    }

    @SuppressWarnings("unchecked")
    private static <T> T getBeanINC(String qualifier, Class<T> beanClass) throws BeanException {
        Object beanObj = BeanHolder.getInstance().getBean(qualifier);

        if (beanClass.isAssignableFrom(beanObj.getClass())) {
            return (T) beanObj;
        } else {
            throw new BeanException("Can't cast bean: '" + qualifier + "', to type: '" + beanClass.getName() + "'");
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String qualifier, Class<T> beanClass) {
        try {
            return getBeanINC(qualifier, beanClass);
        } catch (BeanException e) {
            return (T) Proxy.newProxyInstance(
                    BeanHolder.class.getClassLoader(),
                    new Class[] { beanClass },
                    ((proxy, method, args) -> {
                        Object original = getBeanINC(qualifier, beanClass);

                        Method originalMethod = original.getClass().getMethod(method.getName());

                        return originalMethod.invoke(original, args);
                    })
            );
        }
    }

    public static <T> T getBean(Class<T> beanClass) {
        return getBean(beanClass.getName(), beanClass);
    }
}
