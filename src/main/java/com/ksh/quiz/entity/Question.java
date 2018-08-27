package com.ksh.quiz.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.ksh.core.entity.BaseEntity;

import java.util.List;

/**
 * The persistent class for the question database table.
 * 
 */
@Entity
@Table(name = "question")
public class Question extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(length = 45)
	private String notes;

	@Column(name = "question_order")
	private Long questionOrder;

	@Column(name = "answer_type_id")
	private Long answerTypeId;

//	@Lob
	@Column(nullable = false)
	private String question;

	// bi-directional many-to-one association to Quiz
	@ManyToOne
	@JoinColumn(name = "quiz_id", nullable = false)
	private Quiz quiz;

	// bi-directional many-to-one association to Answer
	@OneToMany(mappedBy = "question", fetch = FetchType.LAZY)
	private List<Answer> answers;

	public Question() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNotes() {
		return this.notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String getQuestion() {
		return this.question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public Quiz getQuiz() {
		return this.quiz;
	}

	public void setQuiz(Quiz quiz) {
		this.quiz = quiz;
	}

	public Long getQuestionOrder() {
		return questionOrder;
	}

	public void setQuestionOrder(Long questionOrder) {
		this.questionOrder = questionOrder;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public Long getAnswerTypeId() {
		return answerTypeId;
	}

	public void setAnswerTypeId(Long answerTypeId) {
		this.answerTypeId = answerTypeId;
	}

	public Answer addAnswer(Answer answer) {
		getAnswers().add(answer);
		answer.setQuestion(this);

		return answer;
	}

	public Answer removeAnswer(Answer answer) {
		getAnswers().remove(answer);
		answer.setQuestion(null);

		return answer;
	}

	@Override
	public Long getRecid() {
		return this.id;
	}

}