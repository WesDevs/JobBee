package com.wesdev.JobBee.utils;

public enum Validation {

    VALIDATE_EMAIL("^([a-zA-Z0-9_\\-\\.]+)@([a-zA-Z0-9_\\-\\.]+)\\.([a-zA-Z]{2,5})$"),
    VALIDATE_NAME_TITLE("^[a-zA-Z0-9 ]+$"),
    VALIDATE_URL("^(https?|ftp|file)://[-a-zA-Z0-9+&@#/%?=~_|!:,.;]*[-a-zA-Z0-9+&@#/%=~_|]");

    private final String value;

    Validation(String value) {
        this.value = value;
    }

    public String val() {
        return value;
    }

}


