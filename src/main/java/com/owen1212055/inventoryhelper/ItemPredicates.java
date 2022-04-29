package com.owen1212055.inventoryhelper;

import com.owen1212055.inventoryhelper.util.*;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.*;

import java.util.*;
import java.util.function.*;

public final class ItemPredicates {

    public static final BiPredicate<ItemStack, ItemStack> SIMILAR = (stack, stack2) -> {
        if (ItemUtil.isEmpty(stack)) {
            return ItemUtil.isEmpty(stack2);
        }

        return stack.isSimilar(stack2);
    };

    public static final BiPredicate<ItemStack, ItemStack> SIMILAR_IGNORE_DURABILITY_ITEM = (stack, stack2) -> {
        if (ItemUtil.isEmpty(stack)) {
            return ItemUtil.isEmpty(stack2);
        }

        ItemStack stackModified = stack.clone();
        {
            ItemMeta meta = stackModified.getItemMeta();
            if (meta instanceof Damageable damageable) {
                damageable.setDamage(0);
            }
            stackModified.setItemMeta(meta);
        }
        ItemStack stack2Modified = stack2.clone();
        {
            ItemMeta meta = stack2Modified.getItemMeta();
            if (meta instanceof Damageable damageable) {
                damageable.setDamage(0);
            }
            stack2Modified.setItemMeta(meta);
        }

        return stackModified.isSimilar(stack2Modified);
    };

    public static final BiPredicate<ItemStack, ItemStack> EQUAL = Objects::equals;

    public static final BiPredicate<ItemStack, ItemStack> SAME_MATERIAL = (stack, stack2) -> {
        if (ItemUtil.isEmpty(stack)) {
            return ItemUtil.isEmpty(stack2);
        }

        return stack.getType() == stack2.getType();
    };

}
