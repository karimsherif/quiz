package com.ksh.quiz.dao;

import java.util.List;
import java.util.Map;

import com.ksh.core.dao.BaseDAO;
import com.ksh.quiz.entity.Question;
import com.ksh.quiz.entity.Quiz;

public interface QuizDAO extends BaseDAO{

	public List<Question> getQuestionsWithAnswers(Long quizId);

	public Long getQuizListRowCount(Map<String, Object> parames);

	public List<Quiz> getQuizList(int first, int pageSize, String sortField, Map<String, Object> parames);

}
