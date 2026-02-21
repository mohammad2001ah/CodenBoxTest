package MyTestCase;

import java.nio.channels.SelectableChannel;
import java.sql.Time;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CodeBoxTest {
	
	WebDriver driver=new EdgeDriver();
	String myWebSite="https://codenboxautomationlab.com/practice/";
	String ParentWindow=driver.getWindowHandle();
	
	Random rand = new Random();
	
	@BeforeTest
	public void MySetUp() {
		driver.get(myWebSite);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
	}
	
	@Test(priority = 1,enabled = false)
	public void RadioButton() {
		List<WebElement> radioButton=driver.findElements(By.className("radioButton"));
		if(radioButton.size()>0) {
			int randomValue=rand.nextInt(radioButton.size());
			WebElement randomeValueButton=radioButton.get(randomValue);
			randomeValueButton.click();
		}
	}
	
	@Test(priority = 2,enabled = false)
	public void PrintCountries() {
		WebElement Countries=driver.findElement(By.id("autocomplete"));
		Countries.sendKeys("Jordan");
	}
	
	@Test(priority = 3,enabled = false)
	public void DropDown() {
		WebElement staticBox=driver.findElement(By.id("dropdown-class-example"));
		Select Static =new Select(staticBox);
		List<WebElement> Option= Static.getOptions();
		int randOption=rand.nextInt(Option.size()-1);
		Static.selectByIndex(randOption);
	}
	
	@Test(priority = 4,enabled = false)
	public void CheckBoxSelect() {
		List<WebElement> checkButton=driver.findElements(By.xpath("//input[@type='checkbox']"));
		if(checkButton.size()>0) {
			int randomCheckOption=rand.nextInt(checkButton.size());
			WebElement randomCheck=checkButton.get(randomCheckOption);
			randomCheck.click();
		}
	}
	
	@Test(priority=5)
	public void OpenWindow() throws InterruptedException {
		WebElement OpenWindowButton=driver.findElement(By.id("openwindow"));
		OpenWindowButton.click();
		Set<String> allwindows=driver.getWindowHandles();
		for(String window:allwindows) {
			if (!window.equals(ParentWindow)) {
				driver.switchTo().window(window);
				System.out.println(driver.getTitle());
				Thread.sleep(1000);
				driver.close();
			}
		}
	}
	
//	@Test(priority=6,enabled = false)
//	public void OpenWindow() throws InterruptedException {
//		WebElement OpenWindowButton=driver.findElement(By.id("openwindow"));
//		OpenWindowButton.click();
//		List<String> windows=new ArrayList<>(driver.getWindowHandles());
//		Thread.sleep(3000);
//		String mainWindow=windows.get(0);
//		 int index=0;
//		 boolean flag=true;
//		 while(flag) {
//			 if(index >= windows.size()) {
//				 flag=false;
//				 break;
//			 }
//			 String newWindow=windows.get(index);
//			 if(!newWindow.equals(mainWindow)) {
//				 driver.switchTo().window(newWindow);
//				 driver.close();
//			 }
//			 index++;	 
//		 } 
//	}
	
	@Test(priority = 7,enabled = false)
	public void NewTab() throws InterruptedException {
		WebElement OpenButton=driver.findElement(By.id("opentab"));
		OpenButton.click();
		String ParentTab=driver.getWindowHandle();
		System.out.println(ParentTab);
		Set<String>allWindow=driver.getWindowHandles();
		for(String window:allWindow) {
			if (!window.equals(ParentTab)) {
				driver.switchTo().window(window);
				System.out.println(driver.getTitle());
				Thread.sleep(1000);
				driver.close();
			}
			
		}
		
	}
	
}
