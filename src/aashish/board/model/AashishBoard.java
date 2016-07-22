
package aashish.board.model;

import aashish.board.pieces.PieceBishop;
import aashish.board.pieces.PieceKing;
import aashish.board.pieces.PieceKnight;
import aashish.board.pieces.PiecePawn;
import aashish.board.pieces.PieceQueen;
import aashish.board.pieces.PieceRook;
import java.awt.*;
import java.awt.Color;


public class AashishBoard extends AashishGame {

    private static final int ROW_COUNT = 8, COLUMN_COUNT = 8;

    
    public AashishBoard() {
        super();
    }
protected void setupSquares() {
        squareBoard = new AashishSquare[COLUMN_COUNT][ROW_COUNT];
        for (int posA = 0; posA < squareBoard.length; posA++)
            for (int posB = 0; posB < squareBoard[posA].length; posB++)
                squareBoard[posA][posB] = new AashishSquare(posA, posB);
    }

    /**
     * Checks if the parity of two numbers is equal.
     *
     * @param a first integer number.
     * @param b second integer number.
     * @return true if a+b is even.
     */
    private boolean checkParity(int a, int b) {
        return (a + b) % 2 == 0;
    }

    /**
     * Paints a square with the color LIGHT_GRAY.
     *
     * @param positionA desired X-coordinate of a square.
     * @param positionB desired Y-coordinate of a square.
     */
    private void paintGray(int positionA, int positionB) {
        squareBoard[positionA][positionB].squareColor = Color.LIGHT_GRAY;
    }

    /**
     * Paints a square with the color WHITE.
     *
     * @param positionA desired X-coordinate of a square.
     * @param positionB desired Y-coordinate of a square.
     */
    private void paintWhite(int positionA, int positionB) {
        squareBoard[positionA][positionB].squareColor = Color.WHITE;
    }

    /**
     * Paints a square either LIGHT_GRAY or WHITE, based on the parity of the coordinates. On the traditional
     * chessboard, if the coordinates of a square have the same parity, the square is painted LIGHT_GRAY (or any dark
     * color).
     *
     * @param positionA desired X-coordinate of a square.
     * @param positionB desired Y-coordinate of a square.
     */
    protected void paintSquareAt(int positionA, int positionB) {
        if (checkParity(positionA, positionB))
            paintGray(positionA, positionB);
        else paintWhite(positionA, positionB);
    }

    /**
     * Paints each square in the board according to the chessboard's specifications. Implementation of Board's abstract
     * method.
     */
    @Override
    protected void paintSquares() {
        for (int positionA = 0; positionA < COLUMN_COUNT; positionA++)
            for (int positionB = 0; positionB < ROW_COUNT; positionB++)
                paintSquareAt(positionA, positionB);
    }

    /**
     * Sets up the traditional chessboard, which has a row of pawns defending a row of specific pieces: PieceRook - PieceKnight -
     * PieceBishop - PieceQueen - PieceKing - PieceBishop - PieceKnight - PieceRook.
     */

    public void setupPieces() {
        for (int positionA = 0; positionA < COLUMN_COUNT; positionA++) {
            putPieceAt(new PiecePawn(false), positionA, 1);   // White pawns
            putPieceAt(new PiecePawn(true), positionA, 6);    // Black pawns
    }

        // White pieces

        putPieceAt(new PieceRook(false), 0, 0);
        putPieceAt(new PieceKnight(false), 1, 0);
        putPieceAt(new PieceBishop(false), 2, 0);
        putPieceAt(new PieceQueen(false), 3, 0);
        putPieceAt(new PieceKing(false), 4, 0);
        putPieceAt(new PieceBishop(false), 5, 0);
        putPieceAt(new PieceKnight(false), 6, 0);
        putPieceAt(new PieceRook(false), 7, 0);

        // Black pieces

        putPieceAt(new PieceRook(true), 0, 7);
        putPieceAt(new PieceKnight(true), 1, 7);
        putPieceAt(new PieceBishop(true), 2, 7);
        putPieceAt(new PieceQueen(true), 3, 7);
        putPieceAt(new PieceKing(true), 4, 7);
        putPieceAt(new PieceBishop(true), 5, 7);
        putPieceAt(new PieceKnight(true), 6, 7);
        putPieceAt(new PieceRook(true), 7, 7);

        // Sets flag for GUI - if those pieces are in play, no other piece should be added

        chessFlag = true;
    }
    
    public boolean aPositionOutOfBounds(int positionA) {
        return positionA < 0 || positionA >= COLUMN_COUNT;
    }

    
    public boolean bPositionOutOfBounds(int positionB) {
        return positionB < 0 || positionB >= ROW_COUNT;
    }

}

