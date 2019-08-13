package fr.frinn.modularmagic.common.crafting.requirement;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.common.crafting.requirement.types.ModularMagicRequirements;
import fr.frinn.modularmagic.common.crafting.requirement.types.RequirementTypeConstellation;
import fr.frinn.modularmagic.common.integration.jei.component.JEIComponentConstellation;
import fr.frinn.modularmagic.common.integration.jei.ingredient.Constellation;
import fr.frinn.modularmagic.common.tile.TileConstellationProvider;
import hellfirepvp.astralsorcery.common.constellation.IConstellation;
import hellfirepvp.modularmachinery.common.crafting.helper.*;
import hellfirepvp.modularmachinery.common.lib.RegistriesMM;
import hellfirepvp.modularmachinery.common.machine.IOType;
import hellfirepvp.modularmachinery.common.util.ResultChance;

import javax.annotation.Nonnull;
import java.util.List;

public class RequirementConstellation extends ComponentRequirement<Constellation, RequirementTypeConstellation> {

    public IConstellation constellation;

    public RequirementConstellation(IOType actionType, IConstellation constellation) {
        super((RequirementTypeConstellation) RegistriesMM.REQUIREMENT_TYPE_REGISTRY.getValue(ModularMagicRequirements.KEY_REQUIREMENT_CONSTELLATION), actionType);
        this.constellation = constellation;
    }

    @Override
    public boolean isValidComponent(ProcessingComponent<?> component, RecipeCraftingContext ctx) {
        return component.getComponent().getContainerProvider() instanceof TileConstellationProvider;
    }

    @Override
    public boolean startCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        return canStartCrafting(component, context, Lists.newArrayList()).isSuccess();
    }

    @Override
    public CraftCheck finishCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, ResultChance chance) {
        return CraftCheck.success();
    }

    @Nonnull
    @Override
    public CraftCheck canStartCrafting(ProcessingComponent<?> component, RecipeCraftingContext context, List<ComponentOutputRestrictor> restrictions) {
        if(getActionType() == IOType.OUTPUT)
            return CraftCheck.failure("error.modularmagic.requirement.invalid");

        if(component.getComponent().getContainerProvider() == null || !(component.getComponent().getContainerProvider() instanceof TileConstellationProvider))
            return CraftCheck.failure("error.modularmagic.requirement.constellation.missingprovider");

        TileConstellationProvider provider = (TileConstellationProvider) component.getComponent().getContainerProvider();
        if(provider.isConstellationInSky(constellation))
            return CraftCheck.success();
        else
            return CraftCheck.failure("error.modularmagic.requirement.constellation.less");
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
        return new JEIComponentConstellation(this);
    }
}
