package personal.firas.rock_paper_scissors.game_session.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.firas.rock_paper_scissors.game_session.model.GameSession;
import personal.firas.rock_paper_scissors.game_session.service.InMemoryGameSessionServiceImpl;

import java.util.UUID;

@CrossOrigin(origins = "http://localhost", allowedHeaders = "*")
@RestController
@RequestMapping("/api/session")
public class GameSessionController {

    @Autowired
    private InMemoryGameSessionServiceImpl inMemoryGameSessionService;

    @PostMapping()
    public ResponseEntity<Object> createSession() {
        GameSession gameSession = inMemoryGameSessionService.createGameSession();
        return new ResponseEntity<>(gameSession, HttpStatus.CREATED);
    }

    @DeleteMapping("/{sessionId}")
    public ResponseEntity<Object> resetSession(@PathVariable("sessionId") String sessionId) {
        try {
            inMemoryGameSessionService.resetGameSession(UUID.fromString(sessionId));
        }
        catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("INVALID SESSION ID", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Session reset", HttpStatus.OK);
    }


    @GetMapping()
    public ResponseEntity<Object> getStatistics() {

        return new ResponseEntity<>(inMemoryGameSessionService.getStats(), HttpStatus.OK);
    }

}
