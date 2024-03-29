package com.intalio.android.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * DAO class for Opportunities.
 * 
 * @author ankit
 */

public class OpportunitiesDAO extends SqlMapClientDaoSupport {
	public List getOpportunities(int offset, int limit) {
		int rowsfrom = offset - 1;
		int rowsto = limit - rowsfrom;
		Map QueryData = new HashMap();
		QueryData.put("startFrom", rowsfrom);
		QueryData.put("upto", rowsto);
		return super.getSqlMapClientTemplate().queryForList("getOpportunities",
				QueryData);
	}

	public List getOpportunitiesByID(long accountid) {
		Map QueryData = new HashMap();
		QueryData.put("id", accountid);
		return super.getSqlMapClientTemplate().queryForList(
				"getOpportunityByID", QueryData);
	}

	public List getOpportunitiesByAccountID(long accountid, int offset,
			int limit) {
		int rowsfrom = offset - 1;
		int rowsto = limit - rowsfrom;
		Map QueryData = new HashMap();
		QueryData.put("startFrom", rowsfrom);
		QueryData.put("upto", rowsto);
		QueryData.put("id", accountid);
		return super.getSqlMapClientTemplate().queryForList(
				"getOpportunitiesByAccountID", QueryData);
	}
}