package com.geblob.cloud.matrix;

import org.javatuples.Pair;

public class UpForward extends DirectionForward {
    public UpForward(Integer[][] matrix, Pair<Integer, Integer> lengthHeightToCompute, Pair<Integer, Integer> position) {
        this.matrix = matrix;
        this.lengthHeightToCompute = lengthHeightToCompute;
        this.position = position;
    }

    @Override
    protected void forward() {
        for (int i = lengthHeightToCompute.getValue1(); i > 0; i--) {
            System.out.println(matrix[position.getValue1()][position.getValue0()]);
            position = Pair.with(position.getValue0(), position.getValue1() - 1);
        }
        if (!isTerminated()) {
            position = Pair.with(position.getValue0() + 1, position.getValue1() + 1);
            new RightForward(matrix, Pair.with(lengthHeightToCompute.getValue0()-1,lengthHeightToCompute.getValue1()), position).forward();
        }
    }
}
