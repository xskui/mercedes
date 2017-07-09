package com.redemption.shawshank;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Author : xingshukui .
 * Date : 2017/7/5.
 * Desc :
 */
public class Constants {

    public static final String CURRENT_USER = "user";
    public static final String password_algorithmName = "MD5";
    public static final int password_hashIterations = 2;
    public static final Gson gson = new GsonBuilder().create();
}
