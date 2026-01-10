package com.example.githubintellijhmsoopproject;

public abstract class Person {

    protected int id;
    protected String name;
    protected int age;

    public Person(int id, String name, int age) {
        setId(id);
        setName(name);
        setAge(age);
    }

    public void setId(int id) {
        if (id <= 0) throw new IllegalArgumentException("Invalid ID");
        this.id = id;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty())
            throw new IllegalArgumentException("Invalid name");
        this.name = name;
    }

    public void setAge(int age) {
        if (age < 0) throw new IllegalArgumentException("Invalid age");
        this.age = age;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public int getAge() { return age; }

    public void work() {
        System.out.println("Person is working");
    }

    @Override
    public String toString() {
        return "ID=" + id + ", Name=" + name + ", Age=" + age;
    }
}
