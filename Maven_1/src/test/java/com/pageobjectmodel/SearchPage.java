package com.pageobjectmodel;

import com.base.BaseClass;
import com.interfaceelements.SearchPageInterfaceElements;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SearchPage extends BaseClass implements SearchPageInterfaceElements {

    @FindBy(xpath = laptops_xpath)
    public static WebElement laptops;

    @FindBy(xpath = sonyVaio_xpath)
    public static WebElement sonyVaio;

    @FindBy(xpath = addToCart_xpath)
    public static WebElement addToCart;

    @FindBy(xpath = cart_xpath)
    public static WebElement cart;

    @FindBy(xpath = cartProduct_xpath)
    public static WebElement cartProduct;

    @FindBy(xpath = placeOrder_xpath)
    public static WebElement placeOrder;

    public SearchPage() {
        PageFactory.initElements(driver, this);
    }

    public static void addSonyVaioToCart() {
        elementClick(laptops);
        waitForElement(sonyVaio, 10);
        elementClick(sonyVaio);
        waitForElement(addToCart, 10);
        elementClick(addToCart);
        waitForAlert(10);
        System.out.println("Alert Message: " + alert("gettext"));
        alert("accept");
    }

    public static void verifyCart() {
        elementClick(cart);
        waitForElement(cartProduct, 10);
        System.out.println("Product in cart: " + getText(cartProduct));
        takeScreenshot();
        elementClick(placeOrder);
    }
}
