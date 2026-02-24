package MyTestCase;

import java.sql.Statement;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CodeBoxTest {

	WebDriver driver = new EdgeDriver();
	String myWebSite = "https://codenboxautomationlab.com/practice/";
	Random rand = new Random();
	
	Connection con;
	Statement stmt;
	ResultSet rs;
	
	String FirstName;
	String LastName;
	String PhoneNumber;
	
	int randomEmailNumber = rand.nextInt(5478);
	int randomEmailNumber2 = rand.nextInt(978);
	

	@BeforeTest
	public void MySetUp() throws SQLException {
		driver.get(myWebSite);
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/classicmodels", "root", "2001");
		String ParentWindow = driver.getWindowHandle();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}

	@Test(priority = 1, enabled = false)
	public void RadioButton() {
		WebElement radioButtonDiv = driver.findElement(By.id("radio-btn-example"));
		radioButtonDiv.findElements(By.tagName("input")).get(0).click();
		boolean ActualResult = radioButtonDiv.findElements(By.tagName("input")).get(0).isSelected();
		boolean ExpetedResult = true;
		Assert.assertEquals(ActualResult, ExpetedResult);
	}

	@Test(priority = 2, enabled = false)
	public void PrintCountries() throws InterruptedException {
		String[] countries = { "jo", "sy", "ir" };
		int randomIndex = rand.nextInt(countries.length);
		String country = countries[randomIndex];

		driver.findElement(By.id("autocomplete")).sendKeys(country);
		Thread.sleep(1000);
		driver.findElement(By.id("autocomplete")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		Thread.sleep(3000);
	}

	@Test(priority = 3, enabled = false)
	public void DropDown() throws InterruptedException {
		WebElement SelectTag = driver.findElement(By.id("dropdown-class-example"));
		Select mySelector = new Select(SelectTag);
		mySelector.selectByIndex(1);
		Thread.sleep(2000);
		mySelector.selectByVisibleText("Appium");
		Thread.sleep(2000);
		mySelector.selectByValue("option3");

	}

	@Test(priority = 4, enabled = false)
	public void CheckBoxSelect() {
		WebElement CheckboxDiv = driver.findElement(By.id("checkbox-example"));
		List<WebElement> AllCheckBoxes = CheckboxDiv.findElements(By.tagName("input"));
		for (int i = 0; i < AllCheckBoxes.size(); i++) {
			AllCheckBoxes.get(i).click();
			Assert.assertEquals(AllCheckBoxes.get(i).isSelected(), true);
		}
	}

	@Test(priority = 5, enabled = false)
	public void OpenWindow() throws InterruptedException {
		WebElement openWindowButton = driver.findElement(By.id("openwindow"));
		openWindowButton.click();

		Set<String> handels = driver.getWindowHandles();
		List<String> allTabs = new ArrayList<>(handels);
		driver.switchTo().window(allTabs.get(1));

		System.out.println(driver.getTitle());
		driver.switchTo().window(allTabs.get(0));
		System.out.println(driver.getTitle());
	}

	@Test(priority = 6, enabled = false)
	public void NewTab() throws InterruptedException {
		driver.findElement(By.id("opentab")).click();

		Set<String> handels = driver.getWindowHandles();
		List<String> allTabs = new ArrayList<>(handels);
		driver.switchTo().window(allTabs.get(1));

		Thread.sleep(2000);
		System.out.println(driver.getTitle());
		driver.switchTo().window(allTabs.get(0));
	}

	@Test(priority = 7, enabled = false)

	public void AlertAndConfirm() throws InterruptedException {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,500)");
		String myname = "ahmad";
		driver.findElement(By.id("name")).sendKeys(myname);

		driver.findElement(By.id("confirmbtn")).click();
		Thread.sleep(2000);
		System.out.println(driver.switchTo().alert().getText().contains(myname));
		boolean ActualValue = driver.switchTo().alert().getText().contains(myname);
		Assert.assertEquals(ActualValue, true);
		driver.switchTo().alert().dismiss();
//		driver.switchTo().alert().accept();
	}

	@Test(priority = 8, enabled = false)
	public void TheTable() {

		WebElement TheTable = driver.findElement(By.id("product"));

		List<WebElement> AllData1 = TheTable.findElements(By.tagName("th"));
		System.out.println();
		for (int i = 0; i < AllData1.size(); i++) {
			System.out.println(AllData1.get(i).getText());
		}
		
		System.out.println("*************");
		
		List<WebElement> AllData2 = TheTable.findElements(By.tagName("td"));
		System.out.println();
//		for (int i = 0; i < AllData2.size(); i++) {
//			System.out.println(AllData2.get(i).getText());
//		}
		System.out.println(AllData2.get(1).getText());
		
		System.out.println("*************");
		List<WebElement> AllData3 = TheTable.findElements(By.tagName("tr"));
		System.out.println();
		for (int i = 0; i < AllData3.size(); i++) {
			System.out.println(AllData3.get(i).getText());
		}
		
	}
	
	@Test(priority = 9,enabled = false)
	public void HideAndShow() {
		WebElement HideButton=driver.findElement(By.id("hide-textbox"));
		WebElement ShowButton=driver.findElement(By.id("show-textbox"));
		WebElement InputBox=driver.findElement(By.id("displayed-text"));
		
		HideButton.click();
		boolean ActualResult=InputBox.isDisplayed();
		boolean ExpectedResult=false;
		Assert.assertEquals(ActualResult, ExpectedResult);
	}
	@Test(priority = 10,enabled = false)
	public void EnabledAndDisabled() {
		WebElement EnableButton=driver.findElement(By.id("enabled-button"));
		WebElement DisabledButton=driver.findElement(By.id("disabled-button"));
		WebElement InputBox=driver.findElement(By.id("enabled-example-input"));
		EnableButton.click();
		InputBox.sendKeys("kjbfui");
		boolean ActualResult=InputBox.isDisplayed();
		boolean ExpectedResult=true;
		Assert.assertEquals(ActualResult, ExpectedResult);
	}
	
	@Test(priority = 11,enabled = false)
	public void MouseHover() throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,1900)");
		Thread.sleep(2000);
		
		WebElement mouseHoverButton=driver.findElement(By.id("mousehover"));
		Actions action=new Actions(driver);
		action.moveToElement(mouseHoverButton).build().perform();
		WebElement reload=driver.findElement(By.linkText("Reload"));
		reload.click();
		
	}
	
	@Test(priority = 12,enabled = false)
	public void Calender() throws InterruptedException, IOException {
		Date mydate= new Date();
		String fileName=mydate.toString().replace(":", "-");
		driver.findElement(By.linkText("Booking Calendar")).click();
		Set<String> handels = driver.getWindowHandles();
		List<String> allTabs = new ArrayList<>(handels);
		driver.switchTo().window(allTabs.get(1));
		Thread.sleep(7000);
		TakesScreenshot ts=(TakesScreenshot)driver;
		File file=ts.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(file, new File("./screenShot/"+fileName+".jpg"));
	}
	
	@Test(priority = 13,enabled = false)
	public void Iframe() {
		driver.switchTo().frame("iframe-name"); 
		driver.findElement(By.cssSelector(".ct-mobile-meta-item.btn-nav-mobile.open-menu")).click();
	}
	
	@Test(priority = 14,enabled = true)
	public void AddDataFromDB() throws SQLException {
		stmt = con.createStatement();
		driver.findElement(By.linkText("Booking Calendar")).click();
		Set<String> handels = driver.getWindowHandles();
		List<String> allTabs = new ArrayList<>(handels);
		driver.switchTo().window(allTabs.get(1));
		
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("window.scrollTo(0,700)");
		
		String Query = "select * from customers";
		rs=stmt.executeQuery(Query);
		if (rs.next()) {
			FirstName = rs.getString("customerName");
			LastName = rs.getString("contactLastName");
			PhoneNumber = rs.getString("phone");
			
			driver.findElement(By.id("name1")).sendKeys(FirstName);
			driver.findElement(By.id("secondname1")).sendKeys(LastName);
			String TheEmail = FirstName + LastName + randomEmailNumber + randomEmailNumber2 + "@gmail.com";
			driver.findElement(By.id("email1")).sendKeys(TheEmail);
			driver.findElement(By.id("phone1")).sendKeys(PhoneNumber);
		}
		con.close();
	}
	
}
