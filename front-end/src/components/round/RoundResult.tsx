import React from "react";
import classnames from "classnames";

import { PlayerChoice } from "../playerChoice/PlayerChoice";

import { RoundModel } from "../../utils/RoundModel";

import "./Round.scss";
import { parseResult } from "../../utils/Utils";

interface RoundResultProps {
  roundResults: RoundModel;
}

export const RoundResult = ({ roundResults }: RoundResultProps) => {
  const gameResultClassNames = classnames("game-result", {
    draw: !roundResults?.gameResult,
  });

  return (
    <div className="round-wrapper">
      <div className="round-container">
        <PlayerChoice choice={roundResults?.firstPlayerChoice} player={1} />
        <PlayerChoice choice={roundResults?.secondPlayerChoice} player={2} />
      </div>
      {!!roundResults?.firstPlayerChoice && (
        <h1 className={gameResultClassNames}>
          {parseResult(roundResults.gameResult)}
        </h1>
      )}
    </div>
  );
};
