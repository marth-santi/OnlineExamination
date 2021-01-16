function addNewQuestion(){
	fetch("../question/add", {
		method: 'get'
	})
		.then((response) => response.text())
		.then(fragment => {
			console.log(fragment);
			$("#questionList").append(fragment);
		});
}