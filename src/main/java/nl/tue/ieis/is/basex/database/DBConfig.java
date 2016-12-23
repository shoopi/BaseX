package main.java.nl.tue.ieis.is.basex.database;

import java.sql.*;

import org.jooq.DSLContext;
import org.jooq.impl.DSL;
import org.jooq.util.GenerationTool;
import org.jooq.util.jaxb.Configuration;
import org.jooq.util.jaxb.Database;
import org.jooq.util.jaxb.Generate;
import org.jooq.util.jaxb.Generator;
import org.jooq.util.jaxb.Jdbc;
import org.jooq.util.jaxb.Target;

import main.java.nl.tue.ieis.is.basex.config.ProjectConfig;

public class DBConfig {
	
    private final static String driverClassName = "com.mysql.jdbc.Driver";  
	private static final String dbConnString = "jdbc:mysql://localhost:3306/basex_database";
	private final static String dbUserName = "root";
	private final static String dbPassword = "123456";
	private final static String jOOQConnString = "org.jooq.util.mysql.MySQLDatabase";
	
	private static Configuration jOOQConfiguration = new Configuration().
			withJdbc(new Jdbc()
	        .withDriver(DBConfig.driverClassName)
	        .withUrl(DBConfig.dbConnString)
	        .withUser(DBConfig.dbUserName)
	        .withPassword(DBConfig.dbPassword))
	    .withGenerator(new Generator()
	        .withDatabase(new Database()
	            .withName(DBConfig.jOOQConnString)
	            .withIncludes(".*")
	            .withExcludes("")
	            .withInputSchema("basex_database"))
	        .withTarget(new Target()
	            .withPackageName("main.java.nl.tue.ieis.is.basex.entities.jooq")
	            .withDirectory(ProjectConfig.projectPath + "/src")));
	
	
	public static DSLContext create = DSL.using(getConnection());

	
	public static void main(String[] args) {
		try {
			Generate g = new Generate();
			//g.setPojos(true);
			jOOQConfiguration.getGenerator().withGenerate(g);
			GenerationTool.generate(jOOQConfiguration);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static Connection getConnection()
	  {
	    Connection conn = null;
	    try {
	      Class.forName(driverClassName);
	      
	      conn = DriverManager.getConnection(dbConnString, dbUserName, dbPassword);
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      e.getStackTrace();
	      System.exit(0);
	    }
	    return conn;
	  }
	
	
}