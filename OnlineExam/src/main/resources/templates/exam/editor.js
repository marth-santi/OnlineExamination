function addNewQuestion(isMultiple) {
  $("#isMultiple").attr("value", isMultiple);
  $("#examTemplate")
    .attr("action", CURRENT_URL + ROUTE.addQuestion)
    .submit();
}
function deleteQuestion(id) {
  $("#examTemplate")
    .attr("action", CURRENT_URL + ROUTE.deleteQuestion)
    .submit();
}
function setDeleteId(id) {
  $("#deleteId").attr("value", id);
}
