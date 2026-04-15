package extentReport;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class ExtentManager {

    public static ExtentReports createInstance(String fileName) {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(fileName);
        htmlReporter.config().setDocumentTitle("Automation Test Results");
        htmlReporter.config().setReportName("Cucumber Test Report");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);

        return extent;
    }
}
