/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aashish.board.moves;

import aashish.board.model.AashishGame;
import aashish.board.model.AashishSquare;

/**
 *
 * @author Toshiba
 */


    
    
    
    
        

public class MovesOfRook extends MainMoves {

    /**
     * Creates a new rook move set having a square, a board and a color as reference.
     *
     * @param referenceSquare the square to be taken as reference for relative moves.
     * @param referenceBoard  the board to be taken as reference for moves.
     * @param colorChoice     the color choice of the piece, in case a piece has color limitations.
     */
    public MovesOfRook(AashishSquare referenceAashishSquare, AashishGame referenceAashishGame, boolean colorChoice) {
        super(referenceAashishSquare, referenceAashishGame, colorChoice);
    }

    /**
     * Process the piece-specific algorithms to acquire the allowed squares to move to.
     */
    protected void learnMoveSet() {
        learnVerticalMoves();
        learnHorizontalMoves();
    }

    /**
     * Goes through all the squares in the vertical direction too see which ones allow a valid move.
     */
    private void learnVerticalMoves() {
        learnVerticalMovesWithDirection(1);
        learnVerticalMovesWithDirection(-1);
    }

    private void learnHorizontalMoves() {
        learnHorizontalMovesWithDirection(1);
        learnHorizontalMovesWithDirection(-1);
    }

    private void learnVerticalMovesWithDirection(int yPace) {
        boolean foundPiece = false;
        for (int bPosition = getRefY() + yPace; !referenceAashishGame.bPositionOutOfBounds(bPosition) && !foundPiece; bPosition += yPace) {
            if (referenceAashishGame.hasChessPieceAt(getRefX(), bPosition)) {
                foundPiece = true;
                if (opponentPieceAt(getRefX(), bPosition)) {
                    addAashishSquareAt(getRefX(), bPosition);
                }
            } else addAashishSquareAt(getRefX(), bPosition);
        }
    }

    /**
     * Goes through all the horizontal squares in a certain direction, according to a pace.
     *
     * @param xPace how the X-coordinate changes with time. (-1) means left, and (+1) means right.
     */
    private void learnHorizontalMovesWithDirection(int xPace) {
        boolean foundPiece = false;
        for (int aPosition = getRefX() + xPace; !(referenceAashishGame.aPositionOutOfBounds(aPosition) || foundPiece); aPosition += xPace) {
            if (referenceAashishGame.hasChessPieceAt(aPosition, getRefY())) {
                foundPiece = true;
                if (opponentPieceAt(aPosition, getRefY()))
                    addAashishSquareAt(aPosition, getRefY());
            } else addAashishSquareAt(aPosition, getRefY());
        }
    }

}
