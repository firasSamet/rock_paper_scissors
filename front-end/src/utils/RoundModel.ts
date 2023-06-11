import { Choice } from "./Constants";

export interface RoundModel {
    firstPlayerChoice: Choice
    secondPlayerChoice: Choice
    gameResult: number
}