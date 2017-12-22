package com.megalink.ems.surface.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.megalink.ems.model.AnnotationEntry;
import com.megalink.ems.surface.dao.AnnotationHelper;

@Service
@Transactional
public class AnnotatorServiceImpl implements AnnotatorService {
	static final Logger log = Logger.getLogger(AnnotatorServiceImpl.class.getName());

	@Autowired
	private AnnotationHelper annotationHelper;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Map<String, Object> search(HttpServletRequest request) {
		String oid = request.getParameter("oid");
		log.debug("search - oid:" + oid);
		Map<String, Object> map = new HashMap();
		List arr = new ArrayList();
		try {
			List<AnnotationEntry> aes = annotationHelper.queryAnnotations(oid);
			for (AnnotationEntry ae : aes) {
				arr.add(ae);
			}
			map.put("total", arr.size() + "");
			map.put("rows", arr);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return map;
	}

	private String getRequestPayload(HttpServletRequest req) {
		StringBuilder sb = new StringBuilder();
		try {
			BufferedReader reader = req.getReader();
			char[] buff = new char[1024];
			int len;
			while ((len = reader.read(buff)) != -1) {
				sb.append(buff, 0, len);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return sb.toString();
	}

	@Override
	public String create(HttpServletRequest request) {
		String str = getRequestPayload(request);
		try {
			annotationHelper.createAnnotation(str);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return str;
	}

	@Override
	public String update(HttpServletRequest request, String id) {
		String annotationId = id;
		String str = getRequestPayload(request);
		log.debug("annotationId:" + annotationId + " - str:" + str);
		try {
			annotationHelper.updateAnnotationByJsonStr(annotationId, str);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return str;
	}

	@Override
	public String delete(HttpServletRequest request, String id) {
		String annotationId = id;
		String action = request.getParameter("action");
		log.debug(action + " - id:" + annotationId);
		try {
			annotationHelper.deleteAnnotation(annotationId);
			JSONObject obj = new JSONObject();
			obj.put("deleted", true);
			obj.put("id", annotationId);
			return obj.toString();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
			JSONObject obj = new JSONObject();
			obj.put("deleted", false);
			obj.put("id", annotationId);
			return obj.toString();
		}
	}

	@Override
	public String getallpagenums(HttpServletRequest request, String oid) {
		log.debug("search - oid:" + oid);
		JSONObject obj = new JSONObject();
		String pagenums = ",";
		try {
			List<AnnotationEntry> aes = annotationHelper.queryAnnotations(oid);
			for (AnnotationEntry ae : aes) {
				if (pagenums.indexOf("," + ae.getPageNumber() + ",") < 0) {
					pagenums = pagenums + ae.getPageNumber() + ",";
				}
			}
			obj.put("pagenums", pagenums.subSequence(1, pagenums.length()));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
			e.printStackTrace();
		}
		return obj.toString();
	}

}
