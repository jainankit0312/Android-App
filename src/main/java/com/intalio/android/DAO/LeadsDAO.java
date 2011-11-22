package com.intalio.android.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * DAO class for Leads.
 * 
 * @author ankit
 */

public class LeadsDAO extends SqlMapClientDaoSupport
{
	public List getLeads(int offset, int limit) 
	{
		int rowsfrom = offset-1;
		int rowsto = limit-rowsfrom;
		Map QueryData = new HashMap();
		QueryData.put("startFrom", rowsfrom);
		QueryData.put("upto", rowsto);
		return super.getSqlMapClientTemplate().queryForList("getLeads",QueryData);
	}
	
	public List getLeadsByID(long leadid) 
	{
		Map QueryData = new HashMap();
		QueryData.put("id", leadid);
		return super.getSqlMapClientTemplate().queryForList("getLeadsByID",QueryData);
	}
}