function addNewQuestion() {
  fetch(CURRENT_URL + "/editor/question/add", {
    method: "post",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(listQuestion),
  })
    .then((res) => res.text())
    .then((fragment) => {
      console.log(fragment);
      $("#questionList").replaceWith(fragment);
    });
}
