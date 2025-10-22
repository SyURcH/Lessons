package com.mts.tests.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class OnlinePaymentPage extends BasePage {

    @FindBy(xpath = "//*[contains(text(), 'Онлайн пополнение без комиссии')]")
    private WebElement blockTitle;

    @FindBy(xpath = "//div[contains(@class, 'pay-systems')]//img")
    private List<WebElement> paymentLogos;

    @FindBy(xpath = "//a[contains(text(), 'Подробнее о сервисе')]")
    private WebElement detailsLink;

    @FindBy(xpath = "//label[contains(., 'Услуги связи')]")
    private WebElement servicesTab;

    @FindBy(xpath = "//label[contains(., 'Домашний интернет')]")
    private WebElement internetTab;

    @FindBy(xpath = "//label[contains(., 'Рассрочка')]")
    private WebElement installmentTab;

    @FindBy(xpath = "//label[contains(., 'Задолженность')]")
    private WebElement debtTab;

    @FindBy(xpath = "//input[@type='tel' or contains(@placeholder, 'номер')]")
    private WebElement phoneInput;

    @FindBy(xpath = "//button[contains(text(), 'Продолжить')]")
    private WebElement continueButton;

    @FindBy(xpath = "//input[@placeholder]")
    private List<WebElement> inputPlaceholders;

    public OnlinePaymentPage(WebDriver driver) {
        super(driver);
    }

    public String getBlockTitle() {
        try {
            wait.until(ExpectedConditions.visibilityOf(blockTitle));
            return blockTitle.getText();
        } catch (Exception e) {
            return "Заголовок не найден";
        }
    }

    public int getPaymentLogosCount() {
        try {
            return paymentLogos.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public void clickDetailsLink() {
        try {
            detailsLink.click();
        } catch (Exception e) {
            System.out.println("Не удалось кликнуть по ссылке 'Подробнее о сервисе': " + e.getMessage());
        }
    }

    public void selectServicesTab() {
        try {
            servicesTab.click();
        } catch (Exception e) {
            System.out.println("Не удалось выбрать вкладку 'Услуги связи': " + e.getMessage());
        }
    }

    public void selectInternetTab() {
        try {
            internetTab.click();
        } catch (Exception e) {
            System.out.println("Не удалось выбрать вкладку 'Домашний интернет': " + e.getMessage());
        }
    }

    public void selectInstallmentTab() {
        try {
            installmentTab.click();
        } catch (Exception e) {
            System.out.println("Не удалось выбрать вкладку 'Рассрочка': " + e.getMessage());
        }
    }

    public void selectDebtTab() {
        try {
            debtTab.click();
        } catch (Exception e) {
            System.out.println("Не удалось выбрать вкладку 'Задолженность': " + e.getMessage());
        }
    }

    public void enterPhoneNumber(String phone) {
        try {
            phoneInput.clear();
            phoneInput.sendKeys(phone);
        } catch (Exception e) {
            System.out.println("Не удалось ввести номер телефона: " + e.getMessage());
        }
    }

    public void clickContinue() {
        try {
            continueButton.click();
        } catch (Exception e) {
            System.out.println("Не удалось нажать кнопку 'Продолжить': " + e.getMessage());
        }
    }

    public String getPhoneInputPlaceholder() {
        try {
            return phoneInput.getAttribute("placeholder");
        } catch (Exception e) {
            return "Плейсхолдер не найден";
        }
    }

    public List<String> getAllPlaceholders() {
        try {
            return inputPlaceholders.stream()
                    .map(element -> element.getAttribute("placeholder"))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return java.util.Collections.emptyList();
        }
    }

    public void switchToNewWindow() {
        try {
            for (String windowHandle : driver.getWindowHandles()) {
                driver.switchTo().window(windowHandle);
            }
        } catch (Exception e) {
            System.out.println("Не удалось переключиться на новое окно: " + e.getMessage());
        }
    }

    public boolean isBlockTitleDisplayed() {
        try {
            return blockTitle.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isDetailsLinkDisplayed() {
        try {
            return detailsLink.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getCurrentUrl() {
        return driver.getCurrentUrl();
    }
}