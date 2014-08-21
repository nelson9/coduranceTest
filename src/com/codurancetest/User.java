package com.codurancetest;

import javax.print.DocFlavor;
import java.util.*;

/**
 * Created by niallferguson on 14/08/2014.
 * User class contains information about user, stores who they are
 * following, their timeline and aggreagated timeline (wall).
 *
 */

public class User {

    private String name;
    private ArrayList<String> follow = new ArrayList<String>();
    private HashMap<String, ArrayList<Post>> wall = new HashMap<String, ArrayList<Post>>();
    private ArrayList<Post> timeline = new ArrayList<Post>();

    public ArrayList<Post> gettLine() {
        return timeline;
    }

    public User(String name) {
        this.name = name;
        wall.put(name, timeline);
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public ArrayList<Post> getTimeline() {
        return timeline;
    }

    /**
     * Takes collection of posts and sorts them in descending
     * order by date
     * @param unSorted a list of posts
     * @return unSorted sorted list in descending order
     */
    public ArrayList<Post> sortCollection(ArrayList<Post> unSorted) {

        Collections.sort(unSorted, new Comparator<Post>() {
            @Override
            public int compare(Post post1, Post post2) {

                return post2.getTime().compareTo(post1.getTime());
            }
        });

        return unSorted;
    }

    /**
     * Allows user to follow another user
     * @param userList a hashmap of containing Users paired to thier username
     * @param userName a Username
     *
     */
    public void follow(HashMap<String, User> userList, String userName) {
        if (!follow.contains(userName)) {

            follow.add(userName);
            for (String s : follow) {
                if (userList.containsKey(s)) {
                    User user = userList.get(s);
                    wall.put(user.getName(), user.getTimeline());
                }
            }
        }
    }

    /**
     * Displays aggregate of user and who they have followed's timelines
     */
    public void wall() {

        ArrayList<Post> aggregatePosts = new ArrayList<Post>();

        Iterator it = wall.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry) it.next();
            aggregatePosts.addAll((ArrayList) pairs.getValue());
        }

        aggregatePosts = sortCollection(aggregatePosts);
        for (Post p : aggregatePosts) {
            p.printPost();
        }
    }

    /**
     * Displays users timeline
     */
    public void printTimeLine() {

        sortCollection(timeline);

        for (Post p : gettLine()) {
            p.printPost();
        }

    }
}
