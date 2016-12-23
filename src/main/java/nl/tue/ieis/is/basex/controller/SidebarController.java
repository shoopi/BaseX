package main.java.nl.tue.ieis.is.basex.controller;

import org.jooq.Record1;
import org.jooq.Result;
import org.zkoss.canvas.Canvas;
import org.zkoss.canvas.DrawingStyle.LineCap;
import org.zkoss.canvas.DrawingStyle.LineJoin;
import org.zkoss.canvas.drawable.Drawable;
import org.zkoss.canvas.drawable.Path;
import org.zkoss.canvas.drawable.Text;
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
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.South;

import main.java.nl.tue.ieis.is.basex.database.DBConfig;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Strategy;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.UserHasStrategy;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.StrategyRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.UserRecord;



public class SidebarController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1116480279298658373L;
	
	@Wire	private Canvas cvs1;
	@Wire	private Grid pyramidGrid, strategyGrid;
	@Wire	private	Button addStrategyBtn; //removeStrategyBtn, saveStrategyBtn;
	@Wire	private	South pyramidSection;
	
	private Drawable s_triangle, bm_shape, sc_shape, bs_shape;

	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		if((Sessions.getCurrent()).getAttribute("user") != null) {
			UserRecord user = (UserRecord)(Sessions.getCurrent()).getAttribute("user");
			
			try {
				
				addStrategyBtn.setDisabled(false);
				
				Result<Record1<String>> strategyIDs = DBConfig.create.select(UserHasStrategy.USER_HAS_STRATEGY.STRATEGY_STRATEGY_ID)
					      .from(UserHasStrategy.USER_HAS_STRATEGY)
					      .where((UserHasStrategy.USER_HAS_STRATEGY.USER_USER_ID.equal(user.getUserId())))
					      .fetch();
				
				if(strategyIDs != null & strategyIDs.size() > 0) {
					for(Record1<String> id : strategyIDs) {
						
						StrategyRecord strategy = DBConfig.create.selectFrom(Strategy.STRATEGY)
													.where(Strategy.STRATEGY.STRATEGY_ID.equal(id.value1().toString()))
													.fetchOne();
						
						Row row = constructSidebarRow(strategy);
						strategyGrid.getRows().appendChild(row);

						StrategyRecord selectedStrategy = (StrategyRecord) (Sessions.getCurrent()).getAttribute("strategy");
						if(selectedStrategy != null && selectedStrategy.getStrategyId().contentEquals(id.value1().toString())) {
							row.setStyle("background-color: #FFFF99; font-weight: bold;");
							
							
							pyramidSection.setVisible(true);							
							drawPyramid();
							
							if((Sessions.getCurrent()).getAttribute("selectedPyramid") != null ) {
								int selected = (int)(Sessions.getCurrent()).getAttribute("selectedPyramid");
								changeSelectedStyle(cvs1, selected);
								((Checkbox)pyramidGrid.getRows().getChildren().get(selected).getChildren().get(0)).setChecked(true);		

							}
							else {
								changeSelectedStyle(cvs1, 1);
								(Sessions.getCurrent()).setAttribute("selectedPyramid" , 1);
								((Checkbox)pyramidGrid.getRows().getChildren().get(1).getChildren().get(0)).setChecked(true);			
							}

						}
					}
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			}
				
		}
		
	}

	private Row constructSidebarRow(StrategyRecord strategy) {
		final Row row = new Row();
		try {
			Label lab = new Label(strategy.getStrategyName());		
			row.appendChild(lab);
			row.setSclass("sidebar-fn");
			
			EventListener<Event> actionListener = new SerializableEventListener<Event>() {
				private static final long serialVersionUID = 1L;

				public void onEvent(Event event) throws Exception {
					(Sessions.getCurrent()).setAttribute("strategy" , strategy);
					Executions.getCurrent().sendRedirect("");
				}
			};
		row.addEventListener(Events.ON_CLICK, actionListener);
		
		} catch(Exception e) { 
			e.printStackTrace();
		}
	return row;
	}
	
	
	@Listen("onClick=#cvs1")
	public void pyramidClick(MouseEvent event){
		for(int i = 0; i < cvs1.size(); i++) {
			Drawable d = cvs1.getDrawable(i);
			if(d instanceof Path) {
				Path s = (Path) d;
				((Checkbox)pyramidGrid.getRows().getChildren().get(i).getChildren().get(0)).setChecked(false);
				if(s.contains(event.getX(), event.getY())) {
					(Sessions.getCurrent()).setAttribute("selectedPyramid" , i);
					((Checkbox)pyramidGrid.getRows().getChildren().get(i).getChildren().get(0)).setChecked(true);		
				}
			}

		}
		Executions.sendRedirect("");
	}

	private void drawPyramid() {
		s_triangle = new Path().moveTo(160,10).lineTo(130,40).lineTo(190,40).lineTo(160,10).closePath();
		bm_shape = new Path().moveTo(125,45).lineTo(95,75).lineTo(225,75).lineTo(195,45).lineTo(125,45).closePath();
		sc_shape = new Path().moveTo(90,80).lineTo(60,110).lineTo(260,110).lineTo(230,80).lineTo(90,80).closePath();
		bs_shape = new Path().moveTo(55,115).lineTo(25,145).lineTo(295,145).lineTo(265,115).lineTo(55,115).closePath();
		
		Drawable  s_text = new Text(" S", 145, 25);
		Drawable bm_text = new Text("BM", 145, 60);
		Drawable sc_text = new Text("SC", 145, 95);
		Drawable bs_text = new Text("BS", 145, 130);
		
		s_text.getDrawingStyle().setFont("20px tahoma");
		s_text.getDrawingStyle().setFillStyle("#ffffff");
		s_text.getDrawingStyle().setStrokeStyle("#ffffff");
		s_text.getDrawingStyle().setShadowColor("#800000");
		s_text.getDrawingStyle().setShadowBlur(5);
		s_text.getDrawingStyle().setShadowOffset(0.05, 0.05);
		s_text.getDrawingStyle().setTextBaseline("middle");
		
		bm_text.copyStyleFrom(s_text); 
		sc_text.copyStyleFrom(s_text); 
		bs_text.copyStyleFrom(s_text);
		
		cvs1.add(s_triangle, bm_shape, sc_shape, bs_shape);
		cvs1.add(s_text, bm_text, sc_text, bs_text);
	}
	
	@Listen("onClick = #addStrategyBtn")
	public void createBlankStrategy() {
		(Sessions.getCurrent()).setAttribute("selectedPyramid" , 0);
		(Sessions.getCurrent()).setAttribute("strategy" , null);
		Executions.sendRedirect("");
	}
	
	
	
	private void changeSelectedStyle(Canvas cvs, int selectedItem) {
		for(int x = 0; x < cvs.getDrawables().size(); x++) {
			if(cvs.getDrawable(x) instanceof Path) {
				Drawable d = cvs.getDrawable(x);
				d.getDrawingStyle().setFillStyle("#000080");
				d.getDrawingStyle().setShadowColor("#800000");
				d.getDrawingStyle().setShadowBlur(5);
				d.getDrawingStyle().setShadowOffset(0.5, 0.5);
				d.getDrawingStyle().setLineCap(LineCap.ROUND);
				d.getDrawingStyle().setLineJoin(LineJoin.ROUND);
				if(x == selectedItem) {
					d.getDrawingStyle().setFillStyle("#7ba9f2");
				}
				cvs.update(x);
			}
		}
	}
}
