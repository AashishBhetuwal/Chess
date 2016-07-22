/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aashish.board.model;

import aashish.board.pieces.MainPiece;
import java.awt.Color;

/**
 *
 * @author Toshiba
 */
public class AashishSquare {
    protected MainPiece squarePiece;
    protected Color squareColor;
    
    private int positionA, positionB;

    /**
     * Creates a square in a certain position of a board. The coordinates are the only things the square knows about its
     * board, as identifying a square on a board could take a lot of time.
     *
     * @param positionA the X-coordinate chosen to create the square on a certain board.
     * @param positionB the Y-coordinate chosen to create the square on a certain board.
     */
    public AashishSquare(int positionA, int positionB) {
        squarePiece = null;
        squareColor = null;
        this.positionA = positionA;
        this.positionB = positionB;
    }

    /**
     * Returns the color of the square, which is set by the chessboard.
     *
     * @return the square color.
     */
    public Color getSquareColor() {
        return squareColor;
    }

    /**
     * Returns the piece on the square.
     *
     * @return the Piece on the square, if any.
     */
    public MainPiece getSquarePiece() {
        return squarePiece;
    }

    /**
     * Returns the X-coordinate of the square in a chessboard.
     *
     * @return an integer containing the X-coordinate of a square.
     */
    public int getPosX() {
        return positionA;
    }

    /**
     * Returns the Y-coordinate of the square in a chessboard.
     *
     * @return an integer containing the Y-coordinate of a square.
     */
    public int getPosY() {
        return positionB;
    }
    
}
