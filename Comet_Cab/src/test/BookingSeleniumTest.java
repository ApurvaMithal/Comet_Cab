package test;

import java.util.List;

import org.junit.*;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

public class BookingSeleniumTest {

	static WebDriver wDriver;

	@BeforeClass
	public static void customerLoginPage() throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
		wDriver = new ChromeDriver();

	}

	@Test
	public void loginTest() throws InterruptedException {
		wDriver.get("http://localhost:8080/Comet_Cab/customerLogin.jsp");
		Assert.assertEquals("Login Page", wDriver.getTitle());
		Thread.sleep(5000);
		WebElement username = wDriver.findElement(By.id("username"));
		username.sendKeys("axm174531");
		WebElement password = wDriver.findElement(By.id("password"));
		password.sendKeys("apurva");
		WebElement login = wDriver.findElement(By.xpath("//html/body/form/input[3]"));
		Thread.sleep(5000);
		login.click();
		Thread.sleep(5000);
		Assert.assertEquals("Welcome User", wDriver.getTitle());
	}

	@Test
	public void reserveBookingTest() throws InterruptedException {
		List<WebElement> options = null;

		// Case 1: pick, drop, cabType are mandatory
		// If cab type not specified it throws error

		// Enter pick details
		wDriver.findElement(By.id("pick")).click();
		Thread.sleep(5000);
		options = wDriver.findElements(By.xpath("//body/div[1]/div[2]/div/ul[@class='dropdown-menu']//li/a"));
		for (WebElement opt : options) {
			String val = opt.getText();
			if (val.equals("UTD")) {
				System.out.println("pick " + val);
				opt.click();
				break;
			}
		}
		Thread.sleep(5000);

		// Enter drop details
		wDriver.findElement(By.id("drop")).click();
		Thread.sleep(5000);
		options = wDriver.findElements(By.xpath("//body/div[2]/div[2]/div/ul[@class='dropdown-menu']//li/a"));
		for (WebElement opt : options) {
			String val = opt.getText();
			if (val.equals("UTD")) {
				System.out.println("drop " + val);
				opt.click();
				break;
			}
		}
		Thread.sleep(5000);

		// On clicking Reserve Button, it throws error: Select a CabType
		wDriver.findElement(By.id("reserveBooking")).click();
		Thread.sleep(5000);
		String reserveModal = wDriver.findElement(By.id("reserve")).getText();
		System.out.println(reserveModal);
		Assert.assertEquals(true, reserveModal.toLowerCase().contains("Select a ".toLowerCase()));
		wDriver.findElement(By.xpath("//*[@id=\"reserveBookingModal\"]/div/div/div[3]/button")).click();
		Thread.sleep(5000);

		// Now Enter Cab Type
		wDriver.findElement(By.id("cab")).click();
		Thread.sleep(5000);
		options = wDriver.findElements(By.xpath("//body/div[3]/div[2]/div/ul[@class='dropdown-menu']//li/a"));
		for (WebElement opt : options) {
			String val = opt.getText();
			if (val.equals("SUV")) {
				System.out.println("cab " + val);
				opt.click();
				break;
			}
		}
		Thread.sleep(5000);

		// Case 2: Pick location cannot be same as Drop Location
		wDriver.findElement(By.id("reserveBooking")).click();
		Thread.sleep(5000);
		reserveModal = wDriver.findElement(By.id("reserve")).getText();
		System.out.println(reserveModal);
		Assert.assertEquals(true,
				reserveModal.toLowerCase().contains("Pickup Location Cannot Be Same As Drop Location".toLowerCase()));
		wDriver.findElement(By.xpath("//*[@id=\"reserveBookingModal\"]/div/div/div[3]/button")).click();
		Thread.sleep(5000);

		// Re-Enter correct drop location
		wDriver.findElement(By.id("drop")).click();
		Thread.sleep(5000);
		options = wDriver.findElements(By.xpath("//body/div[2]/div[2]/div/ul[@class='dropdown-menu']//li/a"));
		for (WebElement opt : options) {
			String val = opt.getText();
			if (val.equals("SSN")) {
				System.out.println("drop " + val);
				opt.click();
				break;
			}
		}
		Thread.sleep(5000);

		
		// Case 3: Cab not available
		wDriver.findElement(By.id("reserveBooking")).click();
		Thread.sleep(5000);
		reserveModal = wDriver.findElement(By.id("reserve")).getText();
		System.out.println(reserveModal);
		Assert.assertEquals(true, reserveModal.toLowerCase().contains("Exception Occurred:".toLowerCase()));
		wDriver.findElement(By.xpath("//*[@id=\"reserveBookingModal\"]/div/div/div[3]/button")).click();
		Thread.sleep(5000);
		
		//Select another cab
		wDriver.findElement(By.id("cab")).click();
		Thread.sleep(5000);
		options = wDriver.findElements(By.xpath("//body/div[3]/div[2]/div/ul[@class='dropdown-menu']//li/a"));
		for (WebElement opt : options) {
			String val = opt.getText();
			if (val.equals("SEDAN")) {
				System.out.println("cab " + val);
				opt.click();
				break;
			}
		}
		Thread.sleep(5000);
		
		// Case 4: Card balance not sufficient
		wDriver.findElement(By.id("reserveBooking")).click();
		Thread.sleep(5000);
		reserveModal = wDriver.findElement(By.id("reserve")).getText();
		System.out.println(reserveModal);
		Assert.assertEquals(true, reserveModal.toLowerCase().contains("Exception Occurred:".toLowerCase()));
		wDriver.findElement(By.xpath("//*[@id=\"reserveBookingModal\"]/div/div/div[3]/button")).click();
		Thread.sleep(30000);
		//update card details from backend
		
		
		//Case 5: Successful Reservation
		wDriver.findElement(By.id("reserveBooking")).click();
		Thread.sleep(5000);
		reserveModal = wDriver.findElement(By.id("reserve")).getText();
		System.out.println(reserveModal);
		Assert.assertEquals(true, reserveModal.toLowerCase().contains("Estimated Fare Is: ".toLowerCase()));
		wDriver.findElement(By.xpath("//*[@id=\"reserveBookingModal\"]/div/div/div[3]/button")).click();
		Thread.sleep(5000);
		
		
		//Confirm Booking
		wDriver.findElement(By.id("confirmBooking")).click();
		Thread.sleep(5000);
		String confirmModal = wDriver.findElement(By.id("confirm")).getText();
		System.out.println(confirmModal);
		Assert.assertEquals(true, confirmModal.toLowerCase().contains("Booking Confirmed!!!".toLowerCase()));
		wDriver.findElement(By.xpath("//*[@id=\"confirmBookingModal\"]/div/div/div[3]/button")).click();
		Thread.sleep(30000);
		
		// reservation cancelled within one minute
		//Cancel Booking
		wDriver.findElement(By.id("cancelBooking")).click();
		Thread.sleep(2500);
		String cancelModal = wDriver.findElement(By.id("cancel")).getText();
		System.out.println(cancelModal);
		Assert.assertEquals(true, cancelModal.toLowerCase().contains("BOOKING CANCELLED".toLowerCase()));
		wDriver.findElement(By.xpath("//*[@id=\"cancelBookingModal\"]/div/div/div[3]/button")).click();
		Thread.sleep(3000);

	}

	@AfterClass
	public static void closePage() {
		wDriver.quit();
	}

}
