package Models;

import Strategies.BotPlayingStrategy.BotPlayingStrategy;

public class Bot extends Player{
    private BotDifficultyLevel botDifficultyLevel;
    private BotPlayingStrategy botPlayingStrategy;
    public Bot(String name, Long id, Symbol symbol, BotDifficultyLevel botDifficultyLevel) {
        super(name, id, PlayerType.BOT, symbol);
        this.botDifficultyLevel = botDifficultyLevel;
    }
}
