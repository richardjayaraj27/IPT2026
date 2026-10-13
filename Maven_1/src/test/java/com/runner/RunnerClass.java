package com.runner;

import com.base.BaseClass;
import com.pageobjectmanager.PageObjectManager;

    public class RunnerClass extends BaseClass {

        public static void main(String[] args) throws InterruptedException {
            LaunchBrowser(PageObjectManager.getPageObjectManager().getFileReaderManager().getProperty("browser"));
            launchUrl(PageObjectManager.getPageObjectManager().getFileReaderManager().getProperty("url"));

            PageObjectManager.getPageObjectManager().getLoginPage().validateLogin();
            PageObjectManager.getPageObjectManager().getSearchPage().addSonyVaioToCart();
            PageObjectManager.getPageObjectManager().getSearchPage().verifyCart();
            PageObjectManager.getPageObjectManager().getCheckoutPage().completePurchase();
            PageObjectManager.getPageObjectManager().getLoginPage().logout();
        }
    }

