package com.funeral.kris.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.funeral.kris.constants.QUESTION;
import com.funeral.kris.model.TFAnswer;
import com.funeral.kris.service.FAnswerService;
import com.funeral.kris.service.OptionService;

@Controller
@RequestMapping(value = "/fAnswer")
public class FAnswerController {

	@Autowired
	private FAnswerService fAnswerService;
	@Autowired
	private OptionService optionService;

	
	
	@ResponseBody
	@RequestMapping(value = "/save", method = RequestMethod.GET, produces = "application/json")
	public void saveAnswer(HttpServletRequest request) {

       String questionId=request.getParameter("questionId");
       String optionId=request.getParameter("optionId");
		
       TFAnswer  newAnswer= new TFAnswer();
		
       newAnswer.setQuestionId(Long.parseLong(questionId));
       
       QUESTION.currentPosition=Long.parseLong(questionId)+1;
       
       newAnswer.setOptionId(Long.parseLong(optionId));
       
       newAnswer.setUserId(QUESTION.ADMIN_ID);
       
       
       newAnswer.setCreateDate(new Date());
       newAnswer.setUpdatedDate(new Date());
       
       TFAnswer  oldAnswer= fAnswerService.getAnswerByQuestionId(Long.parseLong(questionId));
       
       String otherComments="";
       
       if(oldAnswer!=null){
    	   
    	   otherComments= oldAnswer.getOtherComments();
    	   newAnswer.setOtherComments(otherComments);

       }
       
      if(oldAnswer!=null){
    	  
    	if(oldAnswer.getOptionId()==newAnswer.getOptionId()){
    		
    		oldAnswer.setOptionId(-1l);
    		
    		fAnswerService.addResource(oldAnswer);
    		
    	}  else{
    		
    		oldAnswer.setOptionId(newAnswer.getOptionId());
    		
    		fAnswerService.addResource(oldAnswer);
    		
    	}
    	  
      } else{
    	  
    	  
    	  fAnswerService.addResource(newAnswer); 
    	  
      }
      
	}
	
	@ResponseBody
	@RequestMapping(value = "/saveOtherComments", method = RequestMethod.GET, produces = "application/json")
	public void saveOtherComments(HttpServletRequest request) throws Exception {
		
		
		String questionId=request.getParameter("questionId");
		String comments=request.getParameter("comments");
		
		
//		String s2=new String(comments.getBytes("ISO-8859-1"),"UTF-8");
		
		System.out.println(" comments="+comments+"  request.getCharacterEncoding()="+request.getCharacterEncoding());
		
		TFAnswer  oldAnswer= fAnswerService.getAnswerByQuestionId(Long.parseLong(questionId));
		
		
//		comments=s2;
		
		if(oldAnswer!=null){
			
			oldAnswer.setOtherComments(comments);
			
		}else{
			
			oldAnswer=new TFAnswer();
			oldAnswer.setUserId(QUESTION.ADMIN_ID);
			oldAnswer.setQuestionId(Long.parseLong(questionId));
			oldAnswer.setOptionId(-1l);
			oldAnswer.setCreateDate(new Date());
			oldAnswer.setUpdatedDate(new Date());
				}
		
		
		fAnswerService.addResource(oldAnswer);	
		
	}
	
	@ResponseBody
	@RequestMapping(value = "/getOtherComments", method = RequestMethod.GET, produces = "application/json")
	public String getOtherComments(HttpServletRequest request) {
		
		String questionId=request.getParameter("questionId");
		
		
		TFAnswer  tFAnswer= fAnswerService.getAnswerByQuestionId(Long.parseLong(questionId));
		
		System.out.println(this.getClass()+"getOtherComments  questionId= "+questionId);
		
		if(tFAnswer==null){
			
			return "";
		}
		System.out.println(this.getClass()+"getOtherComments  questionId= "+questionId+"  getOtherComments="+tFAnswer.getOtherComments());

		
		return tFAnswer.getOtherComments();
		
		
	
		
	}
	
	
	
	

	public Object verify(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
