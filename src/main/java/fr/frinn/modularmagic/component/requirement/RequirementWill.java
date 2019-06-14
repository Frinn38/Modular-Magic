package fr.frinn.modularmagic.component.requirement;

import WayofTime.bloodmagic.soul.EnumDemonWillType;
import com.google.common.collect.Lists;
import fr.frinn.modularmagic.jei.component.JEIComponentWill;
import fr.frinn.modularmagic.tile.machinecomponent.MachineComponentWillProvider;
import fr.frinn.modularmagic.tile.TileWillProvider;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;
import org.lwjgl.Sys;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementWill extends ComponentRequirement {

    public double willAmount;
    public EnumDemonWillType willType;

    public RequirementWill(MachineComponent.IOType actionType, double willRequired, EnumDemonWillType willType) {
        super(ComponentType.Registry.getComponent("will"), actionType);
        this.willAmount = willRequired;
        this.willType = willType;
    }

    @Override
    public boolean startCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(!canStartCrafting(component, context, Lists.newArrayList()).isSuccess())
            return false;

        if(getActionType() == MachineComponent.IOType.INPUT) {
            TileWillProvider willProvider = (TileWillProvider)component.getContainerProvider();
            willProvider.removeWill(willAmount, willType);
        }
        return true;
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        if(getActionType() == MachineComponent.IOType.OUTPUT) {
            TileWillProvider willProvider = (TileWillProvider)component.getContainerProvider();
            willProvider.addWill(willAmount, willType);
        }
        return true;
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext context, List restrictions) {
        if(!component.getComponentType().equals(this.getRequiredComponentType()) ||
                !(component instanceof MachineComponentWillProvider) ||
                component.getIOType() != getActionType()) return CraftCheck.skipComponent();

        TileWillProvider willProvider = (TileWillProvider)component.getContainerProvider();
        switch (getActionType()) {
            case INPUT:
                if(willProvider.getWill(willType) >= willAmount) {
                    return CraftCheck.success();
                }
                else {
                    return CraftCheck.failure("Not enough " + willType.name + " will");
                }

            case OUTPUT:
                return CraftCheck.success();
        }
        return CraftCheck.failure("Error while checking demon will requirement");
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
