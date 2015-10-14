package com.transaction.save.example;

import org.junit.Before;
import org.mockito.MockitoAnnotations;

public abstract class BaseTestClass {
    @Before
    public void initMocks() throws Exception {
        MockitoAnnotations.initMocks(this);
    }
}
