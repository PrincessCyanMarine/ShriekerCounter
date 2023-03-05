package net.cyanmarine.shriekercounter.client;

import me.lortseam.completeconfig.api.ConfigEntries;
import me.lortseam.completeconfig.api.ConfigEntry;
import me.lortseam.completeconfig.api.ConfigGroup;
import me.lortseam.completeconfig.data.Config;
import net.cyanmarine.shriekercounter.ShriekerCounter;
import net.cyanmarine.shriekercounter.constants.Anchor;

public class ModConfig extends Config {
    @ConfigEntry
    private boolean active = true;
    @ConfigEntry
    private boolean showWhenZero = true;
    @ConfigEntry
    @ConfigEntry.BoundedFloat(min=0.0f, max=10.0f)
    @ConfigEntry.Slider
    private float scale = 1.0f;

    @Transitive
    private final Position position = new Position();



    public float getScale() { return scale; }
    public boolean isActive() { return active; }
    public boolean showWhenZero() { return showWhenZero; }
    public Position getPosition() { return position; }

    public void setScale(float scale) { this.scale = scale; this.save(); }
    public void setActive(boolean active) { this.active = active; this.save(); }
    public void showWhenZero(boolean showWhenZero) { this.showWhenZero = showWhenZero; this.save(); }


    public int setAnchor(Anchor anchor) { this.position.setAnchor(anchor); this.save(); return 0; }
    public void setX(int x) { this.position.setX(x); this.save(); }
    public void setY(int y) { this.position.setY(y); this.save(); }


    @ConfigEntries(includeAll = true)
    public static class Position implements ConfigGroup {
        private Anchor anchor = Anchor.TOP_LEFT;
        private int x = 0;
        private int y = 0;

        public Anchor getAnchor() { return anchor; }
        public int getX() { return x; }
        public int getY() { return y; }

        public void setX(int x) { this.x = x; }
        public void setY(int y) { this.y = y; }
        public void setAnchor(Anchor anchor) { this.anchor = anchor; }
    }

    public ModConfig() {
        super(ShriekerCounter.MOD_ID);
    }
}
