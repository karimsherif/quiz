package com.ksh.quiz.entity;
//package com.ksh.quiz.entity;
//
//import java.io.Serializable;
//
//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//import javax.persistence.JoinColumn;
//import javax.persistence.ManyToOne;
//import javax.persistence.NamedQuery;
//import javax.persistence.Table;
//
//import com.ksh.core.entity.BaseEntity;
//
///**
// * The persistent class for the quiz_visitor database table.
// * 
// */
//@Entity
//@Table(name = "quiz_visitor")
//public class QuizVisitor1 extends BaseEntity implements Serializable {
//	private static final long serialVersionUID = 1L;
//
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	@Column(unique = true, nullable = false)
//	private Long id;
//
//	@Column(length = 45)
//	private String password;
//
//	@Column(name = "user_name", length = 45)
//	private String userName;
//
//	// bi-directional many-to-one association to Quiz
//	@ManyToOne
//	@JoinColumn(name = "quiz_id", nullable = false)
//	private Quiz quiz;
//
//	public QuizVisitor() {
//	}
//
//	public QuizVisitor(Long id) {
//		super();
//		this.id = id;
//	}
//
//	public Long getId() {
//		return this.id;
//	}
//
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	public String getPassword() {
//		return this.password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//
//	public String getUserName() {
//		return this.userName;
//	}
//
//	public void setUserName(String userName) {
//		this.userName = userName;
//	}
//
//	public Quiz getQuiz() {
//		return this.quiz;
//	}
//
//	public void setQuiz(Quiz quiz) {
//		this.quiz = quiz;
//	}
//
//	@Override
//	public Long getRecid() {
//		return this.id;
//	}
//
//}