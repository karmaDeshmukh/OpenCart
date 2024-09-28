package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistrationPage extends BasePage {

	public AccountRegistrationPage(WebDriver driver) {
		super(driver);
	}
	
@FindBy(xpath="//input[@id='input-firstname']")
WebElement txtFirstName;

@FindBy(xpath="//input[@id='input-lastname']")
WebElement txtLastName;

@FindBy(xpath="//input[@id='input-email']")
WebElement txtEmail;

@FindBy(xpath="//input[@id='input-telephone']")
WebElement txtTelephone;

@FindBy(xpath="//input[@id='input-password']")
WebElement txtPassword;

@FindBy(xpath="//input[@id='input-confirm']")
WebElement txtConformPassword;

@FindBy(xpath="//input[@name='agree' and @type='checkbox']")
WebElement chkPolicy;

@FindBy(xpath="//input[@value='Continue' and @type='submit']")
WebElement btnContinue;

@FindBy(xpath="//div[@id='content']/h1")
WebElement msgConformation;

public void setFirstName(String fName) {
	txtFirstName.sendKeys(fName);
}

public void setLastName(String lName) {
	txtLastName.sendKeys(lName);
}

public void setEmail(String email) {
	txtEmail.sendKeys(email);
}

public void setTelephone(String telephone) {
	txtTelephone.sendKeys(telephone);
}

public void setPassword(String password) {
	txtPassword.sendKeys(password);
}

public void setConformPassword(String conformPassword) {
	txtConformPassword.sendKeys(conformPassword);
}

public void clickCheckPolicy() {
	chkPolicy.click();
}

public void clickContinue() {
	//sol1
	btnContinue.click();
	
	//sol2
	//btnContinue.click();
	
	//sol3
	//Actions ac = new Actions(driver);
	//ac.moveToElement(btnContinue).click().perform();
	
	//sol4
	//JavascriptExecutor js=(JavascriptExecutor)driver;
	//js.executeScript("arguments[0].click();", btnContinue);
	
	//sol5
	//btnContinue.sendKeys(Keys.RETURN);
	
	//sol6
	//WebDriverWait myWaitObj=new WebDriverWait(driver, Duration.ofSeconds(10));
	//myWaitObj.until(ExpectedConditions.elementToBeClickable(btnContinue)).click();
}

public String getConfirmationMsg() {
	try {
		return (msgConformation.getText());
	} catch (Exception e) {
		// TODO: handle exception
		return (e.getMessage());
	}
}




}