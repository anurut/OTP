package testing.otp.screens;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;

import testing.otp.base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;


public class EnterPhoneNumberScreen extends ScreenBase{

	public EnterPhoneNumberScreen(AndroidDriver<AndroidElement> driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}


	private void goToEnterPhoneNumberTextBox() {
		try {

			AndroidElement enterMobile = driver.findElement(By.id("com.makemytrip:id/inputs_content"));
			if (enterMobile.isDisplayed()) {

				System.out.println("");
				System.out.println("--------- Going to Enter Mobile Number ---------");

				enterMobile.click();
			}

		} catch (NoSuchElementException e) {

			System.out.println("Enter Mobile NO. not displayed, are you on the right page?");
		}

	}

	//Returns a String number according to the screen displayed
	private String whichScreen() {

		try {
			if(driver.findElement(By.id("com.makemytrip:id/inputs_content")).isDisplayed()){
				System.out.println();
				System.out.println("Found Screen 1");
				return "1";
			}
		} catch (NoSuchElementException e) {
			System.out.println();
			System.out.println("Screen 1 not displayed " + e);
		}

		try {

			if(driver.findElement(By.id("com.makemytrip:id/btn_login")).isDisplayed()){
				System.out.println();
				System.out.println("Found Screen 2");
				return "2";
			}
		} catch (NoSuchElementException e) {
			System.out.println();
			System.out.println("Screen 2 not displayed " + e);
		}

		try {

			if(driver.findElement(By.id("com.makemytrip:id/bb_home")).isDisplayed()){
				System.out.println();
				System.out.println("Found Screen 3");
				return "3";
			}
		} catch (NoSuchElementException e) {
			System.out.println();
			System.out.println("Screen 3 not displayed " + e);
		}

		try {

			if(driver.findElement(By.id("com.makemytrip:id/loginButton")).isDisplayed()){
				System.out.println();
				System.out.println("Found Screen 4");
				return "4";
			}
		} catch (NoSuchElementException e) {
			System.out.println();
			System.out.println("Screen 4 not displayed " + e);
		}
		return "";
	}



	//To enter phone number 
	public EnterPhoneNumberScreen enterPhoneNumber(String phNumber) {

		switch (whichScreen()) {
		case "1":
			goToEnterPhoneNumberTextBox();
			break;

		case "2":
			driver.findElement(By.id("com.makemytrip:id/btn_login")).click();
			break;

		case "3":
			AndroidElement mmtButton = driver.findElement(By.id("com.makemytrip:id/my_profile_icon"));
			mmtButton.click();
			List<AndroidElement> loginButton = driver.findElements(By.className("android.widget.TextView"));
			AndroidElement login = getListElement("LOGIN", loginButton);
			login.click();
			break;

		case "4":
			driver.findElement(By.id("com.makemytrip:id/loginButton")).click();
			break;

		default:
			System.out.println("None of the expected screens were displayed");
			break;
		}	
		AndroidElement phNo = driver.findElement(By.className("android.widget.EditText"));
		if (phNo.isDisplayed()) {

			System.out.println("");
			System.out.println("--------- Entering phone number ---------");

			phNo.sendKeys(phNumber);
		}
		return this;
	} 

	//Click the Arrow button next to the text box
	public EnterOTPScreen clickSubmitButton() {

		try {
			AndroidElement arrowButton = driver.findElement(By.id("com.makemytrip:id/ivNext"));
			System.out.println("");
			System.out.println("--------- clicking submit button ---------");
			System.out.println("Submit Button: " + arrowButton.getText());
			arrowButton.click();
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}

		try {
			AndroidElement createNewAccount = driver.findElement(By.id("com.makemytrip:id/btn_create_acc"));

			if(createNewAccount.isDisplayed()) {
				createNewAccount.click();
			}else Assert.fail();
		} catch (NoSuchElementException e) {
			System.out.println(e);
		}

		return new EnterOTPScreen(driver);

	}
}
