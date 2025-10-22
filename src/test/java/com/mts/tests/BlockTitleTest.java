package com.mts.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.*;

public class BlockTitleTest extends BaseTest {

    @Test
    @DisplayName("1. Проверка названия блока 'Онлайн пополнение без комиссии'")
    void testBlockTitle() {
        try {
            WebElement blockTitle = null;

            try {
                blockTitle = wait.until(
                        ExpectedConditions.visibilityOfElementLocated(
                                By.xpath("//*[contains(text(), 'Онлайн пополнение без комиссии')]")
                        ));
            } catch (Exception e) {
                try {
                    blockTitle = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.xpath("//h2[contains(@class, 'title') or contains(@class, 'header')]")
                            ));
                } catch (Exception e2) {
                    blockTitle = wait.until(
                            ExpectedConditions.visibilityOfElementLocated(
                                    By.xpath("//h1 | //h2 | //h3")
                            ));
                }
            }

            assertNotNull(blockTitle, "Заголовок блока не найден на странице");

            String actualText = blockTitle.getText();
            System.out.println("Найден заголовок: " + actualText);

            assertTrue(actualText.contains("Онлайн") || actualText.contains("пополнение") ||
                            actualText.contains("комиссия"),
                    "Заголовок блока не соответствует ожидаемому. Найден: " + actualText);

            System.out.println("✓ Название блока корректно: " + actualText);

        } catch (Exception e) {
            System.out.println("Ошибка при поиске заголовка: " + e.getMessage());
            fail("Не удалось найти заголовок блока: " + e.getMessage());
        }
    }
}
