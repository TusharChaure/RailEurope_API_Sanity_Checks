package api.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ConfigManager {
	private static ConfigManager manager;

	private static Properties prop;
	private static String projectPath = System.getProperty("user.dir");
	protected static String folderSeparator = "/";

	private ConfigManager() throws Exception
	{
		BufferedReader reader;
		String propertyFilePath = projectPath + "/src/test/resources/config/config.properties";
		try {
			reader = new BufferedReader(new FileReader(propertyFilePath));
			prop = new Properties();
			try {
				prop.load(reader);
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException("Configuration.properties not found at " + propertyFilePath);
		} 
	}

	public static ConfigManager getInstance()
	{
		if(manager==null)
		{ 
			synchronized (ConfigManager.class) {
				try {
					manager=new ConfigManager();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return manager;
	}
	public String getString(String key)
	{
		return System.getProperty(key, prop.getProperty(key));
	}


	public String readRequestJson(String jsonFileName){
		String content = null;

		try {
			content = new String(Files.readAllBytes(Paths.get(projectPath + folderSeparator + "src" + folderSeparator + "test" + folderSeparator + "resources"
					+ folderSeparator + "requestsJsonFiles" + folderSeparator + jsonFileName + ".json")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}
	public String readConfigProperty(String configFileName){
		String content = null;

		try {
			content = new String(Files.readAllBytes(Paths.get(projectPath + folderSeparator + "src" + folderSeparator + "test" + folderSeparator + "resources"
					+ folderSeparator + "config" + folderSeparator + configFileName + ".properties")));
		} catch (IOException e) {
			e.printStackTrace();
		}

		return content;
	}

	public static JSONObject parseJson(String file) throws Exception {
		JSONObject  jsonObject = (JSONObject)  readJsonSimpleDemo(file);
		System.out.println(jsonObject);
		return jsonObject;
	}

	public static Object readJsonSimpleDemo(String filename) throws Exception {
		FileReader reader =new FileReader(projectPath + folderSeparator + "src" + folderSeparator + "test" + folderSeparator + "resources"
				+ folderSeparator + "requestsJsonFiles" + folderSeparator + filename + ".json");
		JSONParser jsonParser = new JSONParser();
		return jsonParser.parse(reader);
	}
}
