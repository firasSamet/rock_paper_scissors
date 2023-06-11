import React from "react";
import { useGameContext } from "../../utils/GameContext";

import "./ResultsTable.scss";

export const StatisticsTable = () => {
  const { gamestate } = useGameContext();

  return (
    <div className="table-container">
      <table className="table">
        <thead>
          <tr>
            <th>Draw</th>
            <th>Player 1</th>
            <th>Player 2</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>{gamestate?.gameStats?.[0]}</td>
            <td>{gamestate?.gameStats?.[1]}</td>
            <td>{gamestate?.gameStats?.[2]}</td>
          </tr>
        </tbody>
      </table>
    </div>
  );
};
