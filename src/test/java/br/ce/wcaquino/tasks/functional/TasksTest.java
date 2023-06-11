package br.ce.wcaquino.tasks.functional;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class TasksTest {
	
	public WebDriver acessarAplicacao() {
	WebDriver driver = new ChromeDriver();
	driver.navigate().to("http://localhost:8001/tasks");
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	return driver;
}
	
	@Test
	public void deveSalvarTarefaComSucesso() {
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
	public void NaoDeveSalvarTarefaComDataPassada() {
	
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
	public void NaoDeveSalvarTarefaSemDescricao() {
	
		WebDriver driver = acessarAplicacao();
		
		driver.findElement(By.id("addTodo")).click();
		driver.findElement(By.id("dueDate")).sendKeys("10/10/2020");
		driver.findElement(By.id("saveButton")).click();
		
		String mensagem = driver.findElement(By.id("message")).getText();
		
		Assert.assertEquals( "Fill the task description", mensagem);
		
		driver.quit();
	
	
	}
	
	
	@Test
	public void deveSalvarTarefaSemData() {
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
