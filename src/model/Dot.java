package model;

public class Dot {

    private final int x;
    private final int y;

    public Dot(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isDotInsideBoard(int rows, int cols) {
        return x >= 0 && x < cols && y >= 0 && y < rows;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dot dot = (Dot) o;
        return x == dot.x && y == dot.y;
    }

    @Override
    public String toString() {
        return x+""+y;
    }
}
