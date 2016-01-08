package net.ilexiconn.bookwiki.api;

import com.google.common.collect.Lists;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * @author iLexiconn
 */
@SideOnly(Side.CLIENT)
public class BookWikiAPI {
    private static List<IComponent> componentList = Lists.newArrayList();

    public static void registerComponent(IComponent component) {
        for (IComponent c : componentList) {
            if (c.getID() == component.getID()) {
                throw new RuntimeException("Already a component with ID " + c.getID() + " registered! (" + c.toString() + " and " + component.toString() + ")");
            }
        }
        componentList.add(component);
    }

    public static IComponent[] getComponents() {
        return componentList.toArray(new IComponent[componentList.size()]);
    }
}
