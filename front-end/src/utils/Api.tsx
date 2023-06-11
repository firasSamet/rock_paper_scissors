import axios from "axios";

const BASE_API = "http://localhost:8080/api";

const getSessionId = async () => await axios.post(`${BASE_API}/session`);

const resetSession = async (id: string) =>
  await axios.delete(`${BASE_API}/session/${id}`);

const playRound = async (id: string) =>
  await axios.post(`${BASE_API}/games/${id}`);

const getRounds = async (id: string) =>
  await axios.get(`${BASE_API}/games/${id}`);

const getStats = async () => await axios.get(`${BASE_API}/session`);

export const api = {
  getSessionId,
  resetSession,
  playRound,
  getRounds,
  getStats,
};
