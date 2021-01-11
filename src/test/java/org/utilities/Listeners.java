package org.utilities;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.IAnnotationTransformer;
import org.testng.IRetryAnalyzer;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.annotations.ITestAnnotation;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class Listeners extends BaseClass implements ITestListener, IAnnotationTransformer {
	
	ExtentReports report = null;
	ExtentTest test;
	ThreadLocal<ExtentTest> extentTest = null;

	// Constructor
	public Listeners()
	{
		report = ReporterTestng.prepareReport();
		extentTest = new ThreadLocal<ExtentTest>();
	}
	
	public void onTestStart(ITestResult result) {
		String methodName = result.getMethod().getMethodName();
		test = report.createTest(methodName);
		extentTest.set(test);
	}

	public void onTestSuccess(ITestResult result) {
		extentTest.get().log(Status.PASS, "Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		extentTest.get().log(Status.FAIL, "Test failed");
		extentTest.get().fail(result.getThrowable());
		String failed = result.getMethod().getMethodName();
		try {

			String captureScreenShot = captureScreenShot(failed);
			extentTest.get().addScreenCaptureFromPath(captureScreenShot, failed);
			extentTest.get().info("Browser=chrome");
		} catch (IOException e) {

			e.printStackTrace();
		}

	}

	public void onTestSkipped(ITestResult result) {
		test.info(result.getMethod().getMethodName() + "is skipped");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	public void onStart(ITestContext context) {
		// TODO Auto-generated method stub

	}

	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		report.flush();
	}

	public void transform(ITestAnnotation annotation, Class testClass, Constructor testConstructor, Method testMethod) {
		IRetryAnalyzer r = annotation.getRetryAnalyzer();
		if (r == null) {
			annotation.setRetryAnalyzer(Failed.class);
		}

	}
}
