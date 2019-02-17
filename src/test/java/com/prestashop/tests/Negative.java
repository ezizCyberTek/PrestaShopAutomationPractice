package com.prestashop.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Negative {
	
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
		driver.close();
	}
	
	@Test
	public void wrongCredentials() {
		
		driver.findElement(By.id("email")).sendKeys("wrongEmail@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("wrongPassword");
		driver.findElement(By.id("SubmitLogin")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[.='Authentication failed.']")).isDisplayed());
	}
	
	@Test
	public void wrongEmailFormat() {
		
		driver.findElement(By.id("email")).sendKeys("wrongEmail");
		driver.findElement(By.id("passwd")).sendKeys("wrongPassword");
		driver.findElement(By.id("SubmitLogin")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[.='Invalid email address.']")).isDisplayed());
	}
	
	@Test
	public void blankEmail() {
		
		driver.findElement(By.id("email")).sendKeys("");
		driver.findElement(By.id("passwd")).sendKeys("wrongPassword");
		driver.findElement(By.id("SubmitLogin")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[.='An email address required.']")).isDisplayed());
	}
	
	@Test
	public void blankPassword() {
		
		driver.findElement(By.id("email")).sendKeys("wrongEmail@gmail.com");
		driver.findElement(By.id("passwd")).sendKeys("");
		driver.findElement(By.id("SubmitLogin")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//li[.='Password is required.']")).isDisplayed());
	}
	
}
