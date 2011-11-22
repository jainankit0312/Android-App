package com.intalio.android.controller;

import java.util.List;

import org.apache.log4j.Logger;
import com.intalio.android.DAO.BusinessProcessesDAO;

/**
 * TODO :: what does this class do?
 * 
 * @author ankit
 * 
 */

public class BusinessProcessesBO 
{
	
	private Logger logger = Logger.getLogger(this.getClass());
	private BusinessProcessesDAO businessprocessDAO;
	
	public BusinessProcessesDAO getBusinessprocessDAO() {
		return businessprocessDAO;
	}
	public void setBusinessprocessDAO(BusinessProcessesDAO businessprocessesDAO) {
		this.businessprocessDAO = businessprocessesDAO;
	}

	/**
	 * Returns processes details
	*/
	public List getBusinessProcesses(int offset,int limit) 
	{
		try 
		{
			return getBusinessprocessDAO().getBusinessProcesses(offset,limit);
		} 
		catch (Exception e) 
		{
			logger.error("Error while getting Processes", e);
		}
		return null;
	}
	/**
	 * Returns Processes details on ID
	*/
	public List getBusinessProcessesByID(long processID) 
	{
		try 
		{
			return getBusinessprocessDAO().getBusinessProcessesByID(processID);
		} 
		catch (Exception e) 
		{
			logger.error("Error while getting Accounts by id", e);
		}
		return null;
	}
	
	
}

