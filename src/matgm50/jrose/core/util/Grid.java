package matgm50.jrose.core.util;

import org.joml.Vector2i;

import java.util.ArrayList;
import java.util.List;

public class Grid<T> {

    private List<T> elements;
    public final int width, height, size;

    public Grid(int width, int height) {

        this.width = width;
        this.height = height;
        size = width * height;

        elements = new ArrayList<>();
        for(int i = 0; i < size; i++) { elements.add(null); }

    }

    public void setElement(T element, int x, int y) { elements.set(getIndexFromXY(x, y), element); }

    public T getElement(int x, int y) { return elements.get(getIndexFromXY(x, y)); }

    public int findElement(T element) { return elements.indexOf(element); }

    public int getIndexFromXY(int x, int y) { return ((width * y) + x); }

    public int getXFromIndex(int index) { return index % width; }

    public int getYFromIndex(int index) {return (index - getXFromIndex(index)) % width; }

}
