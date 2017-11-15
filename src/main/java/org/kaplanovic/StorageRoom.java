package org.kaplanovic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StorageRoom {
    public static final String FLOOR = "floor";
    public static final String NONE = "none";

    private int floorLimit;
    private int stackLimit;
    private Map<String, Container> allContainers;
    private Map<String, Stack<Container>> containerStacks;
    private ArrayList<Stack<Container>> floor;

    public StorageRoom(int floorLimit, int stackLimit) {
        this.floorLimit = floorLimit;
        this.stackLimit = stackLimit;
        allContainers = new HashMap<>();
        containerStacks = new HashMap<>();
        floor = new ArrayList<>();
    }

    public Container fetchContainer(String name) {
        if (allContainers.containsKey(name))
            return allContainers.get(name);

        Container container = new Container(name);
        allContainers.put(name, container);

        return container;
    }

    public boolean placeOn(String sourceName, String destName) {
        if (!hasContainer(sourceName)) return false;
        if (NONE.equals(destName)) {
            if (!isOnTop(sourceName)) return false;

            Stack<Container> sourceStack = containerStacks.get(sourceName);

            sourceStack.pop();

            if (sourceStack.size() == 0)
                floor.remove(sourceStack);

            return true;
        } else if (FLOOR.equals(destName)) {
            if (hasFilledFloor()) return false;

            Container source = allContainers.get(sourceName);

            Stack<Container> stack = new Stack<>();
            stack.push(source);

            floor.add(stack);
            containerStacks.put(sourceName, stack);

            return true;
        }
        else {
            if (!hasContainer(destName)) return false;
            if (!isOnTop(sourceName)) return false;
            if (!isOnTop(destName)) return false;

            Stack<Container> destStack = containerStacks.get(destName);
            if (destStack.size() >= stackLimit) return false;

            Stack<Container> sourceStack = containerStacks.get(sourceName);

            Container source = sourceStack.pop();
            destStack.push(source);

            containerStacks.put(sourceName, destStack);

            if (sourceStack.size() == 0)
                floor.remove(sourceStack);

            return true;
        }
    }

    public boolean hasFilledFloor() {
        return floor.size() >= floorLimit;
    }

    public boolean isOnTop(String name) {
        if (containerStacks.containsKey(name))
            return containerStacks.get(name).peek().getName().equals(name);
        return false;
    }

    public boolean hasContainer(String name) {
        return allContainers.containsKey(name);
    }

    public String getBelowContainerName(String name) {
        if (!containerStacks.containsKey(name))
            return NONE;

        Stack<Container> stack = containerStacks.get(name);

        if (stack.size() <= 1) return FLOOR;
        return stack.get(stack.size() - 2).getName();
    }
}
