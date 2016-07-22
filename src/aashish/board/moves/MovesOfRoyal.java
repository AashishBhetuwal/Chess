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

    
public abstract class MovesOfRoyal extends MainMoves {

    private boolean isInCheck;

    public MovesOfRoyal(AashishSquare referenceAashishSquare, AashishGame referenceAashishGame, boolean colorChoice) {
        super(referenceAashishSquare, referenceAashishGame, colorChoice);
        isInCheck = false;
    }

    private boolean squareThreatenedAt(int positionA, int positionB) {
        return !referenceAashishGame.aPositionOutOfBounds(positionA) && !referenceAashishGame.bPositionOutOfBounds(positionB) &&
                referenceAashishGame.checkThreatAt(positionA, positionB, colorChoice);
    }

    private boolean isBeingAttacked() {
        return squareThreatenedAt(getRefX(), getRefY());
    }
    public boolean getInCheck() {
        if(isBeingAttacked())
            isInCheck = true;
        return isInCheck;
    }
    protected void addSquareIfNotThreatened(int positionA, int positionB) {
        if (referenceAashishGame.hasSquareAt(positionA, positionB))
            if (!squareThreatenedAt(positionA, positionB))
                addSquareAt(positionA, positionB);
    }

    public boolean getInCheckMate(){
        return getInCheck() && cannotMove();
    }

    private boolean cannotMove() {
        return possibleMoves == null || possibleMoves.isEmpty();
    }

        private void addSquareAt(int positionA, int positionB) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

}

