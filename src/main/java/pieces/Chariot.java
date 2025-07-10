package pieces;

import coordinate.Move;
import coordinate.Position;
import helper.Colour;
import helper.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Chariot extends ChessPiece{
    public Chariot(Colour colour, String symbol) {
        super(colour, symbol);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.CHARIOT;
    }

    @Override
    public List<Move> getValidMoves(Position pos, List<List<Position>> board) {
        // The Chariot can move any number of squares along a row or column
        int x = pos.getX();
        int y = pos.getY();
        List<Move> validMoves = new ArrayList<>();
        int[][] directions = {
                {1, 0},  // Right
                {-1, 0}, // Left
                {0, 1},  // Down
                {0, -1}  // Up
        };
        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];
            int step = 1;

            while (true) {
                int newX = x + step * dx;
                int newY = y + step * dy;

                if (newX < 0 || newX >= board.size() || newY < 0 || newY >= board.get(0).size()) {
                    break; // Out of bounds
                }
                Position newPos = board.get(newX).get(newY);
                // If the position is empty, add it as a valid move
                if (newPos.isEmpty()) {
                    validMoves.add(new Move(pos, newPos));
                } else {
                    // If there is a piece, check if it can be captured
                    if (canCapture(this, newPos.getPiece())) {
                        validMoves.add(new Move(pos, newPos));
                    }
                    break; // Cannot move further in this direction
                }
                step++;
            }
        }
        return validMoves;
    }

}
