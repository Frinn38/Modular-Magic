package fr.frinn.modularmagic.common.crafting.requirement;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.integration.jei.component.JEIComponentGrid;
import fr.frinn.modularmagic.common.tile.TileGridProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementGrid extends ComponentRequirement {

    public float power;

    public RequirementGrid(MachineComponent.IOType actionType, float power) {
        super(ComponentType.Registry.getComponent("grid"), actionType);
        this.power = power;
    }

    @Override
    public boolean startCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        TileGridProvider provider = (TileGridProvider) component.getContainerProvider();
        if(provider == null || !canStartCrafting(component, context, Lists.newArrayList()).isSuccess())
            return false;

        switch (getActionType()) {
            case INPUT:
                provider.setPower(-power);
            case OUTPUT:
                provider.setPower(power);
        }
        return true;
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        TileGridProvider provider = (TileGridProvider) component.getContainerProvider();
        if(provider != null)
            provider.setPower(0.0F);
        return true;
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext context, List restrictions) {
        TileGridProvider provider = (TileGridProvider) component.getContainerProvider();
        if(provider == null)
            return CraftCheck.failure("error.modularmagic.requirement.grid.missingprovider");

        if(getActionType() == MachineComponent.IOType.INPUT && provider.getFreq().getPowerCreated() - provider.getFreq().getPowerDrain() < this.power)
            return CraftCheck.failure("error.modularmagic.requirement.grid.less");

        return CraftCheck.success();
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
