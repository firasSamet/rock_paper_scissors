import React from "react";

import { parseResult } from "../../utils/Utils";
import { useGameContext } from "../../utils/GameContext";

import "./ResultsTable.scss";

export const ResultsTable = () => {
  const { gamestate } = useGameContext();
  return (
    <div className="table-container">
      <table className="table">
        <thead>
          <tr>
            <th>Player 1</th>
            <th>Player 2</th>
            <th>Result</th>
          </tr>
        </thead>
        <tbody>
          {gamestate?.gameData?.map((el, i) => (
            <tr key={i}>
              <td>{el.firstPlayerChoice}</td>
              <td>{el.secondPlayerChoice}</td>
              <td>{parseResult(el.gameResult)}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
};
