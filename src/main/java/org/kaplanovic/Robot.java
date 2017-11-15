package org.kaplanovic;

public class Robot {
    private StorageRoom storageRoom;
    private CommandManager commandManager;

    public Robot(StorageRoom storageRoom) {
        this.storageRoom = storageRoom;
        commandManager = new CommandManager();
    }

    public boolean execute(String commandText) {
        String[] parts = commandText.split("\\s+");
        if (parts.length <= 0) return false;
        if (parts[0].equalsIgnoreCase("move") && parts.length == 3) {
            commandManager.execute(new MoveCommand(storageRoom, parts[1], parts[2]));
            return true;
        }
        if (parts[0].equalsIgnoreCase("fill") && parts.length == 3) {
            commandManager.execute(new FillCommand(storageRoom, parts[1], Integer.valueOf(parts[2])));
            return true;
        }
        if (parts[0].equalsIgnoreCase("undo") && parts.length == 1) {
            commandManager.undo();
            return true;
        }
        if (parts[0].equalsIgnoreCase("redo") && parts.length == 1) {
            commandManager.redo();
            return true;
        }

        return false;
    }
}
