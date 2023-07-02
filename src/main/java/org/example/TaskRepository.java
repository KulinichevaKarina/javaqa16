package org.example;

public class TaskRepository {
    private Task[] items = new Task[0];

    public void add(Task task) {
        Task[] tmp = new Task[items.length + 1];
        int i = 0;
        for (Task item : items) {
            tmp[i] = item;
            i++;
        }
        tmp[i] = task;
        items = tmp;
    }

    public Task[] getAll() {
        return this.items;
    }
}
