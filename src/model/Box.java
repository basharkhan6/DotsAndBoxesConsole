package model;

import engine.enumeration.BoxPosition;

import java.util.ArrayList;
import java.util.List;

import static engine.enumeration.BoxPosition.*;

public class Box {

    private final Line top;
    private final Line bottom;
    private final Line left;
    private final Line right;

    public Box(final Line top, final Line bottom, final Line left, final Line right) {
        this.top = top;
        this.bottom = bottom;
        this.left = left;
        this.right = right;
    }

    public Line getTop() {
        return top;
    }

    public Line getBottom() {
        return bottom;
    }

    public Line getLeft() {
        return left;
    }

    public Line getRight() {
        return right;
    }

    public Line getLine(BoxPosition position) {
        if (position == TOP) {
            return top;
        } else if (position == BOTTOM) {
            return bottom;
        } else if (position == LEFT) {
            return left;
        } else {
            return right;
        }
    }

    public List<Line> getLines() {
        List<Line> lines = new ArrayList<>();
        lines.add(top);
        lines.add(bottom);
        lines.add(left);
        lines.add(right);

        return lines;
    }

    @Override
    public String toString() {

        return top.getStart() + "-------" + top.getEnd() + "\n" +
                " |       |" + "\n" +
                " |       |" + "\n" +
                left.getEnd() + "-------" + right.getEnd();
    }
}
