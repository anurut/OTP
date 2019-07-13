package testing.otp.base;

import java.io.IOException;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.asserts.SoftAssert;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import testing.otp.screens.EnterOTPScreen;
import testing.otp.screens.EnterPhoneNumberScreen;
import testing.otp.screens.NotificationScreen;
import testing.otp.utils.commonUtils;


@Listeners({TestListener.class})
public class TestBase {


	public static EnterPhoneNumberScreen pcEnterPhoneNo;
	public static EnterOTPScreen pcEnterOTP;
	public static NotificationScreen pcNotification;
	
	public static AndroidDriver<AndroidElement> driver;

	public static String loadPropertyFile = "Android_Motorola_mmt.properties";

	public SoftAssert softAssertion;

	@BeforeSuite
	public void setUp() throws IOException {


		//load properties file, desired capabilities, initialize Android driver
		if(driver == null) {

			if (loadPropertyFile.startsWith("Android")){

				//load Android properties
				System.out.println("Inside TestBase.setUP method");
				commonUtils.loadAndroidConfigProperties(loadPropertyFile);
				System.out.println("Loaded property file");
				commonUtils.setAndroidCapabilities();
				System.out.println("Setup AndroidCapabilities");
				driver = commonUtils.getAndroidDriver();
				System.out.println("Got Android Driver");

			}else if (loadPropertyFile.startsWith("iOS")) {

				//load iOS properties
			}
		}
	}

	@AfterSuite
	public void tearDown() throws InterruptedException {
		Thread.sleep(3000);
		driver.quit();
		//service.stop();
	}
}
