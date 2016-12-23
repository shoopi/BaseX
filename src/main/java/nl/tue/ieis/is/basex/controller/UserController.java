package main.java.nl.tue.ieis.is.basex.controller;


import java.util.List;
import java.util.UUID;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import main.java.nl.tue.ieis.is.basex.database.DBConfig;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.Company;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.User;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.CompanyRecord;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.UserRecord;
import main.java.nl.tue.ieis.is.basex.utility.PasswordService;

public class UserController extends SelectorComposer<Component> {
	
	private static final long serialVersionUID = 8670883952725538263L;
	
	
	@Wire	private Textbox email, password;
	@Wire	private Textbox regEmail, regPassword, regFirstname, regLastname, regPasswordConfirm;
	@Wire	private Label loginMsgLabel, regMsgLabel;
	@Wire	private Window loginWin, regWin;
	@Wire	private Button regBtn, loginwBtn;
	@Wire	private Combobox regCompany;
	
	
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		List<CompanyRecord> companies = DBConfig.create.selectFrom(Company.COMPANY).fetch();
		if(companies != null && companies.size() > 0) {
			for(CompanyRecord c : companies) {
				Comboitem item = new Comboitem(c.getCompanyName());
				item.setId("comboitem_" + c.getCompanyName());
				item.setValue(c);
				item.setParent(regCompany);
			}
		}
	}
	
	@Listen("onOK = #loginWin")
	public void loginWindowOk() {
		login();
	}
	
	@Listen("onClick = #loginBtn")
	public void loginButtonClick() {
		login();
	}
	
	@Listen("onOK = #regWin")
	public void regWindowOk() {
		register();
	}
	
	@Listen("onClick = #regBtn")
	public void regButtonClick() {
		register();
	}
	
	private void login() {
		try {
			
			UserRecord prev = DBConfig.create.selectFrom(User.USER)
	                .where(User.USER.EMAIL.equal(email.getValue().trim().toLowerCase())).fetchAny();
			
			if(prev != null) {
				if(prev.getPassword().contentEquals(encryptPassword(password.getValue()))) {
					(Sessions.getCurrent()).setAttribute("user", prev);
					loginWin.setVisible(false);
					Executions.sendRedirect("");
				} else 
					loginMsgLabel.setValue("Your password is incorrect. Please try again!");
			} else 
				loginMsgLabel.setValue("Your email address is incorecct. Please try again!");
		} catch(Exception e) {
			e.printStackTrace();
			loginMsgLabel.setValue("An error has occurred. Unfortunately We cannot process your login request!.");

		}
	}
	
	private void register() {		
		String email 		= 	regEmail.getValue();
		String password 	= 	regPassword.getValue();
		String confdPas 	= 	regPasswordConfirm.getValue();
	
		
		if(!password.contentEquals(confdPas)) {
			regMsgLabel.setValue("Your password and confirmation password do not match. Please try again!");
		} else {
	        try {
				UserRecord prev = DBConfig.create.selectFrom(User.USER)
		                .where(User.USER.EMAIL.equal(email.trim().toLowerCase())).fetchAny();
				
				if(prev == null) {
					
					String firstname = "", lastname = "";
					
					if(regFirstname.getValue() != null) 		firstname 	= 	regFirstname.getValue();
					if(regLastname.getValue() != null) 			lastname 	= 	regLastname.getValue();
					if(regCompany.getSelectedItem() != null || regCompany.getValue() != null) { 
						String userId = "USR_" + UUID.randomUUID().toString();
						String companyName = "";
						
						if(regCompany.getSelectedItem() != null) {
							CompanyRecord company = (CompanyRecord)regCompany.getSelectedItem().getValue();
							if(DBConfig.create.selectFrom(Company.COMPANY).fetch().contains(company)) {
								companyName = company.getCompanyName();
							} else {
								regMsgLabel.setValue("No company has been found.");
							}
						} else {
							companyName = regCompany.getValue().trim();
							DBConfig.create.insertInto(Company.COMPANY,  Company.COMPANY.COMPANY_NAME).values(companyName).execute();
						}			
						DBConfig.create.insertInto(User.USER,
						        User.USER.USER_ID, User.USER.EMAIL, User.USER.PASSWORD, User.USER.LASTNAME, User.USER.FIRSTNAME, User.USER.COMPANY_NAME)
						      .values(userId, email.trim().toLowerCase(), encryptPassword(password), lastname, firstname, companyName)
						      .execute();
						
						prev = DBConfig.create.selectFrom(User.USER)
				                .where(User.USER.EMAIL.equal(email.trim().toLowerCase())).fetchAny();
						
						(Sessions.getCurrent()).setAttribute("user", prev);
						regWin.setVisible(false);
						Executions.sendRedirect("");
					} else {
						regMsgLabel.setValue("Please specify your company.");
					} 
				} else {
					regMsgLabel.setValue("An account with " + email.toLowerCase().trim() + " has already been created.");
				}
	        } catch (Exception e) {
				e.printStackTrace();
				regMsgLabel.setValue("An error has occurred. Unfortunately We cannot process your registration request!.");
	        }
		}
	}
	
	private String encryptPassword(String input) throws Exception {
        PasswordService ps = new PasswordService();
        return ps.encrypt(input);
	}
	
	
	
}