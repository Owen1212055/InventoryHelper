package com.owen1212055.inventoryhelper.util;

import org.bukkit.inventory.*;

public class ItemUtil {

    public static boolean isEmpty(ItemStack stack) {
        return stack == null || stack.getType().isAir();
    }
}
