package personal.firas.rock_paper_scissors.game.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import personal.firas.rock_paper_scissors.game.model.Round;
import personal.firas.rock_paper_scissors.game.service.InMemoryRoundServiceImpl;
import personal.firas.rock_paper_scissors.game_session.service.InMemoryGameSessionServiceImpl;

import java.util.ArrayList;
import java.util.UUID;

@CrossOrigin(origins = "http://localhost", allowedHeaders = "*")
@RestController
@RequestMapping("/api/games/{sessionId}")
public class GameController {
    @Autowired
    private InMemoryGameSessionServiceImpl inMemoryGameSessionService;

    @Autowired
    private InMemoryRoundServiceImpl inMemoryRoundServiceImpl;

    @PostMapping()
    public ResponseEntity<Object> createGame(@PathVariable("sessionId") String sessionId) {
        UUID gameSessionId;
        try {
            gameSessionId = UUID.fromString(sessionId);
        }
         catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("INVALID SESSION ID", HttpStatus.BAD_REQUEST);
        }

        Round round = inMemoryRoundServiceImpl.createAutomatedRound();
        inMemoryGameSessionService.addRound(gameSessionId, round);

        return new ResponseEntity<>(inMemoryGameSessionService.getRoundsInSession(gameSessionId), HttpStatus.CREATED);
    }

    @GetMapping()
    public ResponseEntity<Object> getRoundsForSession(@PathVariable("sessionId") String sessionId) {
        UUID gameSessionId;
        try {
            gameSessionId = UUID.fromString(sessionId);
        }
        catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("INVALID SESSION ID", HttpStatus.BAD_REQUEST);
        }

        ArrayList<Round> rounds = inMemoryGameSessionService.getRoundsInSession(gameSessionId);

        return new ResponseEntity<>(rounds, HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<Object> resetSession(@PathVariable("sessionId") String sessionId) {
        try {
            inMemoryGameSessionService.resetGameSession(UUID.fromString(sessionId));
        }
        catch (IllegalArgumentException exception) {
            return new ResponseEntity<>("INVALID SESSION ID", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>("Session reset", HttpStatus.OK);
    }


}
