import React from "react";

import { Round } from "../components/round/Round";
import { ResultsTable } from "../components/resultsTable/ResultsTable";
import { GameContextProvider } from "../utils/GameContext";
import { StatisticsTable } from "../components/resultsTable/StatisticsTable";

import "./MainPage.scss";

export const MainPage = () => {
  return (
    <GameContextProvider>
      <div className="main-page-container">
        <Round />
        <ResultsTable />
      </div>
      <div>
        <StatisticsTable />
      </div>
    </GameContextProvider>
  );
};
