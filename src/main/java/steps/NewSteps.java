package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

public class NewSteps {
    WebDriver driver;
    @Given("The user is on the {string} website and click the sign in button")
    public void theUserIsOnTheWebsiteAndClickTheSignInButton(String url) {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);

        WebElement signIn = driver.findElement(By.className("user-info"));
        signIn.click();
    }

    @And("Fills in email {string} and password {string} and clicks the sign in button.")
    public void fillsInEmailAndPasswordAndClicksTheSignInButton(String login, String password) {
        System.out.println("login page");
        WebElement emailInput = driver.findElement(By.className("form-control"));
        emailInput.clear();
        emailInput.sendKeys(login);

        WebElement passwordInput = driver.findElement(By.className("js-visible-password"));
        passwordInput.clear();
        passwordInput.sendKeys(password);

        WebElement signInButton = driver.findElement(By.id("submit-login"));
        signInButton.click();

    }

    @When("Clicks on the addresses button.")
    public void clicksOnTheAddressesButton() {
        WebElement addressesButton = driver.findElement(By.id("addresses-link"));
        addressesButton.click();

    }

    @And("Clicks on the create new address button")
    public void clicksOnTheCreateNewAddressButton() {
        WebElement createNewAddressButton = driver.findElement(By.xpath("//div[@class='addresses-footer']/a"));
        createNewAddressButton.click();
    }

    @And("Fills in the form with {string}, {string}, {string}, {string}, {string}, {string} and clicks save")
    public void fillsInTheFormWithAndClicksSave(String alias, String address, String city, String zipCode, String country, String phone) {

        WebElement aliasInput = driver.findElement(By.xpath("//input[@name='alias']"));
        aliasInput.clear();
        aliasInput.sendKeys(alias);

        WebElement addressInput = driver.findElement(By.xpath("//input[@name='address1']"));
        addressInput.clear();
        addressInput.sendKeys(address);

        WebElement cityInput = driver.findElement(By.xpath("//input[@name='city']"));
        cityInput.clear();
        cityInput.sendKeys(city);

        WebElement zipCodeInput = driver.findElement(By.xpath("//input[@name='postcode']"));
        zipCodeInput.clear();
        zipCodeInput.sendKeys(zipCode);

//        WebElement countryInput = driver.findElement(By.xpath("//input[@name='address1']"));
//        countryInput.clear();
//        countryInput.sendKeys(country);

        WebElement phoneInput = driver.findElement(By.xpath("//input[@name='phone']"));
        phoneInput.clear();
        phoneInput.sendKeys(phone);

        WebElement saveButton = driver.findElement(By.xpath("//button[@class='btn btn-primary float-xs-right']"));
        saveButton.click();
    }

    @Then("This is the end for now")
    public void thisIsTheEndForNow() throws InterruptedException {
        WebElement name = driver.findElement(By.xpath("//section[@id='content']/div[2]/article/div"));
        String[] addressSummary = name.getText().split("\\n");

        Assert.assertEquals("New", addressSummary[0]);
        Assert.assertEquals("John Doe", addressSummary[1]);
        Assert.assertEquals("20 Guest Rd", addressSummary[2]);
        Assert.assertEquals("Cambridge", addressSummary[3]);
        Assert.assertEquals("CB1 2AL", addressSummary[4]);
        Assert.assertEquals("United Kingdom", addressSummary[5]);
        Assert.assertEquals("+447111038229", addressSummary[6]);

        Thread.sleep(2000);

        WebElement deleteButton = driver.findElement(By.xpath("//section[@id='content']/div[2]/article/div[@class='address-footer']/a[@data-link-action='delete-address']"));
        deleteButton.click();

        WebElement deletedName = null;
        try {
            deletedName = driver.findElement(By.xpath("//section[@id='content']/div[2]/article/div"));
        } catch(NoSuchElementException e) {

        }
        Assert.assertNull(deletedName);

        Thread.sleep(1000);

        WebElement signOutButton = driver.findElement(By.xpath("//a[@class='logout hidden-sm-down']"));
        signOutButton.click();

        Thread.sleep(2000);

        driver.quit();
    }

}
