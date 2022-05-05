package ru.fadesml.di;

import ru.fadesml.di.config.BeanRegistry;
import ru.fadesml.di.core.BeanHolder;
import ru.fadesml.di.services.TestService;

public class Main {
    //required first action
    private static final BeanRegistry registry = BeanRegistry.getInstance();

    public static void main(String[] args) {
        BeanHolder.getBean(TestService.class).test();
    }
}
