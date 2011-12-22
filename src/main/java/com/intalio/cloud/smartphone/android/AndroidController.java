package com.intalio.cloud.smartphone.android;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import com.intalio.android.Constants.AndroidControllerConstants;
import com.intalio.android.controller.AccountsBO;
import com.intalio.android.controller.AssetsBO;
import com.intalio.android.controller.BusinessProcessesBO;
import com.intalio.android.controller.CasesBO;
import com.intalio.android.controller.CompetitorsBO;
import com.intalio.android.controller.ContactsBO;
import com.intalio.android.controller.ContractsBO;
import com.intalio.android.controller.GenericBO;
import com.intalio.android.controller.InvoicesBO;
import com.intalio.android.controller.LeadsBO;
import com.intalio.android.controller.OpportunitiesBO;
import com.intalio.android.controller.PriceListsBO;
import com.intalio.android.controller.ProductsBO;
import com.intalio.android.controller.QuotesBO;
import com.intalio.android.controller.SolutionsBO;

/**
 * Main controller. Checks the authenticity of the Request. It calls the method
 * from the BO classes as per the request parameters. Sends the result-set in
 * JSON format.
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
	private GenericBO genericBO;
	private AssetsBO assetsBO;
	private CasesBO casesBO;
	private SolutionsBO solutionsBO;
	

	public ModelAndView handleRequest(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Logger log = Logger.getLogger(this.getClass());
		Map model = new LinkedHashMap();
		Map inner = new HashMap();
		
		// TODO Can we check if action & subactions validation be done in a seprate method instead of here
		
		// Following code checks the authenticity of the request URL
		boolean proceed = true;
		String ErrorMessage = "";
		String action = "";
		String subaction = "";
		String id = "";
		int startfrom = 0;
		int upto = 0;;
		
		try 
		{
			//Checking the Action
			if (request.getParameter("action") != null) 
			{
				String action1 = request.getParameter("action");
				for(int i=0;i<constant.checkAction.length;i++)
				{
					if(action1.equals(constant.checkAction[i]))
					{
						proceed = true;
						break;
					}
					else // action is not correct
					{
						proceed = false;
						ErrorMessage = "No such action is defined";
					}
				}
			}
			else
			{
				proceed = false;
				ErrorMessage = "No action is defined";
			}
			
			//Checking the SubAction
			if(proceed)
			{
				if (request.getParameter("subaction") != null) 
				{
					String subaction1 = request.getParameter("subaction");
					for(int i=0;i<constant.checkSubAction.length;i++)
					{
						if(subaction1.equals(constant.checkSubAction[i]))
						{
							proceed = true;
							break;
						}
						else // sub action is not correct
						{
							proceed = false;
							ErrorMessage = "No such sub action is defined";
						}
					}
				}
				else
				{
					proceed = false;
					ErrorMessage = "No sub action is defined";
				}
			}
		// Checking and Fetching the parameters
		if(proceed)
		{
			
			action = request.getParameter("action");
			subaction = request.getParameter("subaction");
			
			if(subaction.equals(constant.subActionRows))
			{
				startfrom = Integer.parseInt(request.getParameter(constant.varFrom));
				upto = Integer.parseInt(request.getParameter(constant.varUpto));
			}
			else if(subaction.equals(constant.subActionInfo))
			{
				id = request.getParameter(constant.varId);
			}
			
		}	
		// if the URL is correct then only this block will get executed
		if(proceed) 
		{ 
		
			/*
			 * "accounts" is should be same as what is mentioned in
			 * generic.xml
			 */
			
			if(action.equals(constant.actionCount)) 
			{
				if (subaction.equals(constant.subActionCount)) 
				{
					model.put("count", genericBO.getObjectCount());
				}
			}
			else if (action.equals(constant.actionAccounts)) 
			{
				if (subaction.equals(constant.subActionRows)) 
				{
					
					model.put("data", accountBO.getAccounts(startfrom, upto));
				} 
				else if (subaction.equals(constant.subActionInfo)) 
				{
					inner.put("main", accountBO.getAccountsByIDMain(id));
					inner.put("standard", accountBO.getAccountsByIDStandard(id));
					inner.put("specific", accountBO.getAccountsByIDSpecific(id));
					model.put("data", inner);
					//model.put("data1", specific);				
				}
				
			} 
			else if (action.equals(constant.actionContacts)) 
			{
				if (subaction.equals(constant.subActionRows)) 
				{
					model.put("data", contactsBO.getContacts(startfrom, upto));
				} 
				else if (subaction.equals(constant.subActionInfo)) 
				{
					inner.put("main", contactsBO.getContactsByIDMain(id));
					inner.put("standard", contactsBO.getContactsByIDStandard(id));
					inner.put("specific", contactsBO.getContactsByIDSpecific(id));
					model.put("data", inner);
				} 
				
			} 
			else if (action.equals(constant.actionContracts)) 
			{
				if (subaction.equals(constant.subActionRows)) 
				{
					model.put("data", contractsBO.getContracts(startfrom, upto));
				} 
				else if (subaction.equals(constant.subActionInfo)) 
				{
					inner.put("main", contractsBO.getContractsByIDMain(id));
					inner.put("standard", contractsBO.getContractsByIDStandard(id));
					inner.put("specific", contractsBO.getContractsByIDSpecific(id));
					model.put("data", inner);
					
				} 
				
			} 
			else if (action.equals(constant.actionOpportunities)) 
			{
				if (subaction.equals(constant.subActionRows)) 
				{
					
					model.put("data",opportunitiesBO.getOpportunities(startfrom, upto));
				} 
				else if (subaction.equals(constant.subActionInfo)) 
				{
					inner.put("main", opportunitiesBO.getOpportunitiesByIDMain(id));
					inner.put("standard", opportunitiesBO.getOpportunitiesByIDStandard(id));
					inner.put("specific", opportunitiesBO.getOpportunitiesByIDSpecific(id));
					model.put("data", inner);
					
				} 
			} 
			else if (action.equals(constant.actionInvoices)) 
			{
				if (subaction.equals(constant.subActionRows)) 
				{
					model.put("data", invoicesBO.getInvoices(startfrom, upto));
				} 
				else if (subaction.equals(constant.subActionInfo)) 
				{
					inner.put("main", invoicesBO.getInvoicesByIDMain(id));
					inner.put("standard", invoicesBO.getInvoicesByIDStandard(id));
					inner.put("specific", invoicesBO.getInvoicesByIDSpecific(id));
					model.put("data", inner);
				} 
			} 
			else if (action.equals(constant.actionLeads)) 
			{
				if (subaction.equals(constant.subActionRows)) 
				{
					
					model.put("data", leadsBO.getLeads(startfrom, upto));
				} 
				else if (subaction.equals(constant.subActionInfo)) 
				{
					inner.put("main", leadsBO.getLeadsByIDMain(id));
					inner.put("standard", leadsBO.getLeadsByIDStandard(id));
					inner.put("specific", leadsBO.getLeadsByIDSpecific(id));
					model.put("data", inner);
					
				}
			} 
			else if (action.equals(constant.actionProducts)) 
			{
				if (subaction.equals(constant.subActionRows)) 
				{
		
					model.put("data", productsBO.getProducts(startfrom, upto));
				}
				else if (subaction.equals(constant.subActionInfo)) 
				{
					
					inner.put("main", productsBO.getProductsByIDMain(id));
					inner.put("standard", productsBO.getProductsByIDStandard(id));
					inner.put("specific", productsBO.getProductsByIDSpecific(id));
					model.put("data", inner);
				}
			} 
			else if (action.equals(constant.actionCompetitors)) 
			{
				if (subaction.equals(constant.subActionRows)) 
				{
					model.put("data",competitorsBO.getCompetitors(startfrom, upto));
				} 
				else if (subaction.equals(constant.subActionInfo)) 
				{
					model.put("data",competitorsBO.getCompetitorsByID(id));
				}
			} 
			else if (action.equals(constant.actionPriceList)) 
			{
				if (subaction.equals(constant.subActionRows)) 
				{
					model.put("data",pricelistsBO.getPriceLists(startfrom, upto));
				} 
				else if (subaction.equals(constant.subActionInfo)) 
				{
					model.put("data",pricelistsBO.getPriceListsByID(id));
				} 
				
			} 
			else if (action.equals(constant.actionQuotes)) 
			{
				if (subaction.equals(constant.subActionRows)) 
				{
				
					model.put("data", quotesBO.getQuotes(startfrom, upto));
				} 
				else if (subaction.equals(constant.subActionInfo)) 
				{
					inner.put("main", quotesBO.getQuotesByIDMain(id));
					inner.put("standard", quotesBO.getQuotesByIDStandard(id));
					inner.put("specific", quotesBO.getQuotesByIDSpecific(id));
					model.put("data", inner);
				} 
			
			} 
			else if (action.equals(constant.actionBusinessProcess)) 
			{
				if (subaction.equals(constant.subActionRows)) 
				{
					model.put("data", businessprocessBO.getBusinessProcesses(startfrom, upto));
				} 
				else if (subaction.equals(constant.subActionInfo)) 
				{
					model.put("data", businessprocessBO.getBusinessProcessesByID(id));
				}
			}
			else if (action.equals(constant.actionAssets)) 
			{
				if (subaction.equals(constant.subActionRows)) 
				{
					model.put("data", assetsBO.getAssets(startfrom, upto));
				} 
				else if (subaction.equals(constant.subActionInfo)) 
				{
					inner.put("main", assetsBO.getAssetsByIDMain(id));
					inner.put("standard", assetsBO.getAssetsByIDStandard(id));
					inner.put("specific", assetsBO.getAssetsByIDSpecific(id));
					model.put("data", inner);
				}
			}
			else if (action.equals(constant.actionCases)) 
			{
				if (subaction.equals(constant.subActionRows)) 
				{
					model.put("data", assetsBO.getAssets(startfrom, upto));
				} 
				else if (subaction.equals(constant.subActionInfo)) 
				{
					inner.put("main", casesBO.getCasesByIDMain(id));
					inner.put("standard", casesBO.getCasesByIDStandard(id));
					inner.put("specific", casesBO.getCasesByIDSpecific(id));
					model.put("data", inner);
				}
			}
			else if (action.equals(constant.actionSolutions)) 
			{
				if (subaction.equals(constant.subActionRows)) 
				{
					model.put("data", assetsBO.getAssets(startfrom, upto));
				} 
				else if (subaction.equals(constant.subActionInfo)) 
				{
					inner.put("main", solutionsBO.getSolutionsByIDMain(id));
					inner.put("standard", solutionsBO.getSolutionsByIDStandard(id));
					inner.put("specific", solutionsBO.getSolutionsByIDSpecific(id));
					model.put("data", inner);
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
	catch(Exception e)
	{
			e.printStackTrace();
	}
		
	ModelAndView modelAndView = new ModelAndView("jsonView", model);
	return modelAndView;
		
}
	public CasesBO getCasesBO() {
		return casesBO;
	}

	public void setCasesBO(CasesBO casesBO) {
		this.casesBO = casesBO;
	}
	public AssetsBO getAssetsBO() {
		return assetsBO;
	}

	public void setAssetsBO(AssetsBO assetsBO) {
		this.assetsBO = assetsBO;
	}
	public GenericBO getGenericBO() {
		return genericBO;
	}

	public void setGenericBO(GenericBO genericBO) {
		this.genericBO = genericBO;
	}

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
	public SolutionsBO getSolutionsBO() {
		return solutionsBO;
	}
	public void setSolutionsBO(SolutionsBO solutionsBO) {
		this.solutionsBO = solutionsBO;
	}
}
