package com.intalio.android.DAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

/**
 * DAO class for Products.
 * 
 * @author ankit
 */

public class ProductsDAO extends SqlMapClientDaoSupport {
	public List getProducts(int offset, int limit) {
		int rowsfrom = offset - 1;
		int rowsto = limit - rowsfrom;
		Map QueryData = new HashMap();
		QueryData.put("startFrom", rowsfrom);
		QueryData.put("upto", rowsto);
		return super.getSqlMapClientTemplate().queryForList("getProducts",
				QueryData);
	}

	public List getProductsByID(long productID) {
		Map QueryData = new HashMap();
		QueryData.put("id", productID);
		return super.getSqlMapClientTemplate().queryForList("getProductsByID",
				QueryData);
	}
}