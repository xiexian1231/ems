package com.megalink.ems.surface.dao;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.megalink.ems.model.AnnotationEntry;

@Repository
public class AnnotationHelperImpl implements AnnotationHelper {
	static final Logger log = Logger.getLogger(AnnotationHelperImpl.class.getName());

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@Override
	public AnnotationEntry createAnnotation(String jsonStr) {
		String str = jsonStr;
		str = str.replace("\n", "");
		AnnotationEntry at = new AnnotationEntry(str);
		this.getCurrentSession().saveOrUpdate(at);
		return at;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<AnnotationEntry> queryAnnotations(String docOid) {
		Criteria c = this.getCurrentSession().createCriteria(AnnotationEntry.class);
		c.add(Restrictions.eq("docId", docOid));
		c.addOrder(Order.asc("pageNumber"));
		c.addOrder(Order.asc("id"));
		List<AnnotationEntry> list = c.list();
		return list;
	}

	@Override
	public AnnotationEntry getAnnotation(String annotationId) {
		AnnotationEntry annotationEntry = (AnnotationEntry) this.getCurrentSession().get(AnnotationEntry.class,
				Integer.parseInt(annotationId));
		if (annotationEntry != null) {
			return annotationEntry;
		}
		return null;
	}

	@Override
	public AnnotationEntry updateAnnotationByJsonStr(String annotationId, String jsonStr) {
		AnnotationEntry ae = getAnnotation(annotationId);
		ae.loadFromJsonStr(jsonStr);
		this.getCurrentSession().update(ae);
		return ae;
	}

	@Override
	public void deleteAnnotation(String annotationId) {
		AnnotationEntry ae = getAnnotation(annotationId);
		this.getCurrentSession().delete(ae);
	}

}
