import Question from "components/QuestionCmp/Question";
import React, { useEffect, useState } from "react";
import CONSTANT from "CONST";
import { IExam } from "models/ExamModels";

function Exam() {
  const [questions, setQuestions] = useState<IExam | null>(null);
  // Get questions of Exam
  useEffect(() => {
    fetch(CONSTANT.API.getExam)
      .then(
        async (res: Response): Promise<IExam> => {
          return res.json();
        }
      )
      .then((exam: IExam): void => {
        console.log(exam);
        setQuestions(exam);
      });
  }, []);

  return questions ? (
    <> {
      questions.map((question) => (
        <div className="App">
          <Question {...question}></Question>
        </div>
      ))}
    </>
  ) : (
    <>This Exam has no question</>
  );
}

export default Exam;
