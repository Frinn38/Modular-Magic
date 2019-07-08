package fr.frinn.modularmagic.common.integration.jei.render;

import fr.frinn.modularmagic.common.integration.jei.ingredient.Starlight;
import hellfirepvp.astralsorcery.common.lib.ItemsAS;
import mezz.jei.api.ingredients.IIngredientRenderer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.item.ItemStack;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class StarlightRenderer implements IIngredientRenderer<Starlight> {

    @Override
    public void render(Minecraft minecraft, int xPosition, int yPosition, @Nullable Starlight ingredient) {
        ItemStack stack = new ItemStack(ItemsAS.shiftingStar, 1);
        minecraft.getRenderItem().renderItemIntoGUI(stack, xPosition, yPosition);
    }

    @Override
    public List<String> getTooltip(Minecraft minecraft, Starlight ingredient, ITooltipFlag tooltipFlag) {
        List<String> tooltip = new ArrayList<String>();
        tooltip.add("Starlight : " + (int)ingredient.getAmount());
        return tooltip;
    }

    @Override
    public FontRenderer getFontRenderer(Minecraft minecraft, Starlight ingredient) {
        return minecraft.fontRenderer;
    }
}
