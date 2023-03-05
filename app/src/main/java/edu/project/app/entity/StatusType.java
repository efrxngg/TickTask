package edu.project.app.entity;

public enum StatusType {
    COMPLETED, PENDING, DELETED;

    public static StatusType valueOf(int anInt) {
        StatusType[] values = StatusType.values();
        for (int i = 0; i < values.length; i++) {
            if (i == anInt) {
                return values[i];
            }
        }
        throw new IllegalArgumentException("");
    }
}
