package com.owen1212055.inventoryhelper.operation;

import com.owen1212055.inventoryhelper.inventoryiterator.*;
import org.jetbrains.annotations.*;

public interface Operation<C, R> {

    @NotNull
    R operate(InventoryIterator iterator, C context);
}
