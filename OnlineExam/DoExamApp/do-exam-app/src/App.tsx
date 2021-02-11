import React, { useEffect, useState } from "react";
import "./App.css";
import CONSTANT from "./CONST";

function App() {
  const [data, setData] = useState("");
  useEffect(() => {
    fetch(CONSTANT.api.test)
      .then(
        async (res: Response): Promise<string> => {
          //console.log(res.text());
          return res.text();
        }
      )
      .then((data: string): void => {
        setData(data);
      });
  });
  return (
    <div className="App">
      <p>{data}</p>
    </div>
  );
}

export default App;
