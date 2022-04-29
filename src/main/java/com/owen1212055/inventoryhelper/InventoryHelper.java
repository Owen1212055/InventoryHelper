package com.owen1212055.inventoryhelper;

import com.owen1212055.inventoryhelper.inventoryiterator.*;
import com.owen1212055.inventoryhelper.inventoryiterator.range.*;
import com.owen1212055.inventoryhelper.operation.*;
import com.owen1212055.inventoryhelper.operation.context.add.*;
import com.owen1212055.inventoryhelper.operation.context.remove.*;
import org.bukkit.inventory.*;
import org.jetbrains.annotations.*;

public class InventoryHelper {

    @NotNull
    public static ItemsAddedResult addItem(@NotNull Inventory inventory, @NotNull ItemStack itemStack) {
        return applyOperation(inventory, Operations.ADD, new AddItemContext(itemStack));
    }

    @NotNull
    public static ItemsAddedResult addItem(@NotNull Inventory inventory, @NotNull SlotRange range, @NotNull ItemStack itemStack) {
        return applyOperation(inventory, range, Operations.ADD, new AddItemContext(itemStack));
    }

    @NotNull
    public static ItemsRemovedResult removeItem(@NotNull Inventory inventory, @NotNull ItemStack itemStack) {
        return applyOperation(inventory, Operations.REMOVE, new RemoveItemContext(itemStack));
    }

    @NotNull
    public static ItemsRemovedResult removeItem(@NotNull Inventory inventory, @NotNull SlotRange range, @NotNull ItemStack itemStack) {
        return applyOperation(inventory, range, Operations.REMOVE, new RemoveItemContext(itemStack));
    }

    @NotNull
    public static <C, R> R applyOperation(@NotNull Inventory inventory, @NotNull Operation<C, R> operation, @NotNull C context) {
        return applyOperation(new SimpleInventoryIterator(inventory), operation, context);
    }

    @NotNull
    public static <C, R> R applyOperation(@NotNull Inventory inventory, @NotNull SlotRange range, @NotNull Operation<C, R> operation, @NotNull C context) {
        return applyOperation(new SimpleInventoryIterator(inventory, range.getMin(), range.getMax() + 1), operation, context); // Represents a range, convert it to a length
    }

    @NotNull
    public static <C, R> R applyOperation(@NotNull InventoryIterator iterator, @NotNull Operation<C, R> operation, @NotNull C context) {
        return operation.operate(iterator, context);
    }
}
