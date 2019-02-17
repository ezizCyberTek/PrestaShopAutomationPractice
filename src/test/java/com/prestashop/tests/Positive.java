package com.prestashop.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import com.github.javafaker.Faker;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Positive {
	
	
	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		driver.manage().window().fullscreen();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		String url = "http://automationpractice.com";
		driver.get(url);
		driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
	}
	
	@AfterMethod
	public void tearDown() {
		//driver.close();
	}
	
	@Test
	public void Positive() {
		
		Faker generate = new Faker();
		WebDriverWait d=new WebDriverWait(driver,20);
		
		String firstName = generate.name().firstName();
		String lastName = generate.name().lastName();
		String email = generate.name().firstName()+generate.name().lastName()+"@gmail.com";
		String password = "Hopkins123";
		
		driver.findElement(By.id("email_create")).sendKeys(email);
		driver.findElement(By.id("SubmitCreate")).click();
		driver.findElement(By.id("id_gender1")).click();
		driver.findElement(By.id("customer_firstname")).sendKeys(firstName);
		driver.findElement(By.id("customer_lastname")).sendKeys(lastName);
		driver.findElement(By.id("passwd")).sendKeys(password);
		
		 Select days = new Select(driver.findElement(By.id("days"))); 
		 days.selectByValue("21");
		
		 Select month = new Select(driver.findElement(By.id("months"))); 
		 month.selectByValue("8");
		 
		 Select year = new Select(driver.findElement(By.id("years"))); 
		 year.selectByValue("1990");
		 
		 driver.findElement(By.id("firstname")).sendKeys("John");
		 driver.findElement(By.id("lastname")).sendKeys("Hopkins");
		 driver.findElement(By.id("company")).sendKeys("CTIS");
		 driver.findElement(By.id("address1")).sendKeys("2208 Nowhere str");
		 driver.findElement(By.id("city")).sendKeys("Nowhere");
		 
		 Select state = new Select(driver.findElement(By.id("id_state"))); 
		 state.selectByValue("6");
		 
		 driver.findElement(By.id("postcode")).sendKeys("20763");
		 
		 Select country = new Select(driver.findElement(By.id("id_country"))); 
		 country.selectByValue("21");
		 
		 driver.findElement(By.id("phone")).sendKeys("20763");
		 driver.findElement(By.id("submitAccount")).click();
		 
		 d.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='Log me out']")));
		 
		 driver.findElement(By.xpath("//a[@title='Log me out']")).click();
		 
		 driver.findElement(By.xpath("//a[@title='Log in to your customer account']")).click();
		 driver.findElement(By.id("email")).sendKeys(email);
		 driver.findElement(By.id("passwd")).sendKeys(password);
		 driver.findElement(By.id("SubmitLogin")).click();
		 
		 d.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@title='View my customer account']")));
		 
		 String actual = driver.findElement(By.xpath("//a[@title='View my customer account']")).getText();
		 String expected = firstName + " " + lastName;
		 
		 Assert.assertEquals(actual, expected);
		 
	}
	
	
	
	
	
}
