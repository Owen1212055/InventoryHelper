package com.owen1212055.inventoryhelper.inventoryiterator;

import org.bukkit.inventory.*;

import javax.annotation.*;

public class SimpleInventoryIterator implements InventoryIterator {

    private final Inventory inventory;
    private final int size;
    private int nextIndex;

    @Nullable
    private Boolean lastDirection;

    public SimpleInventoryIterator(Inventory craftInventory) {
        this(craftInventory, 0, craftInventory.getSize());
    }

    public SimpleInventoryIterator(Inventory craftInventory, int min, int max) {
        this.inventory = craftInventory;
        this.nextIndex = min;
        this.size = max;
    }

    @Override
    public boolean hasNext() {
        return this.nextIndex < this.size;
    }

    @Override
    public ItemStack next() {
        this.lastDirection = true;
        return this.inventory.getItem(this.nextIndex++);
    }

    @Override
    public int nextIndex() {
        return this.nextIndex;
    }

    @Override
    public boolean hasPrevious() {
        return this.nextIndex > 0;
    }

    @Override
    public ItemStack previous() {
        this.lastDirection = false;
        return this.inventory.getItem(--this.nextIndex);
    }

    @Override
    public int previousIndex() {
        return this.nextIndex - 1;
    }

    @Override
    public void set(ItemStack item) {
        if (this.lastDirection == null) {
            throw new IllegalStateException("No current item!");
        } else {
            int i = this.lastDirection ? this.nextIndex - 1 : this.nextIndex;
            this.inventory.setItem(i, item);
        }
    }

    @Override
    public void add(ItemStack item) {
        throw new UnsupportedOperationException("Can't change the size of an inventory!");
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Can't change the size of an inventory!");
    }

    @Override
    public void set(int slot, ItemStack stack) {
        this.inventory.setItem(slot, stack);
    }

    @Override
    public int getSize() {
        return this.size;
    }
}
