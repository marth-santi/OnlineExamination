import Question from "components/QuestionCmp/Question";
import React, { useEffect, useState } from "react";
import CONSTANT from "CONST";
import { IExam, IQuestion, IQuestionResponse } from "models/ExamModels";
import API from "customModules/APIRequest";

function ExamView() {
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
      console.log("Changed response", newResponses);
      return newResponses;
    });
  };

  return questionResponses ? (
    <>
      {" "}
      {questionResponses.map((question) => (
        <div className="App">
          <Question {...question} updateResponse={updateResponse}></Question>
        </div>
      ))}
    </>
  ) : (
    <>This Exam has no question</>
  );
}

export default ExamView;
