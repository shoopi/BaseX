package main.java.nl.tue.ieis.is.basex.controller;


import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;
import org.zkoss.zul.Window;



public class MainViewController extends SelectorComposer<Component> {

	private static final long serialVersionUID = 1116480279298658373L;
	
	//@Wire	private Include includeSwitch;	
	@Wire	private	Window	mainWindow;

	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		Include includeSwitch = new Include();
		includeSwitch.setId("includeSwitch");
		
		try {
			if((Sessions.getCurrent()).getAttribute("selectedPyramid") != null) {
				int option = (int)(Sessions.getCurrent()).getAttribute("selectedPyramid");
				
			    if(option == 0)
			    	includeSwitch.setSrc("/gui/strategy.zul?id=0");
			    else if(option == 1)
			    	includeSwitch.setSrc("/gui/business_model.zul");
			    else if(option == 2)
			    	includeSwitch.setSrc("/gui/service_composition.zul");
			    else if(option == 3)
			    	includeSwitch.setSrc("/gui/business_services.zul");
			    else
			    	includeSwitch.setSrc("/gui/default.zul");
		    } else {
		    	includeSwitch.setSrc("/gui/default.zul");
		    }
			mainWindow.appendChild(includeSwitch);
		} catch (Exception e) {}
	}
}
