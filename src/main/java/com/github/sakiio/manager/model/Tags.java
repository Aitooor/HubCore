package com.github.sakiio.manager.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Tags {
    DEFAULT("Default", "", null),
    PRO("PRO", "&3PRO", "pro.tag");


    private final String name;
    private final String prefix;
    private final String permission;
}
