package com.owen1212055.inventoryhelper.operation.context.add;

import com.owen1212055.inventoryhelper.*;
import org.bukkit.inventory.*;
import org.jetbrains.annotations.*;

import java.util.function.*;

public record AddItemContext(@NotNull ItemStack itemStack, int amount,
                             @NotNull BiPredicate<ItemStack, ItemStack> predicate) {

    public AddItemContext(@NotNull ItemStack stack) {
        this(stack, stack.getAmount(), ItemPredicates.SIMILAR);
    }


}
