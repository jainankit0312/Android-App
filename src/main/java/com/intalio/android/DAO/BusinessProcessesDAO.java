package com.intalio.android.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * DAO class for Business Processes.
 * @author ankit
 */

public class BusinessProcessesDAO extends SqlMapClientDaoSupport {
	public List getBusinessProcesses(int offset, int limit) {
		int rowsfrom = offset - 1;
		int rowsto = limit - rowsfrom;
		Map QueryData = new HashMap();
		QueryData.put("startFrom", rowsfrom);
		QueryData.put("upto", rowsto);
		return super.getSqlMapClientTemplate().queryForList("getProcesses",
				QueryData);
	}

	public List getBusinessProcessesByID(String processID) {
		Map QueryData = new HashMap();
		QueryData.put("id", processID);
		return super.getSqlMapClientTemplate().queryForList("getProcessesByID",
				QueryData);
	}
}