package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.concurrent.TimeUnit;

public class ShoppingSteps {
    WebDriver driver;

    @Given("The user is on the {string} website and clicks the sign in button")
    public void theUserIsOnTheWebsiteAndClicksTheSignInButton(String url) {
        System.setProperty("webdriver.chrome.driver",
                "src/main/resources/drivers/chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.get(url);

        WebElement signIn = driver.findElement(By.className("user-info"));
        signIn.click();
    }

    @And("Fills in the email {string}, password {string} and clicks the sign in button")
    public void fillsInTheEmailPasswordAndClicksTheSignInButton(String login, String password) {
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

    @When("Clicks on the clothes category")
    public void clicksOnTheClothesCategory() {
        WebElement clothesButton = driver.findElement(By.id("category-3"));
        clothesButton.click();
    }

    @And("Clicks on the photo of Hummingbird Printed Sweater")
    public void clicksOnThePhotoOfHummingbirdPrintedSweater() {
        WebElement sweaterPhoto = driver.findElement(By.xpath("//div[@class='thumbnail-container']/a/img[@alt='Brown bear printed sweater']"));
        sweaterPhoto.click();
    }

    @And("Chooses {string}")
    public void chooses(String size) {

        WebElement sizeList = driver.findElement(By.id("group_1"));
        sizeList.sendKeys(size);
    }

    @And("Checks the if the discount is 20%")
    public void checksTheIfTheDiscountIs() {
        WebElement discountLabel = driver.findElement(By.xpath("//span[@class='discount discount-percentage']"));
        Assert.assertEquals("SAVE 20%", discountLabel.getText());
    }

    @And("Chooses {int} pieces")
    public void choosesPieces(int quantity) throws InterruptedException {
        WebElement quantityUp = driver.findElement(By.xpath("//button[@class='btn btn-touchspin js-touchspin bootstrap-touchspin-up']"));
        for (int i = 0; i < quantity; i++) {
            quantityUp.click();
            Thread.sleep(800);
        }
        Thread.sleep(1000);
    }

    @And("Adds to the cart")
    public void addsToTheCart() throws InterruptedException {
        WebElement addToCartButton = driver.findElement(By.xpath("//button[@class='btn btn-primary add-to-cart']"));
        addToCartButton.click();
        Thread.sleep(1000);
    }

    @And("Goes to the checkout")
    public void goesToTheCheckout() {
        WebElement firstProceedButton = driver.findElement(By.xpath("//div[@class='cart-content-btn']/a[@class='btn btn-primary']"));
        firstProceedButton.click();
        WebElement secondProceedButton = driver.findElement(By.xpath("//div[@class='text-sm-center']/a[@class='btn btn-primary']"));
        secondProceedButton.click();
    }

    @And("Confirms the address")
    public void confirmsTheAddress() throws InterruptedException {
        WebElement addressCheck = driver.findElement(By.xpath("//div[@class='address']"));
        String[] addressSummary = addressCheck.getText().split("\\n");

        Assert.assertEquals("John Doe", addressSummary[0]);
        Assert.assertEquals("32 Moore St", addressSummary[1]);
        Assert.assertEquals("London", addressSummary[2]);
        Assert.assertEquals("SW3 2QW", addressSummary[3]);
        Assert.assertEquals("United Kingdom", addressSummary[4]);


        Thread.sleep(2000);
    }

    @And("Chooses the pick up in store option")
    public void choosesThePickUpInStoreOption() {
        WebElement shippingMethodHeader = driver.findElement(By.xpath("//section[@id='checkout-delivery-step']/h1[@class='step-title h3']"));
        shippingMethodHeader.click();

        WebElement shopRadioButton2 = driver.findElement(By.id("delivery_option_2"));
        shopRadioButton2.click();

        WebElement shopRadioButton1 = driver.findElement(By.id("delivery_option_1"));
        shopRadioButton1.click();
    }
    @And("Chooses Pay by Check")
    public void choosesPayByCheck() {
        WebElement paymentHeader = driver.findElement(By.xpath("//section[@id='checkout-payment-step']/h1[@class='step-title h3']"));
        paymentHeader.click();
        WebElement paymentRadioButton = driver.findElement(By.id("payment-option-1"));
        paymentRadioButton.click();
    }

    @And("Clicks on the order with an obligation to pay button")
    public void clicksOnTheOrderWithAnObligationToPayButton() {
        WebElement agreeCheckbox = driver.findElement(By.xpath("//input[@id='conditions_to_approve[terms-and-conditions]']"));
        agreeCheckbox.click();
        WebElement orderButton = driver.findElement(By.xpath("//div[@class='ps-shown-by-js']/button[@class='btn btn-primary center-block']"));
        orderButton.click();
    }

    @And("Takes the screenshot with the order confirmation and the total sum")
    public void takesTheScreenshotWithTheOrderConfirmationAndTheTotalSum() throws IOException {
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        File screenshot = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("C:\\Users\\Alicja\\Documents\\Programowanie\\Coders Lab\\Tester Automatyzujący\\5_Zadania zaliczeniowe\\screenshot_" + timestamp.getTime() + ".png"));
    }

    @Then("The order is placed")
    public void theOrderIsPlaced() throws InterruptedException {
        WebElement signOutButton = driver.findElement(By.xpath("//a[@class='logout hidden-sm-down']"));
        signOutButton.click();

        Thread.sleep(2000);
        driver.quit();
    }
}
