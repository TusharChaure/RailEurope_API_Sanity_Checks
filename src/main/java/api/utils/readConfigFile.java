package api.utils;

public interface readConfigFile {

	static String baseURL= ConfigManager.getInstance().getString("base_url");
	static String loginURL = ConfigManager.getInstance().getString("loginUrl");
	static String rootAdminUserName = ConfigManager.getInstance().getString("rootAdminUserName");   
	static String rootAdminPassword = ConfigManager.getInstance().getString("rootAdminPassword");
	static String pos = ConfigManager.getInstance().getString("pos");
	static String ClientId = ConfigManager.getInstance().getString("ClientId");
	static String xAmzTarget = ConfigManager.getInstance().getString("X-Amz-Target");

}
