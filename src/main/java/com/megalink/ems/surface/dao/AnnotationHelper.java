package com.megalink.ems.surface.dao;

import java.util.List;

import com.megalink.ems.model.AnnotationEntry;

public interface AnnotationHelper {
	public AnnotationEntry createAnnotation(String jsonStr);

	public List<AnnotationEntry> queryAnnotations(String docOid);

	public AnnotationEntry getAnnotation(String annotationId);

	public AnnotationEntry updateAnnotationByJsonStr(String annotationId, String jsonStr);

	public void deleteAnnotation(String annotationId);
}
