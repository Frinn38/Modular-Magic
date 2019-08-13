package fr.frinn.modularmagic.common.crafting.requirement;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.crafting.requirement.types.ModularMagicRequirements;
import fr.frinn.modularmagic.common.crafting.requirement.types.RequirementTypeAura;
import fr.frinn.modularmagic.common.integration.jei.component.JEIComponentAura;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Aura;
import fr.frinn.modularmagic.common.tile.TileAuraProvider;
import hellfirepvp.modularmachinery.common.crafting.helper.*;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.modifier.RecipeModifier;
import hellfirepvp.modularmachinery.common.util.ResultChance;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementAura extends ComponentRequirement<Aura, RequirementTypeAura> {

    public Aura aura;
    public int max;
    public int min;

    public RequirementAura(IOType type, Aura aura, int max, int min) {
        super((RequirementTypeAura) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMagicRequirements.KEY_REQUIREMENT_AURA), type);
        this.aura = aura;
        this.max = max;
        this.min = min;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
        return component.getComponent().getContainerProvider() instanceof TileAuraProvider;
    }

    @Override
    public boolean startCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        if(!canStartCrafting(component, context, Lists.newArrayList()).isSuccess())
            return false;

        if(getActionType() == IOType.INPUT) {
            TileAuraProvider provider = (TileAuraProvider) component.getComponent().getContainerProvider();
            provider.removeAura(this.aura);
        }
        return true;
    }

    @Override
    public CraftCheck finishCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        if(getActionType() == IOType.OUTPUT) {
            TileAuraProvider provider = (TileAuraProvider) component.getComponent().getContainerProvider();
            provider.addAura(this.aura);
        }
        return CraftCheck.success();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, List<ComponentOutputRestrictor> restrictions) {
        TileAuraProvider provider = (TileAuraProvider) component.getComponent().getContainerProvider();
        if(provider.getAura().getType() != this.aura.getType())
            return CraftCheck.failure("error.modularmagic.requirement.aura.invalid");

        switch (getActionType()) {
            case INPUT:
                if(provider.getAura().getAmount() - this.aura.getAmount() < this.min)
                    return CraftCheck.failure("error.modularmagic.requirement.aura.less");

            case OUTPUT:
                if(provider.getAura().getAmount() + this.aura.getAmount() > this.max)
                    return CraftCheck.failure("error.modularmagic.requirement.aura.more");
        }
        return CraftCheck.success();
    }

    @Override
    public ComponentRequirement<Aura, RequirementTypeAura> deepCopy() {
        return this;
    }

    @Override
    public ComponentRequirement<Aura, RequirementTypeAura> deepCopyModified(List<RecipeModifier> modifiers) {
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
    public JEIComponent<Aura> provideJEIComponent() {
        return new JEIComponentAura(this);
    }
}
