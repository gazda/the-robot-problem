package org.kaplanovic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ContainerTest {
    private Container subject;

    @Before
    public void setUp() throws Exception {
        subject = new Container("b10");
    }

    @Test
    public void getName() throws Exception {
        assertEquals("b10", subject.getName());
    }

    @Test
    public void getFluidContent() throws Exception {
        assertEquals(0, subject.getFluidContent());
    }

    @Test
    public void addFluid() throws Exception {
        subject.addFluid(20);
        subject.removeFluid(20);
        assertEquals(0, subject.getFluidContent());
    }

}