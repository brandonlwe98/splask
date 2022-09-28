package com.coms3091mc3.projectmanager.utils;

import com.coms3091mc3.projectmanager.data.User;

/**
 * The type Const.
 */
public class Const {
    /**
     * The constant MOCK_SERVER.
     */
    public static final String MOCK_SERVER = "https://bd9f22ed-10c9-4c41-a415-b951634333f6.mock.pstmn.io";
    /**
     * The constant API_SERVER.
     */
//    public static final String API_SERVER = "https://bd9f22ed-10c9-4c41-a415-b951634333f6.mock.pstmn.io";
    public static final String API_SERVER = "http://coms-309-007.class.las.iastate.edu:8080";
    public static final String CHAT_SERVER = "ws://coms-309-007.class.las.iastate.edu:8080/chat/";
    /**
     * The constant username.
     */
    public static String username = "username";

    /**
     * The constant user.
     */
    public static User user = new User(-1, "username", "fullname");
}
