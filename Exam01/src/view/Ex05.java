package view;

import bean.Student;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Bài 4 (20đ): Cho danh sách sinh viên được input từ file student.txt.
 * Mẫu: student.txt
 * 102130174, Le Na, 7.8, Nu
 * 102130125, Hoang Nam, 8.2, Nam
 * 102130176, Van Cuong, 9.8, Nam
 * 102130177, Van Cong, 6.8, Nam
 * 102130178, Ngoc Nu, 9.1, Nu
 * 102130180, Hoang Nhung, 7.8, Nu
 */

public class Ex05 {

    private static final String link = "Student.txt";

    public static void main(String[] args) {
        List<String> data = readFile(new File(link));
        List<Student> students = data.stream()
                .map(Student::transfer)
                .collect(Collectors.toList());

        // 1. Sắp xếp danh sách sinh viên tăng dần theo ĐTB. Nếu DTB bằng nhau sắp xếp tăng
        //dần theo họ tên.
        students.stream()
                .sorted(Comparator.comparing(Student::getAveragePoint).thenComparing(Student::getName))
                .forEach(System.out::println);

        // 2. Viết hàm tìm kiếm danh sách sinh viên có ĐTB > 8
        List<Student> DTBbigger8 = find(students, student -> student.getAveragePoint() > 8);
        DTBbigger8.forEach(System.out::println);
        System.out.println("====================");

        //3 .Viết hàm tìm kiếm toàn bộ các sinh viên NỮ
        List<Student> girdStudents = find(students, student -> "NU".equals(student.getGender()));
        DTBbigger8.forEach(System.out::println);
        System.out.println("====================");

        // 4. Tìm kiếm sinh viên chỉ xuất hiện một lần trong student.txt. Biết rằng 2 sinh viên được
        //phân biệt với nhau thông qua MSSV
        students.stream()
                .collect(Collectors.groupingBy(student -> student, Collectors.counting()))
                .entrySet()
                .stream().filter(entry -> entry.getValue() == 1)
                .map(entry -> entry.getKey())
                . forEach(System.out::println);

    }

    private static List<String> readFile(File file) {
        List<String> result = new ArrayList<>();
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String line = "";
            while ((line = br.readLine()) != null) {
                result.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fr.close();
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private static <E> List<E> find(List<E> elements, Predicate<E> predicate) {
        return  elements.stream()
                .filter(predicate)
                .collect(Collectors.toList());
    }
}
