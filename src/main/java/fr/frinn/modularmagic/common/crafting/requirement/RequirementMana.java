package fr.frinn.modularmagic.common.crafting.requirement;

import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.collect.Lists;

import fr.frinn.modularmagic.common.crafting.requirement.types.ModularMagicRequirements;
import fr.frinn.modularmagic.common.crafting.requirement.types.RequirementTypeMana;
import fr.frinn.modularmagic.common.integration.jei.component.JEIComponentMana;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Mana;
import fr.frinn.modularmagic.common.tile.TileManaProvider;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.ProcessingComponent;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.util.ResultChance;

public class RequirementMana extends ComponentRequirement<Mana, RequirementTypeMana> {

    public int manaAmount;

    public RequirementMana(IOType actionType, int manaAmount) {
        super((RequirementTypeMana) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMagicRequirements.KEY_REQUIREMENT_MANA), actionType);
        this.manaAmount = manaAmount;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
               return component.getComponent().getContainerProvider() instanceof TileManaProvider;
    }

    @Override
    public boolean startCrafting(ProcessingComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(!canStartCrafting(component, context, Lists.newArrayList()).isSuccess())
        	return false;
        TileManaProvider provider = (TileManaProvider) component.getComponent().getContainerProvider();
        provider.recieveMana(-manaAmount);
        return true;
    }

    @Override
    public CraftCheck finishCrafting(ProcessingComponent component, RecipeCraftingContext context, ResultChance chance) {
    	if(getActionType() == IOType.OUTPUT) {
        TileManaProvider provider = (TileManaProvider) component.getComponent().getContainerProvider();
        	if(provider instanceof TileManaProvider.Output)
        provider.recieveMana(manaAmount);
        }
        return CraftCheck.success();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent component, RecipeCraftingContext context, List restrictions) {
            if(getActionType() == IOType.INPUT) {
            	TileManaProvider provider = (TileManaProvider) component.getComponent().getContainerProvider();
                return provider.getCurrentMana() >= this.manaAmount ? CraftCheck.success() : CraftCheck.failure("error.modularmagic.requirement.mana.less");
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
        return new JEIComponentMana(this);
    }
}
