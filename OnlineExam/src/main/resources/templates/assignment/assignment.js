$(".clickable-row").click(function () {
  window.location =
    CURRENT_URL + ROUTE.assignSubject + "/" + $(this).data("href");
});
