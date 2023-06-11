import React from "react";

import { ButtonTypes } from "../../utils/Constants";

import "./Button.scss";

interface ButtonProps {
  type: ButtonTypes;
  title: string;
  onClick: () => void;
}

export const Button = ({ type, title, onClick }: ButtonProps) => {
  const className = `button-base button-${type}`;

  return (
    <button className={className} onClick={onClick}>
      {title}
    </button>
  );
};
