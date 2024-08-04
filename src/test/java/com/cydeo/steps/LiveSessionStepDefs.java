package com.cydeo.steps;

import com.cydeo.pages.UsersPage;
import com.cydeo.utility.BrowserUtil;
import com.cydeo.utility.DB_Util;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.support.ui.Select;

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

}
