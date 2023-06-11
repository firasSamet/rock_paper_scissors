package personal.firas.rock_paper_scissors.game.model;

import java.util.Objects;
import java.util.UUID;

public class Round {
    private final UUID roundId;
    private final PlayerChoice firstPlayerChoice;
    private final PlayerChoice secondPlayerChoice;
    private final int gameResult; // 0 for draw, 1 for first player won, 2 for second player won

    private int getGameResult(PlayerChoice player1Choice, PlayerChoice player2Choice){
        int firstPlayerChoiceIndex = PlayerChoice.valueOf(player1Choice.name()).ordinal();
        int secondPlayerChoiceIndex = PlayerChoice.valueOf(player2Choice.name()).ordinal();
        int choicesIndexDifference = firstPlayerChoiceIndex - secondPlayerChoiceIndex;
        return switch (choicesIndexDifference) {
            case 0 -> 0;
            case 1, -2 -> 1;
            default -> 2;
        };
    }

    public Round(PlayerChoice firstPlayerChoice, PlayerChoice secondPlayerChoice){
        super();
        this.roundId = UUID.randomUUID();
        this.firstPlayerChoice=firstPlayerChoice;
        this.secondPlayerChoice=secondPlayerChoice;
        this.gameResult=this.getGameResult(firstPlayerChoice, secondPlayerChoice);
    }


    public UUID getRoundId() {
        return roundId;
    }

    public PlayerChoice getFirstPlayerChoice() {
        return firstPlayerChoice;
    }
    public PlayerChoice getSecondPlayerChoice() {
        return secondPlayerChoice;
    }

    public int getGameResult() {
        return gameResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Round round = (Round) o;
        return gameResult == round.gameResult && Objects.equals(roundId, round.roundId) && firstPlayerChoice == round.firstPlayerChoice && secondPlayerChoice == round.secondPlayerChoice;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roundId, firstPlayerChoice, secondPlayerChoice, gameResult);
    }
}
