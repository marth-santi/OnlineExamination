import { Box, Typography } from "@material-ui/core";
import React, { useEffect, useState } from "react";

type Props = {
  minutes: number;
  seconds?: number;
  action: () => void;
};

type State = {
  remainingMinute: number;
  remainingSecond: number;
};

function Countdown(props: Props) {
  const [state, setState] = useState({
    remainingMinute: props.minutes,
    remainingSecond: props.seconds ? props.seconds! : 0,
  });
  const [isTimeOver, setTimeOver] = useState(false);

  useEffect(() => {
    if (isTimeOver) props.action();
  }, [isTimeOver]);

  function countdown(state: State): State {
    let newState = { ...state };
    newState.remainingSecond--;
    if (newState.remainingSecond === 0 && newState.remainingMinute === 0) {
      if (!isTimeOver) setTimeOver(true);
    }
    if (newState.remainingSecond < 0) {
      newState.remainingSecond = 59;
      newState.remainingMinute--;
    }
    return newState;
  }

  useEffect(() => {
    var interval = setInterval(() => {
      setState(countdown);
      if (state.remainingMinute <= 0 && state.remainingSecond <= 0) {
        clearInterval(interval);
      }
    }, 1000);

    return () => {
      clearInterval(interval);
    };
  }, []);

  return (
    <>
      <Box fontWeight={500}>Remaining Time</Box>
      <Typography>
        {state.remainingMinute} : {state.remainingSecond}
      </Typography>
    </>
  );
}

export default Countdown;
