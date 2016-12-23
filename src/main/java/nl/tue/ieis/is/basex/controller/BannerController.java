package main.java.nl.tue.ieis.is.basex.controller;


import java.util.Map;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.UserRecord;



public class BannerController extends SelectorComposer<Component> {

	private static final long serialVersionUID = -6360927128036523270L;
	
	@Wire	private Window loginWin, regWin;
	@Wire	private Button initLoginBtn, initRegBtn, logoutBtn;
	@Wire	private Textbox username, password;
	@Wire	private Label userLabel, loginMsgLabel; 
	@Wire	private Div userInfo, bannerWin;

		
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		setUserBanner();
	}
	
	@Listen("onClick = #initLoginBtn")
	public void showLoginWin() {
		try{
			bannerWin.appendChild(loginWin);
		} catch (Exception e) { System.out.println(e.getMessage());}
		if (!loginWin.isVisible())
			loginWin.setVisible(true);
		loginWin.doHighlighted();
	}

	@Listen("onClick = #logoutBtn")
	public void logout() {
		for(Map.Entry<String, Object> session : (Sessions.getCurrent()).getAttributes().entrySet()) {
			session.setValue(null);
		}		
		Executions.sendRedirect("");
	}
	
	@Listen("onClick = #initRegBtn")
	public void showRegWin() {
		try{
			bannerWin.appendChild(regWin);
		} catch (Exception e) { System.out.println(e.getMessage());}
		if (!regWin.isVisible())
			regWin.setVisible(true);
		regWin.doHighlighted();
	}
	
	@Listen("onOK = #loginWin")
	public void onOkLoginWindow(){
		setUserBanner();
	}
	
	private void setUserBanner() {
		UserRecord user = (UserRecord)(Sessions.getCurrent()).getAttribute("user");
		if(user != null) {
			userInfo.setVisible(true);
			initLoginBtn.setVisible(false);
			initRegBtn.setVisible(false);
			userLabel.setValue(user.getFirstname() + " " + user.getLastname() + " (" + user.getEmail() + ")");
			logoutBtn.setVisible(true);
		} else {			
			userInfo.setVisible(false);
			initLoginBtn.setVisible(true);
			initRegBtn.setVisible(true);
			userLabel.setValue("No User");
			logoutBtn.setVisible(false);
		}
	}
}
