package com.megalink.ems.surface.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public interface AnnotatorService {
	public Map<String, Object> search(HttpServletRequest request);

	public String create(HttpServletRequest request);

	public String update(HttpServletRequest request, String id);

	public String delete(HttpServletRequest request, String id);

	public String getallpagenums(HttpServletRequest request, String oid);
}
