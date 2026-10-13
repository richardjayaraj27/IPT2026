package com.base;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.io.File;
import java.time.Duration;
import java.util.*;
import java.util.List;

public abstract class BaseClass {

    public static WebDriver driver;

    protected static WebDriver LaunchBrowser(String browserName) {
        try {
            if (browserName.equalsIgnoreCase("chrome")) {
                ChromeOptions options = new ChromeOptions();
                Map<String, Object> prefs = new HashMap<>();
                prefs.put("profile.password_manager_leak_detection", false);
                prefs.put("credentials_enable_service", false);
                prefs.put("profile.password_manager_enabled", false);
                options.setExperimentalOption("prefs", prefs);
                driver = new ChromeDriver(options);
            } else if (browserName.equalsIgnoreCase("firefox")) {
                driver = new FirefoxDriver();
            } else if (browserName.equalsIgnoreCase("edge")) {
                driver = new EdgeDriver();
            }
            driver.manage().window().maximize();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE LAUNCHING BROWSER");
        }
        return driver;
    }

    protected static void launchUrl(String url) {
        try {
            driver.get(url);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE LAUNCHING URL");
        }
    }

    protected static void passInput(WebElement element, String value) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.clear();
            element.sendKeys(value);
            System.out.println("Typed value: " + value + " into element: " + element);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE PASSING INPUT");
        }
    }

    protected static void elementClick(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE CLICKING ELEMENT");
        }
    }

    protected static void browserTerminate() {
        try {
            driver.quit();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE TERMINATING BROWSER");
        }
    }

    protected static void windowHandling(int num) {
        try {
            List<String> allWindows = new ArrayList<>(driver.getWindowHandles());
            driver.switchTo().window(allWindows.get(num));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SWITCHING WINDOW");
        }
    }

    protected static String getCurrentWindowHandle() {
        try {
            return driver.getWindowHandle();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING CURRENT WINDOW HANDLE");
            return null;
        }
    }

    protected static Set<String> getAllWindowHandles() {
        try {
            return driver.getWindowHandles();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING ALL WINDOW HANDLES");
            return null;
        }
    }

    protected static void switchToWindow(String windowHandle) {
        try {
            driver.switchTo().window(windowHandle);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SWITCHING TO WINDOW");
        }
    }

    protected static void selectOptions(WebElement element, String type, String value) {
        try {
            Select select = new Select(element);
            if (type.equalsIgnoreCase("value")) {
                select.selectByValue(value);
            } else if (type.equalsIgnoreCase("visibletext")) {
                select.selectByVisibleText(value);
            } else if (type.equalsIgnoreCase("index")) {
                select.selectByIndex(Integer.parseInt(value));
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SELECTING OPTION");
        }
    }

    protected static void deselectOptions(WebElement element, String type, String value) {
        try {
            Select select = new Select(element);
            if (type.equalsIgnoreCase("value")) {
                select.deselectByValue(value);
            } else if (type.equalsIgnoreCase("visibletext")) {
                select.deselectByVisibleText(value);
            } else if (type.equalsIgnoreCase("index")) {
                select.deselectByIndex(Integer.parseInt(value));
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE DESELECTING OPTION");
        }
    }

    protected static void closeBrowser() {
        try {
            driver.close();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE CLOSING BROWSER");
        }
    }

    protected static void implicitWait(int timeInSeconds) {
        try {
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(timeInSeconds));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SETTING IMPLICIT WAIT");
        }
    }

    protected static void waitForElement(WebElement element, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR ELEMENT");
        }
    }

    protected static void waitForElementToBeClickable(WebElement element, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR ELEMENT TO BE CLICKABLE");
        }
    }

    protected static void waitForAlert(int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.alertIsPresent());
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR ALERT");
        }
    }

    protected static String alert(String action) {
        try {
            Alert alert = driver.switchTo().alert();
            if (action.equalsIgnoreCase("gettext")) {
                return alert.getText();
            } else if (action.equalsIgnoreCase("accept")) {
                alert.accept();
            } else if (action.equalsIgnoreCase("dismiss")) {
                alert.dismiss();
            } else {
                Assert.fail("ERROR : INVALID ALERT ACTION");
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED WHILE PERFORMING ALERT ACTION");
        }
        return null;
    }

    protected static void takeScreenshot() {
        try {
            Date currentDate = new Date();
            String dateFile = currentDate.toString().replace(":", "_").replace(" ", "_");
            File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileHandler.copy(screenshot, new File("C:\\Users\\richa\\IdeaProjects\\Maven_1\\screenshot" + dateFile + ".png"));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE TAKING SCREENSHOT");
        }
    }
    protected static void waitForElementInvisibility(WebElement element, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR ELEMENT INVISIBILITY");
        }
    }

    protected static void waitForText(WebElement element, String text, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.textToBePresentInElement(element, text));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR TEXT");
        }
    }

    protected static void waitForElementPresence(By locator, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(locator));
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE WAITING FOR ELEMENT PRESENCE");
        }
    }

    protected static void navigate(String action) {
        try {
            if (action.equalsIgnoreCase("back")) {
                driver.navigate().back();
            } else if (action.equalsIgnoreCase("forward")) {
                driver.navigate().forward();
            } else if (action.equalsIgnoreCase("refresh")) {
                driver.navigate().refresh();
            } else {
                Assert.fail("ERROR : INVALID NAVIGATION ACTION");
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE NAVIGATING");
        }
    }

    protected static void alertSendKeys(String value) {
        try {
            driver.switchTo().alert().sendKeys(value);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SENDING KEYS TO ALERT");
        }
    }

    protected static void action(String type, WebElement element) {
        try {
            Actions actions = new Actions(driver);
            switch (type.toLowerCase()) {
                case "movetoelement": actions.moveToElement(element).perform(); break;
                case "click": actions.click(element).perform(); break;
                case "rightclick": actions.contextClick(element).perform(); break;
                case "doubleclick": actions.doubleClick(element).perform(); break;
                case "clickandhold": actions.clickAndHold(element).perform(); break;
                case "release": actions.release(element).perform(); break;
                default: Assert.fail("ERROR : INVALID ACTION TYPE");
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE PERFORMING MOUSE ACTION USING ACTIONS");
        }
    }

    protected static void action(String type, WebElement source, WebElement target) {
        try {
            Actions actions = new Actions(driver);
            if (type.equalsIgnoreCase("draganddrop")) {
                actions.dragAndDrop(source, target).perform();
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE DRAGGING AND DROPPING ELEMENT USING ACTIONS");
        }
    }

    protected static void frameSwitch(int index) {
        try {
            driver.switchTo().frame(index);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SWITCHING TO FRAME USING INDEX");
        }
    }

    protected static void frameSwitch(String nameOrId) {
        try {
            driver.switchTo().frame(nameOrId);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SWITCHING TO FRAME USING NAME OR ID");
        }
    }

    protected static void frameSwitch(WebElement element) {
        try {
            driver.switchTo().frame(element);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SWITCHING TO FRAME USING WEBELEMENT");
        }
    }

    protected static void frameSwitchToDefaultContent() {
        try {
            driver.switchTo().defaultContent();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SWITCHING TO DEFAULT CONTENT");
        }
    }

    protected static void frameSwitchToParentFrame() {
        try {
            driver.switchTo().parentFrame();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SWITCHING TO PARENT FRAME");
        }
    }

    protected static void robotKeyPress(int key) {
        try {
            Robot robot = new Robot();
            robot.keyPress(key);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE PRESSING KEY USING ROBOT");
        }
    }

    protected static void checkBox(WebElement element, String type) {
        try {
            if (type.equalsIgnoreCase("select") && !element.isSelected()) {
                element.click();
            } else if (type.equalsIgnoreCase("deselect") && element.isSelected()) {
                element.click();
            } else if (type.equalsIgnoreCase("click")) {
                element.click();
            }
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE HANDLING CHECKBOX");
        }
    }

    protected static boolean isEnabled(WebElement element) {
        try {
            return element.isEnabled();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE CHECKING ELEMENT IS ENABLED");
            return false;
        }
    }

    protected static boolean isDisplayed(WebElement element) {
        try {
            return element.isDisplayed();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE CHECKING ELEMENT IS DISPLAYED");
            return false;
        }
    }

    protected static List<WebElement> getOptions(WebElement element) {
        try {
            Select select = new Select(element);
            return select.getOptions();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING DROPDOWN OPTIONS");
            return null;
        }
    }

    protected static String getTitle() {
        try {
            return driver.getTitle();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING PAGE TITLE");
            return null;
        }
    }

    protected static String getCurrentUrl() {
        try {
            return driver.getCurrentUrl();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING CURRENT URL");
            return null;
        }
    }

    protected static String getText(WebElement element) {
        try {
            return element.getText();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING TEXT");
            return null;
        }
    }

    protected static String getAttribute(WebElement element, String attributeName) {
        try {
            return element.getAttribute(attributeName);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE GETTING ELEMENT ATTRIBUTE");
            return null;
        }
    }

    protected static void scroll(int pixels) {
        try {
            Actions actions = new Actions(driver);
            actions.scrollByAmount(0, pixels).perform();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCURRED WHILE SCROLLING");
        }
    }

    protected static void scrollToElement(WebElement element) {
        try {
            Actions actions = new Actions(driver);
            actions.scrollToElement(element).perform();
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SCROLLING TO ELEMENT");
        }
    }

    protected static void jsClick(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].click();", element);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE CLICKING ELEMENT USING JAVASCRIPT");
        }
    }

    protected static void jsScrollToElement(WebElement element) {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("arguments[0].scrollIntoView(true);", element);
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SCROLLING TO ELEMENT USING JAVASCRIPT");
        }
    }

    protected static void jsScrollToTop() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, 0);");
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SCROLLING TO TOP USING JAVASCRIPT");
        }
    }

    protected static void jsScrollToBottom() {
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        } catch (Exception e) {
            Assert.fail("ERROR : OCCUR WHILE SCROLLING TO BOTTOM USING JAVASCRIPT");
        }
    }
}


// Additional methods like scroll, jsClick, frameSwitch, actions, etc. can be added here in the same cleaned format.


