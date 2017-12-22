package com.megalink.ems.surface.rest;

import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.megalink.ems.surface.service.AnnotatorService;

@Controller
@RequestMapping("/annotator")
public class AnnotatorController {
	static final Logger log = Logger.getLogger(AnnotatorController.class.getName());

	@Autowired
	private AnnotatorService annotatorService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@ResponseBody
	public Map<String, Object> search(HttpServletRequest request) {
		Map<String, Object> map = annotatorService.search(request);
		return map;
	}

	@RequestMapping(value = "/annotations", method = RequestMethod.POST)
	@ResponseBody
	public String create(HttpServletRequest request) {
		String jsonStr = annotatorService.create(request);
		return jsonStr;
	}

	@RequestMapping(value = "/annotations/{id}", method = RequestMethod.PUT)
	@ResponseBody
	public String update(HttpServletRequest request, @PathVariable String id) {
		String jsonStr = annotatorService.update(request, id);
		return jsonStr;
	}

	@RequestMapping(value = "/annotations/{id}", method = RequestMethod.DELETE)
	@ResponseBody
	public String delete(HttpServletRequest request, @PathVariable String id) {
		String jsonStr = annotatorService.delete(request, id);
		return jsonStr;
	}

	@RequestMapping(value = "/getallpagenums/{oid}", method = RequestMethod.GET)
	@ResponseBody
	public String getallpagenums(HttpServletRequest request, @PathVariable String oid) {
		String jsonStr = annotatorService.getallpagenums(request, oid);
		return jsonStr;
	}

}
