import Question from "components/QuestionCmp/Question";
import React, { useEffect, useState } from "react";
import CONSTANT from "CONST";
import { IQuestionResponse } from "models/ExamModels";
import API from "customModules/APIRequest";
import { Button, Grid, makeStyles } from "@material-ui/core";

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
    API.postJSON<IQuestionResponse[], any>(
      CONSTANT.API.submitExam,
      questionResponses!
    ).then((res: any) => {
      console.log(res);
    });
  };

  return questionResponses ? (
    <>
      <Grid className={classes.actionGroup}>
        <Button
          variant="contained"
          color="secondary"
          className={classes.submitButton}
          onClick={handleSubmit}
        >
          Submit Exam
        </Button>
      </Grid>
      {questionResponses.map((question) => (
        <Question {...question} updateResponse={updateResponse}></Question>
      ))}
    </>
  ) : (
    <>This Exam has no question</>
  );
}

export default ExamView;
