package pieces;

import coordinate.Move;
import coordinate.Position;
import helper.Colour;
import helper.PieceType;

import java.util.List;

public class Horse extends ChessPiece{
    public Horse(Colour colour, String symbol) {
        super(colour, symbol);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.HORSE;
    }



    @Override
    public List<Move> getValidMoves(Position pos, List<List<Position>> board) {

        // The Horse can move in an "L" shape: two squares in one direction and then one square perpendicular
        // Ensure there is // no piece blocking the first square in the direction of the move
        int x = pos.getX();
        int y = pos.getY();
        List<Move> validMoves = new java.util.ArrayList<>();
        int[][] directions = {
            {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, // Horizontal moves
            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}  // Vertical moves
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
            // Check if the position is empty or can be captured
            if (newPos.isEmpty() || canCapture(this, newPos.getPiece())) {
                // Check if the first square in the direction of the move is empty
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
