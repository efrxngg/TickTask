package edu.project.app.entity;

import java.util.UUID;

public class Task {

    private UUID id;
    private String title;
    private String description;
    private long dateCreation;
    private long lastModification;
    private StatusType status;

    //#region CONSTRUCTORS SETS GETS
    public Task() {
    }

    public Task(UUID id, String name, String description, long date_creation, long date_updated, StatusType status) {
        this.id = id;
        this.title = name;
        this.description = description;
        this.dateCreation = date_creation;
        this.lastModification = date_updated;
        this.status = status;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(long dateCreation) {
        this.dateCreation = dateCreation;
    }

    public long getLastModification() {
        return lastModification;
    }

    public void setLastModification(long lastModification) {
        this.lastModification = lastModification;
    }

    public StatusType getStatus() {
        return status;
    }

    public void setStatus(StatusType status) {
        this.status = status;
    }

    //#endregion

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", date_creation=" + dateCreation +
                ", date_updated=" + lastModification +
                ", status=" + status +
                '}';
    }
}
