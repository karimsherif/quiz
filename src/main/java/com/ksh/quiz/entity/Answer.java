package com.ksh.quiz.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ksh.core.entity.BaseEntity;
import com.ksh.core.entity.BaseLookup;


/**
 * The persistent class for the answer database table.
 * 
 */
@Entity
@Table(name = "answer")
//@NamedQuery(name="Answer.findAll", query="SELECT a FROM Answer a")
public class Answer extends BaseEntity implements Serializable, BaseLookup {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Column(name="answer_img_url")
	private String answerImgUrl;

	@Lob
	@Column(name="answer_txt")
	private String answerTxt;


	//bi-directional many-to-one association to Question
	@ManyToOne
	@JoinColumn(name="question_id")
	private Question question;

	public Answer() {
	}

	public Answer(Long id) {
		this.id=id;
	}

	public String getAnswerImgUrl() {
		return this.answerImgUrl;
	}

	public void setAnswerImgUrl(String answerImgUrl) {
		this.answerImgUrl = answerImgUrl;
	}

	public String getAnswerTxt() {
		return this.answerTxt;
	}

	public void setAnswerTxt(String answerTxt) {
		this.answerTxt = answerTxt;
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Question getQuestion() {
		return this.question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	@Override
	public String getLabel() {
		return answerTxt;
	}

	@Override
	public Long getValue() {
		return id;
	}

	@Override
	public Long getRecid() {
		return id;
	}

}