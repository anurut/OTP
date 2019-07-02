package testing.otp.base;

import testing.otp.base.ScreenBase;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener implements ITestListener{


	@Override
	public void onTestStart(ITestResult result) {
		String passFailMethod = result.getMethod().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
		System.out.println(passFailMethod + ": Started");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		String destinationDirectory = System.getProperty("user.dir")+ "/src/test/resources/screenshots/success";
		String passFailMethod = result.getMethod().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();

		System.out.println();
		System.out.println("Test case : " + passFailMethod + " passed");
		System.out.println();

		//Call takeScreenshot method on test fail
		File screenShot = ScreenBase.takeScreenShot();

		//Insert Screen Shot in TestNG report
		try {
			//test.addScreenCaptureFromPath(ScreenBase.insertScreenShotInTestNGReport(screenShot, passFailMethod, destinationDirectory));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onTestFailure(ITestResult result) {

		String destinationDirectory = System.getProperty("user.dir")+ "/src/test/resources/screenshots/failure";
		String failMethod = result.getMethod().getRealClass().getSimpleName() + "." + result.getMethod().getMethodName();
		
		System.out.println();
		System.out.println("Test case : " + failMethod + " failed");
		System.out.println();

		//Call takeScreenshot method on test fail
		File screenShot = ScreenBase.takeScreenShot();

		//Insert Screen Shot in TestNG report
		try {
			ScreenBase.insertScreenShotInTestNGReport(screenShot, failMethod, destinationDirectory);
		} catch (NullPointerException n) {
			n.printStackTrace();
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		 System.out.println("Test case skipped : " + result.getMethod().getMethodName());
	}

	@Override
	public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onStart(ITestContext context) {
		

	}

	@Override
	public void onFinish(ITestContext context) {
	
	}



}
