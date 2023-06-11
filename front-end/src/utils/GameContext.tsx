import React, { createContext, useContext, useEffect, useState } from "react";
import { RoundModel } from "./RoundModel";

export interface GameState {
  sessionId: string;
  gameData: RoundModel[];
  gameStats: number[];
}

interface GameContext {
  gamestate: GameState;
  updateGameContext: (newValue: GameState) => void;
}

const GameContext = createContext<GameContext | undefined>(undefined);

export function useGameContext(): GameContext {
  const context = useContext(GameContext);
  if (!context) {
    throw new Error("useGameContext must be used within a GameContextProvider");
  }
  return context;
}

export function GameContextProvider({
  children,
}: {
  children: React.ReactNode;
}) {
  const [gamestate, setGamestate] = useState<GameState>(() => {
    const storedState = localStorage.getItem("gamestate");
    return storedState
      ? JSON.parse(storedState)
      : { sessionId: "", gameData: [] };
  });

  useEffect(() => {
    localStorage.setItem("gamestate", JSON.stringify(gamestate));
  }, [gamestate]);

  const updateGameContext = (newValue: GameState) => {
    setGamestate(newValue);
  };

  return (
    <GameContext.Provider value={{ gamestate, updateGameContext }}>
      {children}
    </GameContext.Provider>
  );
}
