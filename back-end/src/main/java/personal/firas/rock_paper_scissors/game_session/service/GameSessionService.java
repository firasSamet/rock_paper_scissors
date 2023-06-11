package personal.firas.rock_paper_scissors.game_session.service;

import personal.firas.rock_paper_scissors.game.model.Round;
import personal.firas.rock_paper_scissors.game_session.model.GameSession;

import java.util.ArrayList;
import java.util.UUID;

public interface GameSessionService {
    // creates a game session
    GameSession createGameSession();

    // returns list of rounds in a given session
    ArrayList<Round> getRoundsInSession(UUID sessionID);

    // adds a round to a given session and saves it total rounds
    void addRound(UUID sessionID, Round round);

    // empties the rounds list for a given session
    void resetGameSession(UUID sessionID);

}
