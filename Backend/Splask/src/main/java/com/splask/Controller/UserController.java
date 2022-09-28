package com.splask.Controller;


import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import com.splask.Models.User;
import com.splask.Repositories.UserDB;
import com.splask.Services.ImageService;
import io.swagger.v3.core.util.Json;
import net.minidev.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import com.sun.org.apache.xerces.internal.util.URI;

import net.minidev.json.JSONObject;

@RestController 
public class UserController {

    @Autowired
    UserDB userRepository;

    @Autowired
    ImageService service;

//  Gets user by ID from the database
    @GetMapping("/user/{id}")
    User getUsername(@PathVariable Integer id) {
        return userRepository.findById(id).orElseThrow(RuntimeException::new);
    }

//  Get all the users from the database
    @RequestMapping("/user")
    JSONObject getAllUsers() {

        JSONObject responseBody = new JSONObject();
        JSONArray allUsers = new JSONArray();

        allUsers.addAll(userRepository.findAll());

        responseBody.put("status",200);
        responseBody.put("message","Successfully received all Users from database");
        responseBody.put("users",allUsers);

        return responseBody;
    }

    /**
     * Registers user to the database and checks if there is an
     * existing user registered with the same username
     * @param object
     * @returnJSON Object that hold success or fail statuses and messages
     * 
     */
    @PostMapping("/register")
    JSONObject registerUser(@RequestBody JSONObject object) {
        JSONObject responseBody = new JSONObject();

        User newUser = new User();

        newUser.setUsername(object.getAsString("username"));
        newUser.setUserPassword(object.getAsString("userPassword"));
        newUser.setFullName(object.getAsString("full_name"));

        List<User> users = userRepository.findAll();

        for (User user : users) {
            if (user.getUsername().equals(newUser.getUsername())) {
                responseBody.put("status", 400);
                responseBody.put("message", "User Already Exists!");
                return responseBody;
            }
        }

        if (newUser.getUserPassword().length() < 3) {
            responseBody.put("status", 400);
            responseBody.put("message", "Password should be at least 3 characters long");
            return responseBody;
        }

//      Checks length, upper, lower case, numeric value and special character of the input password

//        if (!newUser.isAllPresent(newUser.password)){ //Checks if the password meets the safety criteria
//            responseBody.put("status", 400);
//            responseBody.put("message", "Please enter a valid password of at least 4 characters containing uppercase, lowercase\n" +
//                    "\t// special character & numeric value");
//        }

        userRepository.save(newUser);
        responseBody.put("status", 200);
        responseBody.put("message", "Account successfully created!");

        return responseBody;
    }

    /**
     * Updates user log status
     * @param user
     * @return JSON Object that hold success or fail statuses and messages
     * 
     */
    @PutMapping("/login")
    JSONObject loginUser(@RequestBody User user) {
        JSONObject responseBody = new JSONObject();
        List<User> users = userRepository.findAll();

        for (User userInDB : users) {

            if (userInDB.equals(user)) {
                userInDB.setLoggedIn(1);

                responseBody.put("user_id", userInDB.getUserId());
                responseBody.put("fullname", userInDB.getFullName());
                responseBody.put("username", userInDB.getUsername());
                        responseBody.put("status", 200);
                        responseBody.put("message", "Login Successful");
                        userRepository.save(userInDB);
                        return responseBody;
                    }
                }


                responseBody.put("status", 400);
                responseBody.put("message", "Login Failed");
                return responseBody;
    }

    /**
     * Updates user log status
     * @param user
     * @return JSON Object that hold success or fail statuses and messages
     */
        @PutMapping("/logout")
        JSONObject logoutUser (@RequestBody User user){
            JSONObject responseBody = new JSONObject();
            List<User> users = userRepository.findAll();

            for (User userInDB : users) {
                if (userInDB.getUsername().equals(user.getUsername())) {

                    userInDB.setLoggedIn(0);

                    responseBody.put("status", 200);
                    responseBody.put("message", "User Successfully logged out");

                    userRepository.save(userInDB);
                    return responseBody;
                }
            }
            responseBody.put("status", 400);
            responseBody.put("message", "Failure to logout");
            return responseBody;
        }

//	 	Delete user by id
        @DeleteMapping("/user/{id}")
        JSONObject deleteUser (@PathVariable Integer id){
            JSONObject responseBody = new JSONObject();
            userRepository.deleteById(id);
            responseBody.put("status", 200);
            responseBody.put("message", "Successfully deleted user");
            return responseBody;
        }

//		Delete ALL users
        @DeleteMapping("/user/all")
        JSONObject deleteUsers () {
            JSONObject responseBody = new JSONObject();
            userRepository.deleteAll();

            responseBody.put("status", 200);
            responseBody.put("message", "Successfully deleted all users");

            return responseBody;
        }
        
        /**
         * Gets all the projects that the user is enrolled in  
         * @param user_id
         * @return JSON Object that hold success or fail statuses and messages
         */
        @GetMapping("/user/{user_id}/projects")
        JSONObject obtainProjects (@PathVariable Integer user_id)
        {
            User user = userRepository.getById(user_id);
            JSONArray projects = new JSONArray();
            JSONObject responseBody = new JSONObject();

            projects.addAll(user.getProject());


            responseBody.put("status", 200);
            responseBody.put("message", "Successfully retrieved all projects from " + user.getUsername());
            responseBody.put("projects", projects);

            return responseBody;
        }

        /**
         * Gets all the teams that the user is enrolled in
         * @param user_id
         * @return JSON Object that hold success or fail statuses and messages
         */
        @GetMapping("/user/{user_id}/teams")
        JSONObject obtainTeams(@PathVariable Integer user_id)
        {
            User user = userRepository.getById(user_id);
            JSONArray teams = new JSONArray();
            JSONObject responseBody = new JSONObject();

            teams.addAll(user.getTeam());


            responseBody.put("status", 200);
            responseBody.put("message", "Successfully retrieved all teams from " + user.getUsername());
            responseBody.put("teams", teams);

            return responseBody;
        }
        
        /**
         * Gets all the tasks that the user is enrolled in
         * @param user_id
         * @return JSON Object that hold success or fail statuses and messages
         */
        @GetMapping("/user/{user_id}/tasks")
        JSONObject obtainTasks (@PathVariable Integer user_id)
        {
            User user = userRepository.getById(user_id);
            JSONArray tasks = new JSONArray();
            JSONObject responseBody = new JSONObject();

            tasks.addAll(user.getTasks());

            responseBody.put("tasks", tasks);
            responseBody.put("status", 200);
            responseBody.put("message", "Successfully retrieved all tasks from " + user.getUsername());

            return responseBody;
        }


    /**
     * gets Image uploaded from the user
     *
     */
    @GetMapping("user/{id}/image")
    public JSONObject getImageById(@PathVariable Integer id) throws IOException {
        JSONObject responseBody = new JSONObject();
        byte[] bytes = service.getUserImageById(id);


        responseBody.put("image", Arrays.toString(bytes));
        responseBody.put("status",200);
        responseBody.put("message","Successfully retrieved image");

        return responseBody;
    }


    @PutMapping("user/{id}/image")
    public JSONObject uploadImage(@PathVariable Integer id, @RequestBody JSONObject request) throws IOException {
        JSONObject responseBody = new JSONObject();
        ArrayList<Integer> imageTemp = (ArrayList<Integer>) request.get("image");

        byte[] image = new byte[imageTemp.size()];
        for (int i = 0; i < imageTemp.size(); i++)
        {
            image[i] = (byte) ((int) imageTemp.get(i));
        }

        service.uploadNewUserImage(id, image);
        responseBody.put("status",200);
        responseBody.put("message","Successfully changed image");


        return responseBody;

    }


}
