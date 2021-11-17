package br.jamtech;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

class SeleniumJunitTest {
	

	private WebDriver driver;

	@BeforeEach
	void setUp() throws Exception {
		System.setProperty("webdriver.chrome.driver", "C:\\drive_chrome_96\\chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterEach
	void tearDown() throws Exception {
		driver.quit();
	}

	@Test
	void test() throws InterruptedException {
        driver.get("http://localhost:8080/");
		WebElement login = driver.findElement(By.name("username"));
		login.sendKeys("jonas");
		WebElement senha = driver.findElement(By.name("password"));
		senha.sendKeys("123456");
		WebElement validar = driver.findElement(By.id("botao_login"));
		validar.click();
		WebElement pagEtoque = driver.findElement(By.id("botao_pag_caixa"));
		pagEtoque.click();
		WebElement buscaestoque = driver.findElement(By.name("descricao"));
		buscaestoque.sendKeys("gabinete");
		WebElement buscarEst = driver.findElement(By.id("busca_est_caixa"));
		buscarEst.click();
		Thread.sleep(9000);
	}

}
