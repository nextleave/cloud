package com.geblob.cloud.matrix;


import org.javatuples.Pair;

public abstract class DirectionForward {
    protected Integer[][] matrix;
    protected Pair<Integer, Integer> lengthHeightToCompute;
    protected Pair<Integer, Integer> position;

    protected boolean isTerminated() {
        Integer zero = new Integer(0);
        return zero.equals(lengthHeightToCompute.getValue0()) || zero.equals(lengthHeightToCompute.getValue1());
    }

    protected abstract void forward();
}
