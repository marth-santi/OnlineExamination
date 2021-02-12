import React, { useEffect, useState } from "react";
import "./App.css";
import CONSTANT from "./CONST";
import { IExamResponse } from "./models/ExamResponse";

function App() {
  const [data, setData] = useState("");
  const [data2, setData2] = useState("");
  useEffect(() => {
    fetch(CONSTANT.API.test)
      .then(
        async (res: Response): Promise<string> => {
          return res.text();
        }
      )
      .then((data: string): void => {
        setData(data);
      });
    fetch(CONSTANT.API.getExam)
      .then(
        async (res: Response): Promise<IExamResponse> => {
          return res.json();
        }
      )
      .then((data2: IExamResponse): void => {
        console.log(data2);
        console.log(data2[0].question);
      });
  });
  return (
    <div className="App">
      Hello from React App
      <p>{data}</p>
      <p>{data2}</p>
    </div>
  );
}

export default App;
