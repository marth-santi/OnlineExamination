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
  Radio,
  RadioGroup,
} from "@material-ui/core";
import { lightBlue, green } from "@material-ui/core/colors";
import { JsxEmit } from "typescript";

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
  option: {
    minWidth: 200,
  },
});

function Question(props: IQuestion): ReactElement {
  const classes = useStyles();
  const [selectedSingleValue, setSelectedSingleValue] = React.useState("0");
  const [selectedMultipleValue, setSelectedMultipleValue] = React.useState({
    selectedOp1: false,
    selectedOp2: false,
    selectedOp3: false,
    selectedOp4: false,
  });

  const handleSingleChoice = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSelectedSingleValue(event.target.value);
  };

  const handleMultipleChoice = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSelectedMultipleValue({
      ...selectedMultipleValue,
      [event.target.name]: event.target.checked,
    });
  };

  const renderQuestion = (): JSX.Element => {
    if (props.isMultiple)
      // Multiple Choice Question
      return (
        <AccordionDetails>
          <FormControl component="fieldset" className={classes.formControl}>
            <FormGroup>
              <FormControlLabel
                control={
                  <Checkbox
                    name="selectedOp1"
                    color="primary"
                    checked={selectedMultipleValue.selectedOp1}
                    onChange={handleMultipleChoice}
                  />
                }
                label={props.op1}
                className={classes.option}
              />
              <FormControlLabel
                control={
                  <Checkbox
                    name="selectedOp2"
                    color="primary"
                    checked={selectedMultipleValue.selectedOp2}
                    onChange={handleMultipleChoice}
                  />
                }
                label={props.op2}
                className={classes.option}
              />
              <FormControlLabel
                control={
                  <Checkbox
                    name="selectedOp3"
                    color="primary"
                    checked={selectedMultipleValue.selectedOp3}
                    onChange={handleMultipleChoice}
                  />
                }
                label={props.op3}
                className={classes.option}
              />
              <FormControlLabel
                control={
                  <Checkbox
                    name="selectedOp4"
                    color="primary"
                    checked={selectedMultipleValue.selectedOp4}
                    onChange={handleMultipleChoice}
                  />
                }
                label={props.op4}
                className={classes.option}
              />
            </FormGroup>
          </FormControl>
        </AccordionDetails>
      );

    // Single Choice Question
    return (
      <AccordionDetails>
        <FormControl component="fieldset" className={classes.formControl}>
          <RadioGroup>
            <FormControlLabel
              control={
                <Radio
                  name="op1"
                  color="primary"
                  value="1"
                  checked={selectedSingleValue === "1"}
                  onChange={handleSingleChoice}
                />
              }
              label={props.op1}
              className={classes.option}
            />
            <FormControlLabel
              control={
                <Radio
                  name="op2"
                  color="primary"
                  value="2"
                  checked={selectedSingleValue === "2"}
                  onChange={handleSingleChoice}
                />
              }
              label={props.op2}
              className={classes.option}
            />
            <FormControlLabel
              control={
                <Radio
                  name="op3"
                  color="primary"
                  value="3"
                  checked={selectedSingleValue === "3"}
                  onChange={handleSingleChoice}
                />
              }
              label={props.op3}
              className={classes.option}
            />
            <FormControlLabel
              control={
                <Radio
                  name="op4"
                  color="primary"
                  value="4"
                  checked={selectedSingleValue === "4"}
                  onChange={handleSingleChoice}
                />
              }
              label={props.op4}
              className={classes.option}
            />
          </RadioGroup>
        </FormControl>
      </AccordionDetails>
    );
  };

  return (
    <>
      <Accordion className={classes.root}>
        <AccordionSummary className={classes.question}>
          <Typography variant="h5" component="h2">
            <Typography> Question {props.id} :</Typography> {props.question}
          </Typography>
        </AccordionSummary>
        {renderQuestion()}
      </Accordion>
      <br />
    </>
  );
}

export default Question;
