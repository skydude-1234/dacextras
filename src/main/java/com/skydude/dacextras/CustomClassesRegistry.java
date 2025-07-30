package com.skydude.dacextras;

import java.util.ArrayList;
import java.util.List;

public class CustomClassesRegistry {
    public static final List<CustomClasses> CLASSES = new ArrayList<>();

    public static void register(CustomClasses clazz) {
        CLASSES.add(clazz);
    }

    public static List<CustomClasses> getAll() {
        return CLASSES;
    }
}
//for (CustomClasses clazz : CustomClassesRegistry.getAll()) {
//        // use clazz
//        }