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

    
public class MovesOfBishop extends MainMoves {

    public MovesOfBishop(AashishSquare referenceAashishSquare, AashishGame referenceAashishGame, boolean colorChoice) {
        super(referenceAashishSquare, referenceAashishGame, colorChoice);
    }

    protected void learnMoveSet() {
        learnDiagonalMoves();
    }

    /**
     * Goes through all the squares diagonally too see which ones allow a valid move.
     */
    private void learnDiagonalMoves() {
        learnDiagonalMovesAtMainDiagonal(1);
        learnDiagonalMovesAtMainDiagonal(-1);
        learnDiagonalMovesAtSecondaryDiagonal(1);
        learnDiagonalMovesAtSecondaryDiagonal(-1);
    }

    
    private void learnDiagonalMovesAtMainDiagonal(int pace) {
        boolean foundPiece = false;
        for (int dist = pace; !outOfBounds(getRefX() + dist, getRefY() + dist) && !foundPiece; dist += pace) {
            if (referenceAashishGame.hasChessPieceAt(getRefX() + dist, getRefY() + dist)) {
                foundPiece = true;
                if (opponentPieceAt(getRefX() + dist, getRefY() + dist))
                    addSquareAt(getRefX() + dist, getRefY() + dist);
            } else addSquareAt(getRefX() + dist, getRefY() + dist);
        }
    }

    
    private void learnDiagonalMovesAtSecondaryDiagonal(int pace) {
        boolean foundPiece = false;
        for (int dist = pace; !outOfBounds(getRefX() - dist, getRefY() + dist) && !foundPiece; dist += pace) {
            if (referenceAashishGame.hasChessPieceAt(getRefX() - dist, getRefY() + dist)) {
                foundPiece = true;
                if (opponentPieceAt(getRefX() - dist, getRefY() + dist))
                    addSquareAt(getRefX() - dist, getRefY() + dist);
            } else addSquareAt(getRefX() - dist, getRefY() + dist);
        }
    }

    
    private boolean outOfBounds(int aPosition, int bPosition) {
        return referenceAashishGame.aPositionOutOfBounds(aPosition) ||
                referenceAashishGame.bPositionOutOfBounds(bPosition);
    }

    private void addSquareAt(int i, int i0) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

