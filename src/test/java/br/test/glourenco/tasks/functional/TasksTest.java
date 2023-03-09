package br.test.glourenco.tasks.functional;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class TasksTest {

    public WebDriver acessarAplicacao(){
        WebDriver driver = new ChromeDriver();
        driver.navigate().to("http://localhost:8001/tasks/add");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        return driver;
    }

    @Test
    public void deveSalvarTarefaComSucesso() {
        WebDriver driver = acessarAplicacao();
        try {
            //Clicar em Add Todo
            //driver.findElement(By.id("addTodo")).click();

            //Escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            //Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

            //Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            //Validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals(mensagem, "Success!");
        } finally {
            //Fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemDescricao() {
        WebDriver driver = acessarAplicacao();
        try {
            //Clicar em Add Todo
            //driver.findElement(By.id("addTodo")).click();

            //Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2030");

            //Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            //Validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals(mensagem, "Fill the task description");
        } finally {
            //Fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarTarefaSemData() {
        WebDriver driver = acessarAplicacao();
        try {
            //Clicar em Add Todo
            //driver.findElement(By.id("addTodo")).click();

            //Escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            //Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            //Validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals(mensagem, "Fill the due date");
        } finally {
            //Fechar o browser
            driver.quit();
        }
    }

    @Test
    public void naoDeveSalvarComDataPassada() {
        WebDriver driver = acessarAplicacao();
        try {
            //Clicar em Add Todo
            //driver.findElement(By.id("addTodo")).click();

            //Escrever descrição
            driver.findElement(By.id("task")).sendKeys("Teste via Selenium");

            //Escrever a data
            driver.findElement(By.id("dueDate")).sendKeys("10/10/2010");

            //Clicar em Salvar
            driver.findElement(By.id("saveButton")).click();

            //Validar mensagem de sucesso
            String mensagem = driver.findElement(By.id("message")).getText();
            Assert.assertEquals(mensagem, "Due date must not be in past");
        } finally {
            //Fechar o browser
            driver.quit();
        }
    }

}