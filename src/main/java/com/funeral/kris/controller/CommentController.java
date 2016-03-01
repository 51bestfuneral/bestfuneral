package com.funeral.kris.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.busModel.CommentJson;
import com.funeral.kris.model.Comment;
import com.funeral.kris.model.User;
import com.funeral.kris.service.CommentService;
import com.funeral.kris.service.UserService;

@Controller
@RequestMapping(value="/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	@Autowired
	private UserService userService;
	@Autowired
	private EntityManager em;
	private Map<String, String> userMap;
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public ModelAndView addCommentPage() {
		ModelAndView modelAndView = new ModelAndView("add-comment-form");
		modelAndView.addObject("comment", new Comment());
		return modelAndView;
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public ModelAndView addingComment(@ModelAttribute Comment comment) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		commentService.addResource(comment);
		
		String message = "Comment was successfully added.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}

	@ResponseBody
	@RequestMapping(value="/list",method=RequestMethod.GET, produces = "application/json")
	public List<CommentJson> listOfComments(HttpServletRequest request) {
		List<CommentJson> commentJsons = new ArrayList<CommentJson>();

		List<Comment> comments = commentService.getResources(request);
		try {
		    userMap = getUserMap();
		}
		catch(Exception e) {
			System.out.println("error happen"+e.getMessage());
		}
		for (Comment comment: comments) {
			CommentJson commentJson = new CommentJson();
			commentJson.setComment(comment);
			commentJson.setUserName(userMap.get(comment.getUserId()));
			List<CommentJson> subComments = getSubComments(comment.getCommentId());
			commentJson.setSubCommentList(subComments);
			commentJsons.add(commentJson);
		}

		return commentJsons;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.GET)
	public ModelAndView editCommentPage(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("edit-comment-form");
		Comment comment = commentService.getResource(id);
		modelAndView.addObject("comment",comment);
		return modelAndView;
	}
	
	@RequestMapping(value="/edit/{id}", method=RequestMethod.POST)
	public ModelAndView edditingComment(@ModelAttribute Comment comment, @PathVariable Integer id) {
		
		ModelAndView modelAndView = new ModelAndView("home");
		
		commentService.updateResource(comment);
		
		String message = "Comment was successfully edited.";
		modelAndView.addObject("message", message);
		
		return modelAndView;
	}
	
	@RequestMapping(value="/delete/{id}", method=RequestMethod.GET)
	public ModelAndView deleteComment(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView("home");
		commentService.deleteResource(id);
		String message = "Comment was successfully deleted.";
		modelAndView.addObject("message", message);
		return modelAndView;
	}

	private Map<String, String> getUserMap() throws Exception{
		Map<String, String> userMap = new HashMap<String, String>();
		List<User> users = userService.getResources();
		for (User user : users) {
			userMap.put(user.getUsrId().toString(), user.getUserName());
		}
		return userMap;
	}

	private List<CommentJson> getSubComments(Integer baseCommentId) {
		List<CommentJson> commentJsons = new ArrayList<CommentJson>();
		String a = "select c from Comment c where c.type = 'sub' and c.wishId='"+baseCommentId+"'";
		Query query = em.createQuery(a);
		List<Comment> comments = query.getResultList();
		for (Comment comment: comments) {
			CommentJson commentJson = new CommentJson();
			commentJson.setComment(comment);
			commentJson.setUserName(userMap.get(comment.getUserId()));
			commentJsons.add(commentJson);
		}
		return commentJsons;
	}
}
