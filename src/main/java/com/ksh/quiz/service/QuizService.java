package com.ksh.quiz.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

import org.primefaces.model.SortOrder;

import com.ksh.quiz.entity.AnswerType;
import com.ksh.quiz.entity.Question;
import com.ksh.quiz.entity.QuestionAnswerResult;
import com.ksh.quiz.entity.Quiz;

public interface QuizService {

	public void SaveQuizWithQuestions(Quiz quiz, List<Question> questionList, String fileName, InputStream inputstream)
			throws IOException;

	public List<AnswerType> getAnswerTypeList();

	public Quiz getQuizByCode(String quizCode);

	public List<Question> getQuestionList(Long quizId);

	public Long getQuizListRowCount(Map<String, Object> parames);

	public List<Quiz> getQuizList(int first, int pageSize, String sortField, Map<String, Object> parames);

	public Quiz getQuizById(Long quizId);

	public void SaveQuestionAnswerList(List<QuestionAnswerResult> questionAnswerList);

}
