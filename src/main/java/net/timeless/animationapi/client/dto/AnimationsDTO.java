package net.timeless.animationapi.client.dto;

import com.google.gson.Gson;
import net.timeless.animationapi.client.Animations;

import java.util.Map;

/**
 * This class can be loaded via {@link Gson#fromJson}. It represents the poses of the animations of a model.
 *
 * @author WorldSEnder
 */
public class AnimationsDTO
{
    /**
     * Maps an {@link Animations} as a string to the list of sequential poses
     */
    public Map<String, PoseDTO[]> poses;
    public int version;
}
