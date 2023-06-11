package personal.firas.rock_paper_scissors.game_session.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import personal.firas.rock_paper_scissors.game_session.model.GameSession;
import personal.firas.rock_paper_scissors.game_session.service.InMemoryGameSessionServiceImpl;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class GameSessionControllerTest {

    @Mock
    private InMemoryGameSessionServiceImpl inMemoryGameSessionService;

    @InjectMocks
    private GameSessionController gameSessionController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("createSession should return a CREATED response and return gameSession")
    void createSession() {
        // Arrange
        GameSession gameSession = new GameSession();
        when(inMemoryGameSessionService.createGameSession()).thenReturn(gameSession);

        // Act
        ResponseEntity<Object> response = gameSessionController.createSession();

        // Assert
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(gameSession, response.getBody());
        verify(inMemoryGameSessionService).createGameSession();
    }

    @Test
    @DisplayName("resetSession should return a OK response if sessionId is valid")
    void resetSessionWithValidSessionId() {
        // Arrange
        String sessionId = "00000000-0000-0000-0000-000000000000";

        // Act
        ResponseEntity<Object> response = gameSessionController.resetSession(sessionId);

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Session reset", response.getBody());
        verify(inMemoryGameSessionService).resetGameSession(UUID.fromString(sessionId));
    }

    @Test
    @DisplayName("resetSession should return a BAD_REQUEST response if sessionId is invalid")
    void resetSession_InvalidSessionId_ReturnsBadRequestResponse() {
        // Arrange
        String sessionId = "invalid-session-id";

        // Act
        ResponseEntity<Object> response = gameSessionController.resetSession(sessionId);

        // Assert
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("INVALID SESSION ID", response.getBody());
    }
}
