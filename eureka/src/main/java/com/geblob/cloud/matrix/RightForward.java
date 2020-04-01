package com.geblob.cloud.matrix;

import org.javatuples.Pair;

public class RightForward extends DirectionForward {
    public RightForward(Integer[][] matrix, Pair<Integer, Integer> lengthHeightToCompute, Pair<Integer, Integer> position) {
        this.matrix = matrix;
        this.lengthHeightToCompute = lengthHeightToCompute;
        this.position = position;
    }

    @Override
    protected void forward() {
        for (int i = lengthHeightToCompute.getValue0(); i > 0; i--) {
            System.out.println(matrix[position.getValue1()][position.getValue0()]);
            position = Pair.with(position.getValue0() + 1, position.getValue1());
        }
        if (!isTerminated()) {
            position = Pair.with(position.getValue0() - 1, position.getValue1() + 1);
            new DownForward(matrix, Pair.with(lengthHeightToCompute.getValue0(), lengthHeightToCompute.getValue1() - 1), position).forward();
        }
    }
}
