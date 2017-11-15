package org.kaplanovic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RobotTest {
    private StorageRoom storageRoom;
    private Robot subject;

    @Before
    public void setUp() throws Exception {
        storageRoom = new StorageRoom(10, 10);
        subject = new Robot(storageRoom);
    }

    @Test
    public void executeEmpty() throws Exception {
        assertFalse(subject.execute(""));
    }

    @Test
    public void executeMove() throws Exception {
        assertTrue(subject.execute("move b1 b2"));
    }

    @Test
    public void executeFill() throws Exception {
        assertTrue(subject.execute("fill b1 10"));
    }

    @Test
    public void executeUndo() throws Exception {
        assertTrue(subject.execute("undo"));
    }

    @Test
    public void executeRedo() throws Exception {
        assertTrue(subject.execute("redo"));
    }

    @Test
    public void executeBad() throws Exception {
        assertFalse(subject.execute("bad"));
    }

}