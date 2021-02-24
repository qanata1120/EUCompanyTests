package test.API;

import org.testng.annotations.Test;
import pages.API.IEndpoint;
import pages.API.IRequestBuilder;

import java.util.ArrayList;

import static org.hamcrest.Matchers.*;

public class JsonplaceholderTest {

    private static final String URL = "https://jsonplaceholder.typicode.com";

    //Verify that all posts have UserId, Id, Title and Body
    @Test(description = "Validate the structure of each Post in response")
    public void verifyPostsBody() {
        IRequestBuilder.getResource(URL + IEndpoint.POSTS)
                .then()
                .assertThat()
                .body("$", everyItem(hasKey("userId")))
                .body("$", everyItem(hasKey("id")))
                .body("$", everyItem(hasKey("title")))
                .body("$", everyItem(hasKey("body")));
    }

    //Find all posts of valid user, verify only posts of that user were returned
    @Test(description = "GET posts by valid User Id")
    public void getPostsByValidUserId() {
        int userId = 5;

        IRequestBuilder.getResource("userId", userId, URL + IEndpoint.POSTS)
                .then()
                .assertThat()
                .body("userId", everyItem(is(userId)));
    }

    //Try to get posts for invalid user, verify no post was returned
    @Test(description = "GET all posts by invalid User Id")
    public void getPostsByInvalidUserId() {
        int userId = 23;

        IRequestBuilder.getResource("userId", userId, URL + IEndpoint.POSTS)
                .then()
                .assertThat() // 404 would be expected here as resource doesn't exist
                .body("size()", is(0));
    }

    //Verify that all users have Id, Name, Username, Email, Address, Phone, Website, Company
    @Test(description = "Validate the structure of each User in response")
    public void validateUsersBody() {
        IRequestBuilder.getResource(URL + IEndpoint.USERS)
                .then()
                .assertThat()
                .body("$", everyItem(hasKey("id")))
                .body("$", everyItem(hasKey("name")))
                .body("$", everyItem(hasKey("username")))
                .body("$", everyItem(hasKey("email")))
                .body("$", everyItem(hasKey("address")))
                .body("$", everyItem(hasKey("phone")))
                .body("$", everyItem(hasKey("website")))
                .body("$", everyItem(hasKey("company")));
    }

    @Test(description = "GET user by valid username")
    public void getUserByValidUsername() {
        String userName = "Delphine";

        IRequestBuilder.getResource("username", userName, URL + IEndpoint.USERS)
                .then()
                .assertThat()
                .body("size()", is(1))
                .body("[0].username", is(userName));
    }

    @Test(description = "GET user by invalid username")
    public void getUserByInvalidUsername() {
        String invalidUsername = "Non existing username";

        IRequestBuilder.getResource("username", invalidUsername, URL + IEndpoint.USERS)
                .then()
                .assertThat()
                .body("size()", is(0));
    }

    @Test(description = "GET all comments under posts of specific user, validate email addresses format")
    public void verifyEmailsHaveValidFormat() {
        String userName = "Delphine";
        String validFormat = "^[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}$";

        int userId =
                IRequestBuilder.getResource("username", userName, URL + IEndpoint.USERS)
                        .then()
                        .body("size()", is(1), "[0].username", is(userName))
                        .extract()
                        .path("[0].id");

        ArrayList<Integer> postIds =
                IRequestBuilder.getResource("userId", userId, URL + IEndpoint.POSTS)
                        .then()
                        .extract()
                        .path("id");

        IRequestBuilder.getResource("postId", postIds, URL + IEndpoint.COMMENTS)
                .then()
                .assertThat()
                .body("postId", everyItem(in(postIds)))
                .body("email", everyItem(matchesPattern(validFormat)));
    }
}
