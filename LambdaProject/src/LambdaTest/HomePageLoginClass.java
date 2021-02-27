package LambdaTest;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;


public class HomePageLoginClass {
	WebDriver driver;
	
	
	@BeforeSuite
	public void settings() {
		System.setProperty("webdriver.chrome.driver", "/Users/mac/Desktop/Installers/chromedriver");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("https://www.lambdatest.com/automation-demos/");
		login("lambda", "lambda123");
		
	}
	
	
	
	@Test(priority = 1)
	public void populateEmailTest() {
		driver.findElement(By.id("developer-name")).sendKeys("onuorahmary@gmail.com");
		driver.findElement(By.id("populate")).click();
		driver.switchTo().alert().accept();
		
		
		WebElement toastMessage = driver.findElement(By.xpath("/html/body/div[3]"));
		
		String message = toastMessage.getText();
		System.out.println(message);
		
		new WebDriverWait(driver, 10).until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("/html/body/div[3]")));
		System.out.println("PASSED");
	}
	
	

	@Test(priority = 2)
	public void radioButtonTest() throws InterruptedException {
		WebElement radio1 = driver.findElement(By.id("3months"));
		radio1.click();
		
		Thread.sleep(2000);
		WebElement radio2 = driver.findElement(By.id("6months"));
		radio2.click();
	}
	
	
	
	@Test(priority = 3)
	public void checkBoxTest() {
		WebElement checkbox1 = driver.findElement(By.xpath("//*[@id=\"customer-service\"]"));
		checkbox1.click();
		
		WebElement checkbox2 = driver.findElement(By.xpath("//*[@id=\"delivery-time\"]"));
		checkbox2.click();
	}
	
	
	
	@Test(priority = 4)
	public void dropdownTest() {
		WebElement dropdown = driver.findElement(By.id("preferred-payment"));
		Select option = new Select(dropdown);
		
		option.selectByIndex(2);
		
		WebElement checkfeedback = driver.findElement(By.xpath("//*[@id=\"tried-ecom\"]"));
		checkfeedback.click();
	}
	
	
	
	@Test(priority = 5)
	public void feedbackTest() {
		driver.findElement(By.xpath("//*[@id=\"comments\"]")).sendKeys("This is my feedback");
	}
	
	
	
	@Test(priority = 6)
	public void ratingScaleTest() {
		
		Actions actions = new Actions(driver);
		WebElement slider = driver.findElement(By.xpath("//*[@id=\"slider\"]/span"));
		
		actions.dragAndDropBy(slider, 500, 0).perform();
		
//		WebElement sliderValue = driver.findElement(By.xpath("/html/body/div[2]/section[3]/div/div[2]/div[2]/div/div[2]/div[9]"));
		
	}
	
	
	
	@Test(priority = 7)
	public void launchnewTabTest() throws IOException {
		String oldHandle= driver.getWindowHandle();
		((JavascriptExecutor)driver).executeScript("window.open()");
		 Set<String> handles = driver.getWindowHandles();
		        for(String actual: handles) {
		         if(!actual.equalsIgnoreCase(oldHandle)){
		             driver.switchTo().window(actual);
	                 driver.get("https://www.lambdatest.com/selenium-automation");
	            }
	        }
	
		WebElement jenkinslogo = driver.findElement(By.xpath("/html/body/div[2]/section[6]/div/div[2]/ul/li[1]/a/img"));
		
		String logoPath = jenkinslogo.getAttribute("src");
		URL logoURL = new URL(logoPath);
		System.out.println(logoURL);
		InputStream input = new BufferedInputStream(logoURL.openStream());
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		    byte[] buffer = new byte[1024];
		    int i = 0;
		    while (-1!=(i=input.read(buffer))) { 
		    	out.write(buffer, 0, i);
		    }
		    out.close();
		    input.close();
		    byte[] result = out.toByteArray();
		    FileOutputStream fos = new FileOutputStream("/Users/mac/eclipse-workspace/LambdaProject/src/jenkins.svg");
		    fos.write(result);
		    fos.close();
		
		driver.switchTo().window(oldHandle);
	}
	
	
	
	@Test(priority = 8)
	public void uploadlogoTest() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor)driver; 
		js.executeScript("window.scrollBy(0,380)");
		
		WebElement uploadImage = driver.findElement(By.xpath("//*[@id=\"file\"]"));
	    uploadImage.sendKeys("/Users/mac/eclipse-workspace/LambdaProject/src/jenkins.svg");
	          
		String alertMessage = driver.switchTo().alert().getText();
		String expectedMessage = "your image upload sucessfully!!";
		
		Assert.assertEquals(alertMessage, expectedMessage);
		
		driver.switchTo().alert().accept();
    }

	
	
	@Test(priority = 9)
	public void submitPageTest() {
		driver.findElement(By.xpath("//*[@id=\"submit-button\"]")).click();		
		
		String successMessage = driver.findElement(By.xpath("//*[@id=\"message\"]/p")).getText();
		String expected = "You have successfully submitted the form.";
		
		Assert.assertEquals(successMessage, expected);
	}
	
	
	@AfterSuite
	public void quitBrowser() {
		driver.quit();
	}
	
	
//Initialization class for login (username and password initialization).
	public void login(String username, String password) {
		driver.findElement(By.xpath("//*[@id=\"username\"]")).sendKeys(username);
		driver.findElement(By.id("password")).sendKeys(password);
		driver.findElement(By.xpath("/html/body/div[1]/header/div[1]/div/div/div[2]/a[1]")).click();
		
		driver.findElement(By.xpath("//*[@id=\"applyform\"]/div/button")).click();
		
	}
}
