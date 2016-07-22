/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aashish.board.pieces;

import aashish.board.model.AashishGame;
import aashish.board.model.AashishSquare;
import aashish.board.moves.MainMoves;
import aashish.board.moves.MovesOfKnight;

/**
 *
 * @author Toshiba
 */

    
public class PieceKnight extends MainPiece {

    /**
     * Creates a knight based on its color (black or white).
     *
     * @param colorChoice a boolean value defining the color of the piece (black if <em>true</em>, white otherwise).
     */
    public PieceKnight(boolean colorChoice) {
        super(colorChoice);
    }

    public void learnMainMovesFrom(AashishSquare referenceAashishSquare, AashishGame referenceAashishGame) {
        referenceMainMoves = new MovesOfKnight(referenceAashishSquare, referenceAashishGame, isBlack);
    }

   
}

