package fr.frinn.modularmagic.common.crafting.requirement;

import WayofTime.bloodmagic.soul.EnumDemonWillType;
import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.crafting.requirement.types.ModularMagicRequirements;
import fr.frinn.modularmagic.common.crafting.requirement.types.RequirementTypeWill;
import fr.frinn.modularmagic.common.integration.jei.component.JEIComponentWill;
import fr.frinn.modularmagic.common.integration.jei.ingredient.DemonWill;
import fr.frinn.modularmagic.common.tile.TileWillProvider;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.ProcessingComponent;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.util.ResultChance;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementWill extends ComponentRequirement<DemonWill, RequirementTypeWill> {

    public double willAmount;
    public EnumDemonWillType willType;
    public double min;
    public double max;

    public RequirementWill(IOType actionType, double willRequired, EnumDemonWillType willType, double min, double max) {
        super((RequirementTypeWill) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMagicRequirements.KEY_REQUIREMENT_WILL), actionType);
        this.willAmount = willRequired;
        this.willType = willType;
        this.min = min;
        this.max = max;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
        return component.getComponent().getContainerProvider() instanceof TileWillProvider;
    }

    @Override
    public boolean startCrafting(ProcessingComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(!canStartCrafting(component, context, Lists.newArrayList()).isSuccess())
            return false;

        if(getActionType() == IOType.INPUT) {
            TileWillProvider willProvider = (TileWillProvider)component.getComponent().getContainerProvider();
            willProvider.removeWill(willAmount, willType);
        }
        return true;
    }

    @Override
    public CraftCheck finishCrafting(ProcessingComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(getActionType() == IOType.OUTPUT) {
            TileWillProvider willProvider = (TileWillProvider)component.getComponent().getContainerProvider();
            willProvider.addWill(willAmount, willType);
        }
        return CraftCheck.success();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent component, RecipeCraftingContext context, List restrictions) {
        TileWillProvider willProvider = (TileWillProvider)component.getComponent().getContainerProvider();
        switch (getActionType()) {
            case INPUT:
                if(willProvider.getWill(this.willType) - this.willAmount < this.min)
                    return CraftCheck.failure("error.modularmagic.requirement.will.less");

            case OUTPUT:
                if(willProvider.getWill(this.willType) - this.willAmount > this.max)
                    return CraftCheck.failure("error.modularmagic.requirement.will.more");
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
        return new JEIComponentWill(this);
    }
}
