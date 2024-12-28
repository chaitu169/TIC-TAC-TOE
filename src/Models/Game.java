package Models;

import Strategies.WinningStrategy.WinningStrategy;
import exceptions.InvalidInputException;
import exceptions.NoOfPlayerBoardSizeMisMatchException;
import exceptions.NonUniqueSymbolException;
import exceptions.TotalNumberOfBotException;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private List<Player> playerList;
    private List<Move> moves;
    private Player winner;
    private GameState gameState;
    private int nextMovePlayerIndex;
    private List<WinningStrategy> gameWinningStrategies;

    public Game(int sizeOfBoard, List<Player> playerList, List<WinningStrategy> gameWinningStrategies) {
        this.playerList = playerList;
        this.gameWinningStrategies = gameWinningStrategies;
        this.board = new Board(sizeOfBoard);
        this.gameState = GameState.IN_PROGRESS;
        this.moves = new ArrayList<>();
        this.nextMovePlayerIndex = 0;
    }

    public static GameBuilder getGameBuilder(){
        return new GameBuilder();
    }

    public Player getWinner() {
        return winner;
    }

    public GameState getGameState() {
        return gameState;
    }

    public void display(){
        this.board.display();
    }

    public void makeMove() throws InvalidInputException {
        //add move
        Player player = this.playerList.get(nextMovePlayerIndex);
        Move move = player.makeMove(board);

        if(move == null){
            return;
        }

        this.moves.add(move);

        //update game status
        if(this.isGameDraw()){
            this.gameState = GameState.DRAW;
            return;
        }

        if(this.checkIfAnyWinner(move)){
            this.gameState = GameState.WIN;
            this.winner = player;
            return;
        }

        //update next player index
        nextMovePlayerIndex = (nextMovePlayerIndex + 1)%(this.playerList.size());
    }

    public boolean isGameDraw(){
        int movesSize = this.moves.size();
        if(movesSize == board.getSize()* board.getSize()){
            return true;
        }
        return false;
    }

    public boolean checkIfAnyWinner(Move lastMove){
        for (int i = 0; i < gameWinningStrategies.size(); i++) {
            if(gameWinningStrategies.get(i).checkForWinner(board, lastMove)){
                return true;
            }
        }
        return false;
    }

    public static class GameBuilder {
        private int sizeOfBoard;
        private List<Player> playerList;
        private List<WinningStrategy> gameWinningStrategies;

        public GameBuilder() {
            this.playerList = new ArrayList<>();
            this.gameWinningStrategies = new ArrayList<>();
        }

        public GameBuilder setSizeOfBoard(int sizeOfBoard) {
            this.sizeOfBoard = sizeOfBoard;
            return this;
        }

        public GameBuilder setPlayerList(List<Player> playerList) {
            this.playerList = playerList;
            return this;
        }

        public GameBuilder addPlayerToList(Player player){
            this.playerList.add(player);
            return this;
        }

        public GameBuilder setGameWinningStrategies(List<WinningStrategy> gameWinningStrategies) {
            this.gameWinningStrategies = gameWinningStrategies;
            return this;
        }

        public GameBuilder addWinningStrategy(WinningStrategy strategy){
            this.gameWinningStrategies.add(strategy);
            return this;
        }

        public Game build() throws TotalNumberOfBotException, NoOfPlayerBoardSizeMisMatchException, NonUniqueSymbolException {
            validateBot();
            validateSize();
            validateSymbols();
            return new Game(sizeOfBoard, playerList, gameWinningStrategies);
        }

        public void validateBot() throws TotalNumberOfBotException {
            long count = this.playerList.stream()
                    .filter(player -> player.getPlayerType() == PlayerType.BOT)
                    .count();

            if(count > 1){
                throw new TotalNumberOfBotException("Total Number of Bots cannot be greater than 1");
            }
        }

        public void validateSymbols() throws NonUniqueSymbolException {
            long count = this.playerList.stream()
                    .map(Player::getSymbol)
                    .map(Symbol::getSymbol)
                    .distinct()
                    .count();

            if(this.playerList.size() != count){
                throw new NonUniqueSymbolException("Give Unique Symbol to each player");
            }
        }

        public void validateSize() throws NoOfPlayerBoardSizeMisMatchException {
            if(this.playerList.size() != sizeOfBoard-1){
                throw new NoOfPlayerBoardSizeMisMatchException(this.playerList.size() + " players cannot play with the board size " + sizeOfBoard);
            }
        }
    }
}
