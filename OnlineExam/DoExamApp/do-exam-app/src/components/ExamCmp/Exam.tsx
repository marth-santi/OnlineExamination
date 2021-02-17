import Question from "components/QuestionCmp/Question";
import React, { useEffect, useState } from "react";
import CONSTANT from "CONST";
import { IExamResponse, IQuestionResponse, ISubject } from "models/ExamModels";
import API from "customModules/APIRequest";
import {
  Backdrop,
  Button,
  CircularProgress,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Grid,
  makeStyles,
  Paper,
} from "@material-ui/core";
import { Mark } from "models/Mark";
import Countdown from "components/Countdown/Countdown";

const useStyles = makeStyles({
  actionGroup: {
    marginBottom: 20,
  },
  submitButton: {
    marginTop: 5,
  },
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
});

function ExamView() {
  const classes = useStyles();
  const [subject, setSubject] = useState<ISubject>();
  const [questionResponses, setResponses] = useState<IQuestionResponse[]>();
  // Get questions of Exam
  useEffect(() => {
    API.get<IExamResponse>(CONSTANT.API.getExam).then(
      (exam: IExamResponse): void => {
        setResponses(exam.responses);
        setSubject(exam.subject);
      }
    );
  }, []);

  // Handle change from question response, reload questions
  const updateResponse = (response: IQuestionResponse): void => {
    setResponses((prevResponses) => {
      if (!prevResponses) return;

      let newResponses: IQuestionResponse[];
      newResponses = prevResponses;
      newResponses[response.id - 1] = response;
      return newResponses;
    });
  };

  // Handle Submit exam
  const handleSubmit = () => {
    console.log("Exam submit: ", questionResponses);
    setOpenSubmitDlg(false);
    setOpenSubmittedAlert(true);
    API.postJSON<IQuestionResponse[], Mark>(
      CONSTANT.API.submitExam,
      questionResponses!
    ).then((res: Mark) => {
      console.log(res);
      setTimeout(() => {
        window.location.href = CONSTANT.VIEW.ResultPage;
      }, 1000);
    });
  };

  // Dialog Submit handling
  const [openConfirmSubmit, setOpenSubmitDlg] = React.useState(false);

  const handleClickOpen = () => {
    setOpenSubmitDlg(true);
  };

  const handleClose = () => {
    setOpenSubmitDlg(false);
  };
  // =====

  // Dialog submitted info
  const [openSummittedDlg, setOpenSubmittedAlert] = React.useState(false);

  return questionResponses && subject ? (
    <>
      <Paper className={classes.clock} elevation={5}>
        <Countdown minutes={subject!.time} action={handleSubmit} />
        <Button
          variant="contained"
          color="secondary"
          className={classes.submitButton}
          onClick={handleClickOpen}
        >
          Submit Exam
        </Button>
      </Paper>
      {questionResponses.map((question) => (
        <Question {...question} updateResponse={updateResponse}></Question>
      ))}
      <Dialog
        open={openConfirmSubmit}
        onClose={handleClose}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle id="alert-dialog-title">{"Submit exam ?"}</DialogTitle>
        <DialogActions>
          <Button onClick={handleClose} color="primary">
            Cancel
          </Button>
          <Button onClick={handleSubmit} color="secondary" autoFocus>
            Confirm
          </Button>
        </DialogActions>
      </Dialog>
      <Backdrop
        open={openSummittedDlg}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <CircularProgress
          style={{ color: "green" }}
          size={200}
        ></CircularProgress>
      </Backdrop>
    </>
  ) : (
    <>This Exam has no question</>
  );
}

export default ExamView;
