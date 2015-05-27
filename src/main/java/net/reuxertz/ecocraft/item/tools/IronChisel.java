package net.reuxertz.ecocraft.item.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemTool;

public class IronChisel extends ItemTool 
{ 
	private static final Set field_150916_c = Sets.newHashSet(new Block[] {Blocks.cobblestone, Blocks.double_stone_slab, 
			Blocks.stone_slab, Blocks.stone, Blocks.sandstone, Blocks.mossy_cobblestone, 
			Blocks.iron_ore, Blocks.iron_block, Blocks.coal_ore, Blocks.gold_block, Blocks.gold_ore, 
			Blocks.diamond_ore, Blocks.diamond_block, Blocks.ice, Blocks.netherrack, Blocks.lapis_ore, 
			Blocks.lapis_block, Blocks.redstone_ore, Blocks.lit_redstone_ore });

	public IronChisel() 
	{
		super(1f, ToolMaterial.IRON, field_150916_c);
	}

}
