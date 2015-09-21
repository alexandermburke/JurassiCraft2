package net.timeless.animationapi;

import net.timeless.animationapi.client.AnimID;

public interface IAnimatedEntity
{
    void setAnimID(AnimID id);

    void setAnimTick(int tick);

    AnimID getAnimID();

    int getAnimTick();
}
