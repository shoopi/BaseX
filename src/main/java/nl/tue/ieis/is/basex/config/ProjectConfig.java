package main.java.nl.tue.ieis.is.basex.config;

import java.util.Map;

import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.util.Initiator;

public class ProjectConfig implements Initiator{

	public static String projectPath = "C:/Users/spourmir/Dropbox/Standard/BaseX";
	
	@Override
	public void doInit(Page page, Map<String, Object> args) throws Exception {
		
		//Connection conn = DBConfig.getConnection();

	}

}
