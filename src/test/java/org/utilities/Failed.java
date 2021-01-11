package org.utilities;

import java.io.IOException;

import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Failed extends BaseClass implements IRetryAnalyzer {
	ExtentReports report = null;
	ExtentTest test;
	int min = 0, max = 3;
	
	public Failed()
	{
		report = ReporterTestng.prepareReport();
	}
	

	public boolean retry(ITestResult result) {
		String failed = result.getName();
		try {
			captureScreenShot(failed);
		} catch (IOException e) {
			
			e.printStackTrace();
		}
		if (min < max) {

			min++;
			return true;
		}
		return false;
	}

	
}
