package fr.frinn.modularmagic.common.container;

import WayofTime.bloodmagic.orb.IBloodOrb;
import fr.frinn.modularmagic.common.tile.TileLifeEssenceProvider;
import hellfirepvp.modularmachinery.common.container.ContainerBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.items.SlotItemHandler;

import javax.annotation.Nonnull;

public class ContainerLifeEssence extends ContainerBase<TileLifeEssenceProvider> {

    public ContainerLifeEssence(TileLifeEssenceProvider owner, EntityPlayer opening) {
        super(owner, opening);

        SlotItemHandler orbSlot = new SlotItemHandler(owner.getInventory().asGUIAccess(), 0, 81, 30) {
            @Override
            public boolean isItemValid(@Nonnull ItemStack stack) {
                return stack.getItem() instanceof IBloodOrb ? true : false;
            }
        };
        addSlotToContainer(orbSlot);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            boolean changed = false;
            if (index >= 0 && index < 36) {
                if(this.mergeItemStack(itemstack1, 36, inventorySlots.size(), false)) {
                    changed = true;
                }
            }

            if(!changed) {
                if (index >= 0 && index < 27) {
                    if (!this.mergeItemStack(itemstack1, 27, 36, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 27 && index < 36) {
                    if (!this.mergeItemStack(itemstack1, 0, 27, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.mergeItemStack(itemstack1, 0, 36, false)) {
                    return ItemStack.EMPTY;
                }
            }
            if (itemstack1.getCount() == 0) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }
            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTake(playerIn, itemstack1);
        }
        return itemstack;
    }
}
