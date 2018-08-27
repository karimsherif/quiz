package com.ksh.quiz.dao;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.ksh.core.dao.impl.BaseDAOImpl;
import com.ksh.core.util.Utils;
import com.ksh.quiz.entity.Owner;
import com.ksh.quiz.entity.Question;
import com.ksh.quiz.entity.Quiz;

public class QuizDAOImpl extends BaseDAOImpl implements QuizDAO {

	private static final long serialVersionUID = 3823476614889367635L;

	@Override
	public List<Question> getQuestionsWithAnswers(Long quizId) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("quiz", new Quiz(quizId));
		return super.getResultList("Questions.find.WithAnswers", params);
	}

	@Override
	public Long getQuizListRowCount(Map<String, Object> parames) {
		CriteriaBuilder criteriaBuilder = super.getCriteriaBuilder();
		CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);

		Root<Quiz> quizRoot = criteriaQuery.from(Quiz.class);
		Join<Quiz, Owner> ownerRoot = quizRoot.join("owner", JoinType.LEFT);

		criteriaQuery.select(criteriaBuilder.count(quizRoot.get("id")));

		Predicate criteria = null;
		Predicate criteriaTemp = null;
		if (Utils.isNotEmpty(parames)) {
			for (Iterator<String> it = parames.keySet().iterator(); it.hasNext();) {

				String filterProperty = it.next();
				Object filterValue = parames.get(filterProperty);

				if (filterProperty.equals("description")) {
					criteriaTemp = criteriaBuilder.like((Expression) quizRoot.get("description"),
							"%" + filterValue + "%");
					criteria = (criteria == null ? criteriaTemp : criteriaBuilder.and(criteria, criteriaTemp));
				}

				if (filterProperty.equals("isPublic")) {
					criteriaTemp = criteriaBuilder.equal(quizRoot.get("isPublic"), filterValue);
					criteria = (criteria == null ? criteriaTemp : criteriaBuilder.and(criteria, criteriaTemp));
				}

				if (filterProperty.equals("creationDateFrom")) {
					Expression<Date> date1 = quizRoot.get("creationDate");
					Date birthDateFrom = Utils.isEmpty(parames.get("creationDateFrom")) ? Utils.getMinDate()
							: (Date) parames.get("creationDateFrom");

					criteriaTemp = criteriaBuilder.greaterThanOrEqualTo(date1, birthDateFrom);
					criteria = (criteria == null ? criteriaTemp : criteriaBuilder.and(criteria, criteriaTemp));
				}
				if (filterProperty.equals("creationDateTo")) {
					Expression<Date> date1 = quizRoot.get("creationDate");
					Date birthDateTo = Utils.isEmpty(parames.get("creationDateTo")) ? Utils.getMaxDate()
							: (Date) parames.get("creationDateTo");

					criteriaTemp = criteriaBuilder.lessThanOrEqualTo(date1, birthDateTo);
					criteria = (criteria == null ? criteriaTemp : criteriaBuilder.and(criteria, criteriaTemp));
				}

			}
			if (null != criteria) {
				criteriaQuery.where(criteria);
			}
		}

		Query query = super.createQuery(criteriaQuery);
		return Long.parseLong(query.getSingleResult() + "");
	}

	@Override
	public List<Quiz> getQuizList(int first, int pageSize, String sortField, Map<String, Object> parames) {
		CriteriaBuilder criteriaBuilder = super.getCriteriaBuilder();
		CriteriaQuery<Quiz> criteriaQuery = criteriaBuilder.createQuery(Quiz.class);

		Root<Quiz> quizRoot = criteriaQuery.from(Quiz.class);
		Join<Quiz, Owner> ownerRoot = quizRoot.join("owner", JoinType.LEFT);

		criteriaQuery.select(criteriaBuilder.construct(Quiz.class, quizRoot.get("id"), quizRoot.get("creationDate"),
				quizRoot.get("description"), quizRoot.get("imgName"), quizRoot.get("imgDisplayName"),
				quizRoot.get("isPublic"), quizRoot.get("quizUrl"), quizRoot.get("code"), quizRoot.get("ownerId"),
				ownerRoot.get("fullName"))).distinct(true);

		Predicate criteria = null;
		Predicate criteriaTemp = null;
		if (Utils.isNotEmpty(parames)) {
			for (Iterator<String> it = parames.keySet().iterator(); it.hasNext();) {

				String filterProperty = it.next();
				Object filterValue = parames.get(filterProperty);

				if (filterProperty.equals("description")) {
					criteriaTemp = criteriaBuilder.like((Expression) quizRoot.get("description"),
							"%" + filterValue + "%");
					criteria = (criteria == null ? criteriaTemp : criteriaBuilder.and(criteria, criteriaTemp));
				}

				if (filterProperty.equals("isPublic")) {
					criteriaTemp = criteriaBuilder.equal(quizRoot.get("isPublic"), filterValue);
					criteria = (criteria == null ? criteriaTemp : criteriaBuilder.and(criteria, criteriaTemp));
				}

				if (filterProperty.equals("creationDateFrom")) {
					Expression<Date> date1 = quizRoot.get("creationDate");
					Date birthDateFrom = Utils.isEmpty(parames.get("creationDateFrom")) ? Utils.getMinDate()
							: (Date) parames.get("creationDateFrom");

					criteriaTemp = criteriaBuilder.greaterThanOrEqualTo(date1, birthDateFrom);
					criteria = (criteria == null ? criteriaTemp : criteriaBuilder.and(criteria, criteriaTemp));
				}
				if (filterProperty.equals("creationDateTo")) {
					Expression<Date> date1 = quizRoot.get("creationDate");
					Date birthDateTo = Utils.isEmpty(parames.get("creationDateTo")) ? Utils.getMaxDate()
							: (Date) parames.get("creationDateTo");

					criteriaTemp = criteriaBuilder.lessThanOrEqualTo(date1, birthDateTo);
					criteria = (criteria == null ? criteriaTemp : criteriaBuilder.and(criteria, criteriaTemp));
				}

			}
			if (null != criteria) {
				criteriaQuery.where(criteria);
			}
		}

		Query query = super.createQuery(criteriaQuery);
		if (first != -1 && pageSize != -1) {
			query.setMaxResults(pageSize);
			query.setFirstResult(first);
		}
		return query.getResultList();

	}

}
