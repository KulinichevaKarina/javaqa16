import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TaskTest {
    TaskRepository repository = new TaskRepository();
    Todos manager;

    TaskTest(){
        this.manager = new Todos(repository);
    }

    @Test
    public void add() {
        manager.add(new SimpleTask(1,"собрание"));
        manager.add(new Epic(2, new String[]{"обсуждение"}));
        manager.add(new Meeting(3,"новая фича","менеджер","03.04.2022"));

        Assertions.assertEquals(3,repository.getAll().length);
    }

    @Test
    public void shouldAddThreeTasksOfDifferentType() {
        SimpleTask simpleTask = new SimpleTask(5, "Позвонить родителям");
        Epic epic = new Epic(55, new String[]{"молоко", "яйца", "хлеб"});
        Meeting meeting = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка",
                "Во вторник после обеда");

        manager.add(simpleTask);
        manager.add(epic);
        manager.add(meeting);

        Task[] expected = { simpleTask, epic, meeting };
        Task[] actual = repository.getAll();
        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldBeMatches() {
        Task searchItem = new SimpleTask(5, "Позвонить родителям");
        manager.add(searchItem);

        Task searchItem1 = new Epic(55, new String[]{"молоко", "яйца", "хлеб"});
        manager.add(searchItem1);

        Task searchItem2 = new Meeting(555, "Выкатка 3й версии приложения", "Приложение НетоБанка",
                "Во вторник после обеда");
        manager.add(searchItem2);

        Meeting searchItem3 = new Meeting(557, "Выкатка 4й версии приложения", "Приложение НетоБанка",
                "В среду после обеда");
        manager.add(searchItem3);

        Assertions.assertArrayEquals(new Task[]{searchItem1}, manager.search("яйца"));
        Assertions.assertArrayEquals(new Task[0], manager.search("яйца1"));
        Assertions.assertEquals(searchItem1.getId(), manager.search("яйца")[0].getId());
        Assertions.assertArrayEquals(new Task[]{searchItem2, searchItem3}, manager.search("Приложение НетоБанка"));
        Assertions.assertArrayEquals(new Task[]{searchItem2}, manager.search("Выкатка 3й версии приложения"));
        Assertions.assertArrayEquals(new Task[]{searchItem2}, manager.search("Во вторник после обеда"));
    }
}

