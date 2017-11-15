package org.kaplanovic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MoveCommandTest {
    private StorageRoom storageRoom;
    private MoveCommand subject;

    @Before
    public void setUp() throws Exception {
        storageRoom = new StorageRoom(10, 10);
        new MoveCommand(storageRoom, "b10", "floor").execute();
        new MoveCommand(storageRoom, "b20", "floor").execute();
        subject = new MoveCommand(storageRoom, "b10", "b20");
    }

    @Test
    public void executeTest() throws Exception {
        subject.execute();
        assertEquals("b20", storageRoom.getBelowContainerName("b10"));
    }

    @Test
    public void executeOneTest() throws Exception {
        storageRoom = new StorageRoom(10, 10);
        subject = new MoveCommand(storageRoom, "b10", "b20");
        subject.execute();
        assertEquals("none", storageRoom.getBelowContainerName("b10"));
    }

    @Test
    public void undoTest() throws Exception {
        subject.execute();
        subject.undo();
        assertEquals("floor", storageRoom.getBelowContainerName("b10"));
    }

    @Test
    public void undoOneTest() throws Exception {
        storageRoom = new StorageRoom(10, 10);
        subject = new MoveCommand(storageRoom, "b10", "floor");
        subject.execute();
        subject.undo();
        assertEquals("floor", storageRoom.getBelowContainerName("b10"));
    }

    @Test
    public void toStringTest() throws Exception {
        assertEquals("move b10 b20", subject.toString());
    }

}