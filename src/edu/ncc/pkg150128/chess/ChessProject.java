package edu.ncc.pkg150128.chess;


import aashish.board.model.AashishBoard;
import aashish.board.model.AashishGame;
import aashish.board.model.AashishSquare;
import aashish.board.pieces.MainPiece;
import aashish.boardUI.AashishBoardInterface;
import aashish.boardUI.AashishChessUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

/**
 * <strong>Game</strong> class - implements the actual game of Chess.
 *
 * 
 
 */
public class ChessProject {

    private AashishBoardInterface chessUI;
    private JFrame gameFrame;
    private AashishSquare gameSquare;
    private boolean selectionPiece;
    private boolean blackplayerTurn;

    /**
     * Starts a new game.
     */
    public ChessProject() {
        AashishGame gameBoard = new AashishBoard();
        gameBoard.setupPieces();
        chessUI = new AashishChessUI(gameBoard);
        selectionPiece = false;
        gameSquare = null;
        blackplayerTurn = false;
        setChessFrame();
        fillingTheFrame();
    }

    /**
     * Configures the main JFrame object for the chess game.
     */
    private void setChessFrame() {
        gameFrame = new JFrame(" ChessProject ");
        gameFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        gameFrame.setSize(1100, 880);
        gameFrame.setResizable(false);
        gameFrame.setLocation(350, 50);
        gameFrame.setVisible(true);
    }

    /**
     * Fills the frame with.
     */
    private void fillingTheFrame() {
        JSplitPane  panelForChess = chessUI.getChessPanel();
        gameFrame.add( panelForChess);
    }

    public static void main(String[] args) {
        ChessProject gamechess1 = new ChessProject();
        gamechess1.defineMouseListener();
        gamechess1.addListenerToOtherButtons();
        gamechess1.gameFrame.setVisible(true);
    }

    /**
     * Defines a MouseListener for each square button on the GUI.
     */
    private void defineMouseListener() {
        for (int positionA = 0; positionA < 8; positionA++) {
            for (int positionB = 0; positionB < 8; positionB++) {
                final JButton button = chessUI.getButtonAt(positionA, positionB);
                final int aPosition = positionA;
                final int bPosition = positionB;
                button.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (!selectionPiece) {
    if (chessUI.hasChessPieceAt(aPosition, bPosition)) 
    {
    if (chessUI.getPieceAt(aPosition, bPosition).isBlack() == blackplayerTurn) {
        foundChessPieces(button, aPosition, bPosition);
    } else {
        wrongColor();
    }
    } else {
        noChessPieces();
    }
    } else {
    AashishSquare newAashishSquare = chessUI.getSquareAt(aPosition, bPosition);
    if (newAashishSquare != gameSquare) {
    if (gameSquare.getSquarePiece().canMoveTo(newAashishSquare)) {
        movePieces(aPosition, bPosition);
    } else {
    if (newAashishSquare.getSquarePiece() == null) {
        notMoveable();
    } else {
    if (newAashishSquare.getSquarePiece().isBlack() != blackplayerTurn) {
        wrongColor();
    } else {
    chooseNewChessPieces(button, newAashishSquare);
         }
       }
    }
    } else {
    cleanBoardAndReferences();
    cancelTheMoves();
                            }
                        }
                        //System.out.println(aPosition + "," + bPosition);
                    }
                });
            }
        }
    }
    

    /**
     * Adds a mouseListener for every button on the left side.
     */
    private void addListenerToOtherButtons() {
        addRenameListener();
        
        addRestartListener();
    }

    /**
     * Adds a mouseListener for the Rename JButton.
     */
    private void addRenameListener() {
        final JButton rename = chessUI.getRename();
        rename.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
    }

    private void addRestartListener() {
        final JButton restart = chessUI.getRestart();
        restart.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }
        });
    }

    /**
     * Cleans board after a move was successfully made or cancelled.
     */
    private void cleanBoardAndReferences() {
        chessUI.originalPainting();
        gameSquare = null;
        selectionPiece = false;
    }


   
    private void foundChessPieces(JButton button, int aPosition, int bPosition) {
        selectionPiece = true;
        gameSquare = chessUI.getSquareAt(aPosition, bPosition);
        chessUI.paintMovesOf(gameSquare.getSquarePiece());
        button.setBackground(Color.CYAN);
        chessUI.setGameMessage("Chess Selected!");
    }

    /**
     * Sets a message on the board corresponding to having selected a new piece.
     */
    private void chooseNewChessPieces(JButton button, AashishSquare newAashishSquare) {
        gameSquare = newAashishSquare;
        chessUI.originalPainting();
        chessUI.paintMovesOf(gameSquare.getSquarePiece());
        button.setBackground(Color.CYAN);
    }

    /**
     * Sets a message on the board corresponding to having moved a piece.
     */
    private void movePieces(int aPosition, int bPosition) {
        final MainPiece movingPiece = gameSquare.getSquarePiece();
        movingPiece.moveTo(aPosition, bPosition);
        cleanBoardAndReferences();
        blackplayerTurn = !blackplayerTurn;
        chessUI.changePlayerTurn(blackplayerTurn);
    }

    
    private void cancelTheMoves() {
        chessUI.setGameMessage(playerColorCapitalized() + "The Player has cancelled his selection. Please try another one.");
    }

    private void wrongColor() {
        chessUI.setGameMessage("Opponent's Piece Selected! You can only move " + playerColorLowercase() +
                "pieces.");
    }

    /**
     * Sets a message on the board corresponding to not having selected a piece.
     */
    private void noChessPieces() {
        chessUI.setGameMessage("Please Select the Pieces First!");
    }
    private void notMoveable() {
        chessUI.setGameMessage("Cannot move the piece to the selected Square.");
    }

    private String playerColorCapitalized() {
        return blackplayerTurn ? "Black " : "White ";
    }

    private String playerColorLowercase() {
        return playerColorCapitalized().toLowerCase();
    }

}
