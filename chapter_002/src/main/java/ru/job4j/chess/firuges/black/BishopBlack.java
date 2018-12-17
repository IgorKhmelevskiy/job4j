package ru.job4j.chess.firuges.black;

import ru.job4j.chess.exceptions.ImpossibleMoveException;
import ru.job4j.chess.firuges.Cell;
import ru.job4j.chess.firuges.Figure;


/**
 *
 * @author Igor Khmelevskiy (igorkkhm@gmail.com)
 * @version $Id$
 * @since 0.1
 */
public class BishopBlack implements Figure {
    private final Cell position;


    public BishopBlack(final Cell position) {
        this.position = position;
    }

    @Override
    public Cell position() {
        return this.position;
    }

    @Override
    public Cell[] way(Cell source, Cell dest) throws ImpossibleMoveException {
        if (this.movePossible(source, dest)) {
            Cell[] cells = new Cell[this.steps(source, dest)];
            for (int i = 0; i < cells.length; i++) {
                if (source.x < dest.x || source.y < dest.y) {
                    cells[i] = Cell.Cell(source.x - 1 - i, source.y - 1 - i);
                } else {
                    cells[i] = new Cell(source.x - 1 - i, source.y - 1 - i);
                }
            }
            return cells;
        } else {
            throw new ImpossibleMoveException("Ход невозможен.");
        }
    }


    @Override
    public Figure copy(Cell dest) {
        return new BishopBlack(dest);
    }

    public boolean movePossible(Cell source, Cell dest) {
        boolean result = false;
        if (source.x < dest.x && source.y > dest.y || // вверх вправо
                source.x > dest.x && source.y < dest.y || // вниз влево
                source.x < dest.x && source.y < dest.y || // вниз вправо
                source.x > dest.x && source.y > dest.y) // вверх вправо
        {
            result = true;
        }
        return result;
    }

    private int steps(Cell source, Cell dest) {
        return Math.abs(dest.x - source.x);
    }
}
