import { Box, Fab, makeStyles, Paper, Typography } from "@material-ui/core";
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

const useStyles = makeStyles({
  clock: {
    margin: 10,
    padding: 10,
    top: "auto",
    right: 20,
    bottom: 20,
    left: "auto",
    position: "fixed",
    zIndex: 9999,
  },
  minute: {},
  second: {},
});

function Countdown(props: Props) {
  const classes = useStyles();
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
    <Paper className={classes.clock} elevation={5}>
      <Box fontWeight={500}>Remaining Time</Box>
      <Typography>
        {state.remainingMinute} : {state.remainingSecond}
      </Typography>
    </Paper>
  );
}

export default Countdown;
