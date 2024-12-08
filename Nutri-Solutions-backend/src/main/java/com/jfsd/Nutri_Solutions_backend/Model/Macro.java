package com.jfsd.Nutri_Solutions_backend.Model;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Column;

@Embeddable
public class Macro {
    @Column(name = "macro_name")
    private String macroName; // e.g., Carbs, Proteins, Fats

    @Column(name = "macro_value")
    private Integer value; // e.g., percentage or grams

    public Macro() {}

    public Macro(String macroName, Integer value) {
        this.macroName = macroName;
        this.value = value;
    }

    public String getMacroName() {
        return macroName;
    }

    public void setMacroName(String macroName) {
        this.macroName = macroName;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Macro{" +
                "macroName='" + macroName + '\'' +
                ", value=" + value +
                '}';
    }
}
