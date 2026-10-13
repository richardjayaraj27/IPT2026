package com.pageobjectmodel;

import com.base.BaseClass;
import com.interfaceelements.LogininterfaceElements;
import com.pageobjectmanager.PageObjectManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BaseClass implements LogininterfaceElements {

    @FindBy(xpath = loginmenu_xpath)
    public static WebElement loginmenu;

    @FindBy(id = username_fieldID)
    public static WebElement username_field;

    @FindBy(id = password_fieldID)
    public static WebElement password_field;

    @FindBy(css = login_buttonCSS)
    public static WebElement login_button;

    @FindBy(id = searchID)
    public static WebElement searchTextID;

    @FindBy(xpath = logout_xpath)
    public static WebElement logout;

    public LoginPage() {
        PageFactory.initElements(driver, this);
    }

    public static void validateLogin() {
        elementClick(loginmenu);
        waitForElement(username_field, 10);
        passInput(username_field, PageObjectManager.getPageObjectManager().getFileReaderManager().getProperty("username"));
        passInput(password_field, PageObjectManager.getPageObjectManager().getFileReaderManager().getProperty("password"));
        elementClick(login_button);
        waitForElement(searchTextID, 10);
        System.out.println(getText(searchTextID));
        takeScreenshot();
    }

    public static void logout() {
        waitForElement(logout, 10);
        elementClick(logout);
        System.out.println("Logout successful");
    }
}
