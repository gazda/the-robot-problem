package org.kaplanovic;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class StorageRoomTest {
    private StorageRoom subject;

    @Before
    public void setUp() throws Exception {
        subject = new StorageRoom(10, 10);
    }

    @Test
    public void fetchContainer() throws Exception {
        Container container = subject.fetchContainer("name");
        Container otherContainer = subject.fetchContainer("name");
        assertSame(container, otherContainer);
    }

    @Test
    public void placeOn() throws Exception {
        subject.fetchContainer("name");
        assertFalse(subject.isOnTop("name"));
        assertTrue(subject.placeOn("name", StorageRoom.FLOOR));
        assertTrue(subject.isOnTop("name"));
    }

    @Test
    public void hasFilledFloor() throws Exception {
        StorageRoom subject = new StorageRoom(1, 10);
        subject.fetchContainer("name");
        assertFalse(subject.hasFilledFloor());
        subject.placeOn("name", StorageRoom.FLOOR);
        assertTrue(subject.hasFilledFloor());
    }

    @Test
    public void hasContainer() throws Exception {
        assertFalse(subject.hasContainer("name"));
        subject.fetchContainer("name");
        assertTrue(subject.hasContainer("name"));
    }

    @Test
    public void getBelowContainerName() throws Exception {
        subject.fetchContainer("name");
        assertEquals(StorageRoom.NONE, subject.getBelowContainerName("name"));
        subject.placeOn("name", StorageRoom.FLOOR);
        assertEquals(StorageRoom.FLOOR, subject.getBelowContainerName("name"));
        subject.fetchContainer("name2");
        subject.placeOn("name2", StorageRoom.FLOOR);
        subject.placeOn("name", "name2");
        assertEquals("name2", subject.getBelowContainerName("name"));
    }

}