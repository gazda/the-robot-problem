package org.kaplanovic;

public class Container {
    private String name;
    private int fluidContent;

    public Container(String name) {
        this.name = name;
        this.fluidContent = 0;
    }

    public String getName() {
        return name;
    }

    public int getFluidContent() {
        return fluidContent;
    }

    public void addFluid(int content) {
        fluidContent += content;
    }

    public void removeFluid(int content) {
        fluidContent -= content;
    }
}
