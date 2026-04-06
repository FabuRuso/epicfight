package maninthehouse.epicfight.utils;

import java.util.List;
import java.util.Map;

import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.model.ModelBox;
import net.minecraft.client.model.TexturedQuad;
import net.minecraft.client.renderer.entity.RenderLivingBase;
import net.minecraft.client.renderer.entity.layers.LayerArmorBase;
import net.minecraft.client.renderer.entity.layers.LayerBipedArmor;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.monster.EntityEnderman;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

public final class McCompat {
    private McCompat() {}

    // -----------------------------------------------------------------------
    // KeyBinding.pressTime  SRG: field_151474_i  (AT: pressTime)
    // -----------------------------------------------------------------------
    public static int getKeyBindingPressTime(KeyBinding key) {
        try {
            return ObfuscationReflectionHelper.getPrivateValue(KeyBinding.class, key, "pressTime", "field_151474_i");
        } catch (Exception e) {
            return 0;
        }
    }

    public static void setKeyBindingPressTime(KeyBinding key, int value) {
        try {
            ObfuscationReflectionHelper.setPrivateValue(KeyBinding.class, key, value, "pressTime", "field_151474_i");
        } catch (Exception ignored) {}
    }

    public static void incrementKeyBindingPressTime(KeyBinding key) {
        setKeyBindingPressTime(key, getKeyBindingPressTime(key) + 1);
    }

    public static void decrementKeyBindingPressTime(KeyBinding key) {
        int value = getKeyBindingPressTime(key);
        if (value > 0) {
            setKeyBindingPressTime(key, value - 1);
        }
    }

    // -----------------------------------------------------------------------
    // EntityPlayerSP.sprintToggleTimer  SRG: field_71156_d  (AT: sprintToggleTimer)
    // -----------------------------------------------------------------------
    public static void setSprintToggleTimer(EntityPlayerSP player, int value) {
        try {
            ObfuscationReflectionHelper.setPrivateValue(EntityPlayerSP.class, player, value, "sprintToggleTimer", "field_71156_d");
        } catch (Exception ignored) {}
    }

    // -----------------------------------------------------------------------
    // EntityDataManager.entries  SRG: field_187234_c  (AT: entries)
    // -----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public static boolean hasDataManagerEntry(EntityDataManager manager, DataParameter<?> key) {
        try {
            Map<Integer, ?> entries = ObfuscationReflectionHelper.getPrivateValue(EntityDataManager.class, manager, "entries", "field_187234_c");
            return entries != null && entries.containsKey(key.getId());
        } catch (Exception e) {
            return false;
        }
    }

    // -----------------------------------------------------------------------
    // EntityEnderman.SCREAMING  SRG: field_184719_bw  (AT: SCREAMING)
    // -----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public static DataParameter<Boolean> getEndermanScreamingParameter() {
        try {
            return ObfuscationReflectionHelper.getPrivateValue(EntityEnderman.class, null, "SCREAMING", "field_184719_bw");
        } catch (Exception e) {
            return null;
        }
    }

    // -----------------------------------------------------------------------
    // RenderLivingBase.layerRenderers  SRG: field_177097_h  (AT: layerRenderers)
    // -----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public static List<LayerRenderer<?>> getLayerRenderers(RenderLivingBase<?> renderer) {
        try {
            return ObfuscationReflectionHelper.getPrivateValue(RenderLivingBase.class, renderer, "layerRenderers", "field_177097_h");
        } catch (Exception e) {
            return null;
        }
    }

    // -----------------------------------------------------------------------
    // LayerArmorBase.ARMOR_TEXTURE_RES_MAP  SRG: field_177191_j  (AT: ARMOR_TEXTURE_RES_MAP)
    // -----------------------------------------------------------------------
    @SuppressWarnings("unchecked")
    public static Map<String, ResourceLocation> getArmorTextureMap() {
        try {
            try {
                return ObfuscationReflectionHelper.getPrivateValue(LayerBipedArmor.class, null, "ARMOR_TEXTURE_RES_MAP", "field_177191_j");
            } catch (Exception ignored) {
                return ObfuscationReflectionHelper.getPrivateValue(LayerArmorBase.class, null, "ARMOR_TEXTURE_RES_MAP", "field_177191_j");
            }
        } catch (Exception e) {
            return null;
        }
    }

    // -----------------------------------------------------------------------
    // ModelBox.quadList  SRG: field_78254_i  (AT: quadList)
    // -----------------------------------------------------------------------
    public static TexturedQuad[] getQuadList(ModelBox cube) {
        try {
            return ObfuscationReflectionHelper.getPrivateValue(ModelBox.class, cube, "quadList", "field_78254_i");
        } catch (Exception e) {
            return null;
        }
    }
}
