package com.cydeo.steps;

import com.cydeo.pages.BookPage;
import com.cydeo.utility.ConfigurationReader;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class LiveLabsStepDefs {

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        // DB_Util.createConnection();
        System.out.println("-----------------------------------------");
        System.out.println("--------DB CONN IS DONE BY HOOK ---------");
        System.out.println("-----------------------------------------");
    }

    String actualCategory;

    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {

        String query = "SELECT bc.name FROM book_borrow bb " +
                "inner join  books b on bb.book_id=b.id " +
                "inner join  book_categories bc on bc.id=b.book_category_id " +
                "group by bc.name " +
                "order by count(*) desc " +
                "limit 1";

        // Run Query
        DB_Util.runQuery(query);

        // Retrieve data
        actualCategory = DB_Util.getFirstRowFirstColumn();
        System.out.println("actualCategory = " + actualCategory);

    }

    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String expectedCategory) {
        Assert.assertEquals(expectedCategory, actualCategory);
    }

    /**
     * Live Lab feature 2
     */

    BookPage bookPage=new BookPage();
    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        bookPage.addBook.click();
    }
    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String bookName) {
        bookPage.bookName.sendKeys(bookName);
    }
    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String isbn) {
        bookPage.isbn.sendKeys(isbn);
    }
    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {
        bookPage.year.sendKeys(year);
    }
    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {
        bookPage.author.sendKeys(author);
    }
    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String bookCategory) {

    }
    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {

    }
    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String expectedMessage) {

    }
    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String expectedBookName) {

    }
}
