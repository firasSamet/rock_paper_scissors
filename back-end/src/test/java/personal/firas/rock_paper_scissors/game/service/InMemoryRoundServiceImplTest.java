package personal.firas.rock_paper_scissors.game.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import personal.firas.rock_paper_scissors.game.model.PlayerChoice;
import personal.firas.rock_paper_scissors.game.model.Round;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InMemoryRoundServiceImplTest {
    private InMemoryRoundServiceImpl roundService;

    @BeforeEach
    void setUp() {
        roundService = new InMemoryRoundServiceImpl();
    }

    @Test
    void createAutomatedRound_ReturnsRoundWithRandomChoiceForFirstPlayerAndRockForSecondPlayer() {
        // Act
        Round round = roundService.createAutomatedRound();

        // Assert
        assertEquals(PlayerChoice.ROCK, round.getSecondPlayerChoice());
        PlayerChoice.randomChoice();
    }
}

