public class App {
    public static void main(String[] args) {
        StudentStream stream = new StudentStream(1);

        StudentGroup group1 = new StudentGroup("A");
        group1.addStudent("Сек");
        group1.addStudent("Рум");
        group1.addStudent("Бу");

        StudentGroup group2 = new StudentGroup("B");
        group2.addStudent("Томми");
        group2.addStudent("Лира");

        stream.addGroup(group1);
        stream.addGroup(group2);

        StudentGroup group3 = new StudentGroup("C");
        group3.addStudent("Боб");
        group3.addStudent("Алиса");
        group3.addStudent("Иванко");
        group3.addStudent("Арти");

        stream.addGroup(group3);

        System.out.println(" - Оригинал:");
        System.out.println(stream.toString());

        System.out.println(" - Сортировать по количеству студентов:");
        stream.sortGroups();
        System.out.println(stream.toString());

        System.out.println(" - Сортировать по кол-ву студентов и группы:");
        stream.sortGroupsById();
        System.out.println(stream.toString());

        System.out.println(" - Группы:");
        for (StudentGroup group : stream) {
            System.out.println(group.toString());
        }
    }
}
