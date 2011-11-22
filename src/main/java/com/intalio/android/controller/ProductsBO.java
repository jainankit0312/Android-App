package com.intalio.android.controller;

import java.util.List;

import org.apache.log4j.Logger;
import com.intalio.android.DAO.ProductsDAO;

/**
 * Main Business Object class for Products
 * 
 * @author ankit
 * 
 */

public class ProductsBO 
{
	
	private Logger logger = Logger.getLogger(this.getClass());
	private ProductsDAO productsDAO;
	
	public ProductsDAO getProductsDAO() 
	{
		return productsDAO;
	}

	public void setProductsDAO(ProductsDAO productsDAO1) 
	{
		this.productsDAO = productsDAO1;
	}

	/**
	 * Returns products
	*/
	public List getProducts(int offset,int limit) 
	{
		try 
		{
			return getProductsDAO().getProducts(offset,limit);
		} 
		catch (Exception e) 
		{
			logger.error("Error while getting products", e);
		}
		return null;
	}
	/**
	 * Returns detail of product on id
	*/
	public List getProductsByID(long productsID) 
	{
		try 
		{
			return getProductsDAO().getProductsByID(productsID);
		} 
		catch (Exception e) 
		{
			logger.error("Error while getting products by id", e);
		}
		return null;
	}
	
}

