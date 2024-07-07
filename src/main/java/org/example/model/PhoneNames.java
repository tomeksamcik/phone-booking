package org.example.model;

public enum PhoneNames {
    SAMSUNG_GALAXY_S9("Samsung Galaxy S9"),
    SAMSUNG_GALAXY_S8("Samsung Galaxy S8"),
    MOTOROLA_NEXUS_6("Motorola Nexus 6"),
    ONEPLUS_9("Oneplus 9"),
    IPHONE_13("Apple iPhone 13"),
    IPHONE_12("Apple iPhone 12"),
    IPHONE_11("Apple iPhone 11"),
    IPHONE_X("iPhone X"),
    NOKIA_3310("Nokia 3310");

    public final String label;

    PhoneNames(String label) {
        this.label = label;
    }
}
