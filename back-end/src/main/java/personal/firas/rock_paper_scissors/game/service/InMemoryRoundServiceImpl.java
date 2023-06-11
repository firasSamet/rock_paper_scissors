package personal.firas.rock_paper_scissors.game.service;

import org.springframework.stereotype.Service;
import personal.firas.rock_paper_scissors.game.model.PlayerChoice;
import personal.firas.rock_paper_scissors.game.model.Round;

@Service
public class InMemoryRoundServiceImpl implements RoundService{
    @Override
    public Round createAutomatedRound() {
        PlayerChoice firstPlayerChoice = PlayerChoice.randomChoice();
        PlayerChoice secondPlayerChoice = PlayerChoice.ROCK;

        return new Round(firstPlayerChoice, secondPlayerChoice);
    }
}
