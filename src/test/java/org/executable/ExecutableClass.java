package org.executable;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.pojo.HomePage;
import org.pojo.LeadsPage;
import org.pojo.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.utilities.BaseClass;

public class ExecutableClass extends BaseClass {
	static String day;
	static String month;
	static String year;

	@BeforeClass
	public void beforeClass() throws IOException {
		// get browser from property file and based on the setting launch the browser
		String browserName = fetchDataFromPropertiesFile("browser");
		if (browserName.equalsIgnoreCase("chrome")) {
			launchChromeBrowser();
		} else if (browserName.equalsIgnoreCase("firefox")) {
			launchFireFoxBrowser();
		}
		performimplicitWait(2000);
	}

	@BeforeMethod
	public void beforMethod() throws IOException {

		loadUrl(fetchDataFromPropertiesFile("url"));
		maxBrowser();
	}

	@Test(dataProvider = "Liida data", dataProviderClass = DataProviderClass.class)
	public void lidaaTest(String btnSalesTools, String btnBuildaProposal, String userId, String password,
			String firstName, String lastName, String email, String phoneNumber, String requestDate, String requestTime,
			String addDocument, String addImage) throws IOException {

		performLogin(userId, password);

		// Home Page
		HomePage homePage = new HomePage();
		btnClick(homePage.getHamburgerMenu());
		performimplicitWait(2000);
		if (btnSalesTools.equalsIgnoreCase("Sales Tools")) {
			btnClick(homePage.getLink_Sales());
		}
		if (btnBuildaProposal.equalsIgnoreCase("Build a Proposal")) {
			btnClick(homePage.getLink_BuildAProposal());
		}
		performimplicitWait(4000);
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click()", homePage.getLink_SelectLead());

		performLeadActions(firstName, lastName, email, phoneNumber, requestDate, requestTime, addDocument, addImage);

	}

	private void performLogin(String userId, String password) {
		// Login Page
		LoginPage loginPage = new LoginPage();
		btnClick(loginPage.getBtnSign());
		fill(loginPage.getTxtUserName(), userId);
		fill(loginPage.getTxtPassword(), password);
		btnClick(loginPage.getBtnLogin());
	}

	private void performLeadActions(String firstName, String lastName, String email, String phoneNumber,
			String requestDate, String requestTime, String addDocument, String addImage) throws IOException {
		// Add a Lead
		String monthYear;
		LeadsPage leadsPage = new LeadsPage();
		performimplicitWait(2000);

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click()", leadsPage.getBtnAddaLead());

		fill(leadsPage.getTxtFirstName(), firstName);
		fill(leadsPage.getTxtLastName(), lastName);
		fill(leadsPage.getTxtEmail(), email);
		fill(leadsPage.getTxtPhoneNum(), phoneNumber);
		String date = requestDate;
		String[] split = date.split("-");
		day = split[0];
		month = split[1];
		year = split[2];

		monthYear = month + " " + year;
		System.out.println(monthYear);
		btnClick(leadsPage.getReqDate());

		setDate(monthYear, day);

		WebElement txtRequestTime = leadsPage.getTxtRequestTime();
		String excelData = requestTime;
		System.out.println(excelData);
		performimplicitWait(2000);
		btnClick(txtRequestTime);
		Select s = new Select(txtRequestTime);
		List<WebElement> options = s.getOptions();
		for (WebElement element : options) {
			String text = element.getText();
			System.out.println("time" + text);
			if (text.equalsIgnoreCase(excelData)) {
				System.out.println("time enters" + excelData);
				element.click();
			}
		}

		// Add Document

		btnClick(leadsPage.getAddDocument());
		Select s1 = new Select(leadsPage.getDocumentTypeDropDown());
		performimplicitWait(3000);
		s1.selectByVisibleText("OTHER");
		performimplicitWait(3000);
		js.executeScript("document.getElementById('multipleFileSelect-1').click();");
		performimplicitWait(8000);
		Runtime.getRuntime().exec("C:\\Users\\Guru Nathan\\Desktop\\lida\\lida.exe");
		performimplicitWait(8000);
		btnClick(leadsPage.getBtnAddToLead());
		performimplicitWait(3000);

		btnClick(leadsPage.getBtnSaveLead());
		Assert.assertTrue(leadsPage.getGetSuccessText().getText().equalsIgnoreCase("Lead Saved Successfully"),
				"Check Lead");
	}

	@AfterClass
	public void afterClass() {
		driver.close();
	}

}
