package test;

import java.util.List;

import org.junit.*;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class DriverSeleniumTest {

	static WebDriver wDriver;

	@BeforeClass
	public static void driverLoginPage() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wDriver = new ChromeDriver();

	}

	@Test
	public void loginTest() throws InterruptedException {
		wDriver.get("http://localhost:8080/Comet_Cab/driverLogin.jsp");
		Assert.assertEquals("Driver Login Page", wDriver.getTitle());
		Thread.sleep(500);
		WebElement username = wDriver.findElement(By.id("username"));
		username.sendKeys("5");
		WebElement password = wDriver.findElement(By.id("password"));
		password.sendKeys("pete");
		WebElement login = wDriver.findElement(By.xpath("//html/body/form/input[3]"));
		Thread.sleep(1000);
		login.click();
		Thread.sleep(1000);
		Assert.assertEquals("Welcome User", wDriver.getTitle());
		Thread.sleep(1000);
	}

	@Test
	public void getRequestTest() throws InterruptedException {

		// get the requests assigned to user
		wDriver.findElement(By.id("getRequest")).click();
		Thread.sleep(5000);

		// Start ride
		wDriver.findElement(By.id("startRide")).click();
		Thread.sleep(5000);
		String startRideModal = wDriver.findElement(By.id("start")).getText();
		System.out.println(startRideModal);
		Assert.assertEquals(true, startRideModal.toLowerCase().contains("Started the ride".toLowerCase()));
		wDriver.findElement(By.xpath("//*[@id=\"startRideModal\"]/div/div/div[3]/button")).click();
		Thread.sleep(5000);

		// end ride
		wDriver.findElement(By.id("endRide")).click();
		Thread.sleep(5000);
		String endRideModal = wDriver.findElement(By.id("end")).getText();
		System.out.println(endRideModal);
		Assert.assertEquals(true, endRideModal.toLowerCase().contains("Ended the ride".toLowerCase()));
		wDriver.findElement(By.xpath("//*[@id=\"endRideModal\"]/div/div/div[3]/button")).click();
		Thread.sleep(5000);

	}

	@AfterClass
	public static void closePage() {
		wDriver.quit();
	}

}
