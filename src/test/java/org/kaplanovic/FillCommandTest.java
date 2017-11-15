package org.kaplanovic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class FillCommandTest {
    private StorageRoom storageRoom;
    private FillCommand subject;

    @Before
    public void setUp() throws Exception {
        storageRoom = new StorageRoom(10, 10);
        storageRoom.fetchContainer("b10");
        storageRoom.placeOn("b10", StorageRoom.FLOOR);
        subject = new FillCommand(storageRoom, "b10", 100);
    }

    @Test
    public void executeTest() throws Exception {
        subject.execute();
        assertEquals(100, storageRoom.fetchContainer("b10").getFluidContent());
    }

    @Test
    public void undoTest() throws Exception {
        subject.execute();
        subject.undo();
        assertEquals(0, storageRoom.fetchContainer("b10").getFluidContent());
    }

    @Test
    public void toStringTest() throws Exception {
        assertEquals("fill b10 100", subject.toString());
    }

}