package org.kaplanovic;

public interface Command {
    void execute();
    void undo();
    String toString();
}
