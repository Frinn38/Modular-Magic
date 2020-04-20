package fr.frinn.modularmagic.common.integration;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.common.integration.jei.helper.*;
import fr.frinn.modularmagic.common.integration.jei.ingredient.*;
import fr.frinn.modularmagic.common.integration.jei.render.*;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;
import thaumcraft.api.aspects.AspectList;

@JEIPlugin
public class JeiPlugin implements IModPlugin {

    public static IGuiHelper GUI_HELPER;

    @Override
    public void register(IModRegistry registry) {
        GUI_HELPER = registry.getJeiHelpers().getGuiHelper();
    }

    @Override
    public void registerIngredients(IModIngredientRegistration registry) {
        if(ModularMagic.bloodmagicLoaded) {
            registry.register(DemonWill.class, Lists.newArrayList(), new DemonWillHelper<>(), new DemonWillRenderer());
            registry.register(LifeEssence.class, Lists.newArrayList(), new LifeEssenceHelper<>(), new LifeEssenceRenderer());
        }
        if(ModularMagic.thaumcraftLoaded && !ModularMagic.thaumicJEILoaded) {
            registry.register(AspectList.class, Lists.newArrayList(), new AspectHelper<>(), new AspectRenderer());
        }
        if(ModularMagic.extraUtils2Loaded) {
            registry.register(Grid.class, Lists.newArrayList(), new GridHelper<>(), new GridRenderer());
            registry.register(Rainbow.class, Lists.newArrayList(), new RainbowHelper<>(), new RainbowRenderer());
        }
        if(ModularMagic.astralLoaded) {
            registry.register(Starlight.class, Lists.newArrayList(), new StarlightHelper<>(), new StarlightRenderer());
            registry.register(Constellation.class, Lists.newArrayList(), new ConstellationHelper<>(), new ConstellationRenderer());
        }
        if(ModularMagic.naturesauraLoaded) {
            registry.register(Aura.class, Lists.newArrayList(), new AuraHelper<>(), new AuraRenderer());
        }
    }
}
