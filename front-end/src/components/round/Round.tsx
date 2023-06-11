import React from "react";

import { RoundResult } from "./RoundResult";

import { Button } from "../button/Button";
import { ButtonTypes } from "../../utils/Constants";
import useGameActions from "../../utils/useGameActions";
import { useGameContext } from "../../utils/GameContext";

import "./Round.scss";

export const Round = () => {
  const { gamestate } = useGameContext();
  const { resetSession, playRound } = useGameActions();

  const onPlayRoundClick = () => {
    playRound();
  };

  const onResetSessionClick = () => {
    resetSession();
  };

  return (
    <>
      <RoundResult roundResults={gamestate?.gameData?.at(-1)} />
      <Button
        type={ButtonTypes.SECONDARY}
        title="Reset results"
        onClick={onResetSessionClick}
      />
      <Button
        type={ButtonTypes.PRIMARY}
        title="Play new round"
        onClick={onPlayRoundClick}
      />
    </>
  );
};
