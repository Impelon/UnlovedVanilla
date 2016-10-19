package de.impelon.unlovedvanilla.items;

import java.util.List;

import de.impelon.unlovedvanilla.UnlovedVanillaMain;
import net.minecraft.block.BlockChest;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityLockable;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.LockCode;
import net.minecraft.world.World;

public class ItemLock extends Item {
	
	public ItemLock() {
		super();
		
		this.setMaxStackSize(16);
		this.setUnlocalizedName("vanillalock");
	}
	
	@Override
	public EnumActionResult onItemUseFirst(ItemStack stack, EntityPlayer player, World world, BlockPos pos,
			EnumFacing side, float hitX, float hitY, float hitZ, EnumHand hand) {
		if (!world.isRemote) {
			if (stack.hasDisplayName()) {
				if (world.getTileEntity(pos) instanceof TileEntityLockable && !((TileEntityLockable) world.getTileEntity(pos)).isLocked()) {
					((TileEntityLockable) world.getTileEntity(pos)).setLockCode(new LockCode(stack.getDisplayName()));
					player.addChatMessage(new TextComponentString(UnlovedVanillaMain.PREFIX + 
							new TextComponentTranslation("msg.successlocked.txt").getFormattedText()));
					if (!player.capabilities.isCreativeMode)
			            --stack.stackSize;
					return EnumActionResult.SUCCESS;
				} else 
					player.addChatMessage(new TextComponentString(UnlovedVanillaMain.PREFIX + 
							new TextComponentTranslation("msg.notlockable.txt").getFormattedText()));
			} else
				player.addChatMessage(new TextComponentString(UnlovedVanillaMain.PREFIX + 
						new TextComponentTranslation("msg.renametouse.txt").getFormattedText()));
		}
		return EnumActionResult.PASS;
	}
	
	@Override
	public void addInformation(ItemStack stack, EntityPlayer playerIn, List<String> tooltip, boolean advanced) {
		tooltip.add(new TextComponentTranslation("msg.itemlocktooltip.txt")
				.setStyle(new Style().setColor(TextFormatting.GOLD)).getFormattedText());
	}

}
