package com.interfaceelements;

public interface CheckoutInterfaceElements {
    String name_xpath = "//input[@id='name']";
    String country_xpath = "//input[@id='country']";
    String city_xpath = "//input[@id='city']";
    String card_xpath = "//input[@id='card']";
    String month_xpath = "//input[@id='month']";
    String year_xpath = "//input[@id='year']";
    String purchase_xpath = "//button[text()='Purchase']";
    String confirmation_xpath = "//div[contains(@class,'sweet-alert')]";
    String orderDetails_xpath = "//p[contains(@class,'Lead')]";
    String confirmationOK_xpath = "//button[text()='OK']";
    String closeButton_css = "#orderModal .modal-footer button[data-dismiss='modal']";
}
