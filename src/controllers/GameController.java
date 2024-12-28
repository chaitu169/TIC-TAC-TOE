package controllers;

import Models.*;
import Strategies.WinningStrategy.WinningStrategy;
import exceptions.InvalidInputException;
import exceptions.NoOfPlayerBoardSizeMisMatchException;
import exceptions.NonUniqueSymbolException;
import exceptions.TotalNumberOfBotException;

import java.util.List;

public class GameController {
    public Game startGame(List<Player> players, int sizeOfBoard, List<WinningStrategy> winningStrategies) throws TotalNumberOfBotException, NoOfPlayerBoardSizeMisMatchException, NonUniqueSymbolException {
        return Game.getGameBuilder()
                .setPlayerList(players)
                .setSizeOfBoard(sizeOfBoard)
                .setGameWinningStrategies(winningStrategies)
                .build();
    }

    public GameState getGameState(Game game){
        return game.getGameState();
    }

    public void displayBoard(Game game){
        game.display();
    }

    public void makeMove(Game game) throws InvalidInputException {
        game.makeMove();
    }

    public Player getWinner(Game game){
        return game.getWinner();
    }
}
