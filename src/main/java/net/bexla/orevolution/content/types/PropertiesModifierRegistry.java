package net.bexla.orevolution.content.types;

import net.bexla.orevolution.content.types.base.interfaces.ItemPropertiesModifiers;

import java.util.ArrayList;
import java.util.List;

public class PropertiesModifierRegistry {
    private static final List<ItemPropertiesModifiers> MODIFIERS = new ArrayList<>();

    public static void register(ItemPropertiesModifiers modifier) {
        MODIFIERS.add(modifier);
    }

    public static List<ItemPropertiesModifiers> getModifiers() {
        return MODIFIERS;
    }
}
