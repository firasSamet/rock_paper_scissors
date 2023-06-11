import { useGameContext } from './GameContext';

import {api} from './Api';

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

  return { getSessionId, resetSession, playRound, getRounds };
};

export default useGameActions;
