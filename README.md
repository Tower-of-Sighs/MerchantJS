## MerchantJS

本模组是KubeJS的附属，拓展其在交易系统方面的内容。

### 自定义额外交易

本模组允许你在任何地方发起交易，与猪交易，与末地石交易，与附魔金苹果交易，甚至是凭空交易！

```javascript
// server_scripts
ItemEvents.entityInteracted(event => {
    // 判读与猪交互。
    if (event.getTarget().type === "minecraft:pig") {
        // 完整地创建一行交易内容。
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
        // 简单地创建一行交易内容。
        let offer1 = MerchantJSUtils.createMerchantOffer({
            buy: Item.of("minecraft:beef").toNBT(),
            sell: Item.of("minecraft:egg").toNBT()
        });
        // 打开自定义交易，需传入玩家、界面标题和交易列表。
        MerchantJSUtils.openMerchant(
            event.player,
            Component.translatable("小飞猪卡通屋"),
            [offer0, offer1]
        );
    }
});
```

以上示例可以让玩家与猪猪进行交易，并且交易面板可以完全自定义！

而如果你需要深度自定义，请跟着我往下看。

### 选择性开放交易

首先解释一下各个参数的意义：

```javascript
let offer0 = MerchantJSUtils.createMerchantOffer({
    buy: Item.of("minecraft:potato").toNBT(), // 输入物品A，必填。
    buyB: Item.of("minecraft:bread").toNBT(), // 输入物品B，可选，默认为空。
    sell: Item.of("minecraft:stone").toNBT(), // 结果物品，必填。
    uses: 0, // 已交易次数，可选，默认为0。
    maxUses: 50, // 最大可交易次数，可选，默认99。
    xp: 5, // 奖励经验值，可选，默认为0。
    priceMultiplier: 0, // 价格浮动乘数，可选，默认为0。
    demand: 0 // 初始需求值，可选，默认为0。
});
```

如果将最大可交易次数设置为0，即可让该交易条目显示为不可用状态。

此时，再搭配上本模组提供的切换交易事件：

```javascript
// client_scripts
MerchantEvents.switchTrade(event => {
    if (event.titleKey == "小飞猪卡通屋") {
        // 判断是否为猪猪的交易面板，原版也是支持的哦！
        if (event.getOffer().getMaxUses() == 0) {
            Client.player.tell("该交易被锁住惹……");
        }
    }
});
```

这样即可实现选择性开放交易，适合搭配阶段玩法使用。

### 原版交易模式继承

原版交易的功能还挺多的，本模组提供的方法主要用于便捷地进行额外交易，至于补货、经验、需求、价格波动这一块，就需要自行实现。

最起码，需要有个地方储存库存信息等交易相关数据，否则每次使用openMerchant方法都是重新定义对吧？

其实KubeJS提供的persistentData就很好用了，那么剩下的关键点就是如何触发数据刷新了。

```javascript
// server_scripts
MerchantEvents.afterTrade(event => {
    event.player.tell(`肥肠感谢宁购买${event.getOffer().result.id}，欢迎下次光临！`);
    event.player.addXP(event.getOffer().xp);
});
```

这个事件发生在一切交易发生后，对于原版也是有效的，如果要触发交易后的附加行为，就在这里进行吧。

至于补货，在打开交易面板的时候判断时间间隔就行啦。

### 后续计划

暂无，可许愿。

![img](https://resource-api.xyeidc.com/client/pics/d9ea7ed6)