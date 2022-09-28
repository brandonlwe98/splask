package com.coms3091mc3.projectmanager;
//
//import android.content.Context;
//
//import androidx.test.platform.app.InstrumentationRegistry;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//import static org.junit.Assert.*;
//
///**
// * Instrumented test, which will execute on an Android device.
// *
// * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
// */
//@RunWith(AndroidJUnit4.class)
//public class Test {
//    @Test
//    public void useAppContext() {
//        // Context of the app under test.
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("com.coms3091mc3.projectmanager", appContext.getPackageName());
//    }
//}

//package testing;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withClassName;
import static androidx.test.espresso.matcher.ViewMatchers.withContentDescription;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.StringEndsWith.endsWith;

import android.view.View;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner;

import org.junit.Rule;
import org.junit.runner.RunWith;

// Mock the RequestServerForService class
@RunWith(AndroidJUnit4ClassRunner.class)
@LargeTest   // large execution time
public class ProjectTest {

    private static final int SIMULATED_DELAY_MS = 1000;
    private View decorView;
//    @Rule   // needed to launch the activity
//    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
    public ActivityScenarioRule<LoginActivity> loginRule = new ActivityScenarioRule<>(LoginActivity.class);

    @org.junit.Test
    public void addCalls(){

        String testUsername = "demo12";
        String testPassword = "test";
        String testFullname = "Coverage Test";
        String testTeamName1 = "Team Alpha";
        String testTeamName2 = "Team Omega";
        String testProject = "test splask9";

        // Type in username and password then click register then login button
        onView(withId(R.id.username))
                .perform(typeText(testUsername), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText(testPassword), closeSoftKeyboard());
        onView(withId(R.id.btnRegister)).perform(click());
        onView(withClassName(endsWith("EditText"))).perform(typeText(testFullname), closeSoftKeyboard());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.btnLogin)).perform(click());

        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        onView(withId(R.id.add_project)).perform(click()); //open project menu
        onView(withText("Add Project")).perform(click()); //add project
        onView(withClassName((endsWith("EditText")))).perform(typeText(testProject),closeSoftKeyboard());
        onView(withText("NEW")).perform(click());

        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        onView(withText(testProject)).perform(click()); //enter project

        //ADD ANNOUNCEMENTS
        onView(withId(R.id.btnProjectDescription)).perform(click());
        onView(withClassName((endsWith("EditText")))).perform(typeText("TEST ANNOUNCEMENT"),closeSoftKeyboard());
        onView(withText("ADD")).perform(click());

//        onView(withText(testProject)).perform(click());
        onView(withId(R.id.add_project)).perform(click()); //open project menu

        onView(withText("Add Members")).perform(click());
        onView(withClassName((endsWith("EditText")))).perform(typeText("brandon"),closeSoftKeyboard());
        onView(withText("ADD")).perform(click()); //add new member

        //add team
        onView(withId(R.id.add_project)).perform(click()); //open project menu
        onView(withText("Create Team")).perform(click());
        onView(withClassName((endsWith("EditText")))).perform(typeText(testTeamName1),closeSoftKeyboard());
        onView(withText("ADD")).perform(click()); //add new team

        onView(withId(R.id.add_project)).perform(click()); //open project menu
        onView(withText("Create Team")).perform(click());
        onView(withClassName((endsWith("EditText")))).perform(typeText(testTeamName2),closeSoftKeyboard());
        onView(withText("ADD")).perform(click()); //add new team

        //go team page
        onView(withId(R.id.add_project)).perform(click()); //open project menu
        onView(withText("View Teams")).perform(click());
        onView(withText(testTeamName1)).perform(click());
        onView(withId(R.id.btnAddUser)).perform(click());
        onView(withClassName(endsWith("EditText"))).perform(typeText(testUsername),closeSoftKeyboard());
        onView(withText("ADD")).perform(click()); //add new member to team

        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        onView(withId(R.id.btnAddUser)).perform(click());
        onView(withClassName(endsWith("EditText"))).perform(typeText("brandon"),closeSoftKeyboard());
        onView(withText("ADD")).perform(click()); //add new member to team

        onView(withText(endsWith("Lwe"))).perform(click());
        onView(withText(endsWith("Test"))).perform(click());


    }

    @org.junit.Test
    public void addMemberTest() {
        ActivityScenario<LoginActivity> scenario = loginRule.getScenario();

        // Your test code goes here.

        String testUsername = "brandon";
        String testPassword = "test";
        String testFullname = "Brandon Lwe";

        // Type in username and password then click login button
        onView(withId(R.id.username))
                .perform(typeText(testUsername), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText(testPassword), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        // Verify that welcome message matches full name of user
        onView(withId(R.id.text_dashboard_username)).check(matches(withText(endsWith(testFullname))));

        //Go into a project
        onView(withText("Task")).perform(click());
//        onView(withId(R.id.overviewText)).check(matches(withText(endsWith("Brandon Lwe")))); //check if group admin is Brandon Lwe
        onView(withId(R.id.add_project)).perform(click()); //open project menu
        onView(withText("View Teams")).perform(click()); //open project menu

        //Go into team
        onView(withText("team1")).perform(click());
        onView(withId(R.id.btnAddUser)).perform(click());
        onView(withClassName(endsWith("EditText"))).perform(typeText("brandon"));
        onView(withText("ADD")).perform(click());

        //Logout
//        onView(withId(R.id.add_project)).perform(click());
//        onView(ViewMatchers.withText("Logout")).perform(click());
    }

    @org.junit.Test
    public void groupChatTest() {
//        ActivityScenario<LoginActivity> scenario = loginRule.getScenario();

        // Your test code goes here.

        String testUsername = "brandon";
        String testPassword = "test";
        String testFullname = "Brandon Lwe";
        String testTeamName = "team1";
        // Type in username and password then click login button
        onView(withId(R.id.username))
                .perform(typeText(testUsername), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText(testPassword), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        //Go to inbox fragment
        onView(withId(R.id.navigation_inbox)).perform(click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }
        //Go to chat fragment (group chat)
        onView(withText(testTeamName)).perform(click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }
//        onView(withId(R.id.teamName)).check(matches(withText(endsWith(testTeamName)))); //check if team name is correct

        //Type a message
        String testMsg = "Hey, just testing the application!";
        onView(withId(R.id.etMsg)).perform(typeText(testMsg), closeSoftKeyboard());

        //Send the message
        onView(withId(R.id.btnMsgSend)).perform(click());

        //Check if message appeared
        onView(withId(R.id.txtConvo)).check(matches(withText(endsWith(testMsg))));


        //Logout
        onView(withId(R.id.navigation_dashboard)).perform(click());
        onView(withId(R.id.add_project)).perform(click());
        onView(ViewMatchers.withText("Logout")).perform(click());
    }

    @org.junit.Test
    public void randomTests() {
//        ActivityScenario<LoginActivity> scenario = loginRule.getScenario();

        // Your test code goes here.

        String testUsername = "brandon";
        String testPassword = "test";
        String testFullname = "Brandon Lwe";
        String testTeamName = "Splask 2.0 Frontend Team";
        // Type in username and password then click login button
        onView(withId(R.id.username))
                .perform(typeText(testUsername), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText(testPassword), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        //Go into a project
        onView(withText("Task")).perform(click());
//        onView(withId(R.id.overviewText)).check(matches(withText(endsWith("Group Admin: Brandon Lwe")))); //check if group admin is Brandon Lwe
        onView(withId(R.id.add_project)).perform(click()); //open project menu
        onView(withText("Members")).perform(click()); //open member list
        onView(withText("brandon")).check(matches(withText(endsWith("brandon")))); //brandon should be in list of members
//        onView(withText("andrew")).check(matches(withText(endsWith("andrew")))); //andrew should be in list of members
//        onView(withText("Chad")).check(matches(withText(endsWith("Chad")))); //Chad should be in list of members
        onView(withText("BACK")).perform(click());

//        onView(withId(R.id.add_project)).perform(click()); //open project menu
//        onView(withText("Add Task")).perform(click());
//
//        onView(withId(R.id.assignTeamSpinner)).perform(click()); //click spinner dropdown

        //Check if all groups are in the assign task options
//        onView(withText("Marketing Team")).inRoot(RootMatchers.isPlatformPopup()).perform(click());
//        onView(withId(R.id.assignTeamSpinner)).perform(click()); //click spinner dropdown
//        onView(withText("Splask 2.0 Frontend Team")).inRoot(RootMatchers.isPlatformPopup()).perform(click());
//        onView(withId(R.id.assignTeamSpinner)).perform(click()); //click spinner dropdown
//        onView(withText("Backend Splask")).inRoot(RootMatchers.isPlatformPopup()).perform(click());


        //Check the task name edit input
//        onView(withId(R.id.editTextTextTaskName)).perform(typeText("Testing Task Name"), closeSoftKeyboard());
//        onView(withId(R.id.editTextTextTaskName)).check(matches(withText(endsWith("Testing Task Name"))));
//        onView(withText("CANCEL")).perform(click()); // close task dialog


        onView(withId(R.id.add_project)).perform(click()); //open project menu
        onView(withText("Add Members")).perform(click()); //add members menu
        onView(withClassName(endsWith("EditText"))).perform(typeText("brandon"), closeSoftKeyboard());
        onView(withClassName(endsWith("EditText"))).check(matches(withText(endsWith("brandon"))));
        onView(withText("ADD")).perform(click());

        //        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

    }

    @org.junit.Test
    public void randomTests2() {
//        ActivityScenario<LoginActivity> scenario = loginRule.getScenario();

        // Your test code goes here.

        String testUsername = "brandon";
        String testPassword = "test";
        String testFullname = "Brandon Lwe";

        // Type in username and password then click login button
        onView(withId(R.id.username))
                .perform(typeText(testUsername), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText(testPassword), closeSoftKeyboard());
        onView(withId(R.id.btnRegister)).perform(click());
        onView(withClassName(endsWith("EditText"))).perform(typeText(testFullname), closeSoftKeyboard());
        onView(withText("OK")).perform(click());
        onView(withId(R.id.btnLogin)).perform(click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        String testProjectName = "Task";
        String testTaskName = "task1";
        String testTaskDesc = "Testing edit description - Espresso";
        onView(withId(R.id.navigation_projects)).perform(click());
        onView(withId(R.id.navigation_dashboard)).perform(click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(3500);
        } catch (InterruptedException e) {
        }

        onView(withText(endsWith(testProjectName))).perform(click()); //go into project;
        onView(withText(endsWith(testTaskName))).perform(click()); //go into task

        onView(withId(R.id.descriptionTitle)).perform(click());
        onView(withClassName(endsWith("EditText"))).perform(typeText(testTaskDesc), closeSoftKeyboard());
        onView(withText("EDIT")).perform(click());
        onView(withId(R.id.taskDescription)).check(matches(withText(endsWith(testTaskDesc))));


    }

    @org.junit.Test
    public void randomTests4() {

        String testUsername = "brandon";
        String testPassword = "test";
        String testFullname = "Brandon Lwe";
        // Type in username and password then click login button
        onView(withId(R.id.username))
                .perform(typeText(testUsername), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText(testPassword), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        onView(withId(R.id.add_project)).perform(click());
        onView(withText("Add Project")).perform(click());
    }

    @org.junit.Test
    public void testUploadImage() {

        String testUsername = "brandon";
        String testPassword = "test";
        String testFullname = "Brandon Lwe";

        // Type in username and password then click login button
        onView(withId(R.id.username))
                .perform(typeText(testUsername), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText(testPassword), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        // Put thread to sleep to allow volley to handle the request
        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        // Type in username and password then click login button
        onView(withId(R.id.navigation_settings))
                .perform(click());
        onView(withId(R.id.avatar))
                .perform(click());
    }

    @org.junit.Test
    public void addCalls2(){

        String testUsername = "demo12";
        String testPassword = "test";
        String testFullname = "Coverage Test";
        String testTeamName1 = "Team Alpha";
        String testTeamName2 = "Team Omega";
        String testProject = "test splask9";
        String testTask = "task splask9";

        // Type in username and password then click register then login button
        onView(withId(R.id.username))
                .perform(typeText(testUsername), closeSoftKeyboard());
        onView(withId(R.id.password))
                .perform(typeText(testPassword), closeSoftKeyboard());
        onView(withId(R.id.btnLogin)).perform(click());

        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        onView(withText(testProject)).perform(click()); //enter project
        onView(withId(R.id.add_project)).perform(click()); //open project menu

        //add task
        onView(withText("Add Task")).perform(click());
        onView(withClassName((endsWith("EditText")))).perform(typeText(testTask),closeSoftKeyboard());
        onView(withId(R.id.assignTeamSpinner)).perform(click()); //click spinner dropdown
        onView(withText(testTeamName1)).inRoot(RootMatchers.isPlatformPopup()).perform(click());
        onView(withText("ADD")).perform(click());

        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        //go to tasks fragment
        onView(withText(testTask)).perform(click());

        try {
            Thread.sleep(SIMULATED_DELAY_MS);
        } catch (InterruptedException e) {
        }

        //EDIT DESCRIPTION
        onView(withId(R.id.descriptionTitle)).perform(click());
        onView(withClassName((endsWith("EditText")))).perform(typeText("taskOne done"),closeSoftKeyboard());
        onView(withText("EDIT")).perform(click());
        onView(withId(R.id.btnCompleteTask)).perform(click());
    }
}

