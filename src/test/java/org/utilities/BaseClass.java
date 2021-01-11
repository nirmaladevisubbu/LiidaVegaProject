package org.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.pojo.LeadsPage;
import org.testng.Reporter;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseClass {
	public static WebDriver driver = null;

	// Initialize chrome browser
	public static WebDriver launchChromeBrowser() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		return driver;
	}

	// Initialize Firefox browser
	public static WebDriver launchFireFoxBrowser() {
		WebDriverManager.firefoxdriver().setup();

		return (driver == null) ? driver = new FirefoxDriver() : driver;
	}

	// get configuration details from property file
	public static String fetchDataFromPropertiesFile(String name) throws IOException {
		Properties prop = new Properties();
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\test\\resources\\data.properties");
		prop.load(fis);
		String property = prop.getProperty(name);
		System.out.println(property);
		return property;
	}

	// load url
	public static void loadUrl(String url) {

		driver.get(url);
	}

	// utility method for implicit wait
	public static void performimplicitWait(int time) {
		driver.manage().timeouts().implicitlyWait(time, TimeUnit.MILLISECONDS);
	}

	// helper method to maximize browser
	public static void maxBrowser() {
		driver.manage().window().maximize();
	}

	// helper method to fill the data to control
	public static WebElement fill(WebElement element, String text) {
		element.sendKeys(text);
		return element;
	}

	// helper method click button
	public static void btnClick(WebElement element) {
		element.click();
	}

	// helper method to capture screenshot
	public static String captureScreenShot(String methodName) throws IOException {
		TakesScreenshot tk = (TakesScreenshot) driver;
		File src = tk.getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "/ErrorScreenshot/" + methodName + ".png";
		File desti = new File(dest);
		FileUtils.copyFile(src, desti);
		Reporter.log("<br><img src='" + desti + "' height='400' width='800'/><br>");
		Reporter.log("Browser=chrome");
		Reporter.log("Sprint-1");
		return dest;
	}

	// closes the browser
	public static void browserClose() {
		driver.close();
	}

	// get the dynamic data from excel file 
	public static String excelData(int rowNo, int columnNo) throws IOException {
		
		// excel path.. The path is mentioned in the property file.
		String testDataLocation = System.getProperty("user.dir") + fetchDataFromPropertiesFile("testdata");
		File f = new File(testDataLocation);
		FileInputStream finStream = new FileInputStream(f);

		// read excel sheet and get data
		Workbook w = new XSSFWorkbook(finStream);
		Sheet s = w.getSheet("Sheet1");
		{
			Row r = s.getRow(rowNo);
			{
				Cell c = r.getCell(columnNo);
				String value = "";
				int cellType = c.getCellType();
				if (cellType == 1) {
					value = c.getStringCellValue();
				} else if (DateUtil.isCellDateFormatted(c)) {
					Date d = c.getDateCellValue();
					SimpleDateFormat sim = new SimpleDateFormat("dd-MM-yyyy");
					value = sim.format(d);

				} else {
					double d = c.getNumericCellValue();
					long l = (long) d;
					value = String.valueOf(l);
				}
				return value;

			}

		}
	}

	// helper method to open up date time picker and set date
	public static void setDate(String passText, String day) {
		LeadsPage leadsPage = new LeadsPage();
		while (!leadsPage.getTxtMonth().getText().equalsIgnoreCase(passText)) {
			btnClick(leadsPage.getBtnNext());
			performimplicitWait(2000);
		}
		List<WebElement> txtSelectDay = leadsPage.getTxtSelectDay();

		for (WebElement dayElement : txtSelectDay) {
			String value = dayElement.getText();
			if (value.equalsIgnoreCase(day)) {
				btnClick(dayElement);
				performimplicitWait(2000);
			}
		}

	}
}
