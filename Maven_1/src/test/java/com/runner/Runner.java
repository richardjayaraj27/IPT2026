package com.runner;

import com.base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class Runner extends BaseClass {

    public static void main(String[] args) {

        // Browser Launch
        LaunchBrowser("chrome");

        // URL Launch
        launchUrl("https://www.amazon.in/");

        // Input and Click
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        passInput(searchBox, "Laptop");
        WebElement searchBtn = driver.findElement(By.id("nav-search-submit-button"));
        elementClick(searchBtn);

        // Navigation
        navigate("back");
        navigate("forward");
        navigate("refresh");

        // Waits
        waitForElement(searchBox, 10);
        waitForElementToBeClickable(searchBtn, 10);
        waitForElementPresence(By.id("twotabsearchtextbox"), 10);
        waitForText(searchBox, "Laptop", 10);
        waitForElementInvisibility(searchBtn, 5);
        waitForAlert(5);

        // Alerts (example only, uncomment if alert exists)
        // alertSendKeys("Test");
        // System.out.println("Alert Text: " + alert("gettext"));
        // alert("accept");

        // Actions
        WebElement mobilesLink = driver.findElement(By.linkText("Mobiles"));
        action("movetoelement", mobilesLink);
        action("click", mobilesLink);
        action("doubleclick", mobilesLink);
        action("rightclick", mobilesLink);
        action("clickandhold", mobilesLink);
        action("release", mobilesLink);
        action("draganddrop", searchBox, searchBtn);

        // Frames (example only, uncomment if frame exists)
        // frameSwitch(0);
        // frameSwitch("frameName");
        // frameSwitch(searchBox);
        // frameSwitchToDefaultContent();
        // frameSwitchToParentFrame();

        // Robot (example only)
        // robotKeyPress(KeyEvent.VK_ENTER);

        // Checkbox (example only, uncomment if checkbox exists)
        // WebElement checkbox = driver.findElement(By.id("checkboxId"));
        // checkBox(checkbox, "select");
        // checkBox(checkbox, "deselect");
        // checkBox(checkbox, "click");

        // Element State
        System.out.println("Is Enabled: " + isEnabled(searchBox));
        System.out.println("Is Displayed: " + isDisplayed(searchBox));

        // Dropdown (example only, uncomment if dropdown exists)
        // WebElement dropdown = driver.findElement(By.id("dropdownId"));
        // selectOptions(dropdown, "value", "option1");
        // deselectOptions(dropdown, "index", "0");
        // System.out.println("Options: " + getOptions(dropdown));

        // Window Handling (example only, uncomment if multiple windows exist)
        // String currentHandle = getCurrentWindowHandle();
        // Set<String> allHandles = getAllWindowHandles();
        // switchToWindow(currentHandle);
        // windowHandling(0);

        // Page Info
        System.out.println("Title: " + getTitle());
        System.out.println("URL: " + getCurrentUrl());
        System.out.println("Search Text: " + getText(searchBox));
        System.out.println("Search Attribute: " + getAttribute(searchBox, "placeholder"));

        // Screenshot
        takeScreenshot();

        // Scroll
        scroll(500);
        scrollToElement(searchBtn);

        // JavaScript
        jsClick(searchBtn);
        jsScrollToElement(searchBtn);
        jsScrollToTop();
        jsScrollToBottom();

        // Close and Quit
        closeBrowser();
        browserTerminate();
    }
}

