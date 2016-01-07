package net.ilexiconn.bookwiki.api;

import com.google.common.collect.Lists;

import java.util.List;

public class BookWikiAPI {
    private static List<IComponent> componentList = Lists.newArrayList();

    public static void registerComponent(IComponent component) {
        componentList.add(component);
    }

    public static IComponent[] getComponents() {
        return componentList.toArray(new IComponent[componentList.size()]);
    }
}
