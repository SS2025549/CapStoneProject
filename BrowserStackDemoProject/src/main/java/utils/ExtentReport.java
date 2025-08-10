package utils;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
    private static ExtentReports extent;

    public static ExtentReports createInstance(String reportName) {
        ExtentSparkReporter spark = new ExtentSparkReporter("test-output/"+reportName+"_ExtentReport.html");
        spark.config().setReportName(reportName);
        spark.config().setDocumentTitle("Automation Report");
        extent = new ExtentReports();
        extent.attachReporter(spark);
        extent.setSystemInfo("Tester", "Shari");
        extent.setSystemInfo("Browser", "Google Chrome");
        return extent;
    }
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
        }
    }

}