import React from "react";

import { Round } from "../components/round/Round";
import { ResultsTable } from "../components/resultsTable/ResultsTable";
import { GameContextProvider } from "../utils/GameContext";

import "./MainPage.scss";

export const MainPage = () => {
  return (
    <GameContextProvider>
      <div className="main-page-container">
        <Round />
        <ResultsTable />
      </div>
    </GameContextProvider>
  );
};
