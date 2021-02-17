import React, { ReactElement, useEffect } from "react";
import { IQuestion, IQuestionResponse } from "models/ExamModels";
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

interface Props extends IQuestion {
  updateResponse: (response: IQuestionResponse) => void;
}

function Question(props: Props): ReactElement {
  const classes = useStyles();
  const initValueSelected = {
    isCheckedOp1: false,
    isCheckedOp2: false,
    isCheckedOp3: false,
    isCheckedOp4: false,
  };
  const [selectedSingleValue, setSelectedSingleValue] = React.useState(
    initValueSelected
  );
  const [selectedMultipleValue, setSelectedMultipleValue] = React.useState(
    initValueSelected
  );
  // Update response up to Exam
  useEffect(() => {
    onResponseChange();
  }, [selectedSingleValue, selectedMultipleValue]);

  const handleSingleChoice = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSelectedSingleValue({
      ...initValueSelected,
      [event.target.name]: event.target.checked,
    });
  };

  const handleMultipleChoice = (event: React.ChangeEvent<HTMLInputElement>) => {
    setSelectedMultipleValue({
      ...selectedMultipleValue,
      [event.target.name]: event.target.checked,
    });
  };

  const onResponseChange = () => {
    let response: IQuestionResponse;
    // Assign Single value choice by Default
    response = {
      ...props,
      ...selectedSingleValue,
    };

    // If this question is Multiple choice, update the response with Correct variable (contains multiple selection)
    if (props.isMultiple) response = { ...response, ...selectedMultipleValue };
    props.updateResponse(response);
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
                    name="isCheckedOp1"
                    color="primary"
                    checked={selectedMultipleValue.isCheckedOp1}
                    onChange={handleMultipleChoice}
                  />
                }
                label={props.op1}
                className={classes.option}
              />
              <FormControlLabel
                control={
                  <Checkbox
                    name="isCheckedOp2"
                    color="primary"
                    checked={selectedMultipleValue.isCheckedOp2}
                    onChange={handleMultipleChoice}
                  />
                }
                label={props.op2}
                className={classes.option}
              />
              <FormControlLabel
                control={
                  <Checkbox
                    name="isCheckedOp3"
                    color="primary"
                    checked={selectedMultipleValue.isCheckedOp3}
                    onChange={handleMultipleChoice}
                  />
                }
                label={props.op3}
                className={classes.option}
              />
              <FormControlLabel
                control={
                  <Checkbox
                    name="isCheckedOp4"
                    color="primary"
                    checked={selectedMultipleValue.isCheckedOp4}
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
                  name="isCheckedOp1"
                  color="primary"
                  value="1"
                  checked={selectedSingleValue.isCheckedOp1}
                  onChange={handleSingleChoice}
                />
              }
              label={props.op1}
              className={classes.option}
            />
            <FormControlLabel
              control={
                <Radio
                  name="isCheckedOp2"
                  color="primary"
                  value="2"
                  checked={selectedSingleValue.isCheckedOp2}
                  onChange={handleSingleChoice}
                />
              }
              label={props.op2}
              className={classes.option}
            />
            <FormControlLabel
              control={
                <Radio
                  name="isCheckedOp3"
                  color="primary"
                  value="3"
                  checked={selectedSingleValue.isCheckedOp3}
                  onChange={handleSingleChoice}
                />
              }
              label={props.op3}
              className={classes.option}
            />
            <FormControlLabel
              control={
                <Radio
                  name="isCheckedOp4"
                  color="primary"
                  value="4"
                  checked={selectedSingleValue.isCheckedOp4}
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

  // ==== Final return element ====
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
