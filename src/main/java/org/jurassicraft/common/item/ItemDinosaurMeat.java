package org.jurassicraft.common.item;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumChatFormatting;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.jurassicraft.common.creativetab.JCCreativeTabs;
import org.jurassicraft.common.dinosaur.Dinosaur;
import org.jurassicraft.common.entity.base.JCEntityRegistry;
import org.jurassicraft.common.genetics.GeneticsContainer;
import org.jurassicraft.common.genetics.GeneticsHelper;
import org.jurassicraft.common.lang.AdvLang;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ItemDinosaurMeat extends ItemFood
{
    public ItemDinosaurMeat()
    {
        super(3, 0.3F, true);
        this.setUnlocalizedName("dinosaur_meat");
        this.setHasSubtypes(true);

        this.setCreativeTab(JCCreativeTabs.foods);
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack)
    {
        Dinosaur dinosaur = this.getDinosaur(stack);

        return new AdvLang("item.dinosaur_meat.name").withProperty("dino", "entity." + dinosaur.getName().replace(" ", "_").toLowerCase() + ".name").build();
    }

    public Dinosaur getDinosaur(ItemStack stack)
    {
        Dinosaur dinosaur = JCEntityRegistry.getDinosaurById(stack.getItemDamage());

        if (dinosaur == null)
        {
            dinosaur = JCEntityRegistry.achillobator;
        }

        return dinosaur;
    }

    public int getContainerDinosaur(ItemStack stack)
    {
        return JCEntityRegistry.getDinosaurId(getDinosaur(stack));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubItems(Item item, CreativeTabs tab, List subtypes)
    {
        List<Dinosaur> dinosaurs = new ArrayList<Dinosaur>(JCEntityRegistry.getDinosaurs());

        Map<Dinosaur, Integer> ids = new HashMap<Dinosaur, Integer>();

        int id = 0;

        for (Dinosaur dino : dinosaurs)
        {
            ids.put(dino, id);

            id++;
        }

        Collections.sort(dinosaurs);

        for (Dinosaur dino : dinosaurs)
        {
            if (dino.shouldRegister())
            {
                subtypes.add(new ItemStack(item, 1, ids.get(dino)));
            }
        }
    }

    public int getDNAQuality(EntityPlayer player, ItemStack stack)
    {
        int quality = player.capabilities.isCreativeMode ? 100 : 0;

        NBTTagCompound nbt = stack.getTagCompound();

        if (nbt == null)
        {
            nbt = new NBTTagCompound();
        }

        if (nbt.hasKey("DNAQuality"))
        {
            quality = nbt.getInteger("DNAQuality");
        }
        else
        {
            nbt.setInteger("DNAQuality", quality);
        }

        stack.setTagCompound(nbt);

        return quality;
    }

    public GeneticsContainer getGeneticCode(EntityPlayer player, ItemStack stack)
    {
        int quality = getDNAQuality(player, stack);

        NBTTagCompound nbt = stack.getTagCompound();

        GeneticsContainer genetics = GeneticsHelper.randomGenetics(player.worldObj.rand, getContainerDinosaur(stack), quality);

        if (nbt == null)
        {
            nbt = new NBTTagCompound();
        }

        if (nbt.hasKey("Genetics"))
        {
            genetics = new GeneticsContainer(nbt.getString("Genetics"));
        }
        else
        {
            nbt.setString("Genetics", genetics.toString());
        }

        stack.setTagCompound(nbt);

        return genetics;
    }

    public void addInformation(ItemStack stack, EntityPlayer player, List lore, boolean advanced)
    {
        int quality = getDNAQuality(player, stack);

        EnumChatFormatting colour;

        if (quality > 75)
        {
            colour = EnumChatFormatting.GREEN;
        }
        else if (quality > 50)
        {
            colour = EnumChatFormatting.YELLOW;
        }
        else if (quality > 25)
        {
            colour = EnumChatFormatting.GOLD;
        }
        else
        {
            colour = EnumChatFormatting.RED;
        }

        lore.add(colour + new AdvLang("lore.dna_quality.name").withProperty("quality", quality + "").build());
        lore.add(EnumChatFormatting.BLUE + new AdvLang("lore.genetic_code.name").withProperty("code", getGeneticCode(player, stack).toString()).build());
    }
}
