package testing.otp.screens;


import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import testing.otp.base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;

public class EnterOTPScreen extends ScreenBase{

	public EnterOTPScreen(AndroidDriver<AndroidElement> driver) {
		super(driver);
		
	}
			
		//To enter OTP fetched from the notification panel
		public EnterOTPScreen enterOTP(String otp) {

			try {
				AndroidElement otpBox = driver.findElement(By.id("com.makemytrip:id/otp_edit_text"));
				if (otpBox.isDisplayed()) {

					System.out.println("");
					System.out.println("--------- Entering OTP ---------");

					if(otp != null) {
						
						otpBox.sendKeys(otp);
					}
				}

			} catch (NoSuchElementException e) {

				System.out.println("OTP textbox not displayed, are you on the right page?");

			}
			return this;
		}

		//Click submit button after entering otp
		public void clickSubmitButton() {

			System.out.println("");
			System.out.println("--------- clicking submit button ---------");
			List<AndroidElement> submitButton = driver.findElements(By.className("android.widget.ImageView"));
			submitButton.get(1).click();

		}
}
