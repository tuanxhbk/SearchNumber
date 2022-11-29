package POM.Badoo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class EncounterPage {
    WebDriver driver;

    By likeBtn = By.xpath("//div[@class='profile-action profile-action--lg profile-action--color-yes profile-action--filled   js-profile-header-vote-yes js-profile-header-vote']");
    By skipNotiBtn = By.xpath("//div[@class='btn btn--monochrome js-chrome-pushes-deny']");
    By profileNameTxt = By.xpath("//span[@class='profile-header__name']");
    By profileAgeTxt = By.xpath("//span[@class='profile-header__age']");
    By aboutMeTxt = By.xpath("//div[@class='profile-section__txt profile-section__txt--about']/p[@class='profile-section__txt-line']");
    By profileImage = By.xpath("//div[@id='mm_cc']//section[@class='encounters-card__content']//div[@class='photo-gallery']/div[1]/img");
    public EncounterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void clickLike() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement element = wait.until(ExpectedConditions.elementToBeClickable(likeBtn));
            element.click();
        } catch (Exception exception) {

        }

    }

    public void clickSkipNoti() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(skipNotiBtn));
            element.click();
        } catch (Exception exception) {

        }
    }

    public String getProfileName() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(profileNameTxt));
            return element.getText();
        } catch (Exception exception) {
            return "";
        }
    }

    public String getProfileAge() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(profileAgeTxt));
            return element.getText();
        } catch (Exception exception) {
            return "";
        }
    }

    public String getAboutMe() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(aboutMeTxt));
            return element.getText();
        } catch (Exception exception) {
            return "";
        }
    }

    public String getProfileImageSrc() {
        return driver.findElement(profileImage).getAttribute("src");
    }
}
