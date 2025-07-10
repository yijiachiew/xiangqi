package pieces;


import coordinate.Move;
import coordinate.Position;
import helper.Colour;
import helper.PieceType;

import java.util.ArrayList;
import java.util.List;

public class Cannon extends ChessPiece{
    public Cannon(Colour colour, String symbol) {
        super(colour, symbol);
    }

    @Override
    public PieceType getPieceType() {
        return PieceType.CANNON;
    }



    @Override
    public List<Move> getValidMoves(Position pos, List<List<Position>> board) {
        List<Move> validMoves = new ArrayList<>();
        int x = pos.getX();
        int y = pos.getY();
        int[][] directions = {
            {1, 0},  // Right
            {-1, 0}, // Left
            {0, 1},  // Down
            {0, -1}  // Up
        };
        boolean platformFound = false;
        for (int[] direction : directions) {
            int dx = direction[0];
            int dy = direction[1];
            int step = 1;
            // Reset platformFound for each direction
            platformFound = false;

            while (true) {
                int newX = x + step * dx;
                int newY = y + step * dy;

                if (newX < 0 || newX >= board.size() || newY < 0 || newY >= board.get(0).size()) {
                    break; // Out of bounds
                }

                Position newPos = board.get(newX).get(newY);
                //If there is a chessPiece
                if (!newPos.isEmpty()){
                    //Check is a platform is already found
                    if (platformFound){
                        //Check if can capture
                        if (canCapture(this, newPos.getPiece())){
                            validMoves.add(new Move(pos, newPos));
                        }
                        //Cannot move after this non-empty position in this direction
                        break;
                    } else{
                        platformFound = true; // Else this becomes a platform
                    }
                } else {
                    //If a platform yet to be found, consider a possible Move
                    if (!platformFound) {
                        validMoves.add(new Move(pos, newPos));
                    }
                }
                step++;
            }
        }

        return validMoves;
    }
}
