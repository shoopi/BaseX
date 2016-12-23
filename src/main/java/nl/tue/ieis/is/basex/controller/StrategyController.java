package main.java.nl.tue.ieis.is.basex.controller;


import java.util.List;
import java.util.UUID;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import main.java.nl.tue.ieis.is.basex.database.DBConfig;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.BusinessModel;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Company;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Strategy;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.User;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.UserHasStrategy;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.StrategyRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.UserRecord;



public class StrategyController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1116480279298658373L;
	
	@Wire	private Window 	strategyWin;
	@Wire	private	Center 	centerSection;
	@Wire	private	Textbox	customerTextbox, experienceTextbox, interactionsTextbox;
	@Wire	private	Textbox	coreServicesTextbox, corePartnersTextbox, focalOrganizationTextbox, enrichingServicesTextbox, enrichingPartnersTextbox;
	@Wire	private	Textbox	coreRelationshipsTextbox, enrichingRelationshipsTextbox;
	@Wire	private	Textbox strategyNameTextbox, strategyDescTextbox;
	@Wire	private Button	removeStrategyBtn, saveStrategyBtn;

	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		if((Sessions.getCurrent()).getAttribute("strategy") == null) {
			centerSection.setTitle("Add a New Strategy");
		} else {
			StrategyRecord strategy = (StrategyRecord)(Sessions.getCurrent()).getAttribute("strategy");
			
			centerSection.setTitle("Strategy: " + strategy.getStrategyName());
			
			if(strategy.getStrategyName() != null)
				strategyNameTextbox.setValue(strategy.getStrategyName());
			
			if(strategy.getStrategyDesc() != null)
				strategyDescTextbox.setValue(strategy.getStrategyDesc());
			
			if(strategy.getViuCustomer() != null)
				customerTextbox.setValue(strategy.getViuCustomer());
			
			if(strategy.getViuExperience() != null)
				experienceTextbox.setValue(strategy.getViuExperience());
			
			if(strategy.getViuInteractions() != null)
				interactionsTextbox.setValue(strategy.getViuInteractions());
			
			if(strategy.getSesCorePartners() != null)
				coreServicesTextbox.setValue(strategy.getSesCorePartners());
			
			if(strategy.getSesCorePartners() != null)
				corePartnersTextbox.setValue(strategy.getSesCorePartners());
			
			if(strategy.getSesFocalOrganization() != null)
				focalOrganizationTextbox.setValue(strategy.getSesFocalOrganization());
			
			if(strategy.getSesEnrichingServices() != null)
				enrichingServicesTextbox.setValue(strategy.getSesEnrichingServices());
			
			if(strategy.getSesEnrichingPartners() != null)
				enrichingPartnersTextbox.setValue(strategy.getSesEnrichingPartners());
			
			if(strategy.getCmCoreRelationship() != null)
				coreRelationshipsTextbox.setValue(strategy.getCmCoreRelationship());
			
			if(strategy.getCmEnrichingRelationships() != null)
				enrichingRelationshipsTextbox.setValue(strategy.getCmEnrichingRelationships());
			
			removeStrategyBtn.setDisabled(false);
		}
	}
	
	@Listen("onClick = #saveStrategyBtn")
	public void addStrategy() {
		 try {
			StrategyRecord sessionStrategy = (StrategyRecord)(Sessions.getCurrent()).getAttribute("strategy");
			String id = "";
			if(sessionStrategy == null) {
				StrategyRecord prev = DBConfig.create.selectFrom(Strategy.STRATEGY).where(Strategy.STRATEGY.STRATEGY_NAME.equal(strategyNameTextbox.getValue().trim())).fetchAny();
				if(prev == null) { 
					id = "STR_" + UUID.randomUUID().toString();
					addOrUpdateStrategy(true, id);
				} else {
					findAndUpdateExistingStrategyBasedOnName();
				}
			} else {
				id = sessionStrategy.getStrategyId(); 
				if(sessionStrategy.getStrategyName().contentEquals(strategyNameTextbox.getValue().trim())) {
					addOrUpdateStrategy(false, id);
				} else {
					findAndUpdateExistingStrategyBasedOnName();
				}
			}
        } catch (Exception e) {
			e.printStackTrace();
			Messagebox.show("An error has occurred. Unfortunately We cannot process your strategy creation request!.", "Error", Messagebox.OK, Messagebox.ERROR);
        }
	}
	
	
	private void findAndUpdateExistingStrategyBasedOnName() {
		StrategyRecord prev = DBConfig.create.selectFrom(Strategy.STRATEGY).where(Strategy.STRATEGY.STRATEGY_NAME.equal(strategyNameTextbox.getValue().trim())).fetchAny();
		if(prev != null) { 
			String message = "A strategy with name: " + prev.getStrategyName() + " has already been created. \nDo you want to update it?";
			EventListener<ClickEvent> clickEventListener = new EventListener<ClickEvent>() {
				public void onEvent(ClickEvent event) {
					if (Messagebox.ON_YES.equals(event.getName())) {	
						addOrUpdateStrategy(false, prev.getStrategyId());
					}
				}
		   };
		   Messagebox.Button[] btns = { Messagebox.Button.YES,Messagebox.Button.NO}; 
		   Messagebox.show(message, "Update Strategy " + prev.getStrategyName(), btns, Messagebox.EXCLAMATION, clickEventListener);
		} else {
			StrategyRecord sessionStrategy = (StrategyRecord)(Sessions.getCurrent()).getAttribute("strategy");
			if(sessionStrategy != null) {
				String message = "You are re-naming the " + sessionStrategy.getStrategyName() + " strategy to " + strategyNameTextbox.getValue().trim() + 
						". \nDo you want to continue?";
				EventListener<ClickEvent> clickEventListener = new EventListener<ClickEvent>() {
					public void onEvent(ClickEvent event) {
						if (Messagebox.ON_YES.equals(event.getName())) {	
							addOrUpdateStrategy(false, sessionStrategy.getStrategyId());
						}
					}
			   };
			   Messagebox.Button[] btns = { Messagebox.Button.YES,Messagebox.Button.NO}; 
			   Messagebox.show(message, "Update Strategy " + sessionStrategy.getStrategyName(), btns, Messagebox.EXCLAMATION, clickEventListener);
			}
		}
	}
	
	private void addOrUpdateStrategy(boolean newRecord, String id) {
		String desc = "", customer = "", experience = "", interactions = "";
		String coreService = "", corePartner = "", focalOrg = "", enrichService = "", enrichPartner = "";
		String coreRel = "", enrichRel = "";
		
		
		if(strategyDescTextbox.getValue() != null)
			desc = strategyDescTextbox.getValue();
		
		if(customerTextbox.getValue() != null)
			customer = customerTextbox.getValue();
		
		if(experienceTextbox.getValue() != null)
			experience = experienceTextbox.getValue();
		
		if(interactionsTextbox.getValue() != null)
			interactions = interactionsTextbox.getValue();
		
		if(coreServicesTextbox.getValue() != null)
			coreService = coreServicesTextbox.getValue();
		
		if(corePartnersTextbox.getValue() != null)
			corePartner = corePartnersTextbox.getValue();
		
		if(focalOrganizationTextbox.getValue() != null)
			focalOrg = focalOrganizationTextbox.getValue();
		
		if(enrichingServicesTextbox.getValue() != null)
			enrichService = enrichingServicesTextbox.getValue();
		
		if(enrichingPartnersTextbox.getValue() != null)
			enrichPartner = enrichingPartnersTextbox.getValue();
		
		if(coreRelationshipsTextbox.getValue() != null)
			coreRel = coreRelationshipsTextbox.getValue();
		
		if(enrichingRelationshipsTextbox.getValue() != null)
			enrichRel = enrichingRelationshipsTextbox.getValue();
		try {
			if(strategyNameTextbox.getValue() != null && strategyNameTextbox.getValue().trim().length() > 0) {
				DBConfig.create.insertInto(Strategy.STRATEGY,
						Strategy.STRATEGY.STRATEGY_ID, 
						Strategy.STRATEGY.STRATEGY_NAME, 
						Strategy.STRATEGY.STRATEGY_DESC,
						Strategy.STRATEGY.VIU_CUSTOMER,
						Strategy.STRATEGY.VIU_EXPERIENCE,
						Strategy.STRATEGY.VIU_INTERACTIONS,
						Strategy.STRATEGY.SES_CORE_SERVICES,
						Strategy.STRATEGY.SES_CORE_PARTNERS,
						Strategy.STRATEGY.SES_FOCAL_ORGANIZATION,
						Strategy.STRATEGY.SES_ENRICHING_SERVICES,
						Strategy.STRATEGY.SES_ENRICHING_PARTNERS,
						Strategy.STRATEGY.CM_CORE_RELATIONSHIP,
						Strategy.STRATEGY.CM_ENRICHING_RELATIONSHIPS)
			      .values(id, strategyNameTextbox.getValue().trim(), 
			    		  desc, customer, experience,interactions, 
			    		  coreService, corePartner, focalOrg, enrichService, enrichPartner,
			    		  coreRel, enrichRel)
			      .onDuplicateKeyUpdate()
			      .set(Strategy.STRATEGY.STRATEGY_NAME, strategyNameTextbox.getValue().trim())
			      .set(Strategy.STRATEGY.STRATEGY_DESC, desc)
			      .set(Strategy.STRATEGY.VIU_CUSTOMER, customer)
				  .set(Strategy.STRATEGY.VIU_EXPERIENCE, experience)
				  .set(Strategy.STRATEGY.VIU_INTERACTIONS, interactions)
				  .set(Strategy.STRATEGY.SES_CORE_SERVICES, coreService)
				  .set(Strategy.STRATEGY.SES_CORE_PARTNERS, corePartner)
				  .set(Strategy.STRATEGY.SES_FOCAL_ORGANIZATION, focalOrg)
				  .set(Strategy.STRATEGY.SES_ENRICHING_SERVICES, enrichService)
				  .set(Strategy.STRATEGY.SES_ENRICHING_PARTNERS, enrichPartner)
				  .set(Strategy.STRATEGY.CM_CORE_RELATIONSHIP, coreRel)
				  .set(Strategy.STRATEGY.CM_ENRICHING_RELATIONSHIPS, enrichRel)
			      .execute();
				
				if(newRecord) {
					UserRecord currentUser = (UserRecord)(Sessions.getCurrent()).getAttribute("user");
					List<UserRecord> sharedCompnayUsers = DBConfig.create.selectFrom(User.USER).where(User.USER.COMPANY_NAME.equal(currentUser.getCompanyName())).fetch();
					
					for(UserRecord u : sharedCompnayUsers) {
						DBConfig.create.insertInto(UserHasStrategy.USER_HAS_STRATEGY,
								UserHasStrategy.USER_HAS_STRATEGY.USER_USER_ID, UserHasStrategy.USER_HAS_STRATEGY.STRATEGY_STRATEGY_ID)
							.values(u.getUserId(), id).execute();
					}
					
				}
				StrategyRecord strategy = DBConfig.create.selectFrom(Strategy.STRATEGY).where(Strategy.STRATEGY.STRATEGY_ID.equal(id)).fetchAny();
				(Sessions.getCurrent()).setAttribute("strategy", strategy);
				Executions.sendRedirect("");
			} else {
				Messagebox.show("The strategy name is not valid", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show("An error has occurred. Unfortunately We cannot process your strategy creation request!.", "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	@Listen("onClick = #removeStrategyBtn")
	public void removeStrategy() {
		try {
			StrategyRecord strategy = (StrategyRecord)(Sessions.getCurrent()).getAttribute("strategy");
			if(strategy != null) {
				
				String message = "Are you sure you want to remove strategy: " + strategy.getStrategyName();
				EventListener<ClickEvent> clickEventListener = new EventListener<ClickEvent>() {
					public void onEvent(ClickEvent event) {
						if (Messagebox.ON_YES.equals(event.getName())) {
															
							DBConfig.create.deleteFrom(UserHasStrategy.USER_HAS_STRATEGY).where(UserHasStrategy.USER_HAS_STRATEGY.STRATEGY_STRATEGY_ID.equal(strategy.getStrategyId())).execute();
							DBConfig.create.deleteFrom(BusinessModel.BUSINESS_MODEL).where(BusinessModel.BUSINESS_MODEL.STRATEGY_ID.equal(strategy.getStrategyId())).execute();
							DBConfig.create.deleteFrom(Strategy.STRATEGY).where(Strategy.STRATEGY.STRATEGY_ID.equal(strategy.getStrategyId())).execute();

							(Sessions.getCurrent()).setAttribute("strategy", null);
							Executions.sendRedirect("");
						}
			      }
			   };
			   Messagebox.Button[] btns = { Messagebox.Button.YES,Messagebox.Button.NO}; 
			   Messagebox.show(message, "Removing Strategy " + strategy.getStrategyName(), btns, Messagebox.EXCLAMATION, clickEventListener);
			} else {
				Messagebox.show("No strategy has been selected to be removed.", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		} catch (Exception e) {
			e.printStackTrace();
			Messagebox.show("An error has occurred. Unfortunately We cannot process your strategy deletion request!.", "Error", Messagebox.OK, Messagebox.ERROR);
		}
		
	}
}
