package com.megalink.ems.surface.service;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.ui.ModelMap;

import com.megalink.ems.model.Jobcard;

public interface JobcardService {
	public List<Jobcard> queryJobcards(HttpServletRequest request, HttpServletResponse response);

	public ModelMap transformXMLToHTML(ModelMap modelMap, HttpServletRequest request, String taskTime, String fileName);

	public String store(HttpServletRequest request, HttpServletResponse response);

	public int updateJobcard(Integer id, String taskTime);
}
