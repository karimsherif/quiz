package com.ksh.quiz.managedBean;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.ksh.common.Constant;
import com.ksh.common.Mail;
import com.ksh.core.managedBean.BaseBean;
import com.ksh.quiz.entity.Visitor;

@ViewScoped
@Named
public class SendQuizResultBean extends BaseBean {
	private static final long serialVersionUID = 1L;

	@Inject
	Visitor Visitor;

	@PostConstruct
	public void loadData() {
	}

	public void SendQuizResult() {
		//ToDo send email to visitor's email
		
		super.addInfoMessage("An Email will be send to your email with the result ");
//		Mail mail=new Mail(Constant.SMTP_AUTH_USER, Visitor.getEmail(), "Quiz || Visitor Result", "This is to note that we sent the email successfully");
//		mail.sendEmail();
	}
	public Visitor getVisitor() {
		return Visitor;
	}

	public void setVisitor(Visitor visitor) {
		Visitor = visitor;
	}

	

}
