package com.efocoder.todoapp.shared;

public enum StatusEnum {
    ACTIVE(1),
    INACTIVE(2),
    DELETED(0);

    private final int value;


    StatusEnum(int status){
        this.value = status;
    }

    // Convert integer to enum
    public static StatusEnum valueToEnum(int currentStatus) {
        for (StatusEnum status : StatusEnum.values()) {
            if (status.value == currentStatus) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown status value: " + currentStatus);
    }

    public int getStatus(){
        return value;
    }

}
