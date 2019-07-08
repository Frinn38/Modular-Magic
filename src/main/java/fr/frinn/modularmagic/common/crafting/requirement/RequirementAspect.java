package fr.frinn.modularmagic.common.crafting.requirement;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.integration.jei.component.JEIComponentAspect;
import fr.frinn.modularmagic.common.tile.TileAspectProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import thaumcraft.api.aspects.Aspect;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementAspect extends ComponentRequirement {

    public int amount;
    public Aspect aspect;

    public RequirementAspect(MachineComponent.IOType actionType, int amount, Aspect aspect) {
        super(ComponentType.Registry.getComponent("aspect"), actionType);

        this.amount = amount;
        this.aspect = aspect;
    }

    @Override
    public boolean startCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(!canStartCrafting(component, context, Lists.newArrayList()).isSuccess())
            return false;

        if(getActionType() == MachineComponent.IOType.INPUT) {
            TileAspectProvider provider = (TileAspectProvider) component.getContainerProvider();
            return provider.takeFromContainer(this.aspect, this.amount);
        }
        return true;
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(getActionType() == MachineComponent.IOType.OUTPUT) {
            TileAspectProvider provider = (TileAspectProvider) component.getContainerProvider();
            provider.addToContainer(this.aspect, this.amount);
        }
        return true;
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext context, List restrictions) {
        TileAspectProvider provider = (TileAspectProvider) component.getContainerProvider();
        switch (getActionType()) {
            case INPUT:
                if(provider.doesContainerContainAmount(this.aspect, this.amount))
                    return CraftCheck.success();
                else
                    return CraftCheck.failure("error.modularmagic.requirement.aspect.less");

            case OUTPUT:
                if(provider.doesContainerAccept(this.aspect))
                    return CraftCheck.success();
                else
                    return CraftCheck.failure("error.modularmagic.requirement.aspect.out");
        }
        return CraftCheck.skipComponent();
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
        return new JEIComponentAspect(this);
    }
}
