package com.ksh.quiz.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.transaction.Transactional;

import com.ksh.common.FileDirectory;
//import com.ksh.core.transaction.Transactional;
import com.ksh.core.util.Util;
import com.ksh.core.util.Utils;
import com.ksh.quiz.dao.QuizDAO;
import com.ksh.quiz.entity.Answer;
import com.ksh.quiz.entity.AnswerType;
import com.ksh.quiz.entity.Question;
import com.ksh.quiz.entity.QuestionAnswerResult;
import com.ksh.quiz.entity.Quiz;

//@ContextPersistenceAware
@Stateless
public class QuizServiceImpl implements QuizService {

	@Inject
	QuizDAO quizDAO;

	@Override
	@Transactional
	public void SaveQuizWithQuestions(Quiz quiz, List<Question> questionList, String fileName, InputStream inputstream)
			throws IOException {
		// Generate File Name
		StringBuilder generatedFileName = new StringBuilder();
		generatedFileName.append(Utils.getGeneratedString(20));
		generatedFileName.append(".").append(Util.getFileExtention(fileName));

		quiz.setCreationDate(new Date());
		quiz.setImgName(generatedFileName.toString());
		quiz.setImgDisplayName(fileName);

		if (Utils.isEmpty(quiz.getRecid())) {
			quizDAO.addEntity(quiz);
		} else {
			quizDAO.updateEntity(quiz);
		}
		Long questionOrder = 1L;
		for (Question question : questionList) {
//			question.setQuiz(quiz);
			question.setQuestionOrder(questionOrder);
			if (Utils.isEmpty(question.getRecid())) {
				quizDAO.addEntity(question);
			} else {
				quizDAO.updateEntity(question);
			}

			if (Utils.isNotEmpty(question.getAnswers())) {
				for (Answer answer : question.getAnswers()) {
					answer.setQuestion(question);
					if (Utils.isEmpty(answer.getRecid())) {
						quizDAO.addEntity(answer);
					} else {
						quizDAO.updateEntity(answer);
					}
				}
			}

			questionOrder++;
		}

		String resourcsFolderValue = (String) Util.getJNDIValue("resourcsFolder");

		FileDirectory.upoladFile(resourcsFolderValue, generatedFileName.toString(), inputstream);

	}

	@Override
	public List<AnswerType> getAnswerTypeList() {
		return quizDAO.getEntityList(AnswerType.class, null);
	}

	@Override
	public Quiz getQuizByCode(String quizCode) {
		return quizDAO.getEntity(Quiz.class, "code", quizCode);
	}

	@Override
	public List<Question> getQuestionList(Long quizId) {
//		return quizDAO.getEntityList(Question.class, "quiz", new Quiz(quizId), "questionOrder");
		return quizDAO.getQuestionsWithAnswers(quizId);

	}

	@Override
	public Long getQuizListRowCount(Map<String, Object> parames) {
		return quizDAO.getQuizListRowCount(parames);
	}

	@Override
	public List<Quiz> getQuizList(int first, int pageSize, String sortField, Map<String, Object> parames) {
		return quizDAO.getQuizList(first, pageSize, sortField, parames);
	}

	@Override
	public Quiz getQuizById(Long quizId) {
		return quizDAO.getEntity(Quiz.class, quizId);
	}

	@Override
	@Transactional
	public void SaveQuestionAnswerList(List<QuestionAnswerResult> questionAnswerList) {
		for (QuestionAnswerResult questionAnswerResult : questionAnswerList) {
			if (Utils.isEmpty(questionAnswerResult.getId())) {
				quizDAO.addEntity(questionAnswerResult);
			} else {
				quizDAO.updateEntity(questionAnswerResult);
			}
		}
	}

}
