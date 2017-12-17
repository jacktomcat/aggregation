package com.gochinatv.cdn.api.jdk.jdk8.lambda;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by jacktomcat on 17/12/17.
 */
public class StreamTest {


    public static void main(String[] args) {
        List<User> userList = new ArrayList<>();
        for(int i=0;i<10;i++){
            User user = new User(i, "jack"+i,i+20);
            userList.add(user);
        }
        //userList.forEach((user) -> System.out.println(user));

        userList.stream().limit(2).skip(1).collect(Collectors.toList()).forEach(user-> System.out.println(user));

        Optional<User> anyUser = userList.stream().findAny();
        System.out.println(anyUser.get());

        boolean anyMatch = userList.stream().anyMatch(user -> user.getName().indexOf("5") > -1);
        System.out.println(anyMatch);

        boolean allMatch = userList.stream().allMatch(user -> user.getName().indexOf("5") > -1);
        System.out.println(allMatch);

        Map<Integer, String> userToMap = userList.stream().collect(Collectors.toMap(user -> user.getId(), user->user.getName()));
        System.out.println(userToMap);


        userList.stream().map(user-> {user.setName(user.getName()+"-tomcat");return user;}).forEach(u-> System.out.println(u));

        Map<Integer, String> collect = userList.stream().collect(Collectors.toMap(User::getId, User::getName, (s,a)->s+","+ a));
        System.out.println(collect);

        Map<Integer, List<User>> groupingBy = userList.stream().collect(Collectors.groupingBy(User::getId));
        System.out.println(groupingBy);

        Map<Boolean, List<User>> partition = userList.stream().collect(Collectors.partitioningBy(user -> user.getId() > 5));
        System.out.println(partition);

        userList.stream().collect(Collectors.partitioningBy(user -> user.getId() > 5,));

    }

}



class User {

    private int id;
    private String name;
    private int age;

    public User(int id, String name, int age) {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
