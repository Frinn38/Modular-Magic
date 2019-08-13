package fr.frinn.modularmagic.common.crafting.requirement;

import WayofTime.bloodmagic.core.data.SoulNetwork;
import WayofTime.bloodmagic.core.data.SoulTicket;
import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.crafting.requirement.types.ModularMagicRequirements;
import fr.frinn.modularmagic.common.crafting.requirement.types.RequirementTypeLifeEssence;
import fr.frinn.modularmagic.common.integration.jei.component.JEIComponentLifeEssence;
import fr.frinn.modularmagic.common.integration.jei.ingredient.LifeEssence;
import fr.frinn.modularmagic.common.tile.TileLifeEssenceProvider;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.ProcessingComponent;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.util.ResultChance;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementLifeEssence extends ComponentRequirement.PerTick<LifeEssence, RequirementTypeLifeEssence> {

    public int essenceAmount;
    public boolean isPerTick;
    SoulNetwork network;
    private boolean flag = false;

    public RequirementLifeEssence(IOType actionType, int essenceAmount, boolean perTick) {
        super((RequirementTypeLifeEssence) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMagicRequirements.KEY_REQUIREMENT_LIFE_ESSENCE), actionType);

        this.essenceAmount = essenceAmount;
        this.isPerTick = perTick;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent component, RecipeCraftingContext ctx) {
        return component.getComponent().getContainerProvider() instanceof TileLifeEssenceProvider;
    }

    @Override
    public boolean startCrafting(ProcessingComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(!canStartCrafting(component, context, Lists.newArrayList()).isSuccess())
            return false;

        TileLifeEssenceProvider essenceProvider = (TileLifeEssenceProvider)component.getComponent().getContainerProvider();
        this.network = essenceProvider.getSoulNetwork();

        if(getActionType() == IOType.INPUT && !isPerTick) {
            essenceProvider.getSoulNetwork().syphon(new SoulTicket(essenceAmount));
        }
        return true;
    }

    @Override
    public CraftCheck finishCrafting(ProcessingComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(getActionType() == IOType.OUTPUT && !isPerTick) {
            TileLifeEssenceProvider essenceProvider = (TileLifeEssenceProvider)component.getComponent().getContainerProvider();
            essenceProvider.getSoulNetwork().add(new SoulTicket(essenceAmount), essenceProvider.getOrbCapacity());
        }
        return CraftCheck.success();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent component, RecipeCraftingContext context, List restrictions) {
        TileLifeEssenceProvider essenceProvider = (TileLifeEssenceProvider)component.getComponent().getContainerProvider();

        if(essenceProvider.getSoulNetwork() == null)
            return CraftCheck.failure("error.modularmagic.requirement.lifeessence.orb");

        switch (getActionType()) {
            case INPUT:
                if(essenceProvider.getSoulNetwork().getCurrentEssence() >= essenceAmount) {
                    return CraftCheck.success();
                }
                else {
                    return CraftCheck.failure("error.modularmagic.requirement.lifeessence.lp");
                }

            case OUTPUT:
                return CraftCheck.success();
        }
        return CraftCheck.failure("error.modularmagic.requirement.lifeessence");
    }

    @Nonnull
    @Override
    public String getMissingComponentErrorMessage(IOType ioType) {
        return "error.modularmagic.component.invalid";
    }

    @Override
    public void startIOTick(RecipeCraftingContext context, float durationMultiplier) {
        flag = false;
    }

    @Nonnull
    @Override
    public CraftCheck resetIOTick(RecipeCraftingContext context) {
        if(!isPerTick || network.getCurrentEssence() >= essenceAmount|| flag == true) {
            return CraftCheck.success();
        }
        else {
            return CraftCheck.failure("error.modularmagic.requirement.lifeessence.lp");
        }

    }

    @Nonnull
    @Override
    public CraftCheck doIOTick(ProcessingComponent component, RecipeCraftingContext context) {
        if(isPerTick) {
            TileLifeEssenceProvider essenceProvider = (TileLifeEssenceProvider)component.getComponent().getContainerProvider();
            switch (getActionType()) {
                case INPUT:
                    if(essenceProvider.getSoulNetwork().getCurrentEssence() >= essenceAmount) {
                        essenceProvider.getSoulNetwork().syphon(new SoulTicket(essenceAmount));
                        flag = true;
                        return CraftCheck.success();
                    }
                    else {
                        flag = false;
                        return CraftCheck.failure("error.modularmagic.requirement.lifeessence.lp");
                    }
                case OUTPUT:
                    essenceProvider.getSoulNetwork().add(new SoulTicket(essenceAmount), essenceProvider.getOrbCapacity());
                    flag = true;
                    return CraftCheck.success();
            }
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
        return new JEIComponentLifeEssence(this);
    }
}
