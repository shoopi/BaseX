package main.java.nl.tue.ieis.is.basex.database;


import java.util.UUID;


import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.User;
import main.java.nl.tue.ieis.is.basex.entities.jooq.tables.records.UserRecord;


public class TestDatabase {
	
	public static void main(String[] args){
		/*
		User user = new User("USR_" + UUID.randomUUID().toString(), "shoopi@gmail.com", "1234456", "Pourmirza", "Shaya", "Eindhoven university of Technology");
		
		UserRecord userRecord = new UserRecord();
		userRecord.from(user);
		create.insertInto(UserTable.USER).set(userRecord);
		*/
		
		UserRecord prev = DBConfig.create.selectFrom(User.USER)
                .where(User.USER.EMAIL.equal("shoopi@gmail.comX")).fetchAny();
		
		if(prev == null) {
			DBConfig.create.insertInto(User.USER,
			        User.USER.USER_ID, User.USER.EMAIL, User.USER.PASSWORD, User.USER.LASTNAME, User.USER.FIRSTNAME, User.USER.COMPANY_NAME)
			      .values("USR_" + UUID.randomUUID().toString(), "shoopi@gmail.com", "1234456", "Pourmirza", "Shaya", "TU/e")
			      .execute();
		} else {
			//error
		}
		
		
		     
	}
}
