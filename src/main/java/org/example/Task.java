package org.example;

public abstract class Task {
    protected int id;

    public Task(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean matches(String text, String query) {
        return text.contains(query);
    }

    public abstract boolean matches(String query);
}
