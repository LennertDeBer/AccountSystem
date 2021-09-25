package za.ac.nwu.as.logic.flow.impl;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import za.ac.nwu.as.logic.flow.impl.acty.FetchAccountTypeFlowImpl;

import static org.junit.Assert.*;

public class FetchAccountTypeFlowImplTest {
private FetchAccountTypeFlowImpl classToTest;
    @Before
    public void setUp() throws Exception {
        classToTest = new FetchAccountTypeFlowImpl(null);
    }

    @After
    public void tearDown() throws Exception {
        classToTest = null;
    }

    //@Ignore pass the error
    @Test
    public void methodToTest() {
        assertTrue(classToTest.methodToTest());
    }
}