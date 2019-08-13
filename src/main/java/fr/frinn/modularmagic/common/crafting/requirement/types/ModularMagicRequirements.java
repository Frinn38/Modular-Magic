package fr.frinn.modularmagic.common.crafting.requirement.types;

import fr.frinn.modularmagic.ModularMagic;
import hellfirepvp.modularmachinery.common.crafting.requirement.type.RequirementType;
import net.minecraft.util.ResourceLocation;

import java.util.ArrayList;

public class ModularMagicRequirements {

    public static ArrayList<RequirementType> REQUIREMENTS = new ArrayList<>();

    public static final ResourceLocation KEY_REQUIREMENT_ASPECT = new ResourceLocation(ModularMagic.MODID, "aspect");
    public static final ResourceLocation KEY_REQUIREMENT_AURA = new ResourceLocation(ModularMagic.MODID, "aura");
    public static final ResourceLocation KEY_REQUIREMENT_CONSTELLATION = new ResourceLocation(ModularMagic.MODID, "constellation");
    public static final ResourceLocation KEY_REQUIREMENT_GRID = new ResourceLocation(ModularMagic.MODID, "grid");
    public static final ResourceLocation KEY_REQUIREMENT_LIFE_ESSENCE = new ResourceLocation(ModularMagic.MODID, "lifeessence");
    public static final ResourceLocation KEY_REQUIREMENT_STARLIGHT = new ResourceLocation(ModularMagic.MODID, "starlight");
    public static final ResourceLocation KEY_REQUIREMENT_WILL = new ResourceLocation(ModularMagic.MODID, "will");

    public static void initRequirements() {
        if(ModularMagic.astralLoaded) {
            registerRequirement(new RequirementTypeConstellation(), KEY_REQUIREMENT_CONSTELLATION);
            registerRequirement(new RequirementTypeStarlight(), KEY_REQUIREMENT_STARLIGHT);
        }
        if(ModularMagic.bloodmagicLoaded) {
            registerRequirement(new RequirementTypeLifeEssence(), KEY_REQUIREMENT_LIFE_ESSENCE);
            registerRequirement(new RequirementTypeWill(), KEY_REQUIREMENT_WILL);
        }
        if(ModularMagic.extraUtils2Loaded) {
            registerRequirement(new RequirementTypeGrid(), KEY_REQUIREMENT_GRID);
        }
        if(ModularMagic.naturesauraLoaded) {
            registerRequirement(new RequirementTypeAura(), KEY_REQUIREMENT_AURA);
        }
        if(ModularMagic.thaumcraftLoaded) {
            registerRequirement(new RequirementTypeAspect(), KEY_REQUIREMENT_ASPECT);
        }
    }

    public static void registerRequirement(RequirementType requirement, ResourceLocation name) {
        requirement.setRegistryName(name);
        REQUIREMENTS.add(requirement);
    }
}
