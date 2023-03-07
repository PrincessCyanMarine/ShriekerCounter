## This mod adds a HUD element that counts how many sculk shriekers were activated by the player
The counter automatically reduces after the 10 minute cooldown and ignores player placed shriekers


[(Inspired by the counter on Yeah Jaron's "Minecraft 100 Days, but I CAN'T JUMP")](https://youtu.be/SXj1FvXyzhs?t=1427)

# Sides
Required server side (I wanted to make it client side only, but didn't find a way to read the shrieker count without the server)

Technically optional on client (of course the HUD element won't show for players who don't have the mod installed, but they will still receive the "notification" from the shriekerNotifyOnChange gamerule)


# Customization
## Position
The position of the counter relative to the anchor
#### value
x: number
y: number

#### default
x = 0

y = 0

### Command
/shriekercounter position <x: number> <y: number>


## Anchor
What the position value will use as reference
#### value
Vertical: TOP CENTER BOTTOM

Horizontal: LEFT CENTER RIGHT

#### default
Vertical = TOP
horizontal = LEFT

### Command
/shriekercounter anchor <top | center | bottom> <left | center | right>

## Scale
The relative scale of the counter
#### value 
A float between 0.0 and 10.0
#### default
scale = 1.0
### Command
/shriekercounter scale <0.0-10.0>

## Show when zero
Whether the counter should be shown when the player's count is equal to 0

#### value
Boolean (true or false)
#### default
show_when_zero = true
### Command
/shriekercounter show_when_zero <true | false>

## Active
Whether the counter should be shown

Can be toggled with a keybind (O by default)

#### value
Boolean (true or false)
#### default
active = true

### Commands
/shriekercounter hide

/shriekercounter show

## Resource Packs
Resource packs can be used to customize the counter's appearence

## Example resource packs
![all](https://user-images.githubusercontent.com/58150571/222997350-ee3cd190-4dee-406e-9047-2760fdea34f7.png)



[Example resource packs are available here](https://github.com/PrincessCyanMarine/ShriekerCounter/tree/main/resourcepacks)

## Gamerule shriekerNotifyOnChange
If true, whenever a player's shrieker count changes, that player will hear a noteblock note and receive a chat message saying
"Your shrieker count [increased/decreased] to [count]"

# Other stuff
This mod requires [completeconfig](https://beta.curseforge.com/minecraft/mc-mods/completeconfig) and fabric API

[YetAnotherConfigLib](https://curseforge.com/minecraft/mc-mods/yacl) is recommended

Complete config is already included in the main file download, Fabric API and YACL aren't

The "pure" file doesn't include any of them and complete config needs to be installed manually
