package pieces;
import helper.Colour;
import helper.PieceType;
import coordinate.Position;
import coordinate.Move;

import java.util.ArrayList;
import java.util.List;

public class Advisor extends ChessPiece {

    public Advisor(Colour colour, String symbol) {
        super(colour, symbol);
    }


    @Override
    public PieceType getPieceType() {
        return PieceType.ADVISOR;
    }



    @Override
    public List<Move> getValidMoves(Position pos, List<List<Position>> board) {
        // The Advisor can only move one step diagonally within the palace
        int x = pos.getX();
        int y = pos.getY();
        List<Move> validMoves = new ArrayList<>();
        // Define the palace boundaries // HARDCODED
        int rowStart = (getColour() == Colour.RED) ? 7 : 0;
        int rowEnd = rowStart + 2;
        int colStart = 3;
        int colEnd = 5;
        //List of directions
        int[][] directions = {
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1} // Diagonal moves
        };
        for (int[] dir : directions) {
            int newX = x + dir[0];
            int newY = y + dir[1];
            // Check if the move is within the palace boundaries
            if (newX >= rowStart && newX <= rowEnd && newY >= colStart && newY <= colEnd) {
                Position newPos = board.get(newX).get(newY);
                //System.out.println("Advisor can move to: ("+ newPos +")" );
                // Check if the target position is empty or occupied by an opponent's piece
                if (board.get(newX).get(newY).isEmpty() ||
                    board.get(newX).get(newY).getPiece().getColour() != this.getColour()) {
                    validMoves.add(new Move(pos, newPos));
                }
            } else{
                //System.out.println("Advisor cannot move to: ("+ newX +"," + newY +") - out of palace bounds" );
            }
        }

        return validMoves;
    }
}
