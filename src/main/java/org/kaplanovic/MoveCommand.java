package org.kaplanovic;

public class MoveCommand implements Command {
    private StorageRoom storageRoom;
    private final String containerName;
    private final String otherContainerName;
    private String belowContainerName;

    public MoveCommand(StorageRoom storageRoom, String containerName, String otherContainerName) {
        this.storageRoom = storageRoom;
        this.containerName = containerName;
        this.otherContainerName = otherContainerName;
    }

    @Override
    public void execute() {
        belowContainerName = storageRoom.getBelowContainerName(containerName);
        storageRoom.fetchContainer(containerName);
        storageRoom.fetchContainer(otherContainerName);
        storageRoom.placeOn(containerName, otherContainerName);
    }

    @Override
    public void undo() {
        storageRoom.placeOn(containerName, belowContainerName);
    }

    @Override
    public String toString() {
        return String.format("move %s %s", containerName, otherContainerName);
    }
}
