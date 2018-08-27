package com.ksh.quiz.managedBean;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

import com.ksh.core.managedBean.BaseBean;
import com.ksh.core.util.Utils;
import com.ksh.quiz.entity.Quiz;
import com.ksh.quiz.service.QuizService;

@ViewScoped
@Named
public class QuizSearchBean extends BaseBean {
	private static final long serialVersionUID = 1L;

//	@Inject
	@EJB
	private QuizService quizService;

	@Inject
	Quiz quiz;

	private Boolean isDefault = Boolean.TRUE;

	private Long selectedQuizId;

	@PostConstruct
	public void loadData() {
	}

	LazyDataModel<Quiz> lazyLoad = new LazyDataModel<Quiz>() {
		@Override
		public List<Quiz> load(int first, int pageSize, String sortField, SortOrder sortOrder,
				Map<String, Object> filters) {
			if (isDefault) {
				lazyLoad.setRowCount(0);
				return null;
			} else {
				Map<String, Object> parames = fillFilters();
				lazyLoad.setRowCount(quizService.getQuizListRowCount(parames).intValue());
				return quizService.getQuizList(first, pageSize, sortField, parames);
			}
		}
	};

	public void searchQuiz() {
		isDefault = false;
		getQuizList();
	}

	private Map<String, Object> fillFilters() {
		Map<String, Object> filter = new HashMap<String, Object>();
		if (Utils.isEmpty(quiz)) {
			return null;
		}

		if (Utils.isNotEmpty(quiz.getDescription())) {
			filter.put("description", quiz.getDescription());
		}
		if (Utils.isNotEmpty(quiz.getIsPublic())) {
			filter.put("isPublic", quiz.getIsPublic());
		}

		return filter;
	}

	public LazyDataModel<Quiz> getQuizList() {
		return lazyLoad;
	}

	public String toQuizDetails() throws IOException {
		super.setSessionAttribute("quizId", selectedQuizId);
		return "toQuizDetails";
	}

	public Long getSelectedQuizId() {
		return selectedQuizId;
	}

	public void setSelectedQuizId(Long selectedQuizId) {
		this.selectedQuizId = selectedQuizId;
	}

	public Quiz getQuiz() {
		return quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

}
