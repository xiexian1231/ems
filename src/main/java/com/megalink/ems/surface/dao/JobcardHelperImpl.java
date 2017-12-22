package com.megalink.ems.surface.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.megalink.ems.model.Jobcard;

@Repository("jobcardHelper")
public class JobcardHelperImpl implements JobcardHelper {

	@Autowired
	private SessionFactory sessionFactory;

	private Session getCurrentSession() {
		return this.sessionFactory.getCurrentSession();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Jobcard> queryJobcards(String planenumber, String entrydate, String username) {
		List<Jobcard> jobcardList = new ArrayList<Jobcard>();
		Session session = this.getCurrentSession();
		String hql = "from Jobcard where aircraftNumber = ? and syncTime = ? and mechanic = ?";
		Query query = session.createQuery(hql);
		query.setString(0, planenumber);
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			Date date = sdf.parse(entrydate);
			query.setDate(1, date);
			query.setString(2, username);
			jobcardList = query.list();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return jobcardList;
	}

	@Override
	public int updateJobcard(Integer id, String taskTime) {
		return this.getCurrentSession().createQuery("update Jobcard set workingHours= ? where id= ?")
				.setParameter(0, taskTime).setParameter(1, id).executeUpdate();
	}

}
