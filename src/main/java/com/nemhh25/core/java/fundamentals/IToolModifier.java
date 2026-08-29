package com.nemhh25.core.java.fundamentals;

public interface IToolModifier {

    int getBaseDurability();
    default int getModifiedDurability(int level) {
        return getBaseDurability() + (level * 100);
    }
}
