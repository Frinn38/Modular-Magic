package fr.frinn.modularmagic.component.requirement;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.integration.jei.component.JEIComponentStarlight;
import fr.frinn.modularmagic.tile.TileStarlightInput;
import fr.frinn.modularmagic.tile.TileStarlightOutput;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementStarlight extends ComponentRequirement {

    public float starlightAmount;

    public RequirementStarlight(MachineComponent.IOType actionType, float starlightAmount) {
        super(ComponentType.Registry.getComponent("starlight"), actionType);
        this.starlightAmount = starlightAmount;
    }

    @Override
    public boolean startCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(!canStartCrafting(component, context, Lists.newArrayList()).isSuccess())
            return false;

        return true;
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(getActionType() == MachineComponent.IOType.OUTPUT) {
            ((TileStarlightOutput) component.getContainerProvider()).setStarlightProduced(0.0F);
        }
        return true;
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext context, List restrictions) {
        if(component.getContainerProvider() instanceof TileStarlightInput || component.getContainerProvider() instanceof TileStarlightOutput) {
            if(getActionType() == MachineComponent.IOType.INPUT) {
                TileStarlightInput provider = (TileStarlightInput) component.getContainerProvider();
                return provider.getStarlightStored() >= this.starlightAmount ? CraftCheck.success() : CraftCheck.failure("error.modularmagic.requirement.starlight.less");
            }
            else if(getActionType() == MachineComponent.IOType.OUTPUT) {
                TileStarlightOutput provider = (TileStarlightOutput) component.getContainerProvider();
                provider.setStarlightProduced(this.starlightAmount / 4000.0F);
                provider.controller = context.getMachineController();
                return CraftCheck.success();
            }
            return CraftCheck.failure("error.modularmagic.requirement.invalid");
        }
        return CraftCheck.failure("error.modularmagic.requirement.starlight.missingprovider");
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
