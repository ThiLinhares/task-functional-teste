package br.ce.wcaquino.tasks.functional;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


public class TasksTest {
	
	public WebDriver acessarAplicacao() throws MalformedURLException {
///	WebDriver driver = new ChromeDriver();
		DesiredCapabilities cap = DesiredCapabilities.chrome();
		WebDriver driver = new RemoteWebDriver(new URL("http://192.168.100.13:4444"), cap);
	driver.navigate().to("http://192.168.100.13:8001/tasks");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	return driver;
}
	
	@Test
	public void deveSalvarTarefaComSucesso() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
		
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("task")).sendKeys("Teste Selenium");
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2025");
		driver.findElement(By.id("saveButton")).click();
		
		String mensagem = driver.findElement(By.id("message")).getText();
		
		Assert.assertEquals( "Success!", mensagem);
		} finally {
			
		}
		
		driver.quit();
	
	}
	
	
	
	@Test
	public void NaoDeveSalvarTarefaComDataPassada() throws MalformedURLException {
	
		WebDriver driver = acessarAplicacao();
		
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("task")).sendKeys("Teste Selenium com erro");
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
		driver.findElement(By.id("saveButton")).click();
		
		String mensagem = driver.findElement(By.id("message")).getText();
		
		Assert.assertEquals( "Due date must not be in past", mensagem);
		
		driver.quit();
	
	
	}
	
	@Test
	public void NaoDeveSalvarTarefaSemDescricao() throws MalformedURLException {
	
		WebDriver driver = acessarAplicacao();
		
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
		driver.findElement(By.id("saveButton")).click();
		
		String mensagem = driver.findElement(By.id("message")).getText();
		
		Assert.assertEquals( "Fill the task description", mensagem);
		
		driver.quit();
	
	
	}
	
	
	@Test
	public void deveSalvarTarefaSemData() throws MalformedURLException {
		WebDriver driver = acessarAplicacao();
		
		try {
		
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("task")).sendKeys("Teste Selenium");
		driver.findElement(By.id("saveButton")).click();
		
		String mensagem = driver.findElement(By.id("message")).getText();
		
		Assert.assertEquals( "Fill the due date", mensagem);
		} finally {
			
		}
		
		driver.quit();
	
	}
	
	

}
