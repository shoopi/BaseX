package main.java.nl.tue.ieis.is.basex.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.jooq.Record1;
import org.jooq.Result;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Center;
import org.zkoss.zul.Column;
import org.zkoss.zul.Columns;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Include;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.South;
import org.zkoss.zul.Space;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import main.java.nl.tue.ieis.is.basex.database.DBConfig;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Actor;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.BmActor;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.BusinessModel;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.CostBenefitActor;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.CpActivity;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.ActorRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.BmActorRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.BusinessModelRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.StrategyRecord;



public class BusinessModelController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1116480279298658373L;
	
	@Wire	private Grid businessModelLists, actorLists;
	@Wire	private Textbox valueInUseTextbox, mainVIUTextbox;
	@Wire	private Center valueInUseSection, center_radar;
	@Wire	private	South bmPropertiesSection;
	@Wire	private	Button addBMBtn, addActorBtn, saveBMBtn, removeBMBtn;
	@Wire	private	Hlayout	hlayoutAddActor;
	
	private BusinessModelRecord selectedBM = new BusinessModelRecord();

	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
				
		try {
			StrategyRecord strategy = (StrategyRecord)(Sessions.getCurrent()).getAttribute("strategy");
			if(strategy != null) {
				Result<BusinessModelRecord> businessModels = DBConfig.create.selectFrom(BusinessModel.BUSINESS_MODEL)
					      .where((BusinessModel.BUSINESS_MODEL.STRATEGY_ID.equal(strategy.getStrategyId())))
					      .fetch();
				selectedBM = (BusinessModelRecord) (Sessions.getCurrent()).getAttribute("businessModel");
				if(businessModels != null & businessModels.size() > 0) {
					for(BusinessModelRecord bm: businessModels) {
						Row row = constructBusinessModelRow(bm);
						businessModelLists.getRows().appendChild(row);

						if(selectedBM != null && selectedBM.getBmId() != null && selectedBM.getBmId().contentEquals(bm.getBmId())) {
							row.setStyle("background-color: #FFFF99; font-weight: bold;");
							valueInUseTextbox.setValue(bm.getValueInUse());
						}
					}
					
				}
				Include includeCanvas = new Include();
				includeCanvas.setId("includeCanvas");
				if(selectedBM != null && selectedBM.getBmId() != null) {
					includeCanvas.setSrc("/gui/bm_radar.zul");
					
					List<BmActorRecord> bmActors = DBConfig.create.selectFrom(BmActor.BM_ACTOR).where(BmActor.BM_ACTOR.BM_ID.equal(selectedBM.getBmId())).fetch();
					for(BmActorRecord bmActor : bmActors) {
						Row row = constructBmActorList (bmActor);
						actorLists.getRows().appendChild(row);

						BmActorRecord selectedBmActor = (BmActorRecord) (Sessions.getCurrent()).getAttribute("bmActor");
						if(selectedBmActor != null && selectedBmActor.getBmActorId().contentEquals(bmActor.getBmActorId())) {
							row.setStyle("background-color: #FFFF99; font-weight: bold;");
						}
					}
					bmPropertiesSection.setVisible(true);
					
				} else if (selectedBM != null && selectedBM.getBmId() == null) {
					includeCanvas.setSrc("/gui/bm_creation.zul");
					bmPropertiesSection.setVisible(false);
				} else {
					includeCanvas.setSrc("/gui/default.zul");
					bmPropertiesSection.setVisible(false);
				}
				includeCanvas.setParent(center_radar);
			}
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private Row constructBmActorList(BmActorRecord bmActor) {
		final Row row = new Row();
		try {
			ActorRecord actor = DBConfig.create.selectFrom(Actor.ACTOR).where(Actor.ACTOR.ACTOR_ID.equal(bmActor.getActorId())).fetchOne();
			Label lab = new Label(actor.getActorName());		
			row.appendChild(lab);
			row.setSclass("sidebar-fn");
			
			EventListener<Event> actionListener = new SerializableEventListener<Event>() {
				private static final long serialVersionUID = 1L;

				public void onEvent(Event event) throws Exception {
					(Sessions.getCurrent()).setAttribute("bmActor" , bmActor);
					(Sessions.getCurrent()).setAttribute("isBmActorPropOpen" , true);

					Executions.getCurrent().sendRedirect("");
				}
			};
		row.addEventListener(Events.ON_CLICK, actionListener);
		
		} catch(Exception e) { 
			e.printStackTrace();
		}
	return row;
	}
	
	private Row constructBusinessModelRow(BusinessModelRecord businessModel) {
		final Row row = new Row();
		try {
			Label lab = new Label(businessModel.getValueInUse());		
			row.appendChild(lab);
			row.setSclass("sidebar-fn");
			
			EventListener<Event> actionListener = new SerializableEventListener<Event>() {
				private static final long serialVersionUID = 1L;

				public void onEvent(Event event) throws Exception {
					(Sessions.getCurrent()).setAttribute("businessModel" , businessModel);
					Executions.getCurrent().sendRedirect("");
				}
			};
		row.addEventListener(Events.ON_CLICK, actionListener);
		
		} catch(Exception e) { 
			e.printStackTrace();
		}
	return row;
	}
	
	@Listen("onClick=#addActorBtn")
	public void addActorToBM() {
		
		Window window = new Window();
		window.setTitle("Add a new actor");
		window.setParent(hlayoutAddActor);
		window.setWidth("550px");
		window.setClosable(true);
		window.doHighlighted();
		
		List<ActorRecord> actors = DBConfig.create.selectFrom(Actor.ACTOR).fetch();
		List<ActorRecord> currentActors = new ArrayList<ActorRecord>();
		
		boolean isAlreadyFocalSelected = false;
		boolean isAlreadyCustomerSelected = false;
		
		if(selectedBM != null && selectedBM.getBmId() != null ) {
			for(BmActorRecord bmActor : DBConfig.create.selectFrom(BmActor.BM_ACTOR).where(BmActor.BM_ACTOR.BM_ID.equal(selectedBM.getBmId())).fetch()) {
				currentActors.add(DBConfig.create.selectFrom(Actor.ACTOR).where(Actor.ACTOR.ACTOR_ID.equal(bmActor.getActorId())).fetchOne());
				if(bmActor.getIsFocal() == 1) isAlreadyFocalSelected = true;
				if(bmActor.getIsCustomer() == 1) isAlreadyCustomerSelected = true;
			}
		}
		actors.removeAll(currentActors);
		
		Grid grid = new Grid();
		grid.setParent(window);
		
		Columns clmns = new Columns();
		clmns.setParent(grid);
		
		Column clm1 = new Column();
		clm1.setWidth("20%");
		Column clm2 = new Column();		
		clm1.setParent(clmns);
		clm2.setParent(clmns);

		Rows rows = new Rows();
		rows.setParent(grid);
		
		Row r1 = new Row();
		r1.setParent(rows);
		
		Label lbl = new Label("Acotr*");
		lbl.setParent(r1);
		
		Combobox actorCombobox = new Combobox();
		actorCombobox.setAutocomplete(true);
		actorCombobox.setButtonVisible(true); 
		actorCombobox.setConstraint("no empty");
		actorCombobox.setWidth("99%");
		actorCombobox.setParent(r1);
		
		for(ActorRecord act : actors) {
			Comboitem item = new Comboitem(act.getActorName());
			item.setId("comboitem_" + act.getActorId() + "_" + UUID.randomUUID().toString());
			item.setValue(act);
			item.setParent(actorCombobox);
		}
		
		Row r2 = new Row();
		r2.setParent(rows);
		
		Label lbl2 = new Label("Description");
		lbl2.setParent(r2);
		
		Textbox actorDescTextbox = new Textbox();
		actorDescTextbox.setMultiline(true);
		actorDescTextbox.setHeight("50px");
		actorDescTextbox.setWidth("99%");
		actorDescTextbox.setParent(r2);
		
		Row r3 = new Row();
		r3.setParent(rows);
		
		Label lbl3 = new Label("Value Proposition");
		lbl3.setParent(r3);
		
		Textbox valuePropositionTextbox = new Textbox();
		valuePropositionTextbox.setMultiline(true);
		valuePropositionTextbox.setHeight("99%");
		valuePropositionTextbox.setWidth("99%");
		valuePropositionTextbox.setConstraint("no empty");
		valuePropositionTextbox.setParent(r3);
		
		Row r4 = new Row();
		r4.setParent(rows);
		
		Label lbl4 = new Label("Role");
		lbl4.setParent(r4);
		
		Hlayout hly = new Hlayout();
		hly.setParent(r4);
		
		Radiogroup focalGroupRadioGroup = new Radiogroup();
		String radioId = "focalGroupRadioGroup_" + UUID.randomUUID().toString(); 
		focalGroupRadioGroup.setId(radioId);
		focalGroupRadioGroup.setParent(hly);
		
		
		Radio isFocalRadio = new Radio();
		isFocalRadio.setId("RD_FOCL_ACT_" + UUID.randomUUID().toString());
		isFocalRadio.setRadiogroup(radioId);
		isFocalRadio.setLabel("Focal Organization");
		isFocalRadio.setParent(hly);
		if(isAlreadyFocalSelected) 
			isFocalRadio.setDisabled(true);
		
		Space sp = new Space();
		sp.setWidth("50px");
		sp.setBar(true);
		sp.setParent(hly);
		
		Radio isCustomerRadio = new Radio();
		isCustomerRadio.setId("RD_CUST_ACT_" + UUID.randomUUID().toString());
		isCustomerRadio.setRadiogroup(radioId);
		isCustomerRadio.setParent(hly);
		isCustomerRadio.setLabel("Customer");
		if(isAlreadyCustomerSelected)
			isCustomerRadio.setDisabled(true);
		
		
		final String newBmActorId = "BMA_" + UUID.randomUUID().toString();

		
		Button saveBtn = new Button();
		saveBtn.setLabel("Add Actor to Business Model");
		saveBtn.setSclass("myButton");
		saveBtn.setImage("/imgs/checkmark.png");
		EventListener<Event> onSaveBtnClick = new SerializableEventListener<Event>() {
			private static final long serialVersionUID = 7147170693994247026L;
			public void onEvent(Event event) throws Exception {
				if(actorCombobox.getSelectedItem() != null || actorCombobox.getValue() != null) { 							
					if(actorCombobox.getSelectedItem() != null) {
						ActorRecord actor = (ActorRecord)actorCombobox.getSelectedItem().getValue();
						if(actor.getActorDesc() != null) {
							actorDescTextbox.setValue(actor.getActorDesc());
						}
						DBConfig.create.insertInto(
								BmActor.BM_ACTOR, 
								BmActor.BM_ACTOR.BM_ACTOR_ID, 
								BmActor.BM_ACTOR.ACTOR_ID, 
								BmActor.BM_ACTOR.BM_ID, 
								BmActor.BM_ACTOR.IS_FOCAL, 
								BmActor.BM_ACTOR.IS_CUSTOMER, 
								BmActor.BM_ACTOR.VALUE_PROPOSITION)
						.values(newBmActorId, actor.getActorId(), selectedBM.getBmId(), (byte)(isFocalRadio.isChecked()?1:0), (byte)(isCustomerRadio.isChecked()?1:0), valuePropositionTextbox.getValue().trim())
						.execute();
						Executions.sendRedirect("");
					} else {
						String message = "Do you want to add " + actorCombobox.getValue().trim() + " as a new Actor?";
						EventListener<ClickEvent> clickEventListener = new EventListener<ClickEvent>() {
							public void onEvent(ClickEvent event) {
								if (Messagebox.ON_YES.equals(event.getName())) {	
									String desc = ""; 
									if (actorDescTextbox.getValue() != null) 
										desc = actorDescTextbox.getValue();
									if(valuePropositionTextbox.getValue() != null && valuePropositionTextbox.getValue().length() > 0) {
										String actorId = "ACR_"+ UUID.randomUUID().toString();
										DBConfig.create.insertInto(Actor.ACTOR,Actor.ACTOR.ACTOR_ID, Actor.ACTOR.ACTOR_NAME, Actor.ACTOR.ACTOR_DESC).
										values(actorId, actorCombobox.getValue().trim(), desc.trim()).execute();
										ActorRecord actor = DBConfig.create.selectFrom(Actor.ACTOR).where(Actor.ACTOR.ACTOR_ID.equal(actorId)).fetchOne();
										if(actor != null) {
											DBConfig.create.insertInto(
													BmActor.BM_ACTOR, 
													BmActor.BM_ACTOR.BM_ACTOR_ID, 
													BmActor.BM_ACTOR.ACTOR_ID, 
													BmActor.BM_ACTOR.BM_ID, 
													BmActor.BM_ACTOR.IS_FOCAL, 
													BmActor.BM_ACTOR.IS_CUSTOMER, 
													BmActor.BM_ACTOR.VALUE_PROPOSITION)
											.values(newBmActorId, actor.getActorId(), selectedBM.getBmId(), (byte)(isFocalRadio.isChecked()?1:0), (byte)(isCustomerRadio.isChecked()?1:0), valuePropositionTextbox.getValue().trim())
											.execute();
										}
										Executions.sendRedirect("");
									} else {
										Messagebox.show("Please insert a value proposition for the actor!", "Error", Messagebox.OK, Messagebox.ERROR);
									}
								}
							}
						};
					   Messagebox.Button[] btns = { Messagebox.Button.YES,Messagebox.Button.NO}; 
					   Messagebox.show(message, "Add New Actor: " + actorCombobox.getValue().trim(), btns, Messagebox.EXCLAMATION, clickEventListener);
					}
				} else {
					Messagebox.show("Please select one of the existing actors or create a new one!", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}
		};
		saveBtn.addEventListener(Events.ON_CLICK, onSaveBtnClick);
		saveBtn.setParent(window);
	}
	@Listen("onClick=#saveBMBtn")
	public void changeBusinessModel() {
	   if(valueInUseTextbox.getValue() != null && valueInUseTextbox.getValue().length() > 1) {
			DBConfig.create.update(BusinessModel.BUSINESS_MODEL).set(BusinessModel.BUSINESS_MODEL.VALUE_IN_USE, valueInUseTextbox.getValue().trim()).
			where(BusinessModel.BUSINESS_MODEL.BM_ID.equal(selectedBM.getBmId())).execute();
			BusinessModelRecord bm = DBConfig.create.selectFrom(BusinessModel.BUSINESS_MODEL).where(BusinessModel.BUSINESS_MODEL.BM_ID.equal(selectedBM.getBmId())).fetchOne();
			(Sessions.getCurrent()).setAttribute("businessModel", bm);
			Executions.sendRedirect("");
		} else {
			Messagebox.show("Please insert a correct value-in-use for the business model!", "Error", Messagebox.OK, Messagebox.ERROR);
		}
	}
	
	@Listen("onClick=#removeBMBtn")
	public void RemoveBusinessModel() {
		String message = "Do you want to remove the business model with " + selectedBM.getValueInUse() + " value-in-use?";
		EventListener<ClickEvent> clickEventListener = new EventListener<ClickEvent>() {
			public void onEvent(ClickEvent event) {
				if (Messagebox.ON_YES.equals(event.getName())) {	
					List<String> bmActorIDs = DBConfig.create.select().from(BmActor.BM_ACTOR).
							where(BmActor.BM_ACTOR.BM_ID.equal(selectedBM.getBmId())).fetch().getValues(BmActor.BM_ACTOR.BM_ACTOR_ID);
					for(String bmActorId : bmActorIDs) {
						DBConfig.create.deleteFrom(CpActivity.CP_ACTIVITY).where(CpActivity.CP_ACTIVITY.BM_ACTOR_ID.equal(bmActorId)).execute();
						DBConfig.create.deleteFrom(CostBenefitActor.COST_BENEFIT_ACTOR).where(CostBenefitActor.COST_BENEFIT_ACTOR.BM_ACTOR_ID.equal(bmActorId)).execute();
						DBConfig.create.deleteFrom(BmActor.BM_ACTOR).where(BmActor.BM_ACTOR.BM_ACTOR_ID.equal(bmActorId)).execute();
					}
					DBConfig.create.deleteFrom(BusinessModel.BUSINESS_MODEL).where(BusinessModel.BUSINESS_MODEL.BM_ID.equal(selectedBM.getBmId())).execute();	
					(Sessions.getCurrent()).setAttribute("businessModel", new BusinessModelRecord());
					Executions.sendRedirect("");
				}
			}
		};
	   Messagebox.Button[] btns = { Messagebox.Button.YES,Messagebox.Button.NO}; 
	   Messagebox.show(message, "Remove Business Model: " + selectedBM.getValueInUse(), btns, Messagebox.EXCLAMATION, clickEventListener);
	   
	}
	
	@Listen("onClick = #addBMBtn")
	public void createBlankBusinessModel() {
		(Sessions.getCurrent()).setAttribute("businessModel" , new BusinessModelRecord());
		Executions.sendRedirect("");
	}
}