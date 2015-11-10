package com.funeral.kris.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.funeral.kris.model.Comment;
import com.funeral.kris.service.CommentService;

@Controller
@RequestMapping(value="/comment")
public class CommentController {
	
	@Autowired
	private CommentService commentService;
	
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
	public List<Comment> listOfComments() {
		ModelAndView modelAndView = new ModelAndView("list-of-comments");

		List<Comment> comments = commentService.getResources();
		modelAndView.addObject("comments", comments);

		return comments;
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

}
