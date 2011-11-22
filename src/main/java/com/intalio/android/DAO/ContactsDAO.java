package com.intalio.android.DAO;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * DAO class for Contacts.
 * 
 * @author ankit
 */

public class ContactsDAO extends SqlMapClientDaoSupport {
	public List getContacts(int offset, int limit) {
		int rowsfrom = offset - 1;
		int rowsto = limit - rowsfrom;
		Map QueryData = new LinkedHashMap();
		QueryData.put("startFrom", rowsfrom);
		QueryData.put("upto", rowsto);
		return super.getSqlMapClientTemplate().queryForList("getContacts",
				QueryData);
	}

	public List getContactsOnID(long id) {
		Map QueryData = new LinkedHashMap();
		QueryData.put("id", id);
		return super.getSqlMapClientTemplate().queryForList("getContactsByID",
				QueryData);
	}

	public List getContactsOnAccountID(long xid, int offset, int limit) {
		int rowsfrom = offset - 1;
		int rowsto = limit - rowsfrom;
		Map QueryData = new LinkedHashMap();
		QueryData.put("startFrom", rowsfrom);
		QueryData.put("upto", rowsto);
		QueryData.put("id", xid);
		return super.getSqlMapClientTemplate().queryForList(
				"getContactsByAccountID", QueryData);
	}

}