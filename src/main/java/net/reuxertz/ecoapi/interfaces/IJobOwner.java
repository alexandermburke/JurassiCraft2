package net.reuxertz.ecoapi.interfaces;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

import javax.vecmath.Point3i;
import java.util.List;

public interface IJobOwner
{

    Point3i GetLocation();

    List<Point3i> GetWorkSites();

    IInventory GetRepository();

    boolean IsBehaviorTime();

    void intakeWorkerProduct(List<ItemStack> drops);
}
