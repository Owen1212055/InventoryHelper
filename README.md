<h1 style="text-align: center;">InventoryHelper</h1>
<p style="text-align: center;">InventoryHelper is a library that focuses on providing utilities based on modifying bukkit item stacks in inventories.</p>

# Documentation

```java
class Example {
    {
        // Remove 5 items in hotbar that have the same material as PLAYER_HEAD
        ItemsRemovedResult removedResult = InventoryHelper.applyOperation(
                inventory,
                SlotRanges.HOTBAR,
                Operations.REMOVE,
                new RemoveItemContext(new ItemStack(Material.PLAYER_HEAD), 5, ItemPredicates.SAME_MATERIAL) // Predicates allow you to control which items are chosen to be removed from
        );
        // Use the result
        System.out.printf("Removed %s items from the player", removedResult.removed());

        // Add 5 player heads in hotbar
        ItemsAddedResult addedResult = InventoryHelper.applyOperation(
                inventory,
                SlotRanges.HOTBAR,
                Operations.ADD,
                new AddItemContext(new ItemStack(Material.PLAYER_HEAD), 5, ItemPredicates.SIMILAR) // Only add to other items that are similar (ignores count)
        );
        // Use the result
        System.out.printf("Added %s items to the player", addedResult.added());   
    }
}
```


<h1 style="text-align: center;">Need help? Feel free to join my discord for help: https://discord.gg/APaZK9tvkw</h1> 
