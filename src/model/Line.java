package model;

import exception.line.InvalidLineException;
import exception.line.ReverseLineException;

public class Line {

    private final Dot start;
    private final Dot end;

    public Line(Dot start, Dot end) throws ReverseLineException {
        if (!isInCorrectDirection(start, end)) {
            Dot temp =start;
            start = end;
            end = temp;
        }
        if (!isValidDistance(start, end) || isDiagonal(start, end)) {
            throw new InvalidLineException("Line distance must be 1 in Horizontal or Vertical!!");
        } else {
            this.start = start;
            this.end = end;
        }
    }

    public Dot getStart() {
        return start;
    }

    public Dot getEnd() {
        return end;
    }

    public boolean isDotInsideBoard(int rows, int cols) {
        return start.isDotInsideBoard(rows, cols) && end.isDotInsideBoard(rows, cols);
    }

    public boolean isHorizontal() {
        return this.start.getY() == this.end.getY();
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Line line = (Line) o;
        if (start.equals(line.start) && end.equals(line.end)) {
            return true;
        }
        if (start.equals(line.end) && end.equals(line.start)) {     // also true in reverse dot of a line
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String string;
        if (isHorizontal()) {
            string = start.toString() + "---" + end.toString();
        } else {
            string = start.toString() + "\n |\n" + end.toString();
        }

        return string;
    }

    private boolean isHorizontal(final Dot start, final Dot end) {
        return start.getY() == end.getY();
    }

    private boolean isVertical(final Dot start, final Dot end) {
        return start.getX() == end.getX();
    }

    private boolean isDiagonal(final Dot start, final Dot end) {
        return !isHorizontal(start, end) && !isVertical(start, end);
    }

    private boolean isInCorrectDirection(final Dot start, final Dot end) {
        return isHorizontal(start, end) && start.getX() < end.getX()
                || isVertical(start, end) && start.getY() < end.getY();
    }

    private boolean isValidDistance(final Dot start, final Dot end) {
        return isHorizontal(start, end) && start.getX() == end.getX() - 1
                || isVertical(start, end) && start.getY() == end.getY() - 1;
    }
}
