package com.intalio.cloud.smartphone.android;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.intalio.android.Constants.AndroidControllerConstants;
import com.intalio.android.controller.AccountsBO;
import com.intalio.android.controller.BusinessProcessesBO;
import com.intalio.android.controller.CompetitorsBO;
import com.intalio.android.controller.ContactsBO;
import com.intalio.android.controller.ContractsBO;
import com.intalio.android.controller.InvoicesBO;
import com.intalio.android.controller.LeadsBO;
import com.intalio.android.controller.OpportunitiesBO;
import com.intalio.android.controller.PriceListsBO;
import com.intalio.android.controller.ProductsBO;
import com.intalio.android.controller.QuotesBO;

/**
 * Main controller. 
 * Checks the authenticity of the Request.
 * It calls the method from the BO classes as per the request parameters.
 * Sends the result-set in JSON format.
 *
 * @author ankit
 */
public class AndroidController implements Controller {

	private AndroidControllerConstants constant;
	private AccountsBO accountBO;
	private ContactsBO contactsBO;
	private ContractsBO contractsBO;
	private OpportunitiesBO opportunitiesBO;
	private InvoicesBO invoicesBO;
	private LeadsBO leadsBO;
	private ProductsBO productsBO;
	private CompetitorsBO competitorsBO;
	private PriceListsBO pricelistsBO;
	private QuotesBO quotesBO;
	private BusinessProcessesBO businessprocessBO;
	
	public AccountsBO getAccountBO() {
		return accountBO;
	}
	public void setAccountBO(AccountsBO accountBO) {
		this.accountBO = accountBO;
	}
	public BusinessProcessesBO getBusinessprocessBO() {
		return businessprocessBO;
	}
	public void setBusinessprocessBO(BusinessProcessesBO businessprocessBO) {
		this.businessprocessBO = businessprocessBO;
	}
	public QuotesBO getQuotesBO() {
		return quotesBO;
	}
	public void setQuotesBO(QuotesBO quotesBO) {
		this.quotesBO = quotesBO;
	}
	public PriceListsBO getPricelistsBO() {
		return pricelistsBO;
	}
	public void setPricelistsBO(PriceListsBO pricelistsBO) {
		this.pricelistsBO = pricelistsBO;
	}
	public CompetitorsBO getCompetitorsBO() {
		return competitorsBO;
	}
	public void setCompetitorsBO(CompetitorsBO competitorsBO) {
		this.competitorsBO = competitorsBO;
	}
	
	public ProductsBO getProductsBO() {
		return productsBO;
	}
	public void setProductsBO(ProductsBO productsBO) {
		this.productsBO = productsBO;
	}
	
	public LeadsBO getLeadsBO() {
		return leadsBO;
	}

	public void setLeadsBO(LeadsBO leadsBO) {
		
		this.leadsBO = leadsBO;
	}
	public ContractsBO getContractsBO() {
		return contractsBO;
	}

	public void setContractsBO(ContractsBO contractsBO) {
		this.contractsBO = contractsBO;
	}

	public ContactsBO getContactsBO() {
		return contactsBO;
	}

	public void setContactsBO(ContactsBO contactsBO) {
		this.contactsBO = contactsBO;
	}

	public OpportunitiesBO getOpportunitiesBO() {
		return opportunitiesBO;
	}

	public void setOpportunitiesBO(OpportunitiesBO opportunitiesBO) {
		this.opportunitiesBO = opportunitiesBO;
	}

	public InvoicesBO getInvoicesBO() {
		return invoicesBO;
	}

	public void setInvoicesBO(InvoicesBO invoicesBO) {
		this.invoicesBO = invoicesBO;
	}
	
	public ModelAndView handleRequest(HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		Map model = new LinkedHashMap();
		
		// Following code checks the authenticity of the request URL
		boolean proceed =true;
		String ErrorMessage = "" ;
		try
		{
			if(request.getParameter("action") != null)
			{
				String action = request.getParameter("action");
				if((action.equals(constant.actionAccounts)) || (action.equals(constant.actionContacts)) ||(action.equals(constant.actionContracts)))
				{
					proceed =true;
				}
				else if((action.equals(constant.actionOpportunities)) || (action.equals(constant.actionInvoices)) ||(action.equals(constant.actionLeads)))
				{	
					proceed =true;					
				}
				else if((action.equals(constant.actionProducts)) || (action.equals(constant.actionCompetitors)) ||(action.equals(constant.actionPriceList)))
				{
					proceed =true;
				}
				else if((action.equals(constant.actionQuotes)) || (action.equals(constant.actionBusinessProcess)) ||(action.equals(constant.actionPriceList)))
				{
					proceed =true;
				}
				else // action is not correct
				{
					proceed =false;
					ErrorMessage = "No such action is defined" ;
				}
				
				if(request.getParameter("subaction") != null)
				{
					String subAction = request.getParameter("subaction");
					if(subAction.equals(constant.subActionRows))
					{
						
						if(request.getParameter(constant.varFrom) == null)
						{
							proceed =false;
							ErrorMessage = "startfrom was expected." ; 
						}
						if(request.getParameter(constant.varUpto) == null)
						{
							proceed =false;
							ErrorMessage = "upto was expected." ; 
						}
					}
					else if(subAction.equals(constant.subActionInfo))
					{
						
						if(request.getParameter(constant.varId) == null)
						{
							proceed =false;
							ErrorMessage = "id was expected." ; 
						}
					}
					else if((subAction.equals(constant.subActionByAccount)) || (subAction.equals(constant.subActionByPriceList)))
					{
						
						if(request.getParameter(constant.varFrom) == null)
						{
							proceed =false;
							ErrorMessage = "startfrom was expected." ; 
						}
						if(request.getParameter(constant.varUpto) == null)
						{
							proceed =false;
							ErrorMessage = "upto was expected." ; 
						}
						if(request.getParameter(constant.varId) == null)
						{
							proceed =false;
							ErrorMessage = "id was expected." ; 
						}
					}
					else // SubAction is not correct
					{
						proceed =false;
						ErrorMessage = "No such subaction is defined" ; 
					}
				}
				else // subAction is null
				{
					proceed =false;
					ErrorMessage = "subaction was expected." ; 
				}
			}
			else // action is null
			{
				proceed =false;
				ErrorMessage = "action was expected." ; 
			}
			
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
			
		}
		
		// if the URL is correct then only this block will get executed
		if(proceed)
		{
			String action = request.getParameter("action");
			if(action.equals(constant.actionAccounts))
			{
				String subaction = request.getParameter("subaction");
				if(subaction.equals(constant.subActionRows))
				{
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("accounts", accountBO.getAccounts(startfrom,upto));
				}
				else if(subaction.equals(constant.subActionInfo))
				{
					long accountid = Long.parseLong(request.getParameter(constant.varId));
					model.put("accounts", accountBO.getAccountsByID(accountid));
				}
			}
			else if(action.equals(constant.actionContacts))
			{
				String subaction = request.getParameter("subaction");
				if(subaction.equals(constant.subActionRows))
				{
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("contacts", contactsBO.getContacts(startfrom,upto));
				}
				else if(subaction.equals(constant.subActionInfo))
				{
					long contactid = Long.parseLong(request.getParameter(constant.varId));
					model.put("contacts", contactsBO.getContactsByID(contactid));
				}
				else if(subaction.equals(constant.subActionByAccount))
				{
					long xid = Long.parseLong(request.getParameter(constant.varId));
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("contacts", contactsBO.getContactsByAccount(xid,startfrom,upto));
				}
			}
			else if(action.equals(constant.actionContracts))
			{
				String subaction = request.getParameter("subaction");
				if(subaction.equals(constant.subActionRows))
				{
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("contracts", contractsBO.getContracts(startfrom,upto));
				}
				else if(subaction.equals(constant.subActionInfo))
				{
					long contactid = Long.parseLong(request.getParameter(constant.varId));
					model.put("contracts", contractsBO.getContractsByID(contactid));
				}
				else if(subaction.equals(constant.subActionByAccount))
				{
					long xid = Long.parseLong(request.getParameter(constant.varId));
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("contracts", contractsBO.getContractsByAccountID(xid,startfrom,upto));
				}
			}
			else if(action.equals(constant.actionOpportunities))
			{
				String subaction = request.getParameter("subaction");
				if(subaction.equals(constant.subActionRows))
				{
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("opportunities", opportunitiesBO.getOpportunities(startfrom,upto));
				}
				else if(subaction.equals(constant.subActionInfo))
				{
					long contactid = Long.parseLong(request.getParameter(constant.varId));
					model.put("opportunities", opportunitiesBO.getOpportunitiesByID(contactid));
				}
				else if(subaction.equals(constant.subActionByAccount))
				{
					long xid = Long.parseLong(request.getParameter(constant.varId));
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("opportunities", opportunitiesBO.getOpportunitiesByAccountID(xid,startfrom,upto));
				}
			}
			else if(action.equals(constant.actionInvoices))
			{
				String subaction = request.getParameter("subaction");
				if(subaction.equals(constant.subActionRows))
				{
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("invoices", invoicesBO.getInvoices(startfrom,upto));
				}
				else if(subaction.equals(constant.subActionInfo))
				{
					long contactid = Long.parseLong(request.getParameter(constant.varId));
					model.put("invoices", invoicesBO.getInvoicesByID(contactid));
				}
				else if(subaction.equals(constant.subActionByAccount))
				{
					long xid = Long.parseLong(request.getParameter(constant.varId));
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("invoices", invoicesBO.getInvoicesByAccountID(xid,startfrom,upto));
				}
			}
			else if(action.equals(constant.actionLeads))
			{
				String subaction = request.getParameter("subaction");
				if(subaction.equals(constant.subActionRows))
				{
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("leads", leadsBO.getLeads(startfrom,upto));
				}
				else if(subaction.equals(constant.subActionInfo))
				{
					long leadsid = Long.parseLong(request.getParameter(constant.varId));
					model.put("leads", leadsBO.getLeadsByID(leadsid));
				}
			}
			else if(action.equals(constant.actionProducts))
			{
				String subaction = request.getParameter("subaction");
				if(subaction.equals(constant.subActionRows))
				{
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("products", productsBO.getProducts(startfrom,upto));
				}
				else if(subaction.equals(constant.subActionInfo))
				{
					long productsid = Long.parseLong(request.getParameter(constant.varId));
					model.put("products", productsBO.getProductsByID(productsid));
				}
			}
			else if(action.equals(constant.actionCompetitors))
			{
				String subaction = request.getParameter("subaction");
				if(subaction.equals(constant.subActionRows))
				{
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("competitors", competitorsBO.getCompetitors(startfrom,upto));
				}
				else if(subaction.equals(constant.subActionInfo))
				{
					long Competitorsid = Long.parseLong(request.getParameter(constant.varId));
					model.put("competitors", competitorsBO.getCompetitorsByID(Competitorsid));
				}
			}
			else if(action.equals(constant.actionPriceList))
			{
				String subaction = request.getParameter("subaction");
				if(subaction.equals(constant.subActionRows))
				{
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("pricelists", pricelistsBO.getPriceLists(startfrom,upto));
				}
				else if(subaction.equals(constant.subActionInfo))
				{
					long Competitorsid = Long.parseLong(request.getParameter(constant.varId));
					model.put("pricelists", pricelistsBO.getPriceListsByID(Competitorsid));
				}
				else if(subaction.equals(constant.subActionByPriceList))
				{
					long xid = Long.parseLong(request.getParameter(constant.varId));
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("pricelists", pricelistsBO.getPriceListsByProductID(xid,startfrom,upto));
				}
			}
			else if(action.equals(constant.actionQuotes))
			{
				String subaction = request.getParameter("subaction");
				if(subaction.equals(constant.subActionRows))
				{
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("quotes", quotesBO.getQuotes(startfrom,upto));
				}
				else if(subaction.equals(constant.subActionInfo))
				{
					long contactid = Long.parseLong(request.getParameter(constant.varId));
					model.put("quotes", quotesBO.getQuotesByID(contactid));
				}
				else if(subaction.equals(constant.subActionByAccount))
				{
					long xid = Long.parseLong(request.getParameter(constant.varId));
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("quotes", quotesBO.getQuotesByAccount(xid,startfrom,upto));
				}
			}
			else if(action.equals(constant.actionBusinessProcess))
			{
				String subaction = request.getParameter("subaction");
				if(subaction.equals(constant.subActionRows))
				{
					int startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
					int upto = Integer.parseInt(request.getParameter(constant.varUpto));
					model.put("businessprocess", businessprocessBO.getBusinessProcesses(startfrom,upto));
				}
				else if(subaction.equals(constant.subActionInfo))
				{
					long processID = Long.parseLong(request.getParameter(constant.varId));
					model.put("businessprocess", businessprocessBO.getBusinessProcessesByID(processID));
				}
			}
		}
		else
		{
			model.put("Error", ErrorMessage);
		}
		ModelAndView modelAndView = new ModelAndView("jsonView", model);
		return modelAndView;
	}
	
	
}
