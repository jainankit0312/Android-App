package com.intalio.android.controller;

import java.util.List;

import org.apache.log4j.Logger;
import com.intalio.android.DAO.LeadsDAO;

/**
 * TODO :: what does this class do?
 * 
 * @author ankit
 * 
 */

public class LeadsBO 
{
	
	private Logger logger = Logger.getLogger(this.getClass());
	private LeadsDAO leadsDAO;
	
	public LeadsDAO getLeadsDAO() 
	{
		return leadsDAO;
	}

	public void setLeadsDAO(LeadsDAO leadsDAO1) 
	{
		this.leadsDAO = leadsDAO1;
	}

	/**
	 * Returns leads
	*/
	public List getLeads(int offset,int limit) 
	{
		try 
		{
			return getLeadsDAO().getLeads(offset,limit);
		} 
		catch (Exception e) 
		{
			logger.error("Error while getting leads", e);
		}
		return null;
	}
	/**
	 * Returns detail of lead on id
	*/
	public List getLeadsByID(long leadsID) 
	{
		try 
		{
			return getLeadsDAO().getLeadsByID(leadsID);
		} 
		catch (Exception e) 
		{
			logger.error("Error while getting leads by id", e);
		}
		return null;
	}
	
}

