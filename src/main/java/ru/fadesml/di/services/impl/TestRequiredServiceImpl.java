package ru.fadesml.di.services.impl;

import ru.fadesml.di.services.TestRequiredService;

public class TestRequiredServiceImpl implements TestRequiredService {
    @Override
    public void requiredTest() {
        System.out.println("required works!");
    }
}
