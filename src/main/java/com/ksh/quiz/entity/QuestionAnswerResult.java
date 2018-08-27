package com.ksh.quiz.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.ksh.core.entity.BaseEntity;

/**
 * The persistent class for the question_answer_result database table.
 * 
 */
@Entity
@Table(name = "question_answer_result")
public class QuestionAnswerResult extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name = "is_right_answer")
	private Boolean isRightAnswer;

	// bi-directional many-to-one association to Answer
	@ManyToOne
	@JoinColumn(name = "answer_id", nullable = false)
	private Answer answer;

	// bi-directional many-to-one association to Answer
	@ManyToOne
	@JoinColumn(name = "right_answer_id")
	private Answer rightAnswer;

//	@Column(name="right_answer_id")
//	private Long rightAnswerId;
	
	// bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name = "question_id")
	private Question question;
	
//	@Column(name="question_id")
//	private Long questionId;

	public QuestionAnswerResult() {
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsRightAnswer() {
		return this.isRightAnswer;
	}

	public void setIsRightAnswer(Boolean isRightAnswer) {
		this.isRightAnswer = isRightAnswer;
	}

	public Answer getAnswer() {
		return this.answer;
	}

	public void setAnswer(Answer answer) {
		this.answer = answer;
	}

	public Answer getRightAnswer() {
		return rightAnswer;
	}

	public void setRightAnswer(Answer rightAnswer) {
		this.rightAnswer = rightAnswer;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public Long getRecid() {
		return id;
	}

}