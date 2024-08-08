package com.cydeo.pages;

import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class BookPage extends BasePage {

    @FindBy(xpath = "//table/tbody/tr")
    public List<WebElement> allRows;

    @FindBy(xpath = "//input[@type='search']")
    public WebElement search;

    @FindBy(xpath = "//div[@class='portlet-title']//a")
    public WebElement addBook;

    @FindBy(id = "book_categories")
    public WebElement mainCategoryElement;

    @FindBy(css = ".dataTables_info")
    public WebElement bookCount;

    @FindBy(name = "name")
    public WebElement bookName;


    @FindBy(xpath = "(//input[@type='text'])[4]")
    public WebElement author;


    @FindBy(name = "year")
    public WebElement year;

    @FindBy(name = "isbn")
    public WebElement isbn;

    @FindBy(id = "description")
    public WebElement description;

    @FindBy(id="book_group_id")
    public WebElement categoryDropdown;

    @FindBy(partialLinkText = "Save changes")
    public WebElement saveChanges;

    @FindBy(xpath = "//div[@class='toast-message']")
    public WebElement toastMessage;




    public WebElement editBook(String book) {
        String xpath = "//td[3][.='" + book + "']/../td/a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }

    public void addBook(List<String> bookDetails){
        bookName.sendKeys(bookDetails.get(0));
        isbn.sendKeys(bookDetails.get(1));
        year.sendKeys(bookDetails.get(2));
        author.sendKeys(bookDetails.get(3));
        BrowserUtil.selectByVisibleText(categoryDropdown,bookDetails.get(4));
        saveChanges.click();
    }




}
