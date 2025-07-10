package pieces;

import coordinate.Move;
import coordinate.Position;
import helper.Colour;
import helper.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Elephant extends ChessPiece{
    public Elephant(Colour colour, String symbol) {
        super(colour, symbol);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.ELEPHANT;
    }



    @Override
    public List<Move> getValidMoves(Position pos, List<List<Position>> board) {
        // The Elephant can move diagonally two squares, but cannot cross the river.
        int x = pos.getX();
        int y = pos.getY();
        int[][] directions = {
            {2, 2}, {2, -2}, {-2, 2}, {-2, -2}
        };
        List<Move> validMoves = new ArrayList<>();
        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];
            int newX = x + dx;
            int newY = y + dy;

            // Check if the new position is within bounds
            if (newX < 0 || newX >= board.size() || newY < 0 || newY >= board.get(0).size()) {
                continue; // Out of bounds
            }

            Position newPos = board.get(newX).get(newY);
            // Check if the move crosses the river // HARDCODED
            if ((this.getColour() == Colour.RED && newX < 5) || (this.getColour() == Colour.BLACK && newX > 4)) {
                continue; // Cannot cross the river
            }

            // Check if the position is empty or can be captured, or the piece is not blocked by another piece
            if (newPos.isEmpty() || canCapture(this, newPos.getPiece())) {
                // Check if the position is blocked by another piece
                int midX = x + dx / 2;
                int midY = y + dy / 2;
                Position midPos = board.get(midX).get(midY);

                if (midPos.isEmpty()) {
                    validMoves.add(new Move(pos, newPos));
                }
            }
        }


        return validMoves;
    }
}
