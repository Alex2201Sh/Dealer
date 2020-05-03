package com.github.alex.dealer.data;

public class Team {
    private Long Id;
    private String name;

    public Team(Long id, String name) {
        Id = id;
        this.name = name;
    }

    public Long getId() {
        return Id;
    }

    public String getName() {
        return name;
    }
}
