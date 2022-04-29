package com.owen1212055.inventoryhelper.inventoryiterator;

import org.bukkit.inventory.*;

import java.util.*;

public interface InventoryIterator extends ListIterator<ItemStack> {

    void set(int slot, ItemStack stack);

    int getSize();

}
