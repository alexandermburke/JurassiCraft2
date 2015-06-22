package net.timeless.jurassicraft.api.animation;

import java.util.List;

import com.google.common.collect.Lists;

public class AnimationTree
{

    private final AnimNode root;

    public AnimationTree()
    {
        root = new AnimNode(null, null);
    }

    /**
     * Adds a leaf to given parent
     * 
     * @param parent
     * @param leaf
     */
    public void leaf(AnimationPhase parent, AnimationPhase leaf)
    {
        if (parent == null)
        {
            root.value = leaf;
        }
        else
        {
            AnimNode node = findNode(parent);
            checkNotNull(node, parent);
            node.addChild(leaf);
        }
    }

    private void checkNotNull(AnimNode node, AnimationPhase parent)
    {
        if (node == null)
        {
            throw new NullPointerException("No node found for " + parent);
        }
    }

    /**
     * Gets the parent node of given value
     * 
     * @param phase
     * @return
     */
    public AnimNode parent(AnimationPhase phase)
    {
        AnimNode node = findNode(phase);
        checkNotNull(node, phase);
        return node.parent;
    }

    /**
     * Gets the parent value of given argument
     * 
     * @param phase
     * @return
     */
    public AnimationPhase parentValue(AnimationPhase phase)
    {
        AnimNode parent = parent(phase);
        if (parent != null)
            return parent.value;
        return null;
    }

    public AnimNode findNode(AnimationPhase value)
    {
        return findNode(root, value);
    }

    public AnimNode findNode(AnimNode from, AnimationPhase value)
    {
        if (from.value == value)
        {
            return from;
        }
        List<AnimNode> children = from.children;
        for (AnimNode n : children)
        {
            if (n.value == value)
            {
                return n;
            }
            AnimNode found = findNode(n, value);
            if (found != null)
            {
                return found;
            }
        }
        return null;
    }

    public void clear()
    {
        root.value = null;
        root.children.clear();
    }

    public AnimNode getRoot()
    {
        return root;
    }

    public class AnimNode
    {
        AnimationPhase value;
        AnimNode parent;
        List<AnimNode> children;

        public AnimNode(AnimationPhase value, AnimNode parent)
        {
            this.value = value;
            this.parent = parent;
            children = Lists.newArrayList();
        }

        public void addChild(AnimationPhase v)
        {
            children.add(new AnimNode(v, this));
        }

        public void addChild(AnimNode node)
        {
            children.add(node);
        }
    }
}
