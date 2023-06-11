package personal.firas.rock_paper_scissors.game_session.model;

import personal.firas.rock_paper_scissors.game.model.Round;

import java.util.ArrayList;
import java.util.UUID;

public class GameSession {
    private ArrayList<Round> rounds;
    private final UUID sessionID;

    public GameSession() {
        this.sessionID = UUID.randomUUID();
        this.rounds = new ArrayList<>();
    }

    public ArrayList<Round> getRounds() {
        return rounds;
    }

    public void setRounds(ArrayList<Round> rounds) {
        this.rounds = rounds;
    }

    public UUID getSessionID() {
        return sessionID;
    }

}
