package com.travix.medusa.busyflights.enums;

/**
 * The class that represents cabin class codes.
 * <p>
 * .
 *
 * @author Daniel Loureiro (danielloureiro1995@hotmail.com)
 */
public enum CabinClassEnum {

    /**
     *
     */
    E("Economy"),

    /**
     *
     */
    B("Business");

    private String description;

    /**
     * The constructor of cabin class codes.
     *
     * @param description The airport name;
     */
    CabinClassEnum(String description) {
        this.description = description;
    }

    public String getDescription() { return description; }
}
