package coordinate;

import pieces.ChessPiece;

public class Move {
    // Represents a move in a chess game
    private Position source;
    private Position destination;

    public Move(Position source, Position destination) {
        this.source = source;
        this.destination = destination;
    }

    public Position getSource() {
        return source;
    }
    public Position getDestination() {
        return destination;
    }
    public boolean equals(Position fromPos, Position toPos) {
        // Check if the move matches the source and destination positions
        return this.source.equals(fromPos) && this.destination.equals(toPos);
    }
    public void applyMove(){
        // Apply the move by updating the source position to the destination position
        // The piece is moved from source to destination
        // Source position will be empty after the move
        ChessPiece piece = source.getPiece();
        destination.setPiece(piece);
        source.emptyPiece();

    }
}
