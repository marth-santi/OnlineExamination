import Question from "components/QuestionCmp/Question";
import React, { useEffect, useState } from "react";
import CONSTANT from "CONST";
import { IQuestionResponse } from "models/ExamModels";
import API from "customModules/APIRequest";
import {
  Button,
  Dialog,
  DialogActions,
  DialogContent,
  DialogContentText,
  DialogTitle,
  Grid,
  makeStyles,
} from "@material-ui/core";
import { Mark } from "models/Mark";

const useStyles = makeStyles({
  actionGroup: {
    marginBottom: 20,
  },
  submitButton: {},
});

function ExamView() {
  const classes = useStyles();
  const [questionResponses, setResponses] = useState<IQuestionResponse[]>();
  // Get questions of Exam
  useEffect(() => {
    API.get<IQuestionResponse[]>(CONSTANT.API.getExam).then(
      (exam: IQuestionResponse[]): void => {
        setResponses(exam);
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
    API.postJSON<IQuestionResponse[], Mark>(
      CONSTANT.API.submitExam,
      questionResponses!
    ).then((res: Mark) => {
      console.log(res);
      setTimeout(() => {
        setOpenSubmittedAlert(true);
        window.location.href = CONSTANT.VIEW.ResultPage;
      }, 2000);
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

  return questionResponses ? (
    <>
      <Grid className={classes.actionGroup}>
        <Button
          variant="contained"
          color="secondary"
          className={classes.submitButton}
          onClick={handleClickOpen}
        >
          Submit Exam
        </Button>
      </Grid>
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
      <Dialog
        open={openSummittedDlg}
        aria-labelledby="alert-dialog-title"
        aria-describedby="alert-dialog-description"
      >
        <DialogTitle>{"Exam submitted. Please wait..."}</DialogTitle>
      </Dialog>
    </>
  ) : (
    <>This Exam has no question</>
  );
}

export default ExamView;
