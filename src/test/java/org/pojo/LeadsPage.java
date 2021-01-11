package org.pojo;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utilities.BaseClass;

public class LeadsPage extends BaseClass {

	public LeadsPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='ADD LEAD']")
	private WebElement btnAddaLead;

	@FindBy(id = "firstName")
	private WebElement txtFirstName;

	@FindBy(id = "lastName")
	private WebElement txtLastName;

	@FindBy(id = "phNo")
	private WebElement txtPhoneNum;

	@FindBy(id = "email")
	private WebElement txtEmail;

	@FindBy(xpath = "//td[@data-handler='selectDay']")
	private List<WebElement> txtSelectDay;

	@FindBy(xpath = "//div[@class='ui-datepicker-title']")
	private WebElement txtMonth;

	@FindBy(xpath = "//a[@data-handler='next']")
	private WebElement btnNext;

	@FindBy(id = "scheduleRequestTime")
	private WebElement txtRequestTime;

	@FindBy(xpath = "//p[text()='Add Document']")
	private WebElement addDocument;

	@FindBy(xpath = "(//select[@class='styled-select documents'])[2]")
	private WebElement documentTypeDropDown;

	@FindBy(xpath = "//input[@id='multipleFileSelect-1']")
	private WebElement btnSelectFile;

	@FindBy(xpath = "//span[text()='Add To Lead']/parent::a")
	private WebElement btnAddToLead;

	@FindBy(xpath = "//input[@id='multipleFileSelect-1']")
	private WebElement addimages;

	@FindBy(xpath = "//button[@id='btn-addLeadsForm']")
	private WebElement btnSaveLead;

	@FindBy(xpath = "//input[@id='calender1']")
	private WebElement reqDate;

	@FindBy(xpath = "//div[@class='messages container']/ul/li")
	private WebElement getSuccessText;

	public WebElement getGetSuccessText() {
		return getSuccessText;
	}

	public WebElement getReqDate() {
		return reqDate;
	}

	public WebElement getBtnAddaLead() {
		return btnAddaLead;
	}

	public WebElement getTxtFirstName() {
		return txtFirstName;
	}

	public WebElement getTxtLastName() {
		return txtLastName;
	}

	public WebElement getTxtPhoneNum() {
		return txtPhoneNum;
	}

	public WebElement getTxtEmail() {
		return txtEmail;
	}

	public List<WebElement> getTxtSelectDay() {
		return txtSelectDay;
	}

	public WebElement getTxtMonth() {
		return txtMonth;
	}

	public WebElement getBtnNext() {
		return btnNext;
	}

	public WebElement getTxtRequestTime() {
		return txtRequestTime;
	}

	public WebElement getAddDocument() {
		return addDocument;
	}

	public WebElement getDocumentTypeDropDown() {
		return documentTypeDropDown;
	}

	public WebElement getBtnSelectFile() {
		return btnSelectFile;
	}

	public WebElement getBtnAddToLead() {
		return btnAddToLead;
	}

	public WebElement getAddimages() {
		return addimages;
	}

	public WebElement getBtnSaveLead() {
		return btnSaveLead;
	}

}
