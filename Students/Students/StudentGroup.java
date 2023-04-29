import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class StudentGroup implements Comparable<StudentGroup> {
    private final String groupId;
    private final List<String> students;

    public StudentGroup(String groupId) {
        this.groupId = groupId;
        this.students = new ArrayList<>();
    }

    public void addStudent(String student) {
        this.students.add(student);
    }

    public int getNumStudents() {
        return this.students.size();
    }

    @Override
    public int compareTo(StudentGroup o) {
        return Integer.compare(this.getNumStudents(), o.getNumStudents());
    }

    @Override
    public String toString() {
        return "Группа " + groupId + ", студенты (" + getNumStudents() + "): " + students;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentGroup that = (StudentGroup) o;
        return Objects.equals(groupId, that.groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupId);
    }
}
