package extentReport;

import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.util.Arrays;
import java.util.List;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import java.util.stream.Collectors;
import io.cucumber.java.Before;
import io.cucumber.plugin.ConcurrentEventListener;
import io.cucumber.plugin.event.EventPublisher;
import io.cucumber.plugin.event.PickleStepTestStep;
import io.cucumber.plugin.event.TestCaseFinished;
import io.cucumber.plugin.event.WriteEvent;
import runners.BaseTestRunner;
import stepDefinitions.BaseClass;

public class ExtentCucumberAdapter extends BaseClass implements ConcurrentEventListener {

	private ExtentReports extent;
	ExtentTest extentTest;
	String filePath, scenarioDescription, log, error, str = "";
	WriteEvent we;
	private StringBuilder logs;

	@Override
	public void setEventPublisher(EventPublisher eventPublisher) {
		extent = ExtentManager.createInstance(BaseTestRunner.path);
		eventPublisher.registerHandlerFor(WriteEvent.class, this::writeLogs);
		eventPublisher.registerHandlerFor(TestCaseFinished.class, event -> {
			try {
				handleTestCaseFinished(event);
			} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException | MalformedURLException e) {
				e.printStackTrace();
			}	
		});
	}

	private void writeLogs(WriteEvent we) {
		str = we.getText();
	}
	
	@SuppressWarnings("deprecation")
	private void testLogs(TestCaseFinished event) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		Field f = BaseClass.scenario.getClass().getDeclaredField("delegate");
		f.setAccessible(true);
		io.cucumber.core.backend.TestCaseState sc = (io.cucumber.core.backend.TestCaseState) f.get(BaseClass.scenario);
		Field f1 = sc.getClass().getDeclaredField("testCase");
		f1.setAccessible(true);
		io.cucumber.plugin.event.TestCase testCase = (io.cucumber.plugin.event.TestCase) f1.get(sc);
		List<PickleStepTestStep> testSteps = testCase.getTestSteps().stream()
                .filter(x -> x instanceof PickleStepTestStep).map(x -> (PickleStepTestStep) x)
                .collect(Collectors.toList());
		logs.append(we.getTestCase().getTags());
		logs.append("<br>");
		logs.append(we.getTestCase().getKeyword() + ": " + event.getTestCase().getName() + "<br>");
		for(int i=0;i<testSteps.size();i++) {
			logs.append(testSteps.get(i).getStepText() + "<br>");
			
		}
		logs.append("<br>" + "<br>" + "</b>");
	}
	
	private void testPassedOrSkipped() {
		extentTest.pass(log);
		extentTest.pass(scenarioDescription);
		extentTest.pass(testLogs);
	}
	
	private void testFailed(TestCaseFinished event) {
		extentTest.fail(log);
		error = "<b>Error: </b><br><br>" + event.getResult().getError() + "<br>";
		extentTest.fail(error);
		extentTest.fail(scenarioDescription);
		extentTest.fail(testLogs);
	}
	
	private void refreshObjects() {
		pojoError = null;
		log = null;
		scenarioDescription = null;
		testLogs = null;
	}

	@Before
	@SuppressWarnings({ "incomplete-switch" })
	private void handleTestCaseFinished(TestCaseFinished event) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException, MalformedURLException {
		we = new WriteEvent(event.getInstant(), event.getTestCase(), str);
		String[] stringArray = event.getTestCase().getUri().toString().split("/");
		extentTest = extent.createTest(event.getTestCase().getName() + testName);
		extentTest.assignCategory(Arrays.asList(stringArray).get(stringArray.length-1));
		logs = new StringBuilder();
		testLogs(event);
		scenarioDescription = "<br><b>Scenario Description: </b><br><br>" + logs.toString();
		if(pojoError == null)
			error = "";
		else 
			error = "<b>Pojo Error: <b><br><br>" + pojoError;
		scenarioDescription = scenarioDescription + error;
		log = "<b>Logs: </b><br><br>" + helper.writeLogtoReport();
		switch (event.getResult().getStatus()) {
		case PASSED:
			if(scenarioDescription.contains("Pojo Error: "))
				testFailed(event);
			else
				testPassedOrSkipped();
			break;
		case FAILED:
			testFailed(event);
			break;
		case SKIPPED:
			testPassedOrSkipped();
			break;
		}
		refreshObjects();
		extent.flush();
	}

}
