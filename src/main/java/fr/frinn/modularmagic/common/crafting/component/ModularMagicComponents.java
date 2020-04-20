package fr.frinn.modularmagic.common.crafting.component;

import fr.frinn.modularmagic.ModularMagic;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class ModularMagicComponents {

    public static ArrayList<ComponentType> COMPONENTS = new ArrayList<>();

    public static final ResourceLocation KEY_COMPONENT_ASPECT = new ResourceLocation(ModularMagic.MODID, "aspect");
    public static final ResourceLocation KEY_COMPONENT_AURA = new ResourceLocation(ModularMagic.MODID, "aura");
    public static final ResourceLocation KEY_COMPONENT_CONSTELLATION = new ResourceLocation(ModularMagic.MODID, "constellation");
    public static final ResourceLocation KEY_COMPONENT_GRID = new ResourceLocation(ModularMagic.MODID, "grid");
    public static final ResourceLocation KEY_COMPONENT_LIFE_ESSENCE = new ResourceLocation(ModularMagic.MODID, "lifeessence");
    public static final ResourceLocation KEY_COMPONENT_RAINBOW = new ResourceLocation(ModularMagic.MODID, "rainbow");
    public static final ResourceLocation KEY_COMPONENT_STARLIGHT = new ResourceLocation(ModularMagic.MODID, "starlight");
    public static final ResourceLocation KEY_COMPONENT_WILL = new ResourceLocation(ModularMagic.MODID, "will");
    public static final ResourceLocation KEY_COMPONENT_MANA = new ResourceLocation(ModularMagic.MODID, "mana");


    public static void initComponents() {
        if(ModularMagic.astralLoaded) {
            registerComponent(new ComponentConstellation(), KEY_COMPONENT_CONSTELLATION);
            registerComponent(new ComponentStarlight(), KEY_COMPONENT_STARLIGHT);
        }
        if(ModularMagic.bloodmagicLoaded) {
            registerComponent(new ComponentLifeEssence(), KEY_COMPONENT_LIFE_ESSENCE);
            registerComponent(new ComponentWill(), KEY_COMPONENT_WILL);
        }
        if(ModularMagic.extraUtils2Loaded) {
            registerComponent(new ComponentGrid(), KEY_COMPONENT_GRID);
            registerComponent(new ComponentRainbow(), KEY_COMPONENT_RAINBOW);
        }
        if(ModularMagic.naturesauraLoaded) {
            registerComponent(new ComponentAura(), KEY_COMPONENT_AURA);
        }
        if(ModularMagic.thaumcraftLoaded) {
            registerComponent(new ComponentAspect(), KEY_COMPONENT_ASPECT);
        }

        if(ModularMagic.botaniaLoaded) {
        	registerComponent(new ComponentMana(), KEY_COMPONENT_MANA);
        }
    }

    public static void registerComponent(ComponentType component, ResourceLocation name) {
        component.setRegistryName(name);
        COMPONENTS.add(component);
    }
}
