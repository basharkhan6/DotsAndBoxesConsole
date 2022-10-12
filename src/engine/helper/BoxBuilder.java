package engine.helper;

import engine.enumeration.BoxPosition;
import model.Box;
import model.Dot;
import model.Line;

import java.util.HashMap;
import java.util.Map;

import static engine.enumeration.BoxPosition.*;

public class BoxBuilder {

    public Map<BoxPosition, Box> buildBoxes(Line centerLine, int row, int col) {  // max 2 box from centerLine
        Map<BoxPosition, Box> boxes = new HashMap<>();

        if (centerLine.isHorizontal()) {
            Box topBox = buildTopBox(centerLine, 0);
            Box bottomBox = buildBottomBox(centerLine, row);

            if (topBox != null) {
                boxes.put(TOP, topBox);
            }
            if (bottomBox != null) {
                boxes.put(BOTTOM, bottomBox);
            }

            return boxes;
        } else {
            Box leftBox = buildLeftBox(centerLine, 0);
            Box rightBox = buildRightBox(centerLine, col);

            if (leftBox != null) {
                boxes.put(LEFT, leftBox);
            }
            if (rightBox != null) {
                boxes.put(RIGHT, rightBox);
            }

            return boxes;
        }
    }


    private Box buildTopBox(Line bottomLine, int firstY) {
        int topY = bottomLine.getStart().getY() - 1;
        if (topY >= firstY) {
            Dot topStart = new Dot(bottomLine.getStart().getX(), topY);
            Dot topEnd = new Dot(bottomLine.getEnd().getX(), topY);

            Line topLine = new Line(topStart, topEnd);
            Line rightLine = new Line(topLine.getEnd(), bottomLine.getEnd());
            Line leftLine = new Line(topLine.getStart(), bottomLine.getStart());

            return new Box(topLine, bottomLine, leftLine, rightLine);
        }
        return null;
    }


    private Box buildBottomBox(Line topLine, int lastY) {
        int bottomY = topLine.getStart().getY() + 1;
        if (bottomY < lastY) {
            Dot bottomStart = new Dot(topLine.getStart().getX(), bottomY);
            Dot bottomEnd = new Dot(topLine.getEnd().getX(), bottomY);

            Line bottomLine = new Line(bottomStart, bottomEnd);
            Line rightLine = new Line(topLine.getEnd(), bottomLine.getEnd());
            Line leftLine = new Line(topLine.getStart(), bottomLine.getStart());

            return new Box(topLine, bottomLine, leftLine, rightLine);
        }
        return null;
    }

    private Box buildLeftBox(Line rightLine, int firstX) {
        int leftX = rightLine.getStart().getX() - 1;

        if (leftX >= firstX) {
            Dot leftStart = new Dot(leftX, rightLine.getStart().getY());
            Dot leftEnd = new Dot(leftX, rightLine.getEnd().getY());

            Line leftLine = new Line(leftStart, leftEnd);
            Line topLine = new Line(leftLine.getStart(), rightLine.getStart());
            Line bottomLine = new Line(leftLine.getEnd(), rightLine.getEnd());

            return new Box(topLine, bottomLine, leftLine, rightLine);
        }
        return null;
    }

    private Box buildRightBox(Line leftLine, int lastX) {
        int rightX = leftLine.getStart().getX() + 1;

        if (rightX < lastX) {
            Dot rightStart = new Dot(rightX, leftLine.getStart().getY());
            Dot rightEnd = new Dot(rightX, leftLine.getEnd().getY());

            Line rightLine = new Line(rightStart, rightEnd);
            Line topLine = new Line(leftLine.getStart(), rightLine.getStart());
            Line bottomLine = new Line(leftLine.getEnd(), rightLine.getEnd());

            return new Box(topLine, bottomLine, leftLine, rightLine);
        }
        return null;
    }
}
