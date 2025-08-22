package BR.example.utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.time.LocalDateTime;

public class ExtentReportManager {

    private static ExtentReports extent;

    public static ExtentReports getReportInstance() {

        if (extent == null) {
            ExtentSparkReporter reporter = new ExtentSparkReporter("test-reports/TripleValueScoopsCupFlow.html");
            reporter.config().setReportName("BaskinRobbin Web Automation Report");
            reporter.config().setDocumentTitle("Test Results");
            reporter.config().setTheme(Theme.STANDARD);
            reporter.config().setDocumentTitle("Automation Test Report");
            reporter.config().setReportName("Regression Test Suite");
            reporter.config().setTimeStampFormat("EEEE, MMMM dd, yyyy, hh:mm a '('zzz')'");
            reporter.config().setEncoding("utf-8"); // Optional: encoding format

            extent = new ExtentReports();
            extent.attachReporter(reporter);
            extent.setSystemInfo("Tester", "John");
            extent.setSystemInfo("Environment", "QA (DEV)");
            extent.setSystemInfo("Browser", "Chrome");
            extent.setSystemInfo("Browser Version", "138.0");
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("OS Version", System.getProperty("os.version"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Selenium Version", "4.27.0");
            extent.setSystemInfo("Maven Version", "3.9.6");
            extent.setSystemInfo("Build Tool", "Maven");
            extent.setSystemInfo("Framework", "TestNG"); // or JUnit
            extent.setSystemInfo("Execution Type", "Regression Suite");
            extent.setSystemInfo("Run Mode", "Headless");
            extent.setSystemInfo("Execution Time", LocalDateTime.now().toString());
        }

        return extent;
    }
}
