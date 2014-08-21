package com.codurancetest;

/**
 * Created by niallferguson on 14/08/2014.
 * Social Media programe that allows user to create messages
 * and post to timeline, view other users timelines and follow
 * users to view aggreagated timeline (wall).  New users are created
 * when they create their first post.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class App {

    String input = null;
    HashMap<String, User> userList = new HashMap<String, User>();
    User currentUser;
    String userName;
    String command;

    boolean keepRunning = true;

    public App(){

    while (true)
    {

        System.out.print(">>");
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        /**
         * Wait for user input
         */
        try
        {
            input = br.readLine();
        }
        catch (IOException ioe)
        {
            System.out.println("IO error trying to read input!");
            System.exit(1);
        }

        /**
         * Tokenize input and add to arraylist
         */
        Scanner scanner = new Scanner(input);

        ArrayList<String> inputList = new ArrayList<String>();

        while (scanner.hasNext())
        {
            inputList.add(scanner.next());
        }


        /**
         * Retrieve first token and assign to username
         */
        userName = inputList.get(0);

        /**
         * exit programme if username equals exit
         */
        if(userName.toLowerCase().equals("exit"))
        {

            System.out.print("Program Exiting");
            keepRunning=false;
        }

        /**
         * check userlist, if empty create first user
         * otherwise asign currentUser to corresponding userName
         */
        if (userList.isEmpty())
        {
            currentUser = new User(userName);
            currentUser.setName(userName);
            userList.put(currentUser.getName(), currentUser);
        }
        else
        {
            if (userList.containsKey(userName))
            {
                currentUser = userList.get(userName);
            }
            else
            {
                currentUser = new User(userName);
                currentUser.setName(userName);
                userList.put(currentUser.getName(), currentUser);
            }
        }

        /**
         * check second token to see if exists, if so execute command
         */
        if (inputList.size() == 1)
        {
            currentUser.printTimeLine();
        }
        else
        {
            command = inputList.get(1);

            switch (command)
            {
                case "->":

                    /**
                     * rebuild message from list
                     */
                    for (int x = 2; x < inputList.size(); x++)
                    {
                        sb.append(inputList.get(x));
                        sb.append(" ");
                    }
                    String message = sb.toString().trim();

                    Post post = new Post(currentUser.getName());
                    post.setContent(message);

                    currentUser.getTimeline().add(post);
                    break;

                case "wall":
                    currentUser.wall();
                    break;

                case "follows":
                    currentUser.follow(userList, inputList.get(2));
                    break;

            }

        }
    }
    }
}


