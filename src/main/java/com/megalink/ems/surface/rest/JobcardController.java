package com.megalink.ems.surface.rest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megalink.ems.model.Jobcard;
import com.megalink.ems.surface.service.JobcardService;

@Controller
@RequestMapping("/jobcard")
public class JobcardController {
	@SuppressWarnings("unused")
	private static final Logger LOGGER = Logger.getLogger(JobcardController.class);

	@Autowired
	private JobcardService jobcardService;

	@RequestMapping(value = "/queryJobcards", method = RequestMethod.POST)
	@ResponseBody
	public List<Jobcard> queryJobcards(HttpServletRequest request, HttpServletResponse response) {
		List<Jobcard> jobcardList = new ArrayList<>();
		jobcardList = jobcardService.queryJobcards(request, response);
		return jobcardList;
	}

	@RequestMapping(value = "/transform/{id}/{taskTime}/{fileName}", method = RequestMethod.GET)
	public String xmltransfer(ModelMap modelMap, HttpServletRequest request, @PathVariable String taskTime,
			@PathVariable String fileName) {
		modelMap = jobcardService.transformXMLToHTML(modelMap, request, taskTime, fileName);
		return "/netmarkets/jsp/surface/jobcard";
	}

	@RequestMapping(value = "/store", method = RequestMethod.POST)
	public void store(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String msg = jobcardService.store(request, response);
		response.getWriter().println(msg);
	}

}
