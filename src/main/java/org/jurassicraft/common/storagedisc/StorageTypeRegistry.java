package org.jurassicraft.common.storagedisc;

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
            if (id == null || id.length() == 0)
            {
                id = "DinoDNA";
            }

            return storageTypes.get(id).newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }
}
