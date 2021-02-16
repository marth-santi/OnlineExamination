export interface IQuestion {
  id: number;
  question: string;
  op1: string;
  op2: string;
  op3: string;
  op4: string;
  isMultiple: boolean;
  subjectId: number;
}

export interface IQuestionResponse extends IQuestion {
  isCheckedOp1: boolean;
  isCheckedOp2: boolean;
  isCheckedOp3: boolean;
  isCheckedOp4: boolean;
}

export type ISubject = {
  id: number;
  subjectName: string;
  startDate: Date;
  endDate: Date;
  time: number;
};

export type IExam = Array<IQuestion>;

export type IExamResponse = {
  subject: ISubject;
  responses: Array<IQuestionResponse>;
};
