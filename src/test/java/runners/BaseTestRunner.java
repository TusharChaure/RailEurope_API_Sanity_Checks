package runners;

import stepDefinitions.BaseClass;

public class BaseTestRunner extends BaseClass{
	
	public static String path;
	
	protected static void getExcelSheetData() {
		commonMessagesMap = getResponseErrorMessages();
	}
	
}
