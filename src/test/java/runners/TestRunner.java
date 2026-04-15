package runners;

import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = { "src/test/resources/rigorousTestCases" }, 
		glue = { "stepDefinitions" },
		plugin = {"extentReport.ExtentCucumberAdapter", "html:target/report/en.html"},
		monochrome = true,
		strict = true)
public class TestRunner extends BaseTestRunner {
	
	@BeforeClass
	public static void setLang() {
		path = "enExtent.html";
//		getExcelSheetData();
	}
	
}