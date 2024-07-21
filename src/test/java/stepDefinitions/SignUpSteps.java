package stepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.junit.Assert.*;
import java.util.logging.Logger;

public class SignUpSteps {

    static WebDriver driver;
    static WebDriverWait wait;
    private static final Logger logger = Logger.getLogger(SignUpSteps.class.getName());

    @Given("I open the browser")
    public void i_open_the_browser() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\admin\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10); // 10 saniye bekleme süresi
    }

    @Given("I navigate to the registration page")
    public void i_navigate_to_the_registration_page() {
        logger.info("Navigating to the registration page...");
        driver.get("https://www.demoblaze.com/");
        try {
            WebElement signUpButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("signin2")));
            signUpButton.click();
            logger.info("Clicked on the sign-up button.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Sign up button not found or clickable");
        }
    }

    @When("I enter valid registration details")
    public void i_enter_valid_registration_details() {
        try {
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-username")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sign-password")));
            usernameField.sendKeys("kngmee");
            passwordField.sendKeys("testpassword");
            logger.info("Entered registration details.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Registration details could not be entered");
        }
    }

    @When("I submit the registration form")
    public void i_submit_the_registration_form() {
        try {
            WebElement signUpSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Sign up')]")));
            signUpSubmitButton.click();
            logger.info("Submitted the registration form.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Sign up form could not be submitted");
        }
    }

    @Then("I should see a successful registration message")
    public void i_should_see_a_successful_registration_message() {
        try {
            Thread.sleep(3000);
            String alertMessage = driver.switchTo().alert().getText();
            assertEquals("Sign up successful.", alertMessage);
            driver.switchTo().alert().accept(); // Alerti kapatın
            logger.info("Registration successful message displayed.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Successful registration message not displayed");
        }
    }

    @Given("I navigate to the login page")
    public void i_navigate_to_the_login_page() {
        logger.info("Navigating to the login page...");
        driver.get("https://www.demoblaze.com/");
        try {
            Thread.sleep(3000); // "Log in" butonunun yüklenmesi için bekleyin
            WebElement loginButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("login2")));
            loginButton.click();
            logger.info("Clicked on the login button.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Login button not found or clickable");
        }
    }

    @When("I enter valid login details")
    public void i_enter_valid_login_details() {
        try {
            WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginusername")));
            WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("loginpassword")));
            usernameField.sendKeys("kngmee");
            passwordField.sendKeys("testpassword");
            logger.info("Entered login details.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Login details could not be entered");
        }
    }

    @When("I submit the login form")
    public void i_submit_the_login_form() {
        try {
            WebElement loginSubmitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Log in')]")));
            loginSubmitButton.click();
            logger.info("Submitted the login form.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Login form could not be submitted");
        }
    }

    @Then("I should see a welcome message")
    public void i_should_see_a_welcome_message() {
        try {
            Thread.sleep(3000);
            WebElement welcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("nameofuser")));
            String expectedMessage = "Welcome kngmee";
            String actualMessage = welcomeMessage.getText();
            assertEquals(expectedMessage, actualMessage);
            logger.info("Welcome message displayed.");
        } catch (Exception e) {
            e.printStackTrace();
            fail("Welcome message not displayed");
        }
    }

    @When("I click on HTC One M9")
    public void i_click_on_htc_one_m9() {
        WebElement productLink = wait.until(ExpectedConditions.elementToBeClickable(By.linkText("HTC One M9")));
        productLink.click();
    }

    @Then("I should see the product details")
    public void i_should_see_the_product_details() {
        WebElement productName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".name")));
        WebElement productPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".price-container")));
        WebElement productDescription = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".description")));

        assertEquals("HTC One M9", productName.getText());
        assertTrue(productPrice.getText().contains("$700"));
        assertTrue(productDescription.getText().contains("The HTC One M9 is powered by 1.5GHz octa-core Qualcomm Snapdragon 810 processor and it comes with 3GB of RAM. The phone packs 32GB of internal storage that can be expanded up to 128GB via a microSD card."));
    }

    @When("I add the product to the cart")
    public void i_add_the_product_to_the_cart() {
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[contains(text(),'Add to cart')]")));
        addToCartButton.click();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        driver.switchTo().alert().accept();
    }

    @When("I navigate to the cart")
    public void i_navigate_to_the_cart() {
        WebElement cartButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("cartur")));
        cartButton.click();
    }

    @Then("I should see the product in the cart")
    public void i_should_see_the_product_in_the_cart() {
        WebElement productNameInCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'HTC One M9')]")));
        WebElement productPriceInCart = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//td[contains(text(),'700')]")));

        assertNotNull(productNameInCart);
        assertNotNull(productPriceInCart);
    }

    @When("I place the order")
    public void i_place_the_order() {
        WebElement placeOrderButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Place Order')]")));
        placeOrderButton.click();
    }

    @When("I fill in the order details")
    public void i_fill_in_the_order_details() {
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("name")));
        WebElement countryField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("country")));
        WebElement cityField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("city")));
        WebElement cardField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("card")));
        WebElement monthField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("month")));
        WebElement yearField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("year")));

        nameField.sendKeys("Test User");
        countryField.sendKeys("Test Country");
        cityField.sendKeys("Test City");
        cardField.sendKeys("1234567890123456");
        monthField.sendKeys("12");
        yearField.sendKeys("2024");
    }

    @When("I purchase the order")
    public void i_purchase_the_order() {
        WebElement purchaseButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Purchase')]")));
        purchaseButton.click();
    }

    @Then("I should see the purchase confirmation")
    public void i_should_see_the_purchase_confirmation() {
        WebElement confirmationMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[contains(text(),'Thank you for your purchase!')]")));
        assertNotNull(confirmationMessage);
    }

    @Given("I close the browser")
    public void i_close_the_browser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
