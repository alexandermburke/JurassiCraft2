package org.jurassicraft.common.entity.ai.util;

import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Iterator;

/**
 * Copyright 2016 Timeless Mod Team.
 * <p/>
 * This traverser iterates "layers" (like an onion) around the center point.  Being Minecraft,
 * the layers are boxes around the center point.
 */
public class OnionTraverser implements Iterable<BlockPos>
{
    public OnionTraverser(BlockPos center, int radius)
    {
        _center = center;
        _maxRadius = radius;
    }

    @Override
    public Iterator<BlockPos> iterator()
    {
        return new OnionIterator();
    }

    public class OnionIterator implements Iterator<BlockPos>
    {
        public OnionIterator()
        {
            _maxCounter = _maxRadius * 2 + 1;
            _currentRadius = 1;
            _y = _center.getY();
            initRing();
        }

        @Override
        public boolean hasNext()
        {
            return _currentRadius <= _maxRadius;
        }

        @Override
        public BlockPos next()
        {
            if (!_returnedCenter)
            {
                _returnedCenter = true;
                return _center;
            }

            // Construct the current one
            BlockPos retVal = new BlockPos(_x, _y, _z);

            if (_y == _center.getY() + _maxRadius || _y == _center.getY() - _maxRadius)
            {
                walkSlab();
            }
            else
            {
                walkPerimeter();
            }

            //BlockPos nextVal = new BlockPos(_x, _y, _z);
            //LOGGER.info(_idx++ + ", facing=" + _facing + ", yCounter=" + _yCounter + "/" + _maxCounter + ", radius=" + _currentRadius + ", pos=" + retVal + ", nextVal=" + nextVal);

            return retVal;
        }

        @Override
        public void remove()
        {
            // We do nothing
        }

        // Walk around the outside perimeter
        private void walkPerimeter()
        {
            switch (_facing)
            {
                case EAST:
                    if (_x == _maxX)
                    {
                        ++_z;
                        _facing = EnumFacing.SOUTH;
                    }
                    else
                    {
                        ++_x;
                    }
                    break;

                case SOUTH:
                    if (_z == _maxZ)
                    {
                        --_x;
                        _facing = EnumFacing.WEST;
                    }
                    else
                    {
                        ++_z;
                    }
                    break;

                case WEST:
                    if (_x == _minX)
                    {
                        --_z;
                        _facing = EnumFacing.NORTH;
                    }
                    else
                    {
                        --_x;
                    }
                    break;

                case NORTH:
                    --_z;
                    if (_z == _minZ)
                    {
                        nextLayer();
                    }
                    break;
            }
        }

        // We go back an forth (east, west) across the slab until we get past maxZ
        // then go to the next layer.
        private void walkSlab()
        {
            switch (_facing)
            {
                case EAST:
                    if (_x == _maxX)
                    {
                        ++_z;
                        _facing = EnumFacing.WEST;
                    }
                    else
                    {
                        ++_x;
                    }
                    break;

                case WEST:
                    if (_x == _minX)
                    {
                        ++_z;
                        _facing = EnumFacing.EAST;
                    }
                    else
                    {
                        --_x;
                    }
                    break;
            }

            if (_z > _maxZ)
            {
                nextLayer();
            }
        }

        // Computes the next layer
        private void nextLayer()
        {
            // We are at the end.  We don't actually want thw return the end because
            // it has already been done as the start point.

            // Move up or down
            // 0,0,1,1,2,2,3,3,4,4,
            ++_yCounter;

            if (_yCounter > _maxCounter)
            {
                ++_currentRadius;
                initRing();
            }

            _x = _minX;
            _z = _minZ;
            if ((_yCounter & 0x1) == 1)
            {
                _y = _center.getY() + _yCounter / 2;
            }
            else
            {
                _y = _center.getY() - _yCounter / 2;
            }
            _facing = EnumFacing.EAST;

            //LOGGER.info("Layer. radius=" + _currentRadius + ", x=" + _x + ", y=" + _y + ", z=" + _z + ", facing=" + _facing);
        }

        // Compute the ring values, min, max, etc.
        private void initRing()
        {
            _yCounter = 1;
            _x = _minX = _center.getX() - _currentRadius;
            _z = _minZ = _center.getZ() - _currentRadius;
            _y = _center.getY();
            _maxX = _center.getX() + _currentRadius;
            _maxZ = _center.getZ() + _currentRadius;
            _facing = EnumFacing.EAST;

            //LOGGER.info("Ring. radius=" + _currentRadius + ", x=" + _minX + "-" + _maxX + ", z=" + _minZ + "-" + _maxZ );
        }

        // The max numbers are inclusive so <= rather than <
        private int _x, _minX, _maxX;
        private int _y;
        private int _z, _minZ, _maxZ;
        private int _currentRadius = 0;
        private int _yCounter = 1;

        private int _idx = 0;

        private final int _maxCounter;
        private EnumFacing _facing;
        private boolean _returnedCenter = false;
    }

    private final BlockPos _center;
    private final int _maxRadius;

    private static final Logger LOGGER = LogManager.getLogger();
}

