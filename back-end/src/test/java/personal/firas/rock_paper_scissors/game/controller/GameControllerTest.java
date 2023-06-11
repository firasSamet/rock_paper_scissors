package personal.firas.rock_paper_scissors.game.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import personal.firas.rock_paper_scissors.game.model.PlayerChoice;
import personal.firas.rock_paper_scissors.game.model.Round;
import personal.firas.rock_paper_scissors.game.service.InMemoryRoundServiceImpl;
import personal.firas.rock_paper_scissors.game_session.service.InMemoryGameSessionServiceImpl;

import java.util.ArrayList;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameControllerTest {
    @Mock
    private InMemoryGameSessionServiceImpl inMemoryGameSessionService;

    @Mock
    private InMemoryRoundServiceImpl inMemoryRoundServiceImpl;

    @InjectMocks
    private GameController gameController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("createGame should return a CREATED response if sessionId is valid")
    void createGameWithValidSessionId() {
        // Arrange
        String sessionId = "00000000-0000-0000-0000-000000000000";
        UUID gameSessionId = UUID.fromString(sessionId);
        Round round = new Round(PlayerChoice.ROCK, PlayerChoice.SCISSOR);
        ArrayList<Round> rounds = new ArrayList<>();
        rounds.add(round);

        when(inMemoryRoundServiceImpl.createAutomatedRound()).thenReturn(round);
        when(inMemoryGameSessionService.getRoundsInSession(gameSessionId)).thenReturn(rounds);

        // Act
        ResponseEntity<Object> response = gameController.createGame(sessionId);

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(rounds, response.getBody());
        verify(inMemoryGameSessionService).addRound(gameSessionId, round);
    }

    @Test
    @DisplayName("createGame should return a BAD_REQUEST response if sessionId is invalid")
    void createGameWithInvalidSessionId() {
        // Arrange
        String sessionId = "invalid-session-id";

        // Act
        ResponseEntity<Object> response = gameController.createGame(sessionId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("INVALID SESSION ID", response.getBody());
    }

    @Test
    @DisplayName("getRoundsForSession should return a OK response if sessionId is valid")
    void getRoundsForSessionWithValidSessionId() {
        // Arrange
        String sessionId = "00000000-0000-0000-0000-000000000000";
        UUID gameSessionId = UUID.fromString(sessionId);
        ArrayList<Round> rounds = new ArrayList<>();

        when(inMemoryGameSessionService.getRoundsInSession(gameSessionId)).thenReturn(rounds);

        // Act
        ResponseEntity<Object> response = gameController.getRoundsForSession(sessionId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(rounds, response.getBody());
    }

    @Test
    @DisplayName("getRoundsForSession should return a BAD_REQUEST response if sessionId is invalid")
    void getRoundsForSessionWithInvalidSessionId() {
        // Arrange
        String sessionId = "invalid-session-id";

        // Act
        ResponseEntity<Object> response = gameController.getRoundsForSession(sessionId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("INVALID SESSION ID", response.getBody());
    }

    @Test
    @DisplayName("resetSession should return a OK response if sessionId is valid")
    void resetSessionWithValidSessionId() {
        // Arrange
        String sessionId = "00000000-0000-0000-0000-000000000000";

        // Act
        ResponseEntity<Object> response = gameController.resetSession(sessionId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Session reset", response.getBody());
        verify(inMemoryGameSessionService).resetGameSession(UUID.fromString(sessionId));
    }

    @Test
    @DisplayName("resetSession should return a BAD_REQUEST response if sessionId is invalid")
    void resetSessionWithInvalidSessionId() {
        // Arrange
        String sessionId = "invalid-session-id";

        // Act
        ResponseEntity<Object> response = gameController.resetSession(sessionId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("INVALID SESSION ID", response.getBody());
    }
}
