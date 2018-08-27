package com.ksh.quiz.managedBean;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import com.ksh.core.managedBean.BaseBean;
import com.ksh.core.util.Utils;
import com.ksh.quiz.entity.Question;
import com.ksh.quiz.entity.QuestionAnswerResult;
import com.ksh.quiz.entity.Quiz;
import com.ksh.quiz.service.QuizService;

@ViewScoped
@Named
public class DoQuizBean extends BaseBean {
	private static final long serialVersionUID = 1L;

//	@Inject
	@EJB
	private QuizService quizService;

	Quiz quiz;

	private List<Question> questionList = new ArrayList<Question>();
	private List<QuestionAnswerResult> questionAnswerList = new ArrayList<QuestionAnswerResult>();

	private Question question = new Question();

	private QuestionAnswerResult currentQuestionAnswerResult;
	private Integer currentIndex = 0;

	private Boolean isFinished=Boolean.FALSE;
			
//	private RegisteredUser registeredUser;

	@PostConstruct
	public void loadData() {
		prepareQuizAndQuestions();

	}

	private void prepareQuizAndQuestions() {
		String quizCode = (String) super.getParameter("q");
		quiz = quizService.getQuizByCode(quizCode);

		if (Utils.isNotEmpty(quiz)) {
			questionList = quizService.getQuestionList(quiz.getId());
			if (Utils.isNotEmpty(questionList)) {
				prepareQuestionAnswer();

			}
		}

	}

	private void prepareQuestionAnswer() {
		for (Question question : questionList) {
			QuestionAnswerResult questionAnswerResult = new QuestionAnswerResult();
			questionAnswerResult.setQuestion(question);

			// To Do set visitor Id from session
//			questionAnswerResult.setQuizVisitor(new QuizVisitor(registeredUser.getVisitorId()));
//			questionAnswerResult.setVisitor(new QuizVisitor(1L));
			questionAnswerList.add(questionAnswerResult);

		}
		currentQuestionAnswerResult = questionAnswerList.get(currentIndex);
	}

	public void toNext() {
		if(Utils.isEmpty(currentQuestionAnswerResult.getAnswer())) {
			super.addErrorMessage("You have to select answer");
			return;
		}
		questionAnswerList.set(currentIndex, currentQuestionAnswerResult);
		currentIndex++;
		currentQuestionAnswerResult = questionAnswerList.get(currentIndex);

	}
	public void finish() {
		if(Utils.isEmpty(currentQuestionAnswerResult.getAnswer())) {
			super.addErrorMessage("You have to select answer");
			return;
		}
		
		quizService.SaveQuestionAnswerList(questionAnswerList);
//	    logger.info("successfully finished quiz="+quiz.getDescription());

		this.isFinished=Boolean.TRUE;
		super.addInfoMessage("Successfully finished");
		
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
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

	public QuestionAnswerResult getCurrentQuestionAnswerResult() {
		return currentQuestionAnswerResult;
	}

	public void setCurrentQuestionAnswerResult(QuestionAnswerResult currentQuestionAnswerResult) {
		this.currentQuestionAnswerResult = currentQuestionAnswerResult;
	}

	public Integer getCurrentIndex() {
		return currentIndex;
	}

	public void setCurrentIndex(Integer currentIndex) {
		this.currentIndex = currentIndex;
	}

	public List<QuestionAnswerResult> getQuestionAnswerList() {
		return questionAnswerList;
	}

	public void setQuestionAnswerList(List<QuestionAnswerResult> questionAnswerList) {
		this.questionAnswerList = questionAnswerList;
	}

	public Boolean getIsFinished() {
		return isFinished;
	}

	public void setIsFinished(Boolean isFinished) {
		this.isFinished = isFinished;
	}

}
