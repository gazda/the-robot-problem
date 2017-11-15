package org.kaplanovic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CommandManagerTest {
    private StorageRoom storageRoom;
    private CommandManager subject;

    @Before
    public void setUp() throws Exception {
        storageRoom = new StorageRoom(10, 10);
        subject = new CommandManager();
        subject.execute(new MoveCommand(storageRoom, "b10", StorageRoom.FLOOR));
        subject.execute(new MoveCommand(storageRoom, "b20", StorageRoom.FLOOR));
        subject.execute(new MoveCommand(storageRoom, "b30", StorageRoom.FLOOR));
        subject.execute(new FillCommand(storageRoom, "b10", 100));
        subject.execute(new FillCommand(storageRoom, "b20", 100));
        subject.execute(new MoveCommand(storageRoom, "b20", "b10"));
    }

    @Test
    public void executeTest() throws Exception {
        subject.execute(new MoveCommand(storageRoom, "b30", "b20"));
        String expected =
                "move b10 floor\n" +
                "move b20 floor\n" +
                "move b30 floor\n" +
                "fill b10 100\n" +
                "fill b20 100\n" +
                "move b20 b10\n" +
                "move b30 b20\n" +
                "--->\n";
        assertEquals(expected, subject.toString());
    }

    @Test
    public void undoTest() throws Exception {
        subject.execute(new MoveCommand(storageRoom, "b30", "b20"));
        subject.undo();
        String expected =
                "move b10 floor\n" +
                "move b20 floor\n" +
                "move b30 floor\n" +
                "fill b10 100\n" +
                "fill b20 100\n" +
                "--->\n" +
                "move b20 b10\n" +
                "move b30 b20\n";
        assertEquals(expected, subject.toString());
    }

    @Test
    public void redoTest() throws Exception {
        subject.execute(new MoveCommand(storageRoom, "b30", "b20"));
        subject.undo();
        subject.redo();
        String expected =
                "move b10 floor\n" +
                "move b20 floor\n" +
                "move b30 floor\n" +
                "fill b10 100\n" +
                "fill b20 100\n" +
                "move b20 b10\n" +
                "move b30 b20\n" +
                "--->\n";
        assertEquals(expected, subject.toString());
    }

    @Test
    public void clearsRedoStack() throws Exception {
        subject.undo();
        subject.execute(new FillCommand(storageRoom, "b30", 100));
        String expected =
                "move b10 floor\n" +
                "move b20 floor\n" +
                "move b30 floor\n" +
                "fill b10 100\n" +
                "fill b20 100\n" +
                "fill b30 100\n" +
                "--->\n";
        assertEquals(expected, subject.toString());
    }

    @Test
    public void toStringTest() throws Exception {
        String expected =
                "move b10 floor\n" +
                "move b20 floor\n" +
                "move b30 floor\n" +
                "fill b10 100\n" +
                "fill b20 100\n" +
                "move b20 b10\n" +
                "--->\n";
        assertEquals(expected, subject.toString());
    }

}