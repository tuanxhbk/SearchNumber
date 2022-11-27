package POM.Badoo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {
    WebDriver driver;

    By signInNameTxt = By.xpath("/html//input[@id='signin-name']");
    By passwordTxt = By.xpath("/html//input[@id='signin-password']");
    By signInBtn = By.xpath("//div[@id='signin-submit']");

    public SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public void signIn(String signInName, String password) throws Exception{
        driver.findElement(signInNameTxt).clear();
        driver.findElement(signInNameTxt).sendKeys(signInName);
        Thread.sleep(2000);

        driver.findElement(passwordTxt).clear();
        driver.findElement(passwordTxt).sendKeys(password);
        Thread.sleep(2000);

        driver.findElement(signInBtn).click();
    }
}
