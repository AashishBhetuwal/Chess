/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aashish.board.pieces;

import aashish.board.model.AashishGame;
import aashish.board.model.AashishSquare;
import aashish.board.moves.MainMoves;
import aashish.board.moves.MovesOfQueen;

/**
 *
 * @author Toshiba
 */

    
public class PieceQueen extends MainPiece {
    public PieceQueen(boolean colorChoice) {
        super(colorChoice);
    }

    public void learnMainMovesFrom(AashishSquare referenceAashishSquare, AashishGame referenceAashishGame) {
        referenceMainMoves = new MovesOfQueen(referenceAashishSquare, referenceAashishGame, this.isBlack);
    }
}

