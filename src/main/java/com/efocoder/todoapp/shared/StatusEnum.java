package com.efocoder.todoapp.shared;


import java.util.Arrays;
import java.util.List;

public enum StatusEnum {
    ACTIVE(1),
    COMPLETED(2),
    IN_PROGRESS(3),
    DELETED(4),
;
    public static final List<StatusEnum> listStatuses = Arrays.asList(StatusEnum.COMPLETED, StatusEnum.IN_PROGRESS, StatusEnum.ACTIVE);
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
