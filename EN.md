## MerchantJS

This mod is an addon for **KubeJS**, expanding its functionality in the **trading system**.

### Create Custom Trades Anywhere

This mod allows you to initiate trades *anywhere* — trade with pigs, with End Stone, with Enchanted Golden Apples, or even out of thin air!

```javascript
// server_scripts
ItemEvents.entityInteracted(event => {
    // Check if the interacted entity is a pig
    if (event.getTarget().type === "minecraft:pig") {
        // Create a complete trade offer
        let offer0 = MerchantJSUtils.createMerchantOffer({
            buy: Item.of("minecraft:potato").toNBT(),
            buyB: Item.of("minecraft:bread").toNBT(),
            sell: Item.of("minecraft:stone").toNBT(),
            uses: 0,
            maxUses: 50,
            xp: 5,
            priceMultiplier: 0,
            demand: 0
        });
        // Create a simple trade offer
        let offer1 = MerchantJSUtils.createMerchantOffer({
            buy: Item.of("minecraft:beef").toNBT(),
            sell: Item.of("minecraft:egg").toNBT()
        });
        // Open a custom trading GUI with the given player, title, and offers
        MerchantJSUtils.openMerchant(
            event.player,
            Component.translatable("Little Piggy’s Cartoon House"),
            [offer0, offer1]
        );
    }
});
```

The above example lets players **trade with pigs**, with a fully customizable trading GUI!

And if you need deeper customization — read on.

---

### Selective Trade Availability

Let’s first explain the meaning of each parameter:

```javascript
let offer0 = MerchantJSUtils.createMerchantOffer({
    buy: Item.of("minecraft:potato").toNBT(), // Input item A (required)
    buyB: Item.of("minecraft:bread").toNBT(), // Input item B (optional, defaults to empty)
    sell: Item.of("minecraft:stone").toNBT(), // Output item (required)
    uses: 0, // Number of times used (optional, default 0)
    maxUses: 50, // Maximum uses (optional, default 99)
    xp: 5, // Experience reward (optional, default 0)
    priceMultiplier: 0, // Price fluctuation multiplier (optional, default 0)
    demand: 0 // Initial demand value (optional, default 0)
});
```

Setting `maxUses` to `0` will make the trade appear **unavailable**.

Now combine it with this event for dynamic trade control:

```javascript
// client_scripts
MerchantEvents.switchTrade(event => {
    if (event.titleKey == "Little Piggy’s Cartoon House") {
        // Check if it’s the pig’s trading window (works with vanilla ones too!)
        if (event.getOffer().getMaxUses() == 0) {
            Client.player.tell("This trade is locked...");
        }
    }
});
```

With this, you can **selectively enable or disable trades**, which is perfect for stage-based gameplay or progression systems.

---

### Vanilla Trade Compatibility

Vanilla trading already has many mechanics.
MerchantJS mainly focuses on **creating additional trades easily**.
For features like restocking, experience, demand, or price fluctuation — you’ll need to handle those yourself.

At minimum, you’ll need a place to store trade-related data (like inventory or stock info),
since each call to `openMerchant` defines the offers anew.

Fortunately, **KubeJS’s `persistentData`** works great for this.
Then, all that’s left is triggering data refresh when needed.

```javascript
// server_scripts
MerchantEvents.afterTrade(event => {
    event.player.tell(`Thank you for purchasing ${event.getOffer().result.id}! Come again soon!`);
    event.player.addXP(event.getOffer().xp);
});
```

This event fires after any trade occurs — and works for vanilla trades too!
If you want to trigger additional actions after trading, this is the place to do it.

As for restocking — just check the time interval whenever you open the trade window.

---

### Future Plans

None for now — but feel free to make a wish!

![img](https://resource-api.xyeidc.com/client/pics/d9ea7ed6)