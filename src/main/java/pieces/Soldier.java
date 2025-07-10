package pieces;

import coordinate.Move;
import coordinate.Position;
import helper.Colour;
import helper.PieceType;

import java.util.List;

public class Soldier extends ChessPiece{
    public Soldier(Colour colour, String symbol) {
        super(colour, symbol);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.SOLDIER;
    }



    @Override
    public List<Move> getValidMoves(Position pos, List<List<Position>> board) {

        // The Soldier can move one square forward, and after crossing the river, it can also move left or right
        int x = pos.getX();
        int y = pos.getY();
        List<Move> validMoves = new java.util.ArrayList<>();
        int forward = (this.getColour() == Colour.RED) ? 1 : -1; // Red moves down, Black moves up
        // Forward move
        int newX = x + forward;
        int newY = y;
        if (newX >= 0 && newX < board.size() && newY >= 0 && newY < board.get(0).size()) {
            Position newPos = board.get(newX).get(newY);
            if (newPos.isEmpty() || canCapture(this, newPos.getPiece())) {
                validMoves.add(new Move(pos, newPos));
            }
        }
        // After crossing the river, Soldier can move left or right
        if ((this.getColour() == Colour.RED && x >= 5) || (this.getColour() == Colour.BLACK && x <= 4)) {
            // Left move
            newX = x;
            newY = y - 1;
            if (newY >= 0 && newY < board.get(0).size()) {
                Position leftPos = board.get(newX).get(newY);
                if (leftPos.isEmpty() || canCapture(this, leftPos.getPiece())) {
                    validMoves.add(new Move(pos, leftPos));
                }
            }
            // Right move
            newX = x;
            newY = y + 1;
            if (newY >= 0 && newY < board.get(0).size()) {
                Position rightPos = board.get(newX).get(newY);
                if (rightPos.isEmpty() || canCapture(this, rightPos.getPiece())) {
                    validMoves.add(new Move(pos, rightPos));
                }
            }
        }
        return validMoves;

    }
}
