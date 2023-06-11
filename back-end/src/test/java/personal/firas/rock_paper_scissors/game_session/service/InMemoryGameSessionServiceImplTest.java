package personal.firas.rock_paper_scissors.game_session.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import personal.firas.rock_paper_scissors.game.model.PlayerChoice;
import personal.firas.rock_paper_scissors.game.model.Round;
import personal.firas.rock_paper_scissors.game_session.model.GameSession;


class InMemoryGameSessionServiceImplTest {
    private GameSessionService gameSessionService;
    private GameSession gameSession;
    private Round testRound = new Round(PlayerChoice.ROCK, PlayerChoice.SCISSOR);
    private Round testRound1 = new Round(PlayerChoice.PAPER, PlayerChoice.SCISSOR);

    @BeforeEach
    void setUp() {
        gameSessionService = new InMemoryGameSessionServiceImpl();

        gameSession = gameSessionService.createGameSession();
        gameSessionService.addRound(gameSession.getSessionID(), testRound);
    }

    @Test
    @DisplayName("Should create a game session with empty rounds")
    void createGameSession() {
        GameSession gs = gameSessionService.createGameSession();
        assert gs.getRounds().isEmpty();
    }

    @Test
    @DisplayName("Should return the played rounds in a session")
    void getRoundsInSession() {
        assert gameSessionService.getRoundsInSession(gameSession.getSessionID()).get(0).equals(testRound);
    }

    @Test
    @DisplayName("Should add a round to a session")
    void addRound() {
        gameSessionService.addRound(gameSession.getSessionID(), testRound1);

        assert gameSessionService.getRoundsInSession(gameSession.getSessionID()).size() == 2;
        assert gameSessionService.getRoundsInSession(gameSession.getSessionID()).get(0).equals(testRound);
        assert gameSessionService.getRoundsInSession(gameSession.getSessionID()).get(1).equals(testRound1);

    }

    @Test
    @DisplayName("Should remove all rounds from a session")
    void resetGameSession() {
        gameSessionService.resetGameSession(gameSession.getSessionID());

        assert gameSessionService.getRoundsInSession(gameSession.getSessionID()).size() == 0;
    }

}