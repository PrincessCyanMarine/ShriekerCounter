## This mod adds a HUD element that counts how many sculk shriekers were activated by the player

# Sides
Required server side (I wanted to make it client side only, but didn't find a way to read shrieker warnings without the server)

Technically optional on client

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
Whether the counter should be shown when the player's warning level is equal to 0

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



# Other stuff
This mod requires [completeconfig](https://beta.curseforge.com/minecraft/mc-mods/completeconfig) and [YetAnotherConfigLib](https://curseforge.com/minecraft/mc-mods/yacl) is recommended. Both are already included in the main file download

The "pure" file doesn't include either and therefore requires them to be installed normally
