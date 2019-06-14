package fr.frinn.modularmagic.integration;

import com.google.common.collect.Lists;
import fr.frinn.modularmagic.ModularMagic;
import fr.frinn.modularmagic.jei.helper.DemonWillHelper;
import fr.frinn.modularmagic.jei.helper.LifeEssenceHelper;
import fr.frinn.modularmagic.jei.ingredient.DemonWill;
import fr.frinn.modularmagic.jei.ingredient.LifeEssence;
import fr.frinn.modularmagic.jei.render.DemonWillRenderer;
import fr.frinn.modularmagic.jei.render.LifeEssenceRenderer;
import mezz.jei.api.IGuiHelper;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import mezz.jei.api.ingredients.IModIngredientRegistration;

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
            registry.register(DemonWill.class, Lists.newArrayList(), new DemonWillHelper<DemonWill>(), new DemonWillRenderer());
            registry.register(LifeEssence.class, Lists.newArrayList(), new LifeEssenceHelper<LifeEssence>(), new LifeEssenceRenderer());
        }
    }
}
