package com.java.xiangqi;

import pieces.ChessPiece;

/*
Test run a working game here
 */
public class Demo {
    public static void main(String[] args) {
        Board board = new Board();
        ChessPiece horsePiece = board.getPieceAtPosition(0, 1);
        System.out.println("Piece at (1,0): " + horsePiece.getSymbol());
        System.out.println(horsePiece.getValidMoves(board.getPosition(0,1), board.getBoard()));
        board.playGame();
    }
}
