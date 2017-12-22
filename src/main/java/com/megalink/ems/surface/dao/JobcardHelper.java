package com.megalink.ems.surface.dao;

import java.util.List;

import com.megalink.ems.model.Jobcard;

public interface JobcardHelper {
	public List<Jobcard> queryJobcards(String planenumber, String entrydate, String username);

	public int updateJobcard(Integer id, String taskTime);
}
