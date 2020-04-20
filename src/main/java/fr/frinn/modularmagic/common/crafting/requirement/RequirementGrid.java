package fr.frinn.modularmagic.common.crafting.requirement;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.crafting.component.ComponentGrid;
import fr.frinn.modularmagic.common.crafting.requirement.types.ModularMagicRequirements;
import fr.frinn.modularmagic.common.crafting.requirement.types.RequirementTypeGrid;
import fr.frinn.modularmagic.common.integration.jei.component.JEIComponentGrid;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Grid;
import fr.frinn.modularmagic.common.tile.TileGridProvider;
import hellfirepvp.modularmachinery.common.crafting.helper.*;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementGrid extends ComponentRequirement.PerTick<Grid, RequirementTypeGrid> {

    public float power;

    public RequirementGrid(IOType actionType, float power) {
        super((RequirementTypeGrid) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMagicRequirements.KEY_REQUIREMENT_GRID), actionType);
        this.power = power;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent component, RecipeCraftingContext ctx) {
        MachineComponent cpn = component.getComponent();
        return cpn.getContainerProvider() instanceof TileGridProvider &&
                cpn.getComponentType() instanceof ComponentGrid &&
                cpn.getIOType() == getActionType();
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
        TileGridProvider provider = (TileGridProvider) component.getComponent().getContainerProvider();
        switch (getActionType()) {
            case OUTPUT:
                provider.setPower(-this.power);

            case INPUT:
                provider.setPower(this.power);
        }
        return CraftCheck.success();
    }

    @Override
    public boolean startCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        return canStartCrafting(component, context, Lists.newArrayList()).isSuccess();
    }

    @Override
    public CraftCheck finishCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        return CraftCheck.success();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, List<ComponentOutputRestrictor> restrictions) {
        TileGridProvider provider = (TileGridProvider) component.getComponent().getContainerProvider();

        if(getActionType() == IOType.INPUT && provider.getFreq().getPowerCreated() - provider.getFreq().getPowerDrain() < this.power)
            return CraftCheck.failure("error.modularmagic.requirement.grid.less");
        else
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
        return new JEIComponentGrid(this);
    }
}
