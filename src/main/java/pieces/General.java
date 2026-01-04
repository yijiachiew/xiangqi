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
                // Check for Flying General rule
                if (isFlyingGeneral(newPos, board, this.getColour())) {
                    continue; // Invalid move due to Flying General rule
                }
                
                validMoves.add(new Move(pos, newPos));
            }
        }
        return validMoves;
    }

    private boolean isFlyingGeneral(Position newPos, List<List<Position>> board, Colour myColour) {
        // Find my new position (newPos)
        // Find the enemy General
        // Check if they are on the same column
        // Check if there are any pieces between them

        int myX = newPos.getX();
        int myY = newPos.getY();

        Position enemyGeneralPos = null;
        
        // Locate enemy general
        for (List<Position> row : board) {
            for (Position p : row) {
                if (!p.isEmpty() && 
                    p.getPiece().getPieceType() == PieceType.GENERAL && 
                    p.getPiece().getColour() != myColour) {
                    enemyGeneralPos = p;
                    break;
                }
            }
            if (enemyGeneralPos != null) break;
        }

        if (enemyGeneralPos == null) return false; // Should not happen in a valid game

        int enemyX = enemyGeneralPos.getX();
        int enemyY = enemyGeneralPos.getY();

        // If not on the same column (Y), Flying General doesn't apply
        if (myY != enemyY) {
            return false; 
        }

        // Check vertical line between generals
        int startX = Math.min(myX, enemyX) + 1;
        int endX = Math.max(myX, enemyX);

        for (int i = startX; i < endX; i++) {
             // If any position between them is occupied, it's blocked, so NO flying general
             // However, we must check if the current position 'pos' was the one blocking it?
             // No, 'board' state still has 'this' general at 'pos'. 
             // We are validating if moving TO 'newPos' causes a Flying General.
             
             // IMPORTANT: When checking the path, we must ignore the current position of the general moving
             // because effectively it has moved to newPos.
             // BUT: The general moves 1 step. 
             // If it moves SIDEWAYS, it changes column.
             // If it moves VERTICALLY, it stays on column.
             
             // The loop checks board.get(i).get(myY).
             // If 'this' General is currently at board.get(i).get(myY), we should treat it as empty 
             // because we are simulating it moving OUT of there to 'newPos'.
             
             // Actually, simplest logic:
             // We iterate strictly between newPos and enemyPos.
             // Since General only moves 1 step, 'newPos' is adjacent to 'pos'.
             // If we move vertically, 'pos' will be outside the range (startX, endX) usually?
             // No. If moving towards enemy, range shrinks. If moving away, range grows.
             // If moving sideways, we enter a new column. Old column doesn't matter. New column matters.
             
             Position currentPosToCheck = board.get(i).get(myY);
             
             // If the position we are checking contains THIS piece (the one moving), skip it (treat as empty)
             if (!currentPosToCheck.isEmpty() && currentPosToCheck.getPiece() == this) {
                 continue; 
             }
             
             if (!currentPosToCheck.isEmpty()) {
                 return false; // Blocked
             }
        }

        // If we get here, they are on same column with NO pieces in between
        return true; 
    }
}
