
package org.utilities;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ReporterTestng {
	public static ExtentReports extent;

	/// Prepare report
	public static ExtentReports prepareReport() {
		// Get report path
		String path = System.getProperty("user.dir") + "\\ErrorScreenshot\\lida.html";
		
		// Prepare Report
		ExtentSparkReporter extentReport = new ExtentSparkReporter(path);
		extentReport.config().setReportName("Liida Vega");
		extentReport.config().setDocumentTitle("Liida vega Result");

		extent = new ExtentReports();
		extent.attachReporter(extentReport);
		extent.setSystemInfo("Tester", "Nirmala");
		return extent;

	}
}
