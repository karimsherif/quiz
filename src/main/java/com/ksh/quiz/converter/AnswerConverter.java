package com.ksh.quiz.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import com.ksh.core.converter.BaseConverter;
import com.ksh.core.util.Util;
import com.ksh.quiz.entity.Answer;

//@FacesConverter("answerConverter")
/*@FacesConverter(value="answerConverter",forClass = Answer.class)*/
public class AnswerConverter extends BaseConverter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent uiComponent, String value) {
    	Answer answer = null;
	if (value == null || (value.trim().length() == 0) || !Util.isNumeric(value)) {
	    return answer;
	}
	try {
	    answer = new Answer(Long.parseLong(value.trim()));
	} catch (NumberFormatException e) {
	    // logger.error("" + e);
	    e.printStackTrace();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return answer;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent uiComponent, Object value) {
	if (value instanceof Answer) {
	    return Long.toString(((Answer) value).getRecid());
	} else
	    return null;
    }
}
