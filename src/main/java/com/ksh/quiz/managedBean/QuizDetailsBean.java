package com.ksh.quiz.managedBean;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.UploadedFile;

import com.ksh.core.managedBean.BaseBean;
import com.ksh.core.util.Util;
import com.ksh.core.util.Utils;
import com.ksh.quiz.entity.Answer;
import com.ksh.quiz.entity.AnswerType;
import com.ksh.quiz.entity.Question;
import com.ksh.quiz.entity.Quiz;
import com.ksh.quiz.service.QuizService;

@ViewScoped
@Named
public class QuizDetailsBean extends BaseBean {
	private static final long serialVersionUID = 1L;

//	@Inject
	@EJB
	private QuizService quizService;

	@Inject
	Quiz quiz;

	private List<Question> questionList = new ArrayList<Question>();
	private List<AnswerType> answerTypeList = new ArrayList<AnswerType>();

	private Question question = new Question();

	private UploadedFile file;

//	private RegisteredUser registeredUser;

	@PostConstruct
	public void loadData() {
		loadQuizDetails();
		answerTypeList = quizService.getAnswerTypeList();
	}

	public void loadQuizDetails() {
		Object quizIdObject = super.getSessionAttribute("quizId");
		super.removeSessionAttribute("quizId");
		if (quizIdObject != null) {

			Long quizId = Long.parseLong(quizIdObject.toString());
			this.quiz = quizService.getQuizById(quizId);
			if (Utils.isNotEmpty(this.quiz)) {
				questionList = quizService.getQuestionList(quizId);
			}
		} else {
			prepareQuiz();
		}
	}

	public void loadAnswerTypeList() {
		setAnswerTypeList(quizService.getAnswerTypeList());
	}

	public void prepareToAddQuestion() {
		this.question = new Question();
		question.setQuiz(this.quiz);
	}

	private void prepareQuiz() {
		// To Do set owner Id from session
//		quiz.setOwnerId(registeredUser.getOwnerId());
		quiz.setOwnerId(1L);

		quiz.setCreationDate(new Date());

		generateUrl();
	}

	private void generateUrl() {
		String code = Utils.getGeneratedString(50);
		String quizURLValueL = (String) Util.getJNDIValue("QuizURL");
		String quizURL = quizURLValueL.replace("%quizCode%", code);

		quiz.setCode(code);
		quiz.setQuizUrl(quizURL);

	}

	public void SaveQuizWithQuestions() {
		try {
			quizService.SaveQuizWithQuestions(quiz, questionList, file.getFileName(), file.getInputstream());
			super.addInfoMessage(super.getMessage("Quiz_General_Save_Successfully"));
		} catch (IOException e) {
			super.addErrorMessage(super.getMessage("Quiz_General_Save_Failuer"));
			e.printStackTrace();
		} catch (Exception e) {
			super.addErrorMessage(super.getMessage("Quiz_General_Save_Failuer"));
			e.printStackTrace();
		}
	}

	public void addQuestionToList() {
		questionList.add(question);

	}

	public void addAnswerToQuestion() {
		if (Utils.isNotEmpty(question)) {
			if (Utils.isEmpty(question.getAnswers())) {
				question.setAnswers(new ArrayList<Answer>());
			}
			Answer answer = new Answer();
			answer.setQuestion(question);
			question.getAnswers().add(answer);
		}
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public UploadedFile getFile() {
		return file;
	}

	public void setFile(UploadedFile file) {
		this.file = file;
	}

	public List<Question> getQuestionList() {
		return questionList;
	}

	public void setQuestionList(List<Question> questionList) {
		this.questionList = questionList;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public List<AnswerType> getAnswerTypeList() {
		return answerTypeList;
	}

	public void setAnswerTypeList(List<AnswerType> answerTypeList) {
		this.answerTypeList = answerTypeList;
	}

}
