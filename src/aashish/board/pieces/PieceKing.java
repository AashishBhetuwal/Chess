/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aashish.board.pieces;

import aashish.board.model.AashishGame;
import aashish.board.model.AashishSquare;
import aashish.board.moves.MainMoves;
import aashish.board.moves.MovesOfKing;
import aashish.board.moves.MovesOfRoyal;

/**
 *
 * @author Toshiba
 */

    
public class PieceKing extends MainPiece {

    /**
     * Creates a king based on its color (black or white).
     *
     * @param colorChoice a boolean value defining the color of the piece (black if <em>true</em>, white otherwise).
     */
    public PieceKing(boolean colorChoice) {
        super(colorChoice);
    }

    public boolean isInCheck() {
        return referenceMainMoves instanceof MovesOfRoyal && ((MovesOfRoyal) referenceMainMoves).getInCheck();
    }

    public boolean isInCheckMate() {
        return referenceMainMoves instanceof MovesOfRoyal && ((MovesOfRoyal) referenceMainMoves).getInCheckMate();
    }
    public void learnMainMovesFrom(AashishSquare referenceAashishSquare, AashishGame referenceAashishGame) {
        referenceMainMoves = new MovesOfKing(referenceAashishSquare, referenceAashishGame, this.isBlack);
    }

}

