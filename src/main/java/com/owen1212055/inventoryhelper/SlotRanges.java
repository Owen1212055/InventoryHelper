package com.owen1212055.inventoryhelper;

import com.owen1212055.inventoryhelper.inventoryiterator.range.*;

public final class SlotRanges {

    public static final SlotRange HOTBAR = range(0, 8);

    /**
     * Represents the upper slots of the player's inventory.
     * These are the top-most 3 rows, (hotbar is excluded).
     */
    public static final SlotRange UPPER_PLAYER_INVENTORY = range(9, 35);

    /**
     * Represents the players main inventory, excluding armor, and offhand.
     */
    public static final SlotRange PLAYER_INVENTORY = range(0, 35);

    /**
     * Represents the player's armor
     */
    public static final SlotRange PLAYER_ARMOR = range(36, 39);

    /**
     * Represents the entire inventory for a player.
     */
    public static final SlotRange ENTIRE_PLAYER_INVENTORY = range(0, 40);

    private static SlotRange range(int min, int max) {
        return new SlotRange() {
            @Override
            public int getMin() {
                return min;
            }

            @Override
            public int getMax() {
                return max;
            }
        };
    }

}
