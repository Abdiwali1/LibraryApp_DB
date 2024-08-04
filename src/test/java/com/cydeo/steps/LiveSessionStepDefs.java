package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.pages.UsersPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.opentelemetry.semconv.trace.attributes.SemanticAttributes;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

import java.awt.print.Book;

public class LiveSessionStepDefs {

    UsersPage usersPage = new UsersPage();

    String actualUserCount;

    @When("the user gets {string} user count")
    public void the_user_gets_user_count(String status) {
//        Select select =new Select(usersPage.userStatusDropdown);
//        select.selectByVisibleText(status);

        BrowserUtil.selectByVisibleText(usersPage.userStatusDropdown, status);

        BrowserUtil.waitFor(2);

        String userDetails = usersPage.userCount.getText();
        // Showing 1 to 10 of 120 entries

//        int startIndex=userDetails.indexOf("f")+2;
//        int endIndex = userDetails.indexOf("entries")-1;
//
//        String actualCount= userDetails.substring(startIndex,endIndex);

        actualUserCount= usersPage.getCount(userDetails);

        System.out.println(status+ "actualUserCount = "+actualUserCount);

    }
    @When("the {string} user count should be equal database")
    public void the_user_count_should_be_equal_database(String status) {
        String query="select count(*) from users " +
                "where status='"+status+"' and user_group_id<>1 ";

        DB_Util.runQuery(query);

        String expectedCount = DB_Util.getFirstRowFirstColumn();

        // To be able to reach out actualUserCount variable, declare it to class level not in previous step:
        Assert.assertEquals(expectedCount,actualUserCount);
    }

    BookPage bookPage=new BookPage();
    String actualBookCount;
    // LS-02
    @When("the user gets {string} book count")
    public void the_user_gets_book_count(String categoryName) {

        BrowserUtil.selectByVisibleText(bookPage.mainCategoryElement,categoryName);
        BrowserUtil.waitFor(2);

        String bookDetails = bookPage.bookCount.getText();
        actualBookCount = bookPage.getCount(bookDetails);
        System.out.println("actualBookCount = " + actualBookCount);


    }
    @Then("the {string} book count should be equal with database")
    public void the_book_count_should_be_equal_with_database(String categoryName) {

        String query="select count(*) from books b " +
                "inner join book_categories bc on bc.id=b.book_category_id " +
                "where bc.name='"+categoryName+"'";

        DB_Util.runQuery(query);

        String expectedBookCount = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedBookCount,actualBookCount);


    }

}
