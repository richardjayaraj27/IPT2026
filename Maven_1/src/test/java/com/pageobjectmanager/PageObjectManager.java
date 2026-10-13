package com.pageobjectmanager;

import com.pageobjectmodel.LoginPage;
import com.pageobjectmodel.CheckoutPage;
import com.pageobjectmodel.SearchPage;
import com.utility.FileReaderManager;
import org.apache.poi.ss.formula.functions.Address;

public class PageObjectManager {

    private FileReaderManager fileReaderManager;
    private static PageObjectManager pageObjectManager;
    private LoginPage loginPage;
    private SearchPage SearchPage;
    private CheckoutPage checkoutPage;


    public FileReaderManager getFileReaderManager() {
        if (fileReaderManager == null) {
            fileReaderManager = new FileReaderManager();
        }
        return fileReaderManager;
    }

    public static PageObjectManager getPageObjectManager() {
        if (pageObjectManager == null) {
            pageObjectManager = new PageObjectManager();
        }
        return pageObjectManager;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            loginPage = new LoginPage();
        }
        return loginPage;
    }

    public SearchPage getSearchPage() {
        if (SearchPage == null) {
            SearchPage = new SearchPage();
        }
        return SearchPage;
    }


    public CheckoutPage getCheckoutPage() {
        if (checkoutPage == null) {
            checkoutPage = new CheckoutPage();
        }
        return checkoutPage;
    }


}

