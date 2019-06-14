package fr.frinn.modularmagic.component.requirement;

import WayofTime.bloodmagic.core.data.SoulNetwork;
import WayofTime.bloodmagic.core.data.SoulTicket;
import com.google.common.collect.Lists;
import fr.frinn.modularmagic.jei.component.JEIComponentLifeEssence;
import fr.frinn.modularmagic.tile.TileLifeEssenceProvider;
import fr.frinn.modularmagic.tile.machinecomponent.MachineComponentLifeEssenceProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementLifeEssence extends ComponentRequirement.PerTick {

    public int essenceAmount;
    public boolean isPerTick;
    SoulNetwork network;
    private boolean flag = false;

    public RequirementLifeEssence(MachineComponent.IOType actionType, int essenceAmount, boolean perTick) {
        super(ComponentType.Registry.getComponent("lifeessence"), actionType);

        this.essenceAmount = essenceAmount;
        this.isPerTick = perTick;
    }

    @Override
    public boolean startCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(!canStartCrafting(component, context, Lists.newArrayList()).isSuccess())
            return false;

        TileLifeEssenceProvider essenceProvider = (TileLifeEssenceProvider)component.getContainerProvider();
        this.network = essenceProvider.getSoulNetwork();

        if(getActionType() == MachineComponent.IOType.INPUT && !isPerTick) {
            essenceProvider.getSoulNetwork().syphon(new SoulTicket(essenceAmount));
        }
        return true;
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(getActionType() == MachineComponent.IOType.OUTPUT && !isPerTick) {
            TileLifeEssenceProvider essenceProvider = (TileLifeEssenceProvider)component.getContainerProvider();
            essenceProvider.getSoulNetwork().add(new SoulTicket(essenceAmount), essenceProvider.getOrbCapacity());
        }
        return true;
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext context, List restrictions) {
        if(!component.getComponentType().equals(this.getRequiredComponentType()) ||
                !(component instanceof MachineComponentLifeEssenceProvider) ||
                component.getIOType() != getActionType()) return CraftCheck.skipComponent();

        TileLifeEssenceProvider essenceProvider = (TileLifeEssenceProvider)component.getContainerProvider();

        if(essenceProvider.getSoulNetwork() == null)
            return CraftCheck.failure("Missing or Unbound Blood Orb");

        switch (getActionType()) {
            case INPUT:
                if(essenceProvider.getSoulNetwork().getCurrentEssence() >= essenceAmount) {
                    return CraftCheck.success();
                }
                else {
                    return CraftCheck.failure("Not enough LP");
                }

            case OUTPUT:
                return CraftCheck.success();
        }
        return CraftCheck.failure("Error while checking LP requirement");
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
            return CraftCheck.failure("Not Enough LP");
        }

    }

    @Nonnull
    @Override
    public CraftCheck doIOTick(MachineComponent component, RecipeCraftingContext context) {
        if(isPerTick) {
            TileLifeEssenceProvider essenceProvider = (TileLifeEssenceProvider)component.getContainerProvider();
            switch (getActionType()) {
                case INPUT:
                    if(essenceProvider.getSoulNetwork().getCurrentEssence() >= essenceAmount) {
                        essenceProvider.getSoulNetwork().syphon(new SoulTicket(essenceAmount));
                        flag = true;
                        return CraftCheck.success();
                    }
                    else {
                        flag = false;
                        return CraftCheck.failure("Not Enough LP");
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
