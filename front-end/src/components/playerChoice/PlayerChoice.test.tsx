import React from "react";
import { render, screen } from "@testing-library/react";
import { PlayerChoice } from "./PlayerChoice";
import { Choice } from "../../utils/Constants";

describe("Button component", () => {
  test("should render a player choice card correctly if there is a choice", () => {
    render(<PlayerChoice player={1} choice={Choice.PAPER} />);

    const choice = screen.queryByText(/PAPER/i);

    expect(choice.textContent).toBe("PAPER");
  });

  test("should render a player choice card correctly if there is no choice", () => {
    render(<PlayerChoice player={1} />);

    const player = screen.queryByText(/Player 1/i);
    const choice = screen.queryByText(/PAPER/i);

    expect(choice).toBeFalsy();
    expect(player.textContent).toBe("Player 1");
  });
});
