package testing.otp.screens;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.openqa.selenium.By;
import org.testng.Assert;

import testing.otp.base.ScreenBase;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import testing.otp.screens.EnterPhoneNumberScreen;

public class NotificationScreen extends ScreenBase{

	public NotificationScreen(AndroidDriver<AndroidElement> driver) {
		super(driver);
		
	}
	
	//To clear all notifications, so that old OTP messages are cleared
	public EnterPhoneNumberScreen clearNotification() throws InterruptedException {

		System.out.println("");
		System.out.println("---------- Inside clearNotification() ----------");

		openNotifications();
		System.out.println("---------- Opened Notifications ----------");

		try {
			AndroidElement notif = driver.findElementById("com.android.systemui:id/dismiss_text");
			
			if (notif.isDisplayed()) {
				notif.click();
				System.out.println("---- Clicked on clear all button ----");
				return new EnterPhoneNumberScreen(driver);
			} else if(!notif.isEnabled()) {
				System.out.println("No notifications to clear, going back");
				clickBackButton();
				return new EnterPhoneNumberScreen(driver);
			}
		} catch (Exception e) {
			System.out.println(e);
			System.out.println("No notifications to clear, going back");
			clickBackButton();
		}
		return new EnterPhoneNumberScreen(driver);
	}


	//Loop to fetch OTP from notification
	private String OTPloop(int size, List<AndroidElement> element) {
		System.out.println("Inside OTP Loop method");
		for (int i = 0; i < size; i++) {
			System.out.println("Current position = " + i);
			if (element.get(i).getText().contains("is the OTP")) {
				return element.get(i).getText();
			}
		}
		return "";
	}

	//To extract OTP using Regex
	private String extractOTP(String OTP) {
		
		Pattern p = Pattern.compile("\\d+");
		Matcher m = p.matcher(OTP);
		
		while(m.find()) {

			System.out.println(m.group().length());
			System.out.println(m.group());

			if(m.group().length()==4) {
				System.out.println("The OTP is: " + m.group());
				return m.group();
			}
		}return "";
	}

	// To retrieve OTP from the notifications
	public String getOTP() throws InterruptedException {
		
		
		String OTP = new String();
		
		try {
		System.out.println("");
		System.out.println("---------- Inside getOTP() ----------");

		openNotifications();

		Thread.sleep(3000);

		List<AndroidElement> messageText = driver.findElements(By.className("android.widget.TextView"));
		int Size = messageText.size();
		System.out.println("Size =" + Size);

		for(int i=0; i<=3; i++) {
			
			Thread.sleep(2000);
			if(OTP.length()==0) {
				OTP = OTPloop(Size, messageText);
			}else {
				System.out.println("Found the OTP, Yay!!!");
				break;
			}
		}	

		if(OTP.length()<4) {
			System.out.println("---- Failed to retrieve OTP ----");
			clickBackButton();
			return "";
		}else {
			OTP = extractOTP(OTP);
		}
		
		if(OTP.length()==0) {
			Assert.fail("OTP not received");
		}else {

			System.out.println("OTP is: " +  OTP);
		}
		
		clickBackButton();

		System.out.println("");
		System.out.println("---------- Exiting getOTP ----------");
		
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
		return OTP;
	}

}
