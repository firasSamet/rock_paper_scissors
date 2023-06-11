import { GameState, useGameContext } from './GameContext';

import {api} from './Api';
import { useEffect } from 'react';

const useGameActions = () => {
  const { gamestate, updateGameContext } = useGameContext();

  const getSessionId = async () => {
    if (!gamestate?.sessionId) {
      try {
        const response = await api.getSessionId();
        const sessionId = response.data.sessionID;
        updateGameContext({ ...gamestate, sessionId });
      } catch (error) {
        console.error('Error retrieving session ID:', error);
      }
    }
  };

  const resetSession = async () => {
    try {
      await api.resetSession(gamestate.sessionId);
      updateGameContext({ ...gamestate, gameData: [] });
    } catch (error) {
      console.error('Error resetting session:', error);
    }
  };

  const playRound = async () => {
    try {
      const response = await api.playRound(gamestate.sessionId);
      const rounds = response.data;
      updateGameContext({ ...gamestate, gameData: rounds });
      return rounds.at(-1);
    } catch (error) {
      console.error('Error playing round:', error);
    }
  };

  const getRounds = async () => {
    try {
      const response = await api.getRounds(gamestate.sessionId);
      updateGameContext({ ...gamestate, gameData: response.data });
    } catch (error) {
      console.error('Error retrieving rounds:', error);
    }
  };

  const getStats = async () => {
    try {
      const response = await api.getStats();
      // @ts-expect-error this will fail Typescript unable to infer the correct type
      updateGameContext((prevState: GameState) => ({ ...prevState, gameStats: response.data }));
    } catch (error) {
      console.error('Error retrieving rounds:', error);
    }
  }

  useEffect(() => {
    const intervalId = setInterval(getStats, 1000);
    return () => clearInterval(intervalId);
    }, []);
  
  return { getSessionId, resetSession, playRound, getRounds, getStats };
};

export default useGameActions;
