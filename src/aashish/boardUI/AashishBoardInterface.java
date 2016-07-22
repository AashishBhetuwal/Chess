
package aashish.boardUI;

import aashish.board.model.AashishGame;
import aashish.board.model.AashishSquare;
import aashish.board.pieces.MainPiece;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;


public abstract class AashishBoardInterface {

protected JPanel gamePanel;
    private JButton restartGame;
    
    private JButton renamePlayer;
    protected AashishGame chessGameModel;
    protected GridLayout gameLayout;
    protected JButton[][] gameButtons;
    protected JSplitPane gameSplit;
    protected JLabel chessGameMessages;
    protected JPanel playerScoreBoard;
    protected JLabel currentPlayerTurn;

    protected JLabel scoreOfWhitePlayer;
    protected String whitePlayerName;
    protected int scoreOfWhite;

    //  Black player

    protected JLabel scoreOfBlackPlayer;
    protected String blackPlayerName;
    protected int scoreOfBlack;

    public AashishBoardInterface(AashishGame chessGameModel) {
        initialGameSetup();
        restartButtonForGame();
        createGameLabels();
        setGameScoreBoard();
        setGameChessPanel(chessGameModel);
        setChessBoardAsButtons();
        setSplitPanel();
    }

    private void initialGameSetup() {
        whitePlayerName = "Player1";
        blackPlayerName = "Player2";
        scoreOfWhite = scoreOfBlack = 0;
    }

    private void restartButtonForGame() {
        restartGame = new JButton("Restart game");
        renamePlayer = new JButton("Change Player's names");
    }

    private void createGameLabels() {
        currentPlayerTurn = blackBoardLabel(Color.ORANGE, "White player's turn");
        chessGameMessages = blackBoardLabel(Color.YELLOW, "Welcome to Chess!");
        scoreOfWhitePlayer = blackBoardLabel(Color.CYAN, getScoreForPlayer(false));
        scoreOfBlackPlayer = blackBoardLabel(Color.CYAN, getScoreForPlayer(true));
//        authorName = blackBoardLabel(Color.GREEN, "Created by authorname");
    }

    private JLabel blackBoardLabel(Color color, String message) {
        JLabel chessGameLabel = new JLabel(centerWrapMessage(message));
        chessGameLabel.setBorder(new LineBorder(Color.BLACK));
        chessGameLabel.setBackground(color);
        chessGameLabel.setOpaque(true);
        chessGameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        return chessGameLabel;
    }

    private void setGameScoreBoard() {
        playerScoreBoard = new JPanel(new GridLayout(9, 0));
        playerScoreBoard.add(restartGame);
        playerScoreBoard.add(currentPlayerTurn);
        playerScoreBoard.add(chessGameMessages);
        playerScoreBoard.add(renamePlayer);
        playerScoreBoard.add(scoreOfWhitePlayer);
        playerScoreBoard.add(scoreOfBlackPlayer);
    }

    
    private void setGameChessPanel(AashishGame chessGameModel) {
        this.chessGameModel = chessGameModel;
        setChessLayout();
        gamePanel = new JPanel();
        gamePanel.setLayout(gameLayout);
    }

    private void setSplitPanel() {
        gameSplit = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        gameSplit.setLeftComponent(gamePanel);
        gameSplit.setRightComponent(playerScoreBoard);
        gameSplit.setDividerLocation(0.8);
        gameSplit.setEnabled(false);
    }

  
    protected abstract void setChessLayout();

    
    protected abstract void setChessBoardAsButtons();

    
    public JSplitPane getChessPanel() {
        return gameSplit;
    }

    
    public JButton getButtonAt(int positionA, int positionB) {
        return gameButtons[positionA][positionB];
    }

    public boolean hasChessPieceAt(int positionA, int positionB) {
        return chessGameModel.hasChessPieceAt(positionA, positionB);
    }

    public AashishSquare getSquareAt(int positionA, int positionB) {
        return chessGameModel.getSquareAt(positionA, positionB);
    }

    public MainPiece getPieceAt(int positionA, int positionB) {
        return chessGameModel.getSquareAt(positionA, positionB).getSquarePiece();
    }
    public void originalPainting() {
        chessGameModel.deselectPiece();
        resetColors();
    }

    public void paintMovesOf(MainPiece piece) {
        chessGameModel.availableMoves(piece);
        resetColors();
    }

    protected abstract void resetColors();
    public void setGameMessage(String message) {
        chessGameMessages.setText(centerWrapMessage(message));
    }
    public void changePlayerTurn(boolean newPlayerTurn) {
        String playerName = newPlayerTurn ? "Black " : "White ";
        currentPlayerTurn.setText(playerName + "Player's turn");
    }

    private String centerWrapMessage(String message) {
        return "<html><div style=\"text-align: center;\">" + message + "</html>";
    }

    public JButton getRestart() {
        return restartGame;
    }

    public JButton getRename() {
        return renamePlayer;
    }

   
    private String getScoreForPlayer(boolean playerIsBlack) {
        if (playerIsBlack)
            return centerWrapMessage(blackPlayerName + " (Black) - " + scoreOfBlack + " point(s)");
        else
            return centerWrapMessage(whitePlayerName + " (White) - " + scoreOfWhite + " point(s)");
    }

    
    public void incrementScoreForPlayer(boolean playerIsBlack) {
        if (playerIsBlack) {
            scoreOfBlack++;
            scoreOfBlackPlayer.setText(getScoreForPlayer(true));
        } else {
            scoreOfWhite++;
            scoreOfWhitePlayer.setText(getScoreForPlayer(false));
        }
    }

}
