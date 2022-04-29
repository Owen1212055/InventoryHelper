package com.owen1212055.inventoryhelper;

import com.owen1212055.inventoryhelper.inventoryiterator.*;
import com.owen1212055.inventoryhelper.operation.*;
import com.owen1212055.inventoryhelper.operation.context.add.*;
import com.owen1212055.inventoryhelper.operation.context.remove.*;
import com.owen1212055.inventoryhelper.util.*;
import org.bukkit.*;
import org.bukkit.inventory.*;
import org.jetbrains.annotations.*;

public final class Operations {

    public static final Operation<RemoveItemContext, ItemsRemovedResult> REMOVE = new Operation<>() {

        private static final ItemStack BLANK_ITEM = new ItemStack(Material.AIR);

        @Override
        public @NotNull ItemsRemovedResult operate(InventoryIterator iterator, RemoveItemContext context) {
            int amtToRemove = context.amount();

            while (iterator.hasNext()) {
                ItemStack item = iterator.next();
                if (!context.predicate().test(context.itemStack(), item)) {
                    continue;
                }

                int selectedItemAmt = item.getAmount();

                // Is the remove amount larger than the individual stack? Chunk it!
                if (amtToRemove >= selectedItemAmt) {
                    iterator.set(BLANK_ITEM);
                } else { // Not bigger? Remove as much as we can
                    item.setAmount(selectedItemAmt - amtToRemove);
                    iterator.set(item);
                }
                amtToRemove -= selectedItemAmt;

                if (amtToRemove <= 0) {
                    break;
                }

            }

            return new ItemsRemovedResult(context.amount() - amtToRemove);
        }
    };

    public static final Operation<AddItemContext, ItemsAddedResult> ADD = new Operation<>() {

        @Override
        public @NotNull ItemsAddedResult operate(InventoryIterator iterator, AddItemContext context) {
            int amtToAdd = context.amount();

            int[] emptySlots = new int[iterator.getSize()];
            int emptySlotCount = 0;

            while (iterator.hasNext()) {
                ItemStack item = iterator.next();
                if (ItemUtil.isEmpty(item)) {
                    emptySlots[emptySlotCount] = iterator.previousIndex();
                    emptySlotCount++;
                }

                if (!context.predicate().test(context.itemStack(), item)) {
                    continue;
                }

                int selectedItemAmt = item.getAmount();

                int addSmallestAmount = Math.min(amtToAdd, item.getMaxStackSize() - selectedItemAmt); // Find the smallest amount of items we can add
                if (addSmallestAmount >= 0) {
                    item.setAmount(selectedItemAmt + addSmallestAmount);
                    amtToAdd -= addSmallestAmount;

                    iterator.set(item);
                }


                if (amtToAdd <= 0) {
                    break;
                }

            }

            if (amtToAdd > 0) {
                for (int i = 0; i < emptySlotCount; i++) {
                    int emptySlot = emptySlots[i];
                    ItemStack cloneItem = context.itemStack().clone();

                    int addToEmptySlot = Math.min(amtToAdd, cloneItem.getMaxStackSize()); // Find the smallest amount of items
                    amtToAdd -= addToEmptySlot;

                    cloneItem.setAmount(addToEmptySlot);
                    iterator.set(emptySlot, cloneItem);

                    if (amtToAdd <= 0) {
                        break;
                    }
                }
            }

            return new ItemsAddedResult(context.amount() - amtToAdd);
        }

    };

}
