package com.funeral.kris.controller;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.bean.QuestionDisplayBean;
import com.funeral.kris.constants.QUESTION;
import com.funeral.kris.model.Option;
import com.funeral.kris.model.TFAnswer;
import com.funeral.kris.model.TFQuestion;
import com.funeral.kris.service.FAnswerService;
import com.funeral.kris.service.FQuestionService;
import com.funeral.kris.service.OptionService;

@Controller
@RequestMapping(value = "/fQuestion")
public class FQuestionController {

	@Autowired
	private FQuestionService fQuestionService;
	@Autowired
	private FAnswerService fAnswerService;
	@Autowired
	private OptionService optionService;

	@ResponseBody
	@RequestMapping(value = "/getQuestionById{id}", method = RequestMethod.GET, produces = "application/json")
	public TFQuestion getQuestionById(Long id) {
		ModelAndView modelAndView = new ModelAndView("list-of-tFQuestion");

		System.out.println(" questionId =" + id);

		if (id == null || "null".equals(id)) {

			return new TFQuestion();
		}

		TFQuestion tFQuestion = fQuestionService.getResource(id);

		modelAndView.addObject("tFQuestion", tFQuestion);

		return tFQuestion;
	}

	@ResponseBody
	@RequestMapping(value = "/add", method = RequestMethod.POST)

	public void add(@RequestBody TFQuestion tFQuestion) {

		System.out.println("tFQuestion=" + tFQuestion.getTitle() + " content =" + tFQuestion.getQuestionContent());

		fQuestionService.addResource(tFQuestion);

	}

	@ResponseBody
	@RequestMapping(value = "/listForUpdate", method = RequestMethod.GET, produces = "application/json")
	public List<TFQuestion> listAll() {

		ModelAndView modelAndView = new ModelAndView("list-of-questionList");

		List<TFQuestion> questionList = fQuestionService.getResources();
		return questionList;
	}

	@ResponseBody
	@RequestMapping(value = "/totalQuestionNumber", method = RequestMethod.GET, produces = "application/json")
	public String totalQuestionNumber() {
		QUESTION.currentPosition = 6l;
		return fQuestionService.count().toString();
	}

	@ResponseBody
	@RequestMapping(value = "/getQuestionById", method = RequestMethod.GET, produces = "application/json")
	public TFQuestion getQuestionById(HttpServletRequest request) {

		String questionId = request.getParameter("questionId");

		TFQuestion tFQuestion = fQuestionService.getResource(Long.parseLong(questionId));
		return tFQuestion;
	}

	@ResponseBody
	@RequestMapping(value = "/getPerCentList", method = RequestMethod.GET, produces = "application/json")
	public List<String> getPerCentList() {

		List<String> percentList = new ArrayList<String>();

		Double a = 8.273;
		Double b = -1.273;

		int i = 0;
		while (i < 12) {

			Double c = a * i + b;

			percentList.add(c.toString()+"%");

			i++;
		}

		return percentList;

	}

	@ResponseBody
	@RequestMapping(value = "/listAllForDisplay", method = RequestMethod.GET, produces = "application/json")
	public List<QuestionDisplayBean> listAllForDisplay() {

		ModelAndView modelAndView = new ModelAndView("list-of-questionList");

		List<TFQuestion> questionList = fQuestionService.getResources();

		List<Option> optionList = optionService.getResources();

		List<QuestionDisplayBean> QuestionDisplayBeanList = new ArrayList<QuestionDisplayBean>();

		Iterator iterator = questionList.iterator();

		while (iterator.hasNext()) {
			TFQuestion tFQuestion = (TFQuestion) iterator.next();

			QuestionDisplayBean questionDisplayBean = new QuestionDisplayBean();

			questionDisplayBean.setQuestion(tFQuestion);

			StringBuffer cssDisplay = new StringBuffer();
			cssDisplay.append("height:700px;                                                  ");
			cssDisplay.append("width:100%;                                                    ");
			cssDisplay.append("border:0px solid #ff5274;                                      ");
			cssDisplay.append("position:relative;                                             ");
			cssDisplay.append("left:0%;                                                       ");
			cssDisplay.append("right:0%;                                                      ");
			cssDisplay.append("top:0px;                                                       ");
			cssDisplay.append("text-align:center;                                             ");
			cssDisplay.append("line-height:40px;                          ");
			cssDisplay.append("color:#707070; face: 'STHeitiSC-Medium'; font-size:40px;    float:left;  ");
			StringBuffer cssHide = new StringBuffer();
			cssHide.append("height:700px;                                                  ");
			cssHide.append("width:100%;                                                    ");
			cssHide.append("border:0px solid #ff5274;                                      ");
			cssHide.append("position:relative;                                             ");
			cssHide.append("left:100%;                                                       ");
			cssHide.append("right:0%;                                                      ");
			cssHide.append("top:0px;                                                       ");
			cssHide.append("text-align:center;                                             ");
			cssHide.append("line-height:40px;                    ");
			cssHide.append("color:#707070; face: 'STHeitiSC-Medium'; font-size:40px;   float:left;  ");

			System.out.println(this.getClass() + "======  currentPosition" + QUESTION.currentPosition);

			if (tFQuestion.getQuestionId() == QUESTION.currentPosition) {

				questionDisplayBean.setStyle(cssDisplay.toString());
			} else {

				questionDisplayBean.setStyle(cssHide.toString());

			}

			TFAnswer answer = fAnswerService.getAnswerByQuestionId(tFQuestion.getQuestionId());
			if (answer != null) {
				questionDisplayBean.setOtherComments(answer.getOtherComments());
			}
			List<Option> optionsList = new ArrayList<Option>();
			questionDisplayBean.setOptionList(optionsList);
			Iterator optionIterator = optionList.iterator();
			while (optionIterator.hasNext()) {

				Option option = (Option) optionIterator.next();

				if (option.getQuestionId().equals(tFQuestion.getQuestionId().toString())) {

					option.setImgUrl(QUESTION.IMG_PATH + option.getImgUrl());

					TFAnswer tFAnswer = fAnswerService.getAnswer(QUESTION.ADMIN_ID, tFQuestion.getQuestionId(),
							option.getOptionId());

					optionsList.add(option);
				}

			}

			TFAnswer tFAnswer = fAnswerService.getAnswerByQuestionId(questionDisplayBean.getQuestion().getQuestionId());

			if (tFAnswer != null) {

				questionDisplayBean.setOtherComments(tFAnswer.getOtherComments());

				questionDisplayBean.setSelectedOptionId(tFAnswer.getOptionId());

			}

			QuestionDisplayBeanList.add(questionDisplayBean);

		}

		return QuestionDisplayBeanList;
	}

	@ResponseBody
	@RequestMapping(value = "edit", method = RequestMethod.POST)
	public void edit(@RequestBody TFQuestion tFQuestion) throws Exception {
		System.out.println(this.getClass() + "----------tFQuestion=" + tFQuestion.getTitle() + " content ="
				+ tFQuestion.getQuestionContent());
		ModelAndView modelAndView = new ModelAndView("start");
		modelAndView.addObject(tFQuestion);

		fQuestionService.updateResource(tFQuestion);

	}

	@RequestMapping(value = "/delete/{id}", method = RequestMethod.POST)
	public ModelAndView delete(@PathVariable long id) {

		ModelAndView modelAndView = new ModelAndView("home");

		fQuestionService.deleteResource(id);

		String message = "question was successfully deleted!";
		modelAndView.addObject("message", message);
		return modelAndView;

	}

	public Object verify(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
