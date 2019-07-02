package testing.otp.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.MobileElement;
//import io.appium.java_client.AppiumDriver;
//import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class commonUtils {

	//Define Capabilities variables here

	public static int EXPLICIT_WAIT;
	public static int DEFAULT_WAIT;
	public static int IMPLICIT_WAIT;
	public static String APPLICATION_APP; //APK PATH
	public static String BASE_PACKAGE;
	public static int APPIUM_SERVER_PORT;
	public static String APPLICATION_ACTIVITY; //EG. MainActivity
	public static String AUTOMATION_NAME;
	public static String BROWSER_NAME;
	public static String PLATFORM_NAME;
	public static String DEVICE_NAME;
	public static String PLATFORM_VERSION;
	public static String AUTO_GRANT_PERMISSIONS;
	public static int COMMAND_TIMEOUT;
	public static int DEVICE_TIMEOUT;
	public static String UDID;
	public static URL serverURL;
	public static String NO_RESET;
	private static IOSDriver<MobileElement> driver;
	private static Properties prop = new Properties();
	public static DesiredCapabilities capability = new DesiredCapabilities();

	public static void loadAndroidConfigProperties(String propertyFilename) throws IOException {

		//get property file from test/resources/properties
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+ "//src//test//resources//properties//" + propertyFilename);
		prop.load(fis);

		IMPLICIT_WAIT = Integer.parseInt(prop.getProperty("implicit.wait"));
		DEFAULT_WAIT = Integer.parseInt(prop.getProperty("default.wait"));
		EXPLICIT_WAIT = Integer.parseInt(prop.getProperty("explicit.wait"));
		APPLICATION_APP = prop.getProperty("application.path");
		BASE_PACKAGE = prop.getProperty("base.pkg");
		APPIUM_SERVER_PORT = Integer.parseInt(prop.getProperty("appium.server.port"));
		APPLICATION_ACTIVITY = prop.getProperty("application.activity");
		AUTOMATION_NAME = prop.getProperty("automation.name");
		BROWSER_NAME = prop.getProperty("browser.name");
		PLATFORM_NAME = prop.getProperty("platform.name");
		DEVICE_NAME = prop.getProperty("device.name");
		PLATFORM_VERSION = prop.getProperty("platform.version");
		COMMAND_TIMEOUT = Integer.parseInt(prop.getProperty("new.command.timeout"));
		DEVICE_TIMEOUT = Integer.parseInt(prop.getProperty("device.ready.timeout"));
		UDID = prop.getProperty("udid");
		NO_RESET = prop.getProperty("no.reset");
		AUTO_GRANT_PERMISSIONS = prop.getProperty("auto.grant.permissions");
	}


	public static void setAndroidCapabilities() {


		capability.setCapability(MobileCapabilityType.PLATFORM_VERSION,
				commonUtils.PLATFORM_VERSION);
		capability.setCapability(MobileCapabilityType.PLATFORM_NAME,
				commonUtils.PLATFORM_NAME);
		capability.setCapability(MobileCapabilityType.DEVICE_NAME,
				commonUtils.DEVICE_NAME);
		capability.setCapability(MobileCapabilityType.AUTOMATION_NAME,
				commonUtils.AUTOMATION_NAME);
		capability.setCapability(MobileCapabilityType.APP, commonUtils.APPLICATION_APP);
		capability.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT,
				commonUtils.COMMAND_TIMEOUT);
		capability.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY,
				commonUtils.APPLICATION_ACTIVITY);
		capability.setCapability(AndroidMobileCapabilityType.APP_PACKAGE,
				commonUtils.BASE_PACKAGE);
		capability.setCapability(MobileCapabilityType.UDID,
				commonUtils.UDID);
		capability.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS,
				commonUtils.AUTO_GRANT_PERMISSIONS);
		capability.setCapability(MobileCapabilityType.NO_RESET, commonUtils.NO_RESET);

		System.out.println(capability.toJson());

	}

	public static void loadiOSConfigProperties(String propertyFilename) {



	}

	public static void setiOSCapabilities() {



	}

	public static AppiumDriverLocalService AppiumService() {

		AppiumDriverLocalService service = AppiumDriverLocalService//.buildDefaultService();
				.buildService(new AppiumServiceBuilder()
						.usingDriverExecutable(new File("/usr/bin/node"))
						.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js")));

		return service;
	}

	public static AndroidDriver<AndroidElement> getAndroidDriver() throws MalformedURLException {

		System.out.println("===============================================");
		System.out.println("Inside commonUtils.getAndroidDriver");
		serverURL = new URL("http://0.0.0.0:" + APPIUM_SERVER_PORT + "/wd/hub");	
		System.out.println("serverURL setup successfully :" + serverURL);
		System.out.println("Setting up Android Driver");
		AndroidDriver<AndroidElement> driver = new AndroidDriver<AndroidElement>(serverURL, capability);
		System.out.println("Setup Android Driver Successfully : " + driver.toString());
		driver.manage().timeouts().implicitlyWait(commonUtils.IMPLICIT_WAIT, TimeUnit.SECONDS);
		return driver;

	}

	public static IOSDriver<MobileElement> getIOSDriver(){
		//iOS driver setup to be done

		return driver;
	}

}
