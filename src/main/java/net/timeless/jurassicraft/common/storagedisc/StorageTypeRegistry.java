package net.timeless.jurassicraft.common.storagedisc;

import java.util.HashMap;
import java.util.Map;

public class StorageTypeRegistry
{
    private static Map<String, Class<? extends IStorageType>> storageTypes = new HashMap<String, Class<? extends IStorageType>>();

    public void register()
    {
        register("DinoDNA", StorageTypeDinosaurDNA.class);
        register("PlantDNA", StorageTypePlantDNA.class);
    }

    private void register(String id, Class<? extends IStorageType> storageType)
    {
        storageTypes.put(id, storageType);
    }

    public static IStorageType getStorageType(String id)
    {
        try
        {
            Class<? extends IStorageType> storageType = storageTypes.get(id);

            if(storageType != null)
            {
                return storageType.newInstance();
            }
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
