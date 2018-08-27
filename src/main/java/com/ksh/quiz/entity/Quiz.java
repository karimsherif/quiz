package com.ksh.quiz.entity;

import java.io.Serializable;
import javax.persistence.*;

import com.ksh.core.BaseObject;
import com.ksh.core.entity.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * The persistent class for the quiz database table.
 * 
 */
@Entity
@Table(name = "quiz")
//@NamedQuery(name = "Quiz.findAll", query = "SELECT q FROM Quiz q")
public class Quiz extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(unique = true, nullable = false)
	private Long id;

	@Temporal(TemporalType.DATE)
	@Column(name = "creation_date")
	private Date creationDate;

	@Column(length = 500)
	private String description;

	@Column(name = "img_name", length = 50)
	private String imgName;

	@Column(name = "image_display_name", length = 50)
	private String imgDisplayName;

	@Column(name = "is_public")
	private Boolean isPublic;

	@Column(name = "quiz_url", length = 2000)
	private String quizUrl;

	@Column(name = "code", length = 100)
	private String code;

	@OneToMany(mappedBy = "quiz", fetch = FetchType.LAZY)
	private List<Question> questions;

	@Column(name = "owner_id")
	private Long ownerId;

	@ManyToOne
	@JoinColumn(name = "owner_id", updatable = false, insertable = false)
	private Owner owner;

	@Column(name = "external_link")
	private String externalLink;

	public Quiz() {
	}

	public Quiz(Long id) {
		super();
		this.id = id;
	}

	public Quiz(Long id, Date creationDate, String description, String imgName, String imgDisplayName, Boolean isPublic,
			String quizUrl, String code, Long ownerId) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.description = description;
		this.imgName = imgName;
		this.imgDisplayName = imgDisplayName;
		this.isPublic = isPublic;
		this.quizUrl = quizUrl;
		this.code = code;
		this.ownerId = ownerId;
	}

	public Quiz(Long id, Date creationDate, String description, String imgName, String imgDisplayName, Boolean isPublic,
			String quizUrl, String code, Long ownerId, String ownerFullName) {
		super();
		this.id = id;
		this.creationDate = creationDate;
		this.description = description;
		this.imgName = imgName;
		this.imgDisplayName = imgDisplayName;
		this.isPublic = isPublic;
		this.quizUrl = quizUrl;
		this.code = code;
		this.ownerId = ownerId;
		this.owner = new Owner(ownerId, ownerFullName);
	}

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreationDate() {
		return this.creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Boolean getIsPublic() {
		return this.isPublic;
	}

	public void setIsPublic(Boolean isPublic) {
		this.isPublic = isPublic;
	}

	public String getQuizUrl() {
		return this.quizUrl;
	}

	public void setQuizUrl(String quizUrl) {
		this.quizUrl = quizUrl;
	}

	public List<Question> getQuestions() {
		return this.questions;
	}

	public void setQuestions(List<Question> questions) {
		this.questions = questions;
	}

	public Question addQuestion(Question question) {
		getQuestions().add(question);
		question.setQuiz(this);

		return question;
	}

	public Question removeQuestion(Question question) {
		getQuestions().remove(question);
		question.setQuiz(null);

		return question;
	}

	public Owner getOwner() {
		return this.owner;
	}

	public void setOwner(Owner owner) {
		this.owner = owner;
	}

	public Long getOwnerId() {
		return ownerId;
	}

	public void setOwnerId(Long ownerId) {
		this.ownerId = ownerId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getImgName() {
		return imgName;
	}

	public void setImgName(String imgName) {
		this.imgName = imgName;
	}

	public String getImgDisplayName() {
		return imgDisplayName;
	}

	public void setImgDisplayName(String imgDisplayName) {
		this.imgDisplayName = imgDisplayName;
	}

	public String getExternalLink() {
		return externalLink;
	}

	public void setExternalLink(String externalLink) {
		this.externalLink = externalLink;
	}

	@Override
	public Long getRecid() {
		return this.id;
	}

}