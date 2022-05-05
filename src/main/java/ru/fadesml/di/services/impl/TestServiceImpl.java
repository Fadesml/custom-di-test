package ru.fadesml.di.services.impl;

import ru.fadesml.di.core.BeanHolder;
import ru.fadesml.di.services.TestRequiredService;
import ru.fadesml.di.services.TestService;

public class TestServiceImpl implements TestService {
    private final TestRequiredService requiredService = BeanHolder.getBean(TestRequiredService.class);

    @Override
    public void test() {
        System.out.println("Test method from TestServiceImpl");
        requiredService.requiredTest();
    }

    @Override
    public void example(String arg) {
        System.out.println("Example method from TestServiceImpl with argument: " + arg);
    }
}
