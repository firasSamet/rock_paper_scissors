import React from "react";
import { render, screen } from "@testing-library/react";
import { Choice } from "../../utils/Constants";
import { RoundResult } from "./RoundResult";

describe("RoundResult component", () => {
  test("Should render correctly", () => {
    const wrapper = render(
      <RoundResult
        roundResults={{
          firstPlayerChoice: Choice.PAPER,
          secondPlayerChoice: Choice.ROCK,
          gameResult: 1,
        }}
      />
    );

    expect(wrapper.baseElement).toMatchSnapshot();
  });

  test("should display correct winner", async () => {
    render(
      <RoundResult
        roundResults={{
          firstPlayerChoice: Choice.PAPER,
          secondPlayerChoice: Choice.ROCK,
          gameResult: 1,
        }}
      />
    );
    const winner = await screen.findByText(/PLAYER 1 WON/i);

    expect(winner.classList.contains("draw")).toBe(false);
  });

  test("should display a draw game correctly", async () => {
    render(
      <RoundResult
        roundResults={{
          firstPlayerChoice: Choice.ROCK,
          secondPlayerChoice: Choice.ROCK,
          gameResult: 0,
        }}
      />
    );
    const winner = await screen.findByText(/DRAW/i);

    expect(winner.classList.contains("draw")).toBe(true);
  });

  test("should not display a result if there is no round data", () => {
    render(<RoundResult />);
    const winner = screen.queryByText(/DRAW/i);

    expect(winner).toBeFalsy();
  });
});
