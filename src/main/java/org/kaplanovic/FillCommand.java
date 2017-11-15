package org.kaplanovic;

public class FillCommand implements Command {
    private StorageRoom storageRoom;
    private final String containerName;
    private final int fluidContent;

    public FillCommand(StorageRoom storageRoom, String containerName, int fluidContent) {
        this.storageRoom = storageRoom;
        this.containerName = containerName;
        this.fluidContent = fluidContent;
    }

    @Override
    public void execute() {
        if (storageRoom.isOnTop(containerName)) {
            Container container = storageRoom.fetchContainer(containerName);
            container.addFluid(fluidContent);
        }
    }

    @Override
    public void undo() {
        if (storageRoom.isOnTop(containerName)) {
            Container container = storageRoom.fetchContainer(containerName);
            container.removeFluid(fluidContent);
        }
    }

    @Override
    public String toString() {
        return String.format("fill %s %d", containerName, fluidContent);
    }
}
