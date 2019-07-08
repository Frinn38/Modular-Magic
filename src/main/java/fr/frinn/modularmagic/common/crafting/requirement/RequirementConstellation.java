package fr.frinn.modularmagic.common.crafting.requirement;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.integration.jei.component.JEIComponentConstellation;
import fr.frinn.modularmagic.common.tile.TileConstellationProvider;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.modularmachinery.common.crafting.ComponentType;
import hellfirepvp.modularmachinery.common.crafting.helper.ComponentRequirement;
import hellfirepvp.modularmachinery.common.crafting.helper.CraftCheck;
import hellfirepvp.modularmachinery.common.crafting.helper.RecipeCraftingContext;
import hellfirepvp.modularmachinery.common.machine.MachineComponent;
import hellfirepvp.modularmachinery.common.util.ResultChance;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementConstellation extends ComponentRequirement {

    public IConstellation constellation;

    public RequirementConstellation(MachineComponent.IOType actionType, IConstellation constellation) {
        super(ComponentType.Registry.getComponent("constellation"), actionType);
        this.constellation = constellation;
    }

    @Override
    public boolean startCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        return canStartCrafting(component, context, Lists.newArrayList()).isSuccess();
    }

    @Override
    public boolean finishCrafting(MachineComponent component, RecipeCraftingContext context, ResultChance chance) {
        return true;
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(MachineComponent component, RecipeCraftingContext context, List restrictions) {
        if(getActionType() == MachineComponent.IOType.OUTPUT)
            return CraftCheck.failure("error.modularmagic.requirement.invalid");

        if(component.getContainerProvider() == null || !(component.getContainerProvider() instanceof TileConstellationProvider))
            return CraftCheck.failure("error.modularmagic.requirement.constellation.missingprovider");

        TileConstellationProvider provider = (TileConstellationProvider) component.getContainerProvider();
        if(provider.isConstellationInSky(constellation))
            return CraftCheck.success();
        else
            return CraftCheck.failure("error.modularmagic.requirement.constellation.less");
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
        return new JEIComponentConstellation(this);
    }
}
