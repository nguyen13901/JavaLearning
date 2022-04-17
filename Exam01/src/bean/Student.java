package bean;

import java.util.Objects;

public class Student {
    private String StudentId;
    private String name;
    private double averagePoint;
    private String gender;

    public Student() {
    }

    public Student(String studentId, String name, double averagePoint, String gender) {
        StudentId = studentId;
        this.name = name;
        this.averagePoint = averagePoint;
        this.gender = gender;
    }

    public String getStudentId() {
        return StudentId;
    }

    public void setStudentId(String studentId) {
        StudentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAveragePoint() {
        return averagePoint;
    }

    public void setAveragePoint(double averagePoint) {
        this.averagePoint = averagePoint;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Student)) {
            return false;
        }
        Student that = (Student) o;
        return getStudentId().equals(that.getStudentId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(StudentId);
    }

    @Override
    public String toString() {
        return "Student{" +
                "StudentId='" + StudentId + '\'' +
                ", name='" + name + '\'' +
                ", averagePoint=" + averagePoint +
                ", gender='" + gender + '\'' +
                '}';
    }

    public static Student transfer(String line) {
        String[] elements = line.split(", ");
        if (elements.length != 4) {
            return null;
        }
        return new Student(elements[0], elements[1], Double.parseDouble(elements[2]), elements[3]);
    }
}
