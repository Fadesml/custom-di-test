package ru.fadesml.di.config;

import ru.fadesml.di.core.BeanHolder;
import ru.fadesml.di.exceptions.BeanException;
import ru.fadesml.di.services.TestRequiredService;
import ru.fadesml.di.services.TestService;
import ru.fadesml.di.services.impl.TestRequiredServiceImpl;
import ru.fadesml.di.services.impl.TestServiceImpl;

public class BeanRegistry {
    private static BeanRegistry INSTANCE = null;
    private final BeanHolder beanHolder = BeanHolder.getInstance();

    private BeanRegistry() {
        try {
            init();
        } catch (BeanException e) {
            e.printStackTrace();
        }
    }

    public static BeanRegistry getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new BeanRegistry();
        }

        return INSTANCE;
    }

    public void init() throws BeanException {
        beanHolder.register(TestService.class.getName(), new TestServiceImpl());
        beanHolder.register(TestRequiredService.class.getName(), new TestRequiredServiceImpl());
    }
}
