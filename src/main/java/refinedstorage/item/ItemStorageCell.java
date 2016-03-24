package refinedstorage.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import refinedstorage.storage.CellStorage;
import refinedstorage.storage.NBTStorage;

import java.util.List;

// @TODO: Rename to storage disk
public class ItemStorageCell extends ItemBase {
    public static final int TYPE_1K = 0;
    public static final int TYPE_4K = 1;
    public static final int TYPE_16K = 2;
    public static final int TYPE_64K = 3;
    public static final int TYPE_CREATIVE = 4;

    public ItemStorageCell() {
        super("storage_cell");

        setMaxStackSize(1);
        setHasSubtypes(true);
        setMaxDamage(0);
    }

    @Override
    public void getSubItems(Item item, CreativeTabs tab, List list) {
        for (int i = 0; i < 5; ++i) {
            list.add(NBTStorage.initNBT(new ItemStack(item, 1, i)));
        }
    }

    @Override
    public void addInformation(ItemStack cell, EntityPlayer player, List list, boolean b) {
        if (CellStorage.getCapacity(cell) == -1) {
            list.add(String.format(I18n.translateToLocal("misc.refinedstorage:storage.stored"), NBTStorage.getStored(cell.getTagCompound())));
        } else {
            list.add(String.format(I18n.translateToLocal("misc.refinedstorage:storage.stored_capacity"), NBTStorage.getStored(cell.getTagCompound()), CellStorage.getCapacity(cell)));
        }
    }

    @Override
    public void onCreated(ItemStack stack, World world, EntityPlayer player) {
        super.onCreated(stack, world, player);

        NBTStorage.initNBT(stack);
    }
}
