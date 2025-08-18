package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws InterruptedException {
        WebDriver driver=new ChromeDriver();
        driver.get("https://www.justdial.com/");
        Thread.sleep(5000);
        driver.findElement(By.linkText("Maybe Later")).click();
    }
}
