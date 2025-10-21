package com.mts.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class DetailsLinkTest extends BaseTest {

    @Test
    @DisplayName("3. Проверка работы ссылки 'Подробнее о сервисе'")
    void testDetailsLink() {
        WebElement detailsLink = wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath("//a[contains(text(), 'Подробнее о сервисе') or " +
                                "contains(text(), 'Подробнее')]")
                ));

        String originalUrl = driver.getCurrentUrl();
        detailsLink.click();

        wait.until(ExpectedConditions.not(ExpectedConditions.urlToBe(originalUrl)));

        String newUrl = driver.getCurrentUrl();
        assertNotEquals(originalUrl, newUrl,
                "URL не изменился после клика по ссылке");

        WebElement pageContent = wait.until(
                ExpectedConditions.presenceOfElementLocated(By.tagName("body")));
        assertNotNull(pageContent, "Новая страница не загрузилась");

        System.out.println("✓ Ссылка 'Подробнее о сервисе' работает корректно");
        System.out.println("  Переход с: " + originalUrl);
        System.out.println("  Переход на: " + newUrl);
    }
}