package main.java.nl.tue.ieis.is.basex.controller;

import java.util.List;
import java.util.UUID;

import org.zkoss.canvas.Canvas;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.event.SerializableEventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Doublespinner;
import org.zkoss.zul.East;
import org.zkoss.zul.Groupbox;
import org.zkoss.zul.Hlayout;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Radio;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Space;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Vlayout;

import main.java.nl.tue.ieis.is.basex.database.DBConfig;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Actor;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.BmActor;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.CostBenefit;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.CostBenefitActor;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.CpActivity;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.LocationGui;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.ActorRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.BmActorRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.BusinessModelRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.CostBenefitActorRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.CostBenefitRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.CpActivityRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.LocationGuiRecord;
import main.java.nl.tue.ieis.is.basex.gui.DrawComponent;


public class BMRadarController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1116480279298658373L;
		
	@Wire	private Canvas 			cvs1;
	@Wire	private	East			actorPropView;
	@Wire	private	Button			saveBmAcotrPropBtn, removeBmActor, addActivityBtn, addCostBenefitBtn;
	@Wire	private	Doublespinner 	xActorSpinner, yActorSpinner, degActorSpinner, xPropSpinner, yPropSpinner, degPropSpinner;
	@Wire	private	Groupbox		bmActorGroupbox, costBenefitGroupbox, activityGroupbox;
	@Wire	private Checkbox 		isFocalCheckbox, isCustomerCheckbox;
	@Wire	private	Textbox			actorValuePropTextbox;
		
	private BusinessModelRecord bm = (BusinessModelRecord)((Sessions.getCurrent()).getAttribute("businessModel"));
	private BmActorRecord selectedBmActor = (BmActorRecord) (Sessions.getCurrent()).getAttribute("bmActor");

	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		DrawComponent drawer = new DrawComponent(cvs1);
		
		List<BmActorRecord> bmActors = DBConfig.create.selectFrom(BmActor.BM_ACTOR).where(BmActor.BM_ACTOR.BM_ID.equal(bm.getBmId())).fetch();
		drawer.drawCanvas(bmActors.size());
		//write value-in-use
		drawer.writeTextToCanvas(bm.getValueInUse(), -1, bmActors.size(), 0, 0, 0, 0, "#751A42", false);
		
		for(int i = 1; i <= bmActors.size(); i++) {
			BmActorRecord bmActor = bmActors.get(i-1);
			
			//write actor name
			ActorRecord actor = DBConfig.create.selectFrom(Actor.ACTOR).where(Actor.ACTOR.ACTOR_ID.equal(bmActor.getActorId())).fetchOne();
			double[] actorNameCoord = getCoordination(bmActor.getBmActorId()+ "_NAME", bmActor.getBmActorId());
			drawer.writeTextToCanvas(actor.getActorName(), 0, bmActors.size(), i, actorNameCoord[0], actorNameCoord[1], actorNameCoord[2], "#751A42", true);
			
			//write value proposition
			double[] propCoord = getCoordination(bmActor.getBmActorId()+ "_PROP", bmActor.getBmActorId());
			drawer.writeTextToCanvas(bmActor.getValueProposition(), 3, bmActors.size(), i, propCoord[0], propCoord[1], propCoord[2], "#751A42", true);
			
			
			//write activities
			List<CpActivityRecord> activities = DBConfig.create.selectFrom(CpActivity.CP_ACTIVITY).where(CpActivity.CP_ACTIVITY.BM_ACTOR_ID.equal(bmActor.getBmActorId())).fetch();
			for(CpActivityRecord activity : activities) {
				double[] coord= getCoordination(activity.getCpActivityId(), bmActor.getBmActorId());
				drawer.writeTextToCanvas(bmActor.getValueProposition(), 2, bmActors.size(), i, coord[0], coord[1], coord[2], "#751A42", true);
			}
			
			//write costs and benefits
			List<CostBenefitActorRecord> costBenfitsActors = DBConfig.create.selectFrom(CostBenefitActor.COST_BENEFIT_ACTOR).
					where(CostBenefitActor.COST_BENEFIT_ACTOR.BM_ACTOR_ID.equal(bmActor.getBmActorId())).fetch();
			for(CostBenefitActorRecord cba : costBenfitsActors) {
				double[] coord= getCoordination(cba.getCbActorId(), bmActor.getBmActorId());
				String color = "#e00808";
				if(cba.getCostOrBenefit().contains("B")) color = "#42f44e";
				String name = DBConfig.create.select(CostBenefit.COST_BENEFIT.NAME).from(CostBenefit.COST_BENEFIT).where(CostBenefit.COST_BENEFIT.CB_ID.equal(cba.getCbId())).fetchOne(CostBenefit.COST_BENEFIT.NAME);
				drawer.writeTextToCanvas(name, 1, bmActors.size(), i, coord[0], coord[1], coord[2], color, true);
			}
		}
		
		if(selectedBmActor == null) {
			bmActorGroupbox.setParent(null);
			actorPropView.setVisible(false);
		} else {
			actorPropView.setVisible(true);
			
			double[] actorNameCoord = getCoordination(selectedBmActor.getBmActorId()+ "_NAME", selectedBmActor.getBmActorId());
			xActorSpinner.setValue(actorNameCoord[0]);
			yActorSpinner.setValue(actorNameCoord[1]);
			degActorSpinner.setValue(actorNameCoord[2]);
			
			double[] propCoord = getCoordination(selectedBmActor.getBmActorId()+ "_PROP", selectedBmActor.getBmActorId());
			xPropSpinner.setValue(propCoord[0]);
			yPropSpinner.setValue(propCoord[1]);
			degPropSpinner.setValue(propCoord[2]);
			
			
			bmActorGroupbox.setTitle((getActor(selectedBmActor.getActorId())).getActorName());
			isFocalCheckbox.setChecked(selectedBmActor.getIsFocal() == 1);
			isCustomerCheckbox.setChecked(selectedBmActor.getIsCustomer() == 1);
			actorValuePropTextbox.setValue(selectedBmActor.getValueProposition());
			
			List<CpActivityRecord> activities = DBConfig.create.selectFrom(CpActivity.CP_ACTIVITY).where(CpActivity.CP_ACTIVITY.BM_ACTOR_ID.equal(selectedBmActor.getBmActorId())).fetch();
			if(activities != null && !activities.isEmpty()) initActivitiesGUI(activities);
			else constructActivityGUI();
			
			List<CostBenefitActorRecord> cp4Actor = DBConfig.create.selectFrom(CostBenefitActor.COST_BENEFIT_ACTOR).
					where(CostBenefitActor.COST_BENEFIT_ACTOR.BM_ACTOR_ID.equal(selectedBmActor.getBmActorId())).fetch();
			if(cp4Actor != null && !cp4Actor.isEmpty()) initCostBenefitGUI(cp4Actor);
			else constructCostBenefitGUI();
		}
		if((Sessions.getCurrent()).getAttribute("isBmActorPropOpen") != null) {
			actorPropView.setOpen((boolean)(Sessions.getCurrent()).getAttribute("isBmActorPropOpen"));
		}
	}
	
	private void initActivitiesGUI(List<CpActivityRecord> activities) {
		for(CpActivityRecord activity : activities) {
			Vlayout vlayoutContainer = constructActivityGUI();
			Combobox activityCombobox =  (Combobox) vlayoutContainer.getFirstChild().getFirstChild();
			for(Component c : activityCombobox.getChildren()) {
				if (c instanceof Comboitem) {
					if(((Comboitem)c).getValue().equals(activity.getName())) {
						activityCombobox.setSelectedItem((Comboitem)c);
						break;
					}
				}
			}
			double[] coord = getCoordination(activity.getCpActivityId(), selectedBmActor.getBmActorId());
			((Doublespinner)(vlayoutContainer.getChildren().get(1).getChildren().get(1))).setValue(coord[0]);
			((Doublespinner)(vlayoutContainer.getChildren().get(1).getChildren().get(4))).setValue(coord[1]);
			((Doublespinner)(vlayoutContainer.getChildren().get(1).getChildren().get(7))).setValue(coord[2]);
		}
	}

	private void bindActivityCombobox(Combobox activityCombobox) {
		List<String> activities = DBConfig.create.selectDistinct(CpActivity.CP_ACTIVITY.NAME).from(CpActivity.CP_ACTIVITY).fetch(CpActivity.CP_ACTIVITY.NAME);
		for(String activity : activities) {
			Comboitem item = new Comboitem();
			item.setId("CI_ACT_" + activity + UUID.randomUUID().toString());
			item.setValue(activity);
			item.setLabel(activity);
			item.setParent(activityCombobox);
		}
	}
	
	private void initCostBenefitGUI(List<CostBenefitActorRecord> costBenefitActors) {
		for(CostBenefitActorRecord costBenefitActor : costBenefitActors) {
			CostBenefitRecord cb = DBConfig.create.selectFrom(CostBenefit.COST_BENEFIT).where(CostBenefit.COST_BENEFIT.CB_ID.equal(costBenefitActor.getCbId())).fetchOne();
			Vlayout vlayoutContainer = constructCostBenefitGUI();
			Combobox costBenefitCombobox =  (Combobox) vlayoutContainer.getFirstChild().getFirstChild();
			
			
			for(Component c : costBenefitCombobox.getChildren()) {
				if (c instanceof Comboitem) {
					if(((Comboitem)c).getValue().equals(cb)) {
						costBenefitCombobox.setSelectedItem((Comboitem)c);
						break;
					}
				}
			}
			if(costBenefitActor.getCostOrBenefit().contains("B"))
				((Radio)(vlayoutContainer.getChildren().get(1).getChildren().get(3))).setChecked(true);
			else 
				((Radio)(vlayoutContainer.getChildren().get(1).getChildren().get(1))).setChecked(true);
			
			double[] coord = getCoordination(costBenefitActor.getCbActorId(), selectedBmActor.getBmActorId());
			((Doublespinner)(vlayoutContainer.getChildren().get(2).getChildren().get(1))).setValue(coord[0]);
			((Doublespinner)(vlayoutContainer.getChildren().get(2).getChildren().get(4))).setValue(coord[1]);
			((Doublespinner)(vlayoutContainer.getChildren().get(2).getChildren().get(7))).setValue(coord[2]);
		}
	}
	
	private void bindCostBenefitCombobox(Combobox costBenefitCombobox) {
		for(CostBenefitRecord cb : DBConfig.create.selectFrom(CostBenefit.COST_BENEFIT).fetch() ) {
			Comboitem item = new Comboitem();
			item.setId("CI_CB_" + cb.getCbId() + UUID.randomUUID().toString());
			item.setValue(cb);
			item.setLabel(cb.getName());
			item.setParent(costBenefitCombobox);
		}
	}
	
	@Listen("onClick = #addActivityBtn")
	public Vlayout constructActivityGUI(){
		Vlayout vlayoutContainer = new Vlayout();
		vlayoutContainer.setSpacing("3px");
		vlayoutContainer.setParent(activityGroupbox);
		
    	Space sep = new Space();
    	sep.setHeight("20px");
    	sep.setOrient("horizontal");
    	sep.setBar(true);
    	sep.setParent(activityGroupbox);
		
    	Hlayout firstHLayout = new Hlayout();
    	firstHLayout.setValign("middle");
    	firstHLayout.setParent(vlayoutContainer);
    	
    	Combobox activityCombobox = new Combobox();
    	activityCombobox.setId("activityCombobox" + UUID.randomUUID().toString()); 
    	activityCombobox.setWidth("260px");
    	activityCombobox.setConstraint("no empty");
    	activityCombobox.setAutocomplete(true);
    	bindActivityCombobox(activityCombobox);
    	activityCombobox.setParent(firstHLayout);
    	
    	
    	Button activityRemoveBtn = new Button();
    	activityRemoveBtn.setId("activityRemoveBtn" + UUID.randomUUID().toString());
    	activityRemoveBtn.setImage("/imgs/del.png");
    	activityRemoveBtn.setParent(firstHLayout);
    	EventListener<Event> onRemoveBtnClick = new SerializableEventListener<Event>() {
			private static final long serialVersionUID = -7024427880320891459L;
			public void onEvent(Event event) throws Exception {
				if(activityCombobox.getSelectedItem() != null) {
					activityCombobox.setConstraint("");
					String message = "Do you want to remove " + activityCombobox.getValue() + " from the actor?";
					EventListener<ClickEvent> clickEventListener = new EventListener<ClickEvent>() {
						public void onEvent(ClickEvent event) {
							if (Messagebox.ON_YES.equals(event.getName())) {	
								DBConfig.create.deleteFrom(CpActivity.CP_ACTIVITY)
								.where(CpActivity.CP_ACTIVITY.NAME.equal(activityCombobox.getValue()))
								.and(CpActivity.CP_ACTIVITY.BM_ACTOR_ID.equal(selectedBmActor.getBmActorId())).execute();
								Executions.sendRedirect("");
							}
						}
					};
					Messagebox.Button[] btns = { Messagebox.Button.YES,Messagebox.Button.NO}; 
					Messagebox.show(message, "Remove Activity: ", btns, Messagebox.EXCLAMATION, clickEventListener);
				} else {
					vlayoutContainer.setParent(null);
					sep.setParent(null);
				}
				
			}
		};
		activityRemoveBtn.addEventListener(Events.ON_CLICK, onRemoveBtnClick);
    	
    	Hlayout thirdHLayout = new Hlayout();
    	thirdHLayout.setValign("middle");
    	thirdHLayout.setParent(vlayoutContainer);
    	
    	Label lbl = new Label("X");
    	lbl.setParent(thirdHLayout);
    	
    	Doublespinner xCostBenefitSpinner = new Doublespinner();
    	xCostBenefitSpinner.setId("xActivitySpinner" + UUID.randomUUID().toString());
    	xCostBenefitSpinner.setWidth("65px");
    	xCostBenefitSpinner.setConstraint("no empty,min -1000 max 1000: ${c:l('err.msg.spinner')}");
    	xCostBenefitSpinner.setParent(thirdHLayout);
    	xCostBenefitSpinner.setValue(0.0);
    	
    	Space sp2 = new Space();
    	sp2.setBar(true);
    	sp2.setSpacing("10px");
    	sp2.setParent(thirdHLayout);
    	
    	Label lbl2 = new Label("Y");
    	lbl2.setParent(thirdHLayout);
    	
    	Doublespinner yCostBenefitSpinner = new Doublespinner();
    	yCostBenefitSpinner.setId("yActivitySpinner" + UUID.randomUUID().toString());
    	yCostBenefitSpinner.setWidth("65px");
    	yCostBenefitSpinner.setConstraint("no empty,min -1000 max 1000: ${c:l('err.msg.spinner')}");
    	yCostBenefitSpinner.setParent(thirdHLayout);
    	yCostBenefitSpinner.setValue(0.0);
    	
    	Space sp3 = new Space();
    	sp3.setBar(true);
    	sp3.setSpacing("10px");
    	sp3.setParent(thirdHLayout);
    	
    	Label lbl3 = new Label("Degree");
    	lbl3.setParent(thirdHLayout);
    	
    	
    	Doublespinner degCostBenefitSpinner = new Doublespinner();
    	degCostBenefitSpinner.setId("degActivitySpinner" + UUID.randomUUID().toString());
    	degCostBenefitSpinner.setWidth("65px");
    	degCostBenefitSpinner.setConstraint("no empty,min -360 max 360: ${c:l('err.msg.spinner')}");
    	degCostBenefitSpinner.setParent(thirdHLayout);
    	degCostBenefitSpinner.setValue(0.0);
    	
    	return vlayoutContainer;
	}
	
	@Listen("onClick = #addCostBenefitBtn")
	public Vlayout constructCostBenefitGUI(){
		Vlayout vlayoutContainer = new Vlayout();
		vlayoutContainer.setSpacing("3px");
		vlayoutContainer.setParent(costBenefitGroupbox);
		
    	Space sep = new Space();
    	sep.setHeight("20px");
    	sep.setOrient("horizontal");
    	sep.setBar(true);
    	sep.setParent(costBenefitGroupbox);
		
    	Hlayout firstHLayout = new Hlayout();
    	firstHLayout.setValign("middle");
    	firstHLayout.setParent(vlayoutContainer);
    	
    	Combobox costBenefitCombobox = new Combobox();
    	costBenefitCombobox.setId("costBenefitCombobox" + UUID.randomUUID().toString()); 
    	costBenefitCombobox.setWidth("260px");
    	costBenefitCombobox.setConstraint("no empty");
    	costBenefitCombobox.setAutocomplete(true);
    	bindCostBenefitCombobox(costBenefitCombobox);
    	costBenefitCombobox.setParent(firstHLayout);
    	
    	
    	Button costBenefitRemoveBtn = new Button();
    	costBenefitRemoveBtn.setId("costBenefitRemoveBtn" + UUID.randomUUID().toString());
    	costBenefitRemoveBtn.setImage("/imgs/del.png");
    	costBenefitRemoveBtn.setParent(firstHLayout);
    	EventListener<Event> onRemoveBtnClick = new SerializableEventListener<Event>() {
			private static final long serialVersionUID = -7024427880320891459L;
			public void onEvent(Event event) throws Exception {
				if(costBenefitCombobox.getSelectedItem() != null) {
					costBenefitCombobox.setConstraint("");
					String message = "Do you want to remove " + ((CostBenefitRecord)costBenefitCombobox.getSelectedItem().getValue()).getName() + " from the actor?";
					EventListener<ClickEvent> clickEventListener = new EventListener<ClickEvent>() {
						public void onEvent(ClickEvent event) {
							if (Messagebox.ON_YES.equals(event.getName())) {	
								DBConfig.create.deleteFrom(CostBenefitActor.COST_BENEFIT_ACTOR).where(
									CostBenefitActor.COST_BENEFIT_ACTOR.CB_ID.equal(((CostBenefitRecord)costBenefitCombobox.getSelectedItem().getValue()).getCbId()))
								.and(CostBenefitActor.COST_BENEFIT_ACTOR.BM_ACTOR_ID.equal(selectedBmActor.getBmActorId())).execute();
								Executions.sendRedirect("");
							}
						}
					};
					Messagebox.Button[] btns = { Messagebox.Button.YES,Messagebox.Button.NO}; 
					Messagebox.show(message, "Remove Cost/Benefit: ", btns, Messagebox.EXCLAMATION, clickEventListener);
				} else {
					vlayoutContainer.setParent(null);
					sep.setParent(null);
				}
				
			}
		};
		costBenefitRemoveBtn.addEventListener(Events.ON_CLICK, onRemoveBtnClick);
		
    	Hlayout secondHLayout = new Hlayout();
    	secondHLayout.setParent(vlayoutContainer);
    	
    	Radiogroup costBenefitRd = new Radiogroup();
    	String costbenefitRadiogroupId = "costBenefitRd" + UUID.randomUUID().toString();
    	costBenefitRd.setId(costbenefitRadiogroupId);
    	costBenefitRd.setParent(secondHLayout);
    	
    	Radio rdCost = new Radio();
    	rdCost.setId("rdCost" + UUID.randomUUID().toString());
    	rdCost.setLabel("Cost");
    	rdCost.setRadiogroup(costbenefitRadiogroupId);
    	rdCost.setParent(secondHLayout);
    	
    	Space sp = new Space();
    	sp.setBar(true);
    	sp.setSpacing("55px");
    	sp.setParent(secondHLayout);
    	
    	
    	Radio rdBenefit = new Radio();
    	rdBenefit.setId("rdBenefit" + UUID.randomUUID().toString());
    	rdBenefit.setLabel("Benefit");
    	rdBenefit.setRadiogroup(costbenefitRadiogroupId);
    	rdBenefit.setParent(secondHLayout);
    	
    	costBenefitRd.setSelectedIndex(0);
    	
    	Hlayout thirdHLayout = new Hlayout();
    	thirdHLayout.setValign("middle");
    	thirdHLayout.setParent(vlayoutContainer);
    	
    	Label lbl = new Label("X");
    	lbl.setParent(thirdHLayout);
    	
    	Doublespinner xCostBenefitSpinner = new Doublespinner();
    	xCostBenefitSpinner.setId("xCostBenefitSpinner" + UUID.randomUUID().toString());
    	xCostBenefitSpinner.setWidth("65px");
    	xCostBenefitSpinner.setConstraint("no empty,min -1000 max 1000: ${c:l('err.msg.spinner')}");
    	xCostBenefitSpinner.setParent(thirdHLayout);
    	xCostBenefitSpinner.setValue(0.0);
    	
    	Space sp2 = new Space();
    	sp2.setBar(true);
    	sp2.setSpacing("10px");
    	sp2.setParent(thirdHLayout);
    	
    	Label lbl2 = new Label("Y");
    	lbl2.setParent(thirdHLayout);
    	
    	Doublespinner yCostBenefitSpinner = new Doublespinner();
    	yCostBenefitSpinner.setId("yCostBenefitSpinner" + UUID.randomUUID().toString());
    	yCostBenefitSpinner.setWidth("65px");
    	yCostBenefitSpinner.setConstraint("no empty,min -1000 max 1000: ${c:l('err.msg.spinner')}");
    	yCostBenefitSpinner.setParent(thirdHLayout);
    	yCostBenefitSpinner.setValue(0.0);
    	
    	Space sp3 = new Space();
    	sp3.setBar(true);
    	sp3.setSpacing("10px");
    	sp3.setParent(thirdHLayout);
    	
    	Label lbl3 = new Label("Degree");
    	lbl3.setParent(thirdHLayout);
    	
    	Doublespinner degCostBenefitSpinner = new Doublespinner();
    	degCostBenefitSpinner.setId("degCostBenefitSpinner" + UUID.randomUUID().toString());
    	degCostBenefitSpinner.setWidth("65px");
    	degCostBenefitSpinner.setConstraint("no empty,min -360 max 360: ${c:l('err.msg.spinner')}");
    	degCostBenefitSpinner.setParent(thirdHLayout);
    	degCostBenefitSpinner.setValue(0.0);
    	
    	return vlayoutContainer;
	}
	
	@Listen("onOpen = #actorPropView")
	public void changeViews(){
		(Sessions.getCurrent()).setAttribute("isBmActorPropOpen", actorPropView.isOpen());
	}
	
	private ActorRecord getActor(String actorId) {
		return DBConfig.create.selectFrom(Actor.ACTOR).where(Actor.ACTOR.ACTOR_ID.equal(actorId)).fetchOne();
	}
	
	@Listen ("onClick = #saveBmAcotrPropBtn")
	public void saveBmActorProp() {
		String valueProposition = actorValuePropTextbox.getValue().trim();
		if(valueProposition.length() > 1) {
			DBConfig.create.update(BmActor.BM_ACTOR).
			set(BmActor.BM_ACTOR.VALUE_PROPOSITION, valueProposition)
			.set(BmActor.BM_ACTOR.IS_FOCAL, (byte) (isFocalCheckbox.isChecked() ? 1 : 0 ))
			.set(BmActor.BM_ACTOR.IS_CUSTOMER, (byte) (isCustomerCheckbox.isChecked() ? 1 : 0 ))
			.where(BmActor.BM_ACTOR.BM_ACTOR_ID.equal(selectedBmActor.getBmActorId())).execute();
			
			saveComonentLocation(selectedBmActor.getBmActorId() +"_NAME", xActorSpinner.getValue(),  yActorSpinner.getValue(), degActorSpinner.getValue());
			saveComonentLocation(selectedBmActor.getBmActorId() + "_PROP", xPropSpinner.getValue(),  yPropSpinner.getValue(), degPropSpinner.getValue());

			saveActivities(); 
			saveCostBenefits();
			
			BmActorRecord bmActor = DBConfig.create.selectFrom(BmActor.BM_ACTOR).where(BmActor.BM_ACTOR.BM_ACTOR_ID.equal(selectedBmActor.getBmActorId())).fetchOne();
			(Sessions.getCurrent()).setAttribute("bmActor", bmActor);
			
			Executions.sendRedirect("");
		} else {
			Messagebox.show("Please insert correct value proposition.");
		}
	}
	
	@Listen ("onClick = #removeBmActor")
	public void removeBmActor() {
		if(selectedBmActor != null) {
			if(selectedBmActor.getIsCustomer() == 0 && selectedBmActor.getIsFocal() == 0) {
				DBConfig.create.deleteFrom(CpActivity.CP_ACTIVITY).where(CpActivity.CP_ACTIVITY.BM_ACTOR_ID.equal(selectedBmActor.getBmActorId())).execute();
				DBConfig.create.deleteFrom(CostBenefitActor.COST_BENEFIT_ACTOR).where(CostBenefitActor.COST_BENEFIT_ACTOR.BM_ACTOR_ID.equal(selectedBmActor.getBmActorId())).execute();
				DBConfig.create.deleteFrom(BmActor.BM_ACTOR).where(BmActor.BM_ACTOR.BM_ACTOR_ID.equal(selectedBmActor.getBmActorId())).execute();
				(Sessions.getCurrent()).setAttribute("bmActor", null);
				Executions.sendRedirect("");
			} else {
				Messagebox.show("You cannot remove the focal organization and the customer.", "Error", Messagebox.OK, Messagebox.ERROR);
			}
		}		
	}
	
	private void saveActivities() {
		for(Component c : activityGroupbox.getChildren()) {
			if(c instanceof Vlayout) {
				Combobox activityCombobox = (Combobox) c.getFirstChild().getFirstChild();
				if(activityCombobox.getValue() != null) {
					String componentId = "ACT_" + UUID.randomUUID().toString();
					if(activityCombobox.getSelectedItem() == null) {
						DBConfig.create.insertInto(CpActivity.CP_ACTIVITY,
								CpActivity.CP_ACTIVITY.CP_ACTIVITY_ID, CpActivity.CP_ACTIVITY.NAME, CpActivity.CP_ACTIVITY.BM_ACTOR_ID)
						.values(componentId, activityCombobox.getValue().trim(), selectedBmActor.getBmActorId()).execute();
					} else {
						CpActivityRecord foundActivity = DBConfig.create.selectFrom(CpActivity.CP_ACTIVITY)
								.where(CpActivity.CP_ACTIVITY.NAME.equal(activityCombobox.getValue().trim())).
										and(CpActivity.CP_ACTIVITY.BM_ACTOR_ID.equal(selectedBmActor.getBmActorId())).fetchOne();
						if(foundActivity == null) {
							DBConfig.create.insertInto(CpActivity.CP_ACTIVITY,
									CpActivity.CP_ACTIVITY.CP_ACTIVITY_ID, CpActivity.CP_ACTIVITY.NAME, CpActivity.CP_ACTIVITY.BM_ACTOR_ID)
							.values(componentId, activityCombobox.getValue().trim(), selectedBmActor.getBmActorId()).execute();
						} else {
							componentId = foundActivity.getCpActivityId();
						}
					}
					
					double x = ((Doublespinner)(c.getChildren().get(1).getChildren().get(1))).getValue();
					double y = ((Doublespinner)(c.getChildren().get(1).getChildren().get(4))).getValue();
					double degree = ((Doublespinner)(c.getChildren().get(1).getChildren().get(7))).getValue();
					saveComonentLocation(componentId, x, y, degree);
					
				} else {
					Messagebox.show("The value for activity cannot be empty.", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}
		}
	}
	
	private void saveComonentLocation(String componentId, double x, double y, double degree) {
		DBConfig.create.insertInto(LocationGui.LOCATION_GUI, 
				LocationGui.LOCATION_GUI.COMPONENT_ID, 
				LocationGui.LOCATION_GUI.BM_ID,
				LocationGui.LOCATION_GUI.BM_ACTOR_ID,
				LocationGui.LOCATION_GUI.X,
				LocationGui.LOCATION_GUI.Y,
				LocationGui.LOCATION_GUI.DEGREE)
		  .values(componentId, bm.getBmId(), selectedBmActor.getBmActorId(), x, y, degree)
		  .onDuplicateKeyUpdate()
		  .set(LocationGui.LOCATION_GUI.X, x)
		  .set(LocationGui.LOCATION_GUI.Y, y)
		  .set(LocationGui.LOCATION_GUI.DEGREE, degree)
		  .execute();
	}
	
	private void saveCostBenefits() {
		for(Component c : costBenefitGroupbox.getChildren()) {
			if(c instanceof Vlayout) {
				boolean isBenefit = ((Radio)(c.getChildren().get(1).getChildren().get(3))).isChecked();
				String B_C = "C"; if(isBenefit) B_C = "B";
			
				Combobox costBenefitCombobox = (Combobox) c.getFirstChild().getFirstChild();
				if(costBenefitCombobox.getValue() != null ) {
					String componentId = "CBA_" + UUID.randomUUID().toString();
					if(costBenefitCombobox.getSelectedItem() != null) {
						CostBenefitRecord selectedCBR = costBenefitCombobox.getSelectedItem().getValue();
						
						CostBenefitActorRecord foundCostBenefitActor = DBConfig.create.selectFrom(CostBenefitActor.COST_BENEFIT_ACTOR)
								.where(CostBenefitActor.COST_BENEFIT_ACTOR.CB_ID.equal(selectedCBR.getCbId()).
										and(CostBenefitActor.COST_BENEFIT_ACTOR.BM_ACTOR_ID.equal(selectedBmActor.getBmActorId()))).fetchOne();
						if(foundCostBenefitActor != null) {
							DBConfig.create.update(CostBenefitActor.COST_BENEFIT_ACTOR)
							.set(CostBenefitActor.COST_BENEFIT_ACTOR.COST_OR_BENEFIT, B_C)
							.where(CostBenefitActor.COST_BENEFIT_ACTOR.CB_ACTOR_ID.equal(foundCostBenefitActor.getCbActorId()))
							.execute();
							
							componentId = foundCostBenefitActor.getCbActorId();
						} else {
							DBConfig.create.insertInto(CostBenefitActor.COST_BENEFIT_ACTOR, 
									CostBenefitActor.COST_BENEFIT_ACTOR.CB_ACTOR_ID, 
									CostBenefitActor.COST_BENEFIT_ACTOR.BM_ACTOR_ID, 
									CostBenefitActor.COST_BENEFIT_ACTOR.CB_ID, 
									CostBenefitActor.COST_BENEFIT_ACTOR.COST_OR_BENEFIT)
							.values(componentId, selectedBmActor.getBmActorId(), selectedCBR.getCbId(), B_C).execute();
						}
					} else {
						String costBenefitId = "C_B_" + UUID.randomUUID().toString();
						DBConfig.create.insertInto(CostBenefit.COST_BENEFIT, CostBenefit.COST_BENEFIT.CB_ID, CostBenefit.COST_BENEFIT.NAME)
						.values(costBenefitId, costBenefitCombobox.getValue().trim()).execute();
						
						if(DBConfig.create.selectFrom(CostBenefit.COST_BENEFIT).where(CostBenefit.COST_BENEFIT.CB_ID.equal(costBenefitId)).fetchOne() != null) {
							DBConfig.create.insertInto(CostBenefitActor.COST_BENEFIT_ACTOR, CostBenefitActor.COST_BENEFIT_ACTOR.CB_ACTOR_ID, 
									CostBenefitActor.COST_BENEFIT_ACTOR.BM_ACTOR_ID, 
									CostBenefitActor.COST_BENEFIT_ACTOR.CB_ID, 
									CostBenefitActor.COST_BENEFIT_ACTOR.COST_OR_BENEFIT)
							.values(componentId, selectedBmActor.getBmActorId(), costBenefitId, B_C).execute();
						} else {
							Messagebox.show("The value " + costBenefitCombobox.getValue().trim() + " for Cost/Benefit cannot be stored.", "Error", Messagebox.OK, Messagebox.ERROR);
						}
					}
					
					double x = ((Doublespinner)(c.getChildren().get(2).getChildren().get(1))).getValue();
					double y = ((Doublespinner)(c.getChildren().get(2).getChildren().get(4))).getValue();
					double degree = ((Doublespinner)(c.getChildren().get(2).getChildren().get(7))).getValue();
					saveComonentLocation(componentId, x, y, degree);
					
				} else {
					Messagebox.show("The value for cost or benefit cannot be empty.", "Error", Messagebox.OK, Messagebox.ERROR);
				}
			}
		}
	}
	
	private double[] getCoordination(String componentId, String bmActorId) {
		double[] coordinations = new double[3];
		coordinations[0] = 0; coordinations[1] = 0; coordinations[2] = 0;
		LocationGuiRecord record = DBConfig.create.selectFrom(LocationGui.LOCATION_GUI).
				where(LocationGui.LOCATION_GUI.COMPONENT_ID.equal(componentId)).
				and(LocationGui.LOCATION_GUI.BM_ID.equal(bm.getBmId())).
				and(LocationGui.LOCATION_GUI.BM_ACTOR_ID.equal(bmActorId)).
				fetchOne();
		if(record != null) {
			coordinations[0] = record.getX();
			coordinations[1] = record.getY();
			coordinations[2] = record.getDegree();
		}
		return coordinations;
	}
	
	@Listen("onClick=#cvs1")
	public void onMouseAction(MouseEvent event){
		for(int i = 0; i < cvs1.size(); i++) {
			
		}
	}
}
