package com.github.sakiio.manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ColorChat {
    DEFAULT("Default", "&7", null),
    RED("RED", "&4", "red.color");



    private final String color_name;
    private final String color;
    private final String permissions;
}
