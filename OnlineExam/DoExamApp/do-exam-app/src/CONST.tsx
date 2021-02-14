var BASE_URL = window.location.origin;
const CONST = {
  API: {
    test: `${BASE_URL}/api/students/test`,
    getExam: `${BASE_URL}/api/students/getExam`,
    submitExam: `${BASE_URL}/api/students/submitExam`,
  },
  VIEW: {
    ResultPage: `${BASE_URL}/students/results`,
  },
};

export default CONST;
