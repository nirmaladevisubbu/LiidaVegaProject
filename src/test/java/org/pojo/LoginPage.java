package org.pojo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utilities.BaseClass;

public class LoginPage extends BaseClass {
	public LoginPage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//a[text()='Sign In']")
	private WebElement btnSign;

	@FindBy(id = "j_username")
	private WebElement txtUserName;

	@FindBy(id = "j_password")
	private WebElement txtPassword;

	@FindBy(id = "loginSubmit")
	private WebElement btnLogin;

	public WebElement getBtnSign() {
		return btnSign;
	}

	public WebElement getTxtUserName() {
		return txtUserName;
	}

	public WebElement getTxtPassword() {
		return txtPassword;
	}

	public WebElement getBtnLogin() {
		return btnLogin;
	}

}
