package com.intalio.android.controller;

import java.util.List;

import org.apache.log4j.Logger;
import com.intalio.android.DAO.InvoicesDAO;

/**
 * TODO :: what does this class do?
 * 
 * @author ankit
 * 
 */

public class InvoicesBO 
{
	
	private Logger logger = Logger.getLogger(this.getClass());
	private InvoicesDAO invoiceDAO;
	
	public InvoicesDAO getInvoicesDAO() 
	{
		return invoiceDAO;
	}

	public void setInvoicesDAO(InvoicesDAO invoiceDAO1) 
	{
		this.invoiceDAO = invoiceDAO1;
	}

	/**
	 * Returns Invoices 
	*/
	public List getInvoices(int from, int to) 
	{
		try 
		{
			return getInvoicesDAO().getInvoices(from,to);
		} 
		catch (Exception e) 
		{
			logger.error("Error while getting invoices", e);
		}
		return null;
	}
	/**
	 * Returns invoice Details on its id
	*/
	public List getInvoicesByID(long invoiceid) 
	{
		try 
		{
			return getInvoicesDAO().getInvoicesOnID(invoiceid);
		} 
		catch (Exception e) 
		{
			logger.error("Error while getting invoice by id", e);
		}
		return null;
	}
	/**
	 * Returns invoice Details on its id
	*/
	public List getInvoicesByAccountID(long accountid,int from, int to) 
	{
		try 
		{
			return getInvoicesDAO().getInvoicesOnAccountID(accountid,from,to);
		} 
		catch (Exception e) 
		{
			logger.error("Error while getting invoice by id", e);
		}
		return null;
	}
}

