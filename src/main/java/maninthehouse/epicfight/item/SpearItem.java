package maninthehouse.epicfight.item;

import java.lang.reflect.Field;

import maninthehouse.epicfight.animation.LivingMotion;
import maninthehouse.epicfight.capabilities.item.CapabilityItem.HandProperty;
import maninthehouse.epicfight.capabilities.item.CapabilityItem.WeaponCategory;
import maninthehouse.epicfight.capabilities.item.CapabilityItem.WieldStyle;
import maninthehouse.epicfight.capabilities.item.ModWeaponCapability;
import maninthehouse.epicfight.gamedata.Animations;
import maninthehouse.epicfight.gamedata.Colliders;
import maninthehouse.epicfight.gamedata.Skills;
import maninthehouse.epicfight.gamedata.Sounds;
import maninthehouse.epicfight.main.EpicFightMod;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item.ToolMaterial;
import net.minecraft.item.ItemTool;
import net.minecraftforge.fml.relauncher.ReflectionHelper;

public class SpearItem extends WeaponItem {
    public SpearItem(ToolMaterial materialIn) {
        super(materialIn, 3 + (int)materialIn.getAttackDamage(), -2.8F);
        this.setStats();
    }

    @Override
    public boolean canHarvestBlock(IBlockState blockIn) {
        return false;
    }

    @Override
    public void setWeaponCapability() {
    }

    private ToolMaterial getToolMaterial() {
        try {
            Field f = ReflectionHelper.findField(ItemTool.class, "toolMaterial", "field_77864_a");
            return (ToolMaterial) f.get(this);
        } catch (Exception e) {
            EpicFightMod.LOGGER.error("Failed to get tool material for SpearItem", e);
            return ToolMaterial.STONE;
        }
    }

    public void setStats() {
        int harvestLevel = getToolMaterial().getHarvestLevel();
        capability = new ModWeaponCapability(WeaponCategory.SPEAR, (playerdata) -> playerdata.getOriginalEntity().getHeldItemOffhand().isEmpty() ? WieldStyle.TWO_HAND : WieldStyle.ONE_HAND,
                null, Sounds.WHOOSH, Sounds.BLADE_HIT, Colliders.spearNarrow, HandProperty.MAINHAND_ONLY);
        capability.addStyleCombo(WieldStyle.ONE_HAND, Animations.SPEAR_ONEHAND_AUTO, Animations.SPEAR_DASH);
        capability.addStyleCombo(WieldStyle.TWO_HAND, Animations.SPEAR_TWOHAND_AUTO_1, Animations.SPEAR_TWOHAND_AUTO_2, Animations.SPEAR_DASH);
        capability.addStyleCombo(WieldStyle.MOUNT, Animations.SPEAR_MOUNT_ATTACK);
        capability.addStyleAttributeSimple(WieldStyle.ONE_HAND, 4.0D + 4.0D * harvestLevel, 2.4D + harvestLevel * 0.3D, 1);
        capability.addStyleAttributeSimple(WieldStyle.TWO_HAND, 0.0D, 0.6D + harvestLevel * 0.5D, 3);
        capability.addStyleSpecialAttack(WieldStyle.ONE_HAND, Skills.HEARTPIERCER);
        capability.addStyleSpecialAttack(WieldStyle.TWO_HAND, Skills.SLAUGHTER_STANCE);
        capability.addLivingMotionChanger(LivingMotion.RUNNING, Animations.BIPED_RUN_HELDING_WEAPON);
    }
}