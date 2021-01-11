package org.pojo;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.utilities.BaseClass;

public class HomePage extends BaseClass {
	public HomePage() {
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//i[@class='far fa-bars v2-hamburger-menu']")
	private WebElement hamburgerMenu;

	@FindBy(xpath = "//a[text()='Sales Tools']")
	private WebElement link_Sales;

	@FindBy(xpath = "//a[text()='Build a Proposal']")
	private WebElement link_BuildAProposal;

	@FindBy(xpath = "//*[text()='SELECT LEAD']")
	private WebElement link_SelectLead;

	public WebElement getHamburgerMenu() {
		return hamburgerMenu;
	}

	public WebElement getLink_Sales() {
		return link_Sales;
	}

	public WebElement getLink_BuildAProposal() {
		return link_BuildAProposal;
	}

	public WebElement getLink_SelectLead() {
		return link_SelectLead;
	}

}
