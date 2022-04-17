package bean;

import java.util.Comparator;

public class Student implements Comparator<Student> {

    private int id;
    private String name;
    private double cgpa;

    Student() {

    }

    public Student(int id, String name, double cgpa) {
        this.id = id;
        this.name = name;
        this.cgpa = cgpa;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getCgpa() {
        return cgpa;
    }

    @Override
    public int compare(Student o1, Student o2) {
        return 0;
    }
}
