import Models.*;
import Strategies.WinningStrategy.ColumnWinningStrategy;
import Strategies.WinningStrategy.WinningStrategy;
import controllers.GameController;
import exceptions.InvalidInputException;
import exceptions.NoOfPlayerBoardSizeMisMatchException;
import exceptions.NonUniqueSymbolException;
import exceptions.TotalNumberOfBotException;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws TotalNumberOfBotException, NoOfPlayerBoardSizeMisMatchException, NonUniqueSymbolException, InvalidInputException {
        List<Player> players = List.of(
                new Player("chaitu", 1L, PlayerType.HUMAN, new Symbol('X')),
                new Player("maya", 2L, PlayerType.HUMAN, new Symbol('O'))
                //new Bot("GPT", 2L, new Symbol('O'), BotDifficultyLevel.EASY)
                );

        int sizeOfBoard = 3;

        List<WinningStrategy> winningStrategies = List.of(new ColumnWinningStrategy());

        GameController gameController = new GameController();

        Game game = gameController.startGame(players, sizeOfBoard, winningStrategies);

        while(gameController.getGameState(game).equals(GameState.IN_PROGRESS)){
            gameController.displayBoard(game);
            gameController.makeMove(game);
        }

        if(gameController.getGameState(game).equals(GameState.DRAW)){
            System.out.println("Game is declared draw");
        }

        if(gameController.getGameState(game).equals(GameState.WIN)){
            System.out.println();
            gameController.displayBoard(game);
            System.out.println();
            System.out.println("Game is completed and the winner is " + gameController.getWinner(game).getName());
        }
    }
}