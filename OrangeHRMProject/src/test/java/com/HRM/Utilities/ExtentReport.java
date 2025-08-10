package com.HRM.Utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReport {
	private ExtentReports report;
	private ExtentSparkReporter  htmlReport;
	private ExtentTest test;
	
	public ExtentReport() {
		this.htmlReport = new ExtentSparkReporter("AutomationReport.html");
		this.report = new ExtentReports();
		report.attachReporter(htmlReport);
	}
	
	public void InitialConfig() {
		//htmlReport.config().setReportName("Automation Results");
		htmlReport.config().setDocumentTitle("TestCase results");
		htmlReport.config().setTimeStampFormat("dd-MMMM-YYYY");		
		report.setSystemInfo("Project Name", "OrangeHRMProject");
		report.setSystemInfo("User","Shari");
		report.setSystemInfo("Browser","Google Chrome");
	}
	
	public void generateReport(String TestCaseName, String TestStatus) {
		//InitialConfig();
		test = report.createTest(TestCaseName);
		if(TestStatus.equalsIgnoreCase("Pass")) {
			test.log(Status.PASS, "Login Successful");
		}
		else {
			test.log(Status.FAIL, "Login Failed");
		}
			
		report.flush();
	}
	

}
