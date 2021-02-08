import React, { useEffect, useState } from "react";
import logo from "./logo.svg";
import "./App.css";

function App() {
  const [data, setData] = useState("");
  useEffect(() => {
    fetch("//localhost:8080/api/students/test")
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
      <header className="App-header">
        <p>
          Edit <code>src/App.tsx</code> and save to reload.
        </p>
        <p>{data}</p>
      </header>
    </div>
  );
}

export default App;
