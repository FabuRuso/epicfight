package maninthehouse.epicfight.capabilities.item;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import maninthehouse.epicfight.animation.types.StaticAnimation;
import maninthehouse.epicfight.gamedata.Animations;
import maninthehouse.epicfight.main.EpicFightMod;
import net.minecraft.item.Item;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSword;
import net.minecraft.item.ItemTool;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public abstract class MaterialItemCapability extends CapabilityItem {
    protected ToolMaterial material;

    protected static List<StaticAnimation> toolAttackMotion;
    protected static List<StaticAnimation> mountAttackMotion;

    static {
        toolAttackMotion = new ArrayList<StaticAnimation>();
        toolAttackMotion.add(Animations.TOOL_AUTO_1);
        toolAttackMotion.add(Animations.TOOL_AUTO_2);
        toolAttackMotion.add(Animations.TOOL_DASH);
        mountAttackMotion = new ArrayList<StaticAnimation>();
        mountAttackMotion.add(Animations.SWORD_MOUNT_ATTACK);
    }

    public MaterialItemCapability(Item item, WeaponCategory category) {
        super(item, category);

        try {
            if (item instanceof ItemTool) {
                Field f = ReflectionHelper.findField(ItemTool.class, "toolMaterial", "field_77864_a");
                this.material = (ToolMaterial) f.get(item);
            } else if (item instanceof ItemSword) {
                Field f = ReflectionHelper.findField(ItemSword.class, "material", "field_77865_bY");
                this.material = (ToolMaterial) f.get(item);
            } else if (item instanceof ItemHoe) {
                Field f = ReflectionHelper.findField(ItemHoe.class, "toolMaterial", "field_77864_a");
                this.material = (ToolMaterial) f.get(item);
            } else {
                this.material = ToolMaterial.STONE;
            }
        } catch (Exception e) {
            EpicFightMod.LOGGER.error("Failed to get tool material for " + item.getRegistryName(), e);
            this.material = ToolMaterial.STONE;
        }

        if (EpicFightMod.isPhysicalClient()) {
            loadClientThings();
        }
        registerAttribute();
    }

    @Override
    public List<StaticAnimation> getMountAttackMotion() {
        return mountAttackMotion;
    }
}