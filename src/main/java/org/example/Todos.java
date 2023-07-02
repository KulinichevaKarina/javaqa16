package org.example;

public class Todos {
    TaskRepository repository;
    public Todos(TaskRepository repository) {
        this.repository = repository;
    }

    public void add(Task task) {
        repository.add(task);
    }
    public Task[] search(String query) {
        Task[] result = new Task[0];
        for (Task task: repository.getAll()) {
            if (task.matches(query)) {
                result = addToArray(result, task);
            }
        }

        return result;
    }

    private Task[] addToArray(Task[] current, Task task) {
        Task[] tmp = new Task[current.length + 1];
        for (int i = 0; i < current.length; i++) {
            tmp[i] = current[i];
        }
        tmp[tmp.length - 1] = task;
        return tmp;
    }

}
