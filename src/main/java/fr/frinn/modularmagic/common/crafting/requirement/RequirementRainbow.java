package fr.frinn.modularmagic.common.crafting.requirement;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.crafting.component.ComponentRainbow;
import fr.frinn.modularmagic.common.crafting.requirement.types.ModularMagicRequirements;
import fr.frinn.modularmagic.common.crafting.requirement.types.RequirementTypeRainbow;
import fr.frinn.modularmagic.common.integration.jei.component.JEIComponentRainbow;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Rainbow;
import fr.frinn.modularmagic.common.tile.TileRainbowProvider;
import hellfirepvp.modularmachinery.common.crafting.helper.*;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import hellfirepvp.modularmachinery.common.util.ResultChance;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementRainbow extends ComponentRequirement<Rainbow, RequirementTypeRainbow> {

    public RequirementRainbow(IOType actionType) {
        super((RequirementTypeRainbow) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMagicRequirements.KEY_REQUIREMENT_RAINBOW), actionType);
    }

    @Override
    public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
        MachineComponent cpn = component.getComponent();
        return cpn.getContainerProvider() instanceof TileRainbowProvider &&
                cpn.getComponentType() instanceof ComponentRainbow &&
                cpn.getIOType() == getActionType();
    }

    @Override
    public boolean startCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        if(!canStartCrafting(component, context, Lists.newArrayList()).isSuccess())
            return false;

        return true;
    }

    @Nonnull
    @Override
    public CraftCheck finishCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        return CraftCheck.success();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, List<ComponentOutputRestrictor> restrictions) {
        TileRainbowProvider provider = (TileRainbowProvider) component.getComponent().getContainerProvider();
        if(provider.rainbow())
            return CraftCheck.success();
        else
            return CraftCheck.failure("error.modularmagic.requirement.rainbow.less");
    }

    @Override
    public ComponentRequirement<Rainbow, RequirementTypeRainbow> deepCopy() {
        return this;
    }

    @Override
    public ComponentRequirement<Rainbow, RequirementTypeRainbow> deepCopyModified(List<RecipeModifier> modifiers) {
        return this;
    }

    @Override
    public void startRequirementCheck(ResultChance contextChance, RecipeCraftingContext context) {

    }

    @Override
    public void endRequirementCheck() {

    }

    @Nonnull
    @Override
    public String getMissingComponentErrorMessage(IOType ioType) {
        return "error.modularmagic.component.invalid";
    }

    @Override
    public JEIComponent<Rainbow> provideJEIComponent() {
        return new JEIComponentRainbow();
    }
}
