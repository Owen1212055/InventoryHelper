package com.owen1212055.inventoryhelper.operation.context.remove;

import com.owen1212055.inventoryhelper.*;
import org.bukkit.inventory.*;
import org.jetbrains.annotations.*;

import java.util.function.*;

public record RemoveItemContext(@NotNull ItemStack itemStack, int amount,
                                @NotNull BiPredicate<ItemStack, ItemStack> predicate) {

    public RemoveItemContext(@NotNull ItemStack stack) {
        this(stack, stack.getAmount(), ItemPredicates.SIMILAR);
    }


}
