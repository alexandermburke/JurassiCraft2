package org.jurassicraft.common.storagedisc;

import com.google.common.base.Supplier;
import net.ilexiconn.llibrary.common.content.IContentHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class StorageTypeRegistry implements IContentHandler
{
    private static Map<String, Supplier<? extends IStorageType>> storageTypes = new HashMap<String, Supplier<? extends IStorageType>>();

    @Override
    public void init()
    {

    }

    @Override
    public void gameRegistry() throws Exception
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
