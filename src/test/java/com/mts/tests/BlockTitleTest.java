package com.mts.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BlockTitleTest extends BaseTest {

    @Test
    @DisplayName("1. Проверка названия блока 'Онлайн пополнение без комиссии'")
    void testBlockTitle() {
        WebElement blockTitle = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//*[contains(text(), 'Онлайн пополнение без комиссии')]")
                ));

        assertTrue(blockTitle.getText().contains("Онлайн пополнение без комиссии"),
                "Заголовок блока не соответствует ожидаемому");

        System.out.println("✓ Название блока корректно: " + blockTitle.getText());
    }
}
