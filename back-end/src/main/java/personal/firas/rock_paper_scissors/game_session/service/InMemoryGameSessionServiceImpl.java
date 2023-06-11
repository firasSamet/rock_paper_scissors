package personal.firas.rock_paper_scissors.game_session.service;

import org.springframework.stereotype.Service;
import personal.firas.rock_paper_scissors.game.model.Round;
import personal.firas.rock_paper_scissors.game_session.model.GameSession;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class InMemoryGameSessionServiceImpl implements GameSessionService {
    private static final Map<UUID, GameSession> gameSessions = new HashMap<>();

    private static final Map<Integer, Integer> totalResults = new ConcurrentHashMap<>();

    @Override
    public GameSession createGameSession() {
        GameSession gameSession = new GameSession();
        gameSession.setRounds(new ArrayList<>());
        gameSessions.put(gameSession.getSessionID(), gameSession);
        return gameSession;
    }

    @Override
    public ArrayList<Round> getRoundsInSession(UUID sessionID) {
        GameSession gameSession = gameSessions.get(sessionID);
        return gameSession.getRounds();
    }

    @Override
    public void addRound(UUID sessionID, Round round) {
        GameSession gameSession = gameSessions.get(sessionID);
        totalResults.put(round.getGameResult(), totalResults.getOrDefault(round.getGameResult(), 0) + 1);
        gameSession.getRounds().add(round);
    }

    @Override
    public void resetGameSession(UUID sessionID) {
        GameSession gameSession = gameSessions.get(sessionID);
        gameSession.setRounds(new ArrayList<>());
    }

    @Override
    public Map<Integer, Integer> getStats() {
        return totalResults;
    }

}
