package site.nomoreparties.stellarburgers;

import io.qameta.allure.Description;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.WebElement;
import register.site.nomoreparties.stellarburgers.BaseTest;
import site.nomoreparties.stellarburgers.page.MainPage;

import java.util.List;

@RunWith(Parameterized.class)
public class ParameterizedTestTransition extends BaseTest{

    private final int indexElement;
    public ParameterizedTestTransition(int indexElement){
        this.indexElement = indexElement;

    }

    @Before
    public void createTestData(){
        driver.get("https://stellarburgers.nomoreparties.site/");
    }

    @Parameterized.Parameters
    public static Object[][] getClassName(){
        return new Object[][]{
                {2},
                {1},
                {0},
        };
    }

    @Test
    @Description(" переходы к разделам:«Булки» «Соусы» «Начинки»")
    public void checkTransitionIngredients() {
        MainPage mainPage = new MainPage(driver);
        List<WebElement> elements = mainPage.getIngredientsHeader();

        try {
            elements.get(indexElement).click();
            Assert.assertEquals(elements.get(indexElement).getAttribute("class"),"tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect");
        }catch (ElementClickInterceptedException exception){
            elements.get(indexElement+1).click();
            Assert.assertEquals(elements.get(indexElement).getAttribute("class"),"tab_tab__1SPyG  pt-4 pr-10 pb-4 pl-10 noselect");
        }



    }
}