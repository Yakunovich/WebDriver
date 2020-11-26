import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import java.util.List;
import org.openqa.selenium.chrome.ChromeOptions;

public class RevolveclothingTest {
    WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void driverInitiate(){
        ChromeOptions option = new ChromeOptions();
        option.addArguments("--window-size=1200,800");
        driver = new ChromeDriver(option);
    }

    @Test
    public void addShoesToBagTest() throws InterruptedException {
        driver.get("https://www.revolve.com/women/?navsrc=main");
        WebElement closeBanner = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By
                        .xpath("//*[@id='ntf_dialog_close']")));

        closeBanner.click();

        WebElement inputString = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.
                        xpath("//*[@id='search_term_new']")));

        inputString.sendKeys("Vans");

        inputString.sendKeys(Keys.ENTER);

        WebElement pickShoesButton = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.
                        xpath("//*[@id='VANX-UZ2']/div/div/a[1]")));
        pickShoesButton.click();

        WebElement pichSizeButton = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.
                        xpath("//*[@id='size-ul']/li[1]/label")));
        pichSizeButton.click();

        WebElement addToBagButton = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfElementLocated(By.
                        xpath("//*[@id='addtobagbutton']")));

        addToBagButton.click();

        driver.get("https://www.revolve.com/r/ShoppingBag.jsp?navsrc=header");

        List<WebElement> bagItems = new WebDriverWait(driver, 3)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.
                        xpath("//*[@id='bag_item_VANX-UZ2_Mens_4_Womens_5_5']")));

        Assert.assertTrue(bagItems.size()>0);
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
