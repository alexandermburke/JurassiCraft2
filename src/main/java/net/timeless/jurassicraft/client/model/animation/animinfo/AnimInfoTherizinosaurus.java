package net.timeless.jurassicraft.client.model.animation.animinfo;

import net.timeless.animationapi.client.AnimID;

/**
 * @author jabelar
 *
 */

public class AnimInfoTherizinosaurus extends AnimInfo
{
    // Tell the code where your tabula model assets are
    // the first one must be your "default" pose (i.e one that is used at spawn time)
    public final static String modelAssetPath = "/assets/jurassicraft/models/entities/therizinosaurus/";
    public final static String[] modelAssetFileNameArray = new String[] {
            "therizinosaurus_default",
            "therizinosaurus_head_cock_left",
            "therizinosaurus_head_cock_right",
            "therizinosaurus_hissing",
            "therizinosaurus_mating",
            "therizinosaurus_eating_1",
            "therizinosaurus_eating_1b",
            "therizinosaurus_eating_2",
            "therizinosaurus_eating_3",
            "therizinosaurus_eating_4",
            "therizinosaurus_eating_4b",
            "therizinosaurus_eating_5",
            "therizinosaurus_eating_6",
            "therizinosaurus_eating_6b",
            "therizinosaurus_drinking_1",
            "therizinosaurus_drinking_2",
            "therizinosaurus_drinking_3",
            "therizinosaurus_drinking_4",
            "therizinosaurus_resting",
            "therizinosaurus_sleeping",
            "therizinosaurus_calling_1",
            "therizinosaurus_calling_2",
            "therizinosaurus_calling_3",
            "therizinosaurus_calling_4",
            "therizinosaurus_attacking_1",
            "therizinosaurus_attacking_2",
            "therizinosaurus_attacking_3",
            "therizinosaurus_sniffing_1",
            "therizinosaurus_sniffing_2"
    };

    private static int getPoseID(String parPose)
    {
        int index = -1;
        for (int assetPathIndex = 0; assetPathIndex < modelAssetFileNameArray.length; assetPathIndex++)
        {
            if (modelAssetFileNameArray[assetPathIndex].contains(parPose.toLowerCase()))
            {
                index = assetPathIndex;
                break;
            }
        }
        return index;
    }
    
    /* 
     * Define your animation sequence here
     * First element is target pose model index (i.e. order of model assets listed in
     * modelAssetPaths array above),
     * Second element is the number of ticks it should take to tween to that pose
     */
    protected static int[][][] arrayOfSequences = new int[AnimID.NUM_IDS][][];
    static
    {
        arrayOfSequences[AnimID.IDLE] = new int[][] {
            {getPoseID("default"), 200}
        };
    
        arrayOfSequences[AnimID.LOOKING_LEFT] = new int[][] {
            {getPoseID("head_cock_left"), 100}, {getPoseID("head_cock_left"), 80}, {getPoseID("default"), 100}
        };
        
        arrayOfSequences[AnimID.LOOKING_RIGHT] = new int[][] {
            {getPoseID("head_cock_right"), 100}, {getPoseID("head_cock_right"), 80}, {getPoseID("default"), 100}
        };
        
        arrayOfSequences[AnimID.HISSING] = new int[][] {
            {getPoseID("hissing"), 100}, {getPoseID("hissing"), 80}, {getPoseID("default"), 100}
        };
        
        arrayOfSequences[AnimID.MATING] = new int[][] {
            {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("hissing"), 10},
            {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, 
            {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("hissing"), 10}, {getPoseID("mating"), 10}, {getPoseID("default"), 100}
        };
        
        arrayOfSequences[AnimID.EATING] = new int[][] {
            {getPoseID("eating_1"), 100}, {getPoseID("eating_1b"), 40}, {getPoseID("eating_1"), 40}, 
            {getPoseID("eating_1b"), 40}, {getPoseID("eating_1"), 40}, {getPoseID("eating_1b"), 40}, 
            {getPoseID("eating_1"), 40}, {getPoseID("eating_2"), 80}, {getPoseID("eating_3"), 80}, 
            {getPoseID("eating_4"), 100}, {getPoseID("eating_4b"), 40}, {getPoseID("eating_4"), 40}, 
            {getPoseID("eating_4b"), 40}, {getPoseID("eating_4"), 40}, {getPoseID("eating_4b"), 40}, 
            {getPoseID("eating_4"), 40}, {getPoseID("eating_4b"), 40}, {getPoseID("eating_3"), 80}, 
            {getPoseID("eating_5"), 160}, {getPoseID("eating_6"), 100}, {getPoseID("eating_6b"), 40}, 
            {getPoseID("eating_6"), 40}, {getPoseID("eating_6b"), 40}, {getPoseID("eating_6"), 40}, 
            {getPoseID("eating_6b"), 40}, {getPoseID("eating_6"), 40}, {getPoseID("eating_6b"), 40},
            {getPoseID("eating_5"), 80}, {getPoseID("default"), 80}, {getPoseID("default"), 200}
        };
        
        arrayOfSequences[AnimID.DRINKING]= new int[][] {
            {getPoseID("drinking_1"), 100}, {getPoseID("drinking_2"), 40}, {getPoseID("drinking_2"), 40}, 
            {getPoseID("drinking_3"), 20}, {getPoseID("drinking_4"), 40}, {getPoseID("drinking_4"), 100}, 
            {getPoseID("drinking_1"), 60}, {getPoseID("drinking_2"), 40}, {getPoseID("drinking_2"), 40}, 
            {getPoseID("drinking_3"), 20}, {getPoseID("drinking_4"), 40}, {getPoseID("drinking_4"), 100}, 
            {getPoseID("drinking_1"), 60}, {getPoseID("drinking_2"), 40}, {getPoseID("drinking_2"), 40}, 
            {getPoseID("drinking_3"), 20}, {getPoseID("drinking_4"), 40}, {getPoseID("drinking_4"), 100}, 
            {getPoseID("default"), 100}
        };
        
        arrayOfSequences[AnimID.RESTING]= new int[][] {
            {getPoseID("resting"), 100}, {getPoseID("resting"), 800}, {getPoseID("default"), 200}
        };
        
        arrayOfSequences[AnimID.SLEEPING] = new int[][] {
            {getPoseID("resting"), 100}, {getPoseID("sleeping"), 80}, {getPoseID("sleeping"), 800}, {getPoseID("resting"), 40}, {getPoseID("default"), 200}
        };
        
        arrayOfSequences[AnimID.CALLING] = new int[][] {
            {getPoseID("calling_1"), 100}, {getPoseID("calling_2"), 60}, {getPoseID("calling_2"), 40}, {getPoseID("calling_3"), 40}, 
            {23, 80}, {getPoseID("calling_2"), 40}, {getPoseID("default"), 200}  
        };
        
        arrayOfSequences[AnimID.ATTACKING] = new int[][] {
            {getPoseID("attacking_1"), 100}, {getPoseID("attacking_2"), 60}, {getPoseID("attacking_3"), 20}, 
            {getPoseID("attacking_2"), 60}, {getPoseID("attacking_3"), 20}, {getPoseID("default"), 200}
        };
        
        arrayOfSequences[AnimID.SNIFFING] = new int[][] {
            {getPoseID("sniffing_1"), 100}, {getPoseID("sniffing_2"), 20}, {getPoseID("sniffing_1"), 20}, 
            {getPoseID("sniffing_2"), 20}, {getPoseID("sniffing_1"), 20}, {getPoseID("default"), 200}
        };
    
    }


}
