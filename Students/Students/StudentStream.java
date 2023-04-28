import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

public class StudentStream implements Iterable<StudentGroup> {
    private final int streamId;
    private final List<StudentGroup> groups;

    public StudentStream(int streamId) {
        this.streamId = streamId;
        this.groups = new ArrayList<>();
    }

    public void addGroup(StudentGroup group) {
        this.groups.add(group);
    }

    public int getNumGroups() {
        return this.groups.size();
    }

    @Override
    public Iterator<StudentGroup> iterator() {
        return this.groups.iterator();
    }

    public void sortGroups() {
        Collections.sort(this.groups);
    }

    public void sortGroupsById() {
        Collections.sort(this.groups, (g1, g2) -> {
            int result = g1.compareTo(g2);
            if (result == 0) {
                result = g1.equals(g2) ? 0 : g1.hashCode() > g2.hashCode() ? 1 : -1;
            }
            return result;
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Поток: ").append(streamId).append(", Кол-во групп: ").append(getNumGroups()).append("\n");
        for (StudentGroup group : groups) {
            sb.append(group.toString()).append("\n");
        }
        return sb.toString();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentStream that = (StudentStream) o;
        return streamId == that.streamId && Objects.equals(groups, that.groups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(streamId, groups);
    }
}
