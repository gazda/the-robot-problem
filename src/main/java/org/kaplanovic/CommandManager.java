package org.kaplanovic;

import java.util.Stack;

public class CommandManager {
    private Stack<Command> undoStack = new Stack<>();
    private Stack<Command> redoStack = new Stack<>();

    public CommandManager() {
    }

    public void execute(Command command) {
        command.execute();
        undoStack.push(command);
        redoStack.clear();
    }

    public void undo() {
        boolean done = undoStack.size() <= 0;
        while (!done) {
            Command command = undoStack.pop();
            command.undo();
            redoStack.push(command);
            done = undoStack.size() <= 0 || !(command instanceof MoveCommand && undoStack.peek() instanceof MoveCommand);
        }
    }

    public void redo() {
        boolean done = redoStack.size() <= 0;
        while (!done) {
            Command command = redoStack.pop();
            command.execute();
            undoStack.push(command);
            done = redoStack.size() <= 0 || !(command instanceof MoveCommand && redoStack.peek() instanceof MoveCommand);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Command command : undoStack) {
            sb.append(command);
            sb.append("\n");
        }
        sb.append("--->\n");
        for (int i = redoStack.size() - 1; i >= 0; i--) {
            Command command = redoStack.get(i);
            sb.append(command);
            sb.append("\n");
        }
        return sb.toString();
    }
}
