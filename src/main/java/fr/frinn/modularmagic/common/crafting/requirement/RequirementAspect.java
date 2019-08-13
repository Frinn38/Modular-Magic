package fr.frinn.modularmagic.common.crafting.requirement;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.crafting.requirement.types.ModularMagicRequirements;
import fr.frinn.modularmagic.common.crafting.requirement.types.RequirementTypeAspect;
import fr.frinn.modularmagic.common.integration.jei.component.JEIComponentAspect;
import fr.frinn.modularmagic.common.tile.TileAspectProvider;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.ProcessingComponent;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementAspect extends ComponentRequirement<AspectList, RequirementTypeAspect> {

    public int amount;
    public int countAmount;
    public Aspect aspect;

    public RequirementAspect(IOType actionType, int amount, Aspect aspect) {
        super((RequirementTypeAspect)RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMagicRequirements.KEY_REQUIREMENT_ASPECT), actionType);

        this.amount = amount;
        this.aspect = aspect;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent component, RecipeCraftingContext ctx) {
        return component.getComponent().getContainerProvider() instanceof TileAspectProvider;
    }

    @Override
    public boolean startCrafting(ProcessingComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(!canStartCrafting(component, context, Lists.newArrayList()).isSuccess())
            return false;

        if(getActionType() == IOType.INPUT) {
            TileAspectProvider provider = (TileAspectProvider) component.getComponent().getContainerProvider();
            return provider.takeFromContainer(this.aspect, this.amount);
        }
        return false;
    }

    @Override
    public CraftCheck finishCrafting(ProcessingComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(getActionType() == IOType.OUTPUT) {
            TileAspectProvider provider = (TileAspectProvider) component.getComponent().getContainerProvider();
            provider.addToContainer(this.aspect, this.amount);
        }
        return CraftCheck.success();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent component, RecipeCraftingContext context, List restrictions) {
        TileAspectProvider provider = (TileAspectProvider) component.getComponent().getContainerProvider();
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
        return new JEIComponentAspect(this);
    }
}
