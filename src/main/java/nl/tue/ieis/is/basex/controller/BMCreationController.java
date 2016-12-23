package main.java.nl.tue.ieis.is.basex.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Row;
import org.zkoss.zul.South;
import org.zkoss.zul.Spinner;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import main.java.nl.tue.ieis.is.basex.database.DBConfig;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Actor;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.BmActor;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.BusinessModel;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.ActorRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.BmActorRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.BusinessModelRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.StrategyRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.UserRecord;



public class BMCreationController extends SelectorComposer<Component> {
	
	@Wire	private Textbox mainVIUTextbox, BusinessModelDescTextbox;
	@Wire	private Spinner actorsSpinner;
	@Wire	private	Grid actorGrid;
	@Wire	private	South southActorSection;
	@Wire	private	Window mainWindow;
	@Wire	private Radiogroup focalGroupRadioGroup, customerGroupRadioGroup;
	@Wire	private Button saveBmBtn;
	
	private List<BmActorRecord> selectedBmActors = new ArrayList<BmActorRecord>();
	private UserRecord user = (UserRecord)((Sessions.getCurrent()).getAttribute("user"));
	private StrategyRecord strategy = (StrategyRecord)((Sessions.getCurrent()).getAttribute("strategy"));
	
	
	private static final long serialVersionUID = 7410369403946865595L;

	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);	
		
		try {
			actorsSpinner.setValue(4);
			constructActorSection(4);
			focalGroupRadioGroup.setSelectedIndex(0);
			customerGroupRadioGroup.setSelectedIndex(1);
			onFocalSelected();
			onCustomerSelected();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void constructActorSection(int actorNumber) {
		try {
			actorGrid.getRows().getChildren().clear();	
			for(int i = 1; i < actorsSpinner.getValue() + 1; i++) {
				final int index = i -1 ;
				final ActorRecord selectedActor = new ActorRecord();
				
				Row row = new Row();
				Combobox actorCombobox = new Combobox();
				actorCombobox.setAutocomplete(true);
				actorCombobox.setButtonVisible(true); 
				actorCombobox.setConstraint("no empty");
				for(ActorRecord act : getAllActors()) {
					Comboitem item = new Comboitem(act.getActorName());
					item.setId("comboitem_" + i + "_" + act.getActorId());
					item.setValue(act);
					item.setParent(actorCombobox);
				}
				
				Textbox actorDescTextbox = new Textbox();
				actorDescTextbox.setMultiline(true);
				actorDescTextbox.setHeight("99%");
				actorDescTextbox.setWidth("99%");
				
				Textbox valuePropositionTextbox = new Textbox();
				valuePropositionTextbox.setMultiline(true);
				valuePropositionTextbox.setHeight("99%");
				valuePropositionTextbox.setWidth("99%");
				valuePropositionTextbox.setConstraint("no empty");
				
				Radio isFocalRadio = new Radio();
				isFocalRadio.setId("RD_FOCL_ACT_" + i);
				isFocalRadio.setRadiogroup(focalGroupRadioGroup);

				
				Radio isCustomerRadio = new Radio();
				isCustomerRadio.setId("RD_CUST_ACT_" + i);
				isCustomerRadio.setRadiogroup(customerGroupRadioGroup);
				
				
				Button removeBtn = new Button();
				removeBtn.setHeight("1px");
				removeBtn.setWidth("1px");
				removeBtn.setImage("/imgs/del.png");
				EventListener<Event> onRemoveBtnClick = new SerializableEventListener<Event>() {
					private static final long serialVersionUID = 1L;
					public void onEvent(Event event) throws Exception {
						int cur = actorsSpinner.getValue();
						cur = cur - 1;
						if(cur > 1) {
							actorsSpinner.setValue(cur);
							constructActorSection(cur);
							
							if(selectedBmActors.size() > index && selectedBmActors.remove(index) != null) {
								selectedBmActors.remove(index);
							} else {
								System.out.println("HOW COME?");
							}
						} else {
							Messagebox.show("The minimum number of actors is 2", "Error", Messagebox.OK, Messagebox.ERROR);
						}
					}
				};
				removeBtn.addEventListener(Events.ON_CLICK, onRemoveBtnClick);
				
				
				Button saveBtn = new Button();
				saveBtn.setHeight("1px");
				saveBtn.setWidth("1px");
				saveBtn.setImage("/imgs/checkmark.png");
				EventListener<Event> onSaveBtnClick = new SerializableEventListener<Event>() {
					private static final long serialVersionUID = 7147170693994247026L;
					public void onEvent(Event event) throws Exception {
						if(selectedActor.getActorId() != null) {
							if(valuePropositionTextbox.getValue() != null) {
								String id = "BMA_" + UUID.randomUUID().toString();
								
								boolean isFocal = false; if(focalGroupRadioGroup.getSelectedIndex() == index)  isFocal = true;
								byte isFocalByte = (byte)(isFocal?1:0);
								
								boolean isCustomer = false; if(customerGroupRadioGroup.getSelectedIndex() == index) isCustomer = true;
								byte isCustomerlByte = (byte)(isCustomer?1:0);

								selectedBmActors.add(new BmActorRecord(id, selectedActor.getActorId(), null, isFocalByte, isCustomerlByte, valuePropositionTextbox.getValue().trim()));
								actorCombobox.setDisabled(true);
								valuePropositionTextbox.setDisabled(true);
								actorDescTextbox.setDisabled(true);
								valuePropositionTextbox.setDisabled(true);
								isFocalRadio.setDisabled(true);
								isCustomerRadio.setDisabled(true);
								
								saveBtn.setDisabled(true);
								
							} else {
								Messagebox.show("Please enter a value proposition for " + selectedActor.getActorName() + ".",  "Error", Messagebox.OK, Messagebox.ERROR);
							}
						} else {
							Messagebox.show("Please select an actor.", "Error", Messagebox.OK, Messagebox.ERROR);
						}
					}
				};
				saveBtn.addEventListener(Events.ON_CLICK, onSaveBtnClick);
				
				row.appendChild(actorCombobox);
				row.appendChild(actorDescTextbox);
				row.appendChild(valuePropositionTextbox);
				row.appendChild(isFocalRadio);
				row.appendChild(isCustomerRadio);
				row.appendChild(saveBtn);
				row.appendChild(removeBtn);

				row.setSclass("sidebar-fn");
				actorGrid.getRows().appendChild(row);
				
				EventListener<Event> onActorSelect = new SerializableEventListener<Event>() {
					private static final long serialVersionUID = 1982007331724277134L;
					public void onEvent(Event event) throws Exception {
						if(actorCombobox.getSelectedItem() != null || actorCombobox.getValue() != null) { 							
							if(actorCombobox.getSelectedItem() != null) {
								ActorRecord actor = (ActorRecord)actorCombobox.getSelectedItem().getValue();
								if(actor.getActorDesc() != null) {
									actorDescTextbox.setValue(actor.getActorDesc());
									selectedActor.setActorDesc(actor.getActorDesc());
								}
								selectedActor.setActorId(actor.getActorId());
								selectedActor.setActorName(actor.getActorName());
							} else {
								String message = "Do you want to add " + actorCombobox.getValue().trim() + " as a new Actor?";
								EventListener<ClickEvent> clickEventListener = new EventListener<ClickEvent>() {
									public void onEvent(ClickEvent event) {
										if (Messagebox.ON_YES.equals(event.getName())) {	
											String desc = ""; 
											if (actorDescTextbox.getValue() != null) 
												desc = actorDescTextbox.getValue();
											String actorId = "ACR_"+ UUID.randomUUID().toString();
											DBConfig.create.insertInto(Actor.ACTOR,Actor.ACTOR.ACTOR_ID, Actor.ACTOR.ACTOR_NAME, Actor.ACTOR.ACTOR_DESC).
											values(actorId, actorCombobox.getValue().trim(), desc.trim()).execute();
											ActorRecord actor = DBConfig.create.selectFrom(Actor.ACTOR).where(Actor.ACTOR.ACTOR_ID.equal(actorId)).fetchOne();
											if(actor != null) {
												selectedActor.setActorId(actor.getActorId());
												selectedActor.setActorName(actor.getActorName());
												if(actor.getActorDesc() != null) 
													selectedActor.setActorDesc(actor.getActorDesc());
											}
											else Messagebox.show("Oops ... Something went wrong! Please check your inputs!", "Error", Messagebox.OK, Messagebox.ERROR);
										}
									}
							   };
							   Messagebox.Button[] btns = { Messagebox.Button.YES,Messagebox.Button.NO}; 
							   Messagebox.show(message, "Add New Actor: " + actorCombobox.getValue().trim(), btns, Messagebox.EXCLAMATION, clickEventListener);
							}
							
						}			
					}
				};
			actorCombobox.addEventListener(Events.ON_CHANGE, onActorSelect);
			}			
		} catch(Exception e) { 
			e.printStackTrace();
		}
	}
	
	@Listen("onChange = #actorsSpinner")
	public void createActorField() {
		int focal = focalGroupRadioGroup.getSelectedIndex();
		int cust = customerGroupRadioGroup.getSelectedIndex();
		
		constructActorSection(actorsSpinner.getValue());
		
		if(focal != 1 && focal < actorsSpinner.getValue()) 	focalGroupRadioGroup.setSelectedIndex(focal);
		else focalGroupRadioGroup.setSelectedIndex(0);
		
		if(cust != 0 && cust < actorsSpinner.getValue())	customerGroupRadioGroup.setSelectedIndex(cust);
		else customerGroupRadioGroup.setSelectedIndex(1);
		
		onFocalSelected();
		onCustomerSelected();
	}
	
	@Listen("onClick = #addBMBtn")
	public void saveBusinessModel() {
		(Sessions.getCurrent()).setAttribute("businessModel" , new BusinessModelRecord());
		Executions.sendRedirect("");
	}
	
	@Listen("onCheck = #focalGroupRadioGroup")
	public void onFocalSelected() {		
		int selectedIndex = focalGroupRadioGroup.getSelectedIndex();
		for (int index = 0; index < focalGroupRadioGroup.getItems().size(); index++) {
			if(index == selectedIndex) customerGroupRadioGroup.getItemAtIndex(index).setDisabled(true);
			else customerGroupRadioGroup.getItemAtIndex(index).setDisabled(false);
		}
	}
	
	@Listen("onCheck = #customerGroupRadioGroup")
	public void onCustomerSelected() {
		int selectedIndex = customerGroupRadioGroup.getSelectedIndex();
		for (int index = 0; index < focalGroupRadioGroup.getItems().size(); index++) {
			if(index == selectedIndex) focalGroupRadioGroup.getItemAtIndex(index).setDisabled(true);
			else focalGroupRadioGroup.getItemAtIndex(index).setDisabled(false);
		}
	}
	
	@Listen("onClick=#saveBmBtn")
	public void addBusinessModel() {
		if(selectedBmActors.size() < actorsSpinner.getValue()) {
			Messagebox.show("You have not selected enough actors ... Please add  " + (actorsSpinner.getValue()-selectedBmActors.size()) + " more actor(s)!", "Error", Messagebox.OK, Messagebox.ERROR);
		} else {
			selectedBmActors.subList(0, actorsSpinner.getValue()-1);
			if(mainVIUTextbox.getValue() != null && mainVIUTextbox.getValue().length() > 1) {
				String bmId = "BM_" + UUID.randomUUID().toString();
				String bmDesc = ""; 
				if(BusinessModelDescTextbox.getValue() != null) bmDesc = BusinessModelDescTextbox.getValue();
				Timestamp now = new Timestamp((new Date()).getTime());
				DBConfig.create.insertInto(BusinessModel.BUSINESS_MODEL, 
						BusinessModel.BUSINESS_MODEL.BM_ID,
						BusinessModel.BUSINESS_MODEL.VALUE_IN_USE,
						BusinessModel.BUSINESS_MODEL.DESC,
						BusinessModel.BUSINESS_MODEL.STRATEGY_ID,
						BusinessModel.BUSINESS_MODEL.VERSION,
						BusinessModel.BUSINESS_MODEL.IMAGE,
						BusinessModel.BUSINESS_MODEL.CREATED_DATE,
						BusinessModel.BUSINESS_MODEL.LAST_EDITED_TIME,
						BusinessModel.BUSINESS_MODEL.CREATED_BY,
						BusinessModel.BUSINESS_MODEL.LAST_EDITED_BY)
				.values(bmId, mainVIUTextbox.getValue().trim(), bmDesc, strategy.getStrategyId(), 0.1, null, now, now, user.getUserId(), user.getUserId())
				.execute();
				
				BusinessModelRecord bm = DBConfig.create.selectFrom(BusinessModel.BUSINESS_MODEL).where(BusinessModel.BUSINESS_MODEL.BM_ID.equal(bmId)).fetchOne();
				if(bm != null) {
					for(BmActorRecord bmActor : selectedBmActors) {
						bmActor.setBmId(bmId);
						DBConfig.create.insertInto(
								BmActor.BM_ACTOR, 
								BmActor.BM_ACTOR.BM_ACTOR_ID, 
								BmActor.BM_ACTOR.ACTOR_ID, 
								BmActor.BM_ACTOR.BM_ID, 
								BmActor.BM_ACTOR.IS_FOCAL, 
								BmActor.BM_ACTOR.IS_CUSTOMER, 
								BmActor.BM_ACTOR.VALUE_PROPOSITION)
						.values(bmActor.getBmActorId(), bmActor.getActorId(), bm.getBmId(), bmActor.getIsFocal(), bmActor.getIsCustomer(), bmActor.getValueProposition())
						.execute();
					}
					(Sessions.getCurrent()).setAttribute("businessModel", bm);
					Executions.sendRedirect("");
					
				} else {
					Messagebox.show("The system cannot process your business model creation. Please check your entered values and try again!!", "Error", Messagebox.OK, Messagebox.ERROR);
				}
				
			} else {
				Messagebox.show("Please enter a value-in-use for the business model!", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}	
	}
	
	private Result<ActorRecord> getAllActors() {
		Result<ActorRecord> actors =  DBConfig.create.selectFrom(Actor.ACTOR).fetch();
		return actors;
	}
}