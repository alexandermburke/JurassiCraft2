package net.reuxertz.ecocraft.item.tools;

import java.util.Set;

import com.google.common.collect.Sets;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemTool;

public class IronKnife extends ItemTool 
{ 
	private static final Set field_150916_c = Sets.newHashSet(new Block[] {});// {Blocks.grass, Blocks.dirt, Blocks.sand, Blocks.gravel, 
			//Blocks.snow_layer, Blocks.snow, Blocks.clay, Blocks.farmland, Blocks.soul_sand, Blocks.mycelium});

	public IronKnife() 
	{
		super(3f, ToolMaterial.IRON, field_150916_c);
	}

}
