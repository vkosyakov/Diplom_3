package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebElement;
import register.site.nomoreparties.stellarburgers.BaseTest;
import site.nomoreparties.stellarburgers.page.MainPage;
import userAPI.User;
import userAPI.UserCredentials;

import java.util.List;

@RunWith(Parameterized.class)
public class ParameterizedTestTransition extends BaseTest {

    private final int numberElement;

    private final String className;

    public ParameterizedTestTransition(int numberElement, String className){
        this.numberElement = numberElement;
        this.className = className;
    }

    @Before
    public void createTestData(){
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Parameterized.Parameters
    public static Object[][] getClassName(){
        return new Object[][]{
            {2,"tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect"},
            {1,"tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect"},
        };
    }

    @Test
    @Description(" переходы к разделам:«Булки» «Соусы» «Начинки»")
    public void checkTransitionIngredients() {
        MainPage mainPage = new MainPage(driver);
        List<WebElement> elements = mainPage.getIngredientsHeader();
        elements.get(numberElement).click();
        Assert.assertEquals(elements.get(numberElement).getAttribute("class"),className);
    }
}
