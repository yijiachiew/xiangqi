package pieces;

import coordinate.Move;
import coordinate.Position;
import helper.Colour;
import helper.PieceType;

import java.util.ArrayList;
import java.util.List;

public class General extends ChessPiece{
    public General(Colour colour, String symbol) {
        super(colour, symbol);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.GENERAL;
    }



    @Override
    public List<Move> getValidMoves(Position pos, List<List<Position>> board) {
        // The General can move one square in any direction, but only within the palace area
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
            int newX = x + dx;
            int newY = y + dy;

            // Check if the new position is within bounds
            if (newX < 0 || newX >= board.size() || newY < 0 || newY >= board.get(0).size()) {
                continue; // Out of bounds
            }

            Position newPos = board.get(newX).get(newY);
            // Check if the move is within the palace area // HARDCODED
            if ((this.getColour() == Colour.RED && (newX < 7 || newY < 3 || newY > 5)) ||
                (this.getColour() == Colour.BLACK && (newX > 2 || newY < 3 || newY > 5))) {
                continue; // Cannot move outside the palace area
            }

            // Check if the position is empty or can be captured
            if (newPos.isEmpty() || canCapture(this, newPos.getPiece())) {
                // Must check that the move does not put the General in check
                boolean inCheck = false;
                
                validMoves.add(new Move(pos, newPos));
            }
        }
        return validMoves;
    }
}
