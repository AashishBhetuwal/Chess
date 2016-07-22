/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package aashish.board.model;

import aashish.board.pieces.MainPiece;
import aashish.exceptionhandling.DoublePieceException;
import aashish.exceptionhandling.DuringGameException;
import aashish.exceptionhandling.PiecePositionException;
import java.awt.Color;

/**
 *
 * @author Toshiba
 */
public abstract class AashishGame {
    
    protected AashishSquare[][] squareBoard;
    protected boolean chessFlag;

    
    public AashishGame() {
        setupSquares();
        paintSquares();
        chessFlag = false;
    }

    protected abstract void setupSquares();

    protected abstract void paintSquares();

    public abstract void setupPieces();

    public boolean hasSquareAt(int markA, int markB) {
        return !(aPositionOutOfBounds(markA) || bPositionOutOfBounds(markB)) && squareBoard[markA][markB] != null;
    }

    public AashishSquare getSquareAt(int positionA, int positionB) {
        return squareBoard[positionA][positionB];
    }
    public boolean hasChessPieceAt(int positionA, int positionB) {
        return !(aPositionOutOfBounds(positionA) || bPositionOutOfBounds(positionB)) && squareBoard[positionA][positionB].getSquarePiece() != null;
    }

    public void putPieceAt(MainPiece piece, int positionA, int positionB) {
        if (isInGame())
            throw new DuringGameException("You tried to set a piece when a game is already being played");
        if (alreadyHasPieceOnBoard(piece))
            throw new DoublePieceException("You tried to add the same piece twice on the board");
        if (hasChessPieceAt(positionA, positionB))
            throw new PiecePositionException("You tried to set a piece on the same place as another piece");
        squareBoard[positionA][positionB].squarePiece = piece;
        piece.learnMainMovesFrom(squareBoard[positionA][positionB], this);
        updateAllPieces();
    }

    
    public void movePieces(AashishSquare originalSquare, int positionA, int positionB) {
        squareBoard[positionA][positionB].squarePiece = (MainPiece) originalSquare.getSquarePiece();
        originalSquare.squarePiece = null;
        updateAllPieces();
    }

    private void updateAllPieces() {
        for (AashishSquare[] squareArray : squareBoard) {
            for (AashishSquare square : squareArray) {
                if (square.getSquarePiece() != null) {
                    square.getSquarePiece().updateMainMoves();
                }
            }
        }
    }

    public MainPiece getPieceAt(int positionA, int positionB) {
        return (MainPiece) squareBoard[positionA][positionB].getSquarePiece();
    }

    public boolean isInGame() {
        return chessFlag;
    }

    public boolean alreadyHasPieceOnBoard(MainPiece piece) {
        for (AashishSquare[] squareArray : squareBoard) {
            for (AashishSquare square : squareArray) {
                if (square.getSquarePiece() == piece)
                    return true;
            }
        }
        return false;
    }

    public abstract boolean aPositionOutOfBounds(int positionA);
    public abstract boolean bPositionOutOfBounds(int positionB);


   public void availableMoves(MainPiece selectedPiece) {
        for (AashishSquare[] squareArray : squareBoard) {
            for (final AashishSquare s : squareArray) {
                if (selectedPiece.canMoveTo(s)) {
                    s.squareColor = Color.GREEN;
                    //System.out.println("Painted move");
                }
            }
        }
    }

    public void deselectPiece() {
        paintSquares();
    }

    public boolean checkThreatAt(int positionA, int positionB, boolean colorChoice) {
        for (AashishSquare[] squareArray : squareBoard) {
            for (AashishSquare square : squareArray) {
                if (square.getSquarePiece() != null && square.getSquarePiece().isBlack() != colorChoice) {
                    if (square.getSquarePiece().canMoveTo(squareBoard[positionA][positionB]))
                        return true;
                }
            }
        }
        return false;
    }
    
    
    
    
    
    

   
    
}
