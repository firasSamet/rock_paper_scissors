import React from "react";
import { render, screen } from "@testing-library/react";

import { Button } from "./Button";
import { ButtonTypes } from "../../utils/Constants";

describe("Button component", () => {
  test("should render a primary button correctly", () => {
    render(
      <Button
        type={ButtonTypes.PRIMARY}
        title="primary"
        onClick={() => undefined}
      />
    );
    const buttonElement = screen.getByText(/primary/i);
    expect(buttonElement.classList.contains("button-primary")).toBe(true);
    expect(buttonElement.classList.contains("button-secondary")).toBe(false);
  });

  test("should render a secondary button correctly", () => {
    render(
      <Button
        type={ButtonTypes.SECONDARY}
        title="secondary"
        onClick={() => undefined}
      />
    );
    const buttonElement = screen.getByText(/secondary/i);
    expect(buttonElement.classList.contains("button-primary")).toBe(false);
    expect(buttonElement.classList.contains("button-secondary")).toBe(true);
  });

  test("should call onClick when button is clicked", () => {
    const onclickMock = jest.fn();
    render(
      <Button
        type={ButtonTypes.PRIMARY}
        title="primary"
        onClick={onclickMock}
      />
    );
    const buttonElement = screen.getByText(/primary/i);
    buttonElement.click();
    expect(onclickMock).toBeCalled();
    expect(onclickMock).toBeCalledTimes(1);
  });
});
