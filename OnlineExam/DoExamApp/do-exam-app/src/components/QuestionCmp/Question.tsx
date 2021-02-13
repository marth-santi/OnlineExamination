import React, { ReactElement } from "react";
import { IQuestion, IQuestionResponse } from "models/ExamModels";
import Card from "@material-ui/core/Card";
import CardActionArea from "@material-ui/core/CardActionArea";
import CardActions from "@material-ui/core/CardActions";
import Typography from "@material-ui/core/Typography";
import FormControl from "@material-ui/core/FormControl";
import FormGroup from "@material-ui/core/FormGroup";
import Checkbox from "@material-ui/core/Checkbox";
import {
  Accordion,
  AccordionDetails,
  AccordionSummary,
  FormControlLabel,
  makeStyles,
} from "@material-ui/core";
import { lightBlue, green } from "@material-ui/core/colors";

const useStyles = makeStyles({
  root: {
    margin: 10,
  },
  formControl: {
    padding: 5,
  },
  questionLabel: {
    fontWeight: "bolder",
  },
  question: {
    backgroundColor: green[600],
    color: lightBlue[50],
    padding: 5,
    paddingLeft: 10,
    textAlign: "left",
  },
  option: {},
});

function Question(props: IQuestion): ReactElement {
  const classes = useStyles();

  return (
    <>
      <Accordion className={classes.root}>
        <AccordionSummary className={classes.question}>
          <Typography variant="h5" component="h2">
            <Typography> Question {props.id} :</Typography> {props.question}
          </Typography>
        </AccordionSummary>
        <AccordionDetails>
          <FormControl component="fieldset" className={classes.formControl}>
            <FormGroup>
              <FormControlLabel
                control={<Checkbox name="op1" color="primary" />}
                label={props.op1}
                className={classes.option}
              />
              <FormControlLabel
                control={<Checkbox name="op2" color="primary" />}
                label={props.op2}
                className={classes.option}
              />
              <FormControlLabel
                control={<Checkbox name="op3" color="primary" />}
                label={props.op3}
                className={classes.option}
              />
              <FormControlLabel
                control={<Checkbox name="op4" color="primary" />}
                label={props.op4}
                className={classes.option}
              />
            </FormGroup>
          </FormControl>
        </AccordionDetails>
      </Accordion>
      <br />
    </>
  );
}

export default Question;
