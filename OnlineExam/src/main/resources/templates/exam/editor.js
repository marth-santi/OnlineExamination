console.log($("#questionList").serialize());
function addNewQuestion(isMultiple) {
  $("#isMultiple").attr("value", isMultiple);
  $("#examTemplate")
    .attr("action", CURRENT_URL + ROUTE.addQuestion)
    .submit();
}
