import React from "react";

import "./PlayerChoice.scss";

interface PlayerChoiceProps {
  choice?: string;
  player: number;
}

export const PlayerChoice = ({ choice, player }: PlayerChoiceProps) => (
  <div className="player-choice-wrapper">
    <div className="player-choice-container">
      <div>
        <h3>Player {player}</h3>
        <p>{choice}</p>
      </div>
    </div>
  </div>
);
