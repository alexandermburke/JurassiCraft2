package net.timeless.unilib.common;

import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.Logger;

public abstract class BaseMod {

    protected Logger logger;
    private CommonProxy proxy;

    public void preInitMod(FMLPreInitializationEvent evt) {
        logger = evt.getModLog();
        if(proxy != null) {
            proxy.preInit();
        }
    }

    public void initMod(FMLInitializationEvent evt) {
        if(proxy != null) {
            proxy.init();
        }
    }

    public void postInitMod(FMLPostInitializationEvent evt) {
        if(proxy != null) {
            proxy.postInit();
        }
    }

    public Logger getLogger() {
        return logger;
    }

    public void setProxy(CommonProxy proxy) {
        this.proxy = proxy;
    }

    public CommonProxy getProxy() {
        return proxy;
    }
}
