package MyTestCase;

import java.nio.channels.SelectableChannel;
import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CodeBoxTest {
	
	WebDriver driver=new EdgeDriver();
	String myWebSite="https://codenboxautomationlab.com/practice/";
	
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
	
	@Test(priority = 4)
	public void CheckBoxSelect() {
		//"//input[@name='username']"
		List<WebElement> checkButton=driver.findElements(By.xpath("//input[@type='checkbox']"));
		if(checkButton.size()>0) {
			int randomCheckOption=rand.nextInt(checkButton.size());
			WebElement randomCheck=checkButton.get(randomCheckOption);
			randomCheck.click();
		}
	}
	
	
}
