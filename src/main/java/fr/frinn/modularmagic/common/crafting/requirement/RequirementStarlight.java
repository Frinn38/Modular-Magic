package fr.frinn.modularmagic.common.crafting.requirement;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.crafting.requirement.types.ModularMagicRequirements;
import fr.frinn.modularmagic.common.crafting.requirement.types.RequirementTypeStarlight;
import fr.frinn.modularmagic.common.integration.jei.component.JEIComponentStarlight;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Starlight;
import fr.frinn.modularmagic.common.tile.TileStarlightInput;
import fr.frinn.modularmagic.common.tile.TileStarlightOutput;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.ProcessingComponent;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.util.ResultChance;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementStarlight extends ComponentRequirement.PerTick<Starlight, RequirementTypeStarlight> {

    public float starlightAmount;

    public RequirementStarlight(IOType actionType, float starlightAmount) {
        super((RequirementTypeStarlight) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMagicRequirements.KEY_REQUIREMENT_STARLIGHT), actionType);
        this.starlightAmount = starlightAmount;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
        return component.getComponent().getContainerProvider() instanceof TileStarlightInput ||
                component.getComponent().getContainerProvider() instanceof TileStarlightOutput;
    }

    @Override
    public void startIOTick(RecipeCraftingContext context, float durationMultiplier) {

    }

    @Nonnull
    @Override
    public CraftCheck resetIOTick(RecipeCraftingContext context) {
        return CraftCheck.success();
    }

    @Nonnull
    @Override
    public CraftCheck doIOTick(ProcessingComponent<?> component, RecipeCraftingContext context) {
        if (getActionType() == IOType.OUTPUT)
            ((TileStarlightOutput) component.getComponent().getContainerProvider()).setStarlightProduced(this.starlightAmount / 4000);

        return CraftCheck.success();
    }

    @Override
    public boolean startCrafting(ProcessingComponent component, RecipeCraftingContext context, ResultChance chance) {
        return canStartCrafting(component, context, Lists.newArrayList()).isSuccess();
    }

    @Override
    public CraftCheck finishCrafting(ProcessingComponent component, RecipeCraftingContext context, ResultChance chance) {
        return CraftCheck.success();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent component, RecipeCraftingContext context, List restrictions) {
            if(getActionType() == IOType.INPUT) {
                TileStarlightInput provider = (TileStarlightInput) component.getComponent().getContainerProvider();
                return provider.getStarlightStored() >= this.starlightAmount ? CraftCheck.success() : CraftCheck.failure("error.modularmagic.requirement.starlight.less");
            }
            return CraftCheck.success();
    }

    @Nonnull
    @Override
    public String getMissingComponentErrorMessage(IOType ioType) {
        return "error.modularmagic.component.invalid";
    }

    @Override
    public ComponentRequirement deepCopy() {
        return this;
    }

    @Override
    public ComponentRequirement deepCopyModified(List list) {
        return this;
    }

    @Override
    public void startRequirementCheck(ResultChance contextChance, RecipeCraftingContext context) {

    }

    @Override
    public void endRequirementCheck() {

    }

    @Override
    public JEIComponent provideJEIComponent() {
        return new JEIComponentStarlight(this);
    }
}
