console.log($("#questionList").serialize());
function addNewQuestion() {
  $("#examTemplate")
    .attr("action", CURRENT_URL + ROUTE.addQuestion)
    .submit();
}
