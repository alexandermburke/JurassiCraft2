package org.jurassicraft.common.storagedisc;

import com.google.common.base.Supplier;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StorageTypeRegistry
{
    private static final Map<String, Supplier<? extends IStorageType>> storageTypes = new HashMap<>();

    public void register()
    {
        register("DinoDNA", new Supplier<IStorageType>()
        {
            @Override
            public StorageTypeDinosaurDNA get()
            {
                return new StorageTypeDinosaurDNA();
            }
        });
        register("PlantDNA", new Supplier<IStorageType>()
        {

            @Override
            public StorageTypePlantDNA get()
            {
                return new StorageTypePlantDNA();
            }
        });
    }

    private void register(String id, Supplier<? extends IStorageType> storageType)
    {
        storageTypes.put(id, Objects.requireNonNull(storageType));
    }

    public static IStorageType getStorageType(String id)
    {
        if (id == null || id.isEmpty())
        {
            id = "DinoDNA";
        }

        return storageTypes.get(id).get();
    }
}
