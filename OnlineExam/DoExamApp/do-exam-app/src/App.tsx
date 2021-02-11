import React, { useEffect, useState } from "react";
import "./App.css";
import CONSTANT from "./CONST";

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
        async (res: Response): Promise<string> => {
          return res.json();
        }
      )
      .then((data2: any): void => {
        console.log(data2);
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
