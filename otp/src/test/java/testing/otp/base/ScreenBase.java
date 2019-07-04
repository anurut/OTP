package testing.otp.base;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import testing.otp.utils.commonUtils;;

public class ScreenBase {

	//Variables
	public static AndroidDriver<AndroidElement> driver;
	public static WebDriverWait wait;

	//Constructor
	public ScreenBase(AndroidDriver<AndroidElement> driver) {

		ScreenBase.driver = driver;
		wait = new WebDriverWait(driver, 20);
		loadElements();//for @AndroidFindBy
	}

	//Load elements using PageFactory
	public void loadElements() {

		PageFactory.initElements(new AppiumFieldDecorator(driver), this);

	}

	//To hide the keyboard
	public static void hideKeyboard() {

		driver.hideKeyboard();
	}

	//To open notification
	public static void openNotifications() {

		driver.openNotifications();
	}


	//Explicit wait
	public static void waitForElement(String locator) {
		wait = new WebDriverWait(driver, commonUtils.EXPLICIT_WAIT);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id(locator)));

	}

	//get specific item from a list
	public AndroidElement getListElement(String elementName, List<AndroidElement> listElement){

		for(int i=0; i<listElement.size();i++) {

			if (listElement.get(i).getText().contains(elementName)) {
				System.out.println("*Got* " + listElement.get(i).getText());
				return listElement.get(i);
			}
		}
		return null;
	}


	//To click hardware back button
	public void clickBackButton() {
		driver.pressKey(new KeyEvent(AndroidKey.BACK));
	}

	//Capture screenshot
	public static File takeScreenShot() {

		//Capturing screenshot
		File screenshotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		return screenshotFile;

	}

	//Include screenshot in TestNG report
	public static String insertScreenShotInTestNGReport(File screenShot, String passFailMethod, String destinationDirectory) {
		
		DateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy__hh_mm_ssaa");
		
		//Create folder to store screenshots
		new File(destinationDirectory).mkdirs();

		//Set file name
		String destinationFile = passFailMethod + " - " + dateFormat.format(new Date()) + ".png";

		//Store file at destinationFolder
		try {
			File screenShotName = new File(destinationDirectory + "/" + destinationFile);
			FileUtils.copyFile(screenShot, screenShotName);
			String filePath = screenShotName.toString();

			System.out.println(filePath);
			//Add screenshot to TestNG report
			String path = "<br><img src = '" + filePath + "' height = '400' /><br>";
			Reporter.log(path);
			
			return filePath;
		}catch (IOException e) {
			e.printStackTrace();
			return null;
		}	
	}
}