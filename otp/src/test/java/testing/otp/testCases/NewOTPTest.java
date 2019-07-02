package testing.otp.testCases;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import testing.otp.base.TestBase;
import testing.otp.screens.EnterOTPScreen;
import testing.otp.screens.EnterPhoneNumberScreen;
import testing.otp.screens.NotificationScreen;

import org.testng.annotations.BeforeTest;

public class NewOTPTest extends TestBase{

	@Test(priority=1)
	public void enterPhoneNumber() throws InterruptedException {
		pcNotification = new NotificationScreen(driver);
		pcNotification.clearNotification();
		
		pcEnterPhoneNo = new EnterPhoneNumberScreen(driver);
		
		//Enter phone number at which you want to get the OTP
		pcEnterPhoneNo.enterPhoneNumber("Enter phone number here");
		pcEnterPhoneNo.clickSubmitButton();
	}

	@Test(priority=2)
	public void enterOTPReceived() throws InterruptedException {

		pcEnterOTP = new EnterOTPScreen(driver);

		String otp = pcNotification.getOTP();

		if(otp.contentEquals("")) {
			System.out.println("Waiting for 20 seconds to manually enter OTP");
			Thread.sleep(20000);
			pcEnterOTP.clickSubmitButton();
		}else {
			pcEnterOTP.enterOTP(otp);
			pcEnterOTP.clickSubmitButton();
		}
	}


	@BeforeTest
	public void init() {
		softAssertion = new SoftAssert();
	}


}
