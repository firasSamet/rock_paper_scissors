package personal.firas.rock_paper_scissors.game.model;

import java.util.Random;

public enum PlayerChoice {
    ROCK, PAPER, SCISSOR;

    private static final Random RNG = new Random();

    public static PlayerChoice randomChoice()  {
        PlayerChoice[] choices = values();
        return choices[RNG.nextInt(choices.length)];
    }
}
