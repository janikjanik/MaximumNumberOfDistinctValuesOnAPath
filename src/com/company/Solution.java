package com.company;

import java.util.*;

class Interval implements Comparable{
    private int A;
    private int B;

    public Interval(int A, int B) {
        this.A = A;
        this.B = B;
    }

    public int getA() {
        return A;
    }

    public int getB() {
        return B;
    }

    @Override
    public int compareTo(Object o) {
        Interval interval = (Interval)o;
            if(A < interval.getA())
                return -1;
            else
                return 1;
    }

    @Override
    public String toString() {
        return A + "   " + B;
    }
}

class Solution {
    private ArrayList <Interval> arrayListOfIntervals = new ArrayList();

    public static void main(String[] args) {
        int[] A = new int[]{-10,-8,-2};
        int[] B = new int[]{10,8,2};
        System.out.println(new Solution().solution(A, B));
    }

    public int solution(int[] A, int[] B) {

        for (int j = 0; j < A.length; j++) {
            arrayListOfIntervals.add(new Interval(A[j], B[j]));
        }

        int i = 0;
        Collections.sort(arrayListOfIntervals);

        while(i < arrayListOfIntervals.size() - 1){
            Interval firstInterval = arrayListOfIntervals.get(i);
            Interval secondInterval = arrayListOfIntervals.get(i + 1);
            if(checkIfNumbersOverlap(firstInterval, secondInterval)){
                arrayListOfIntervals.add(i, union(firstInterval, secondInterval));
                arrayListOfIntervals.remove(firstInterval);
                arrayListOfIntervals.remove(secondInterval);
            }
            else{
                i += 1;
            }
        }

        return arrayListOfIntervals.size();
    }

    public boolean checkIfNumbersOverlap(Interval interval1, Interval interval2){
        return !(interval1.getA() > interval2.getB() || interval1.getB() < interval2.getA());
    }

    public Interval union(Interval interval1, Interval interval2){
        return new Interval(Math.min(interval1.getA(), interval2.getA()), Math.max(interval1.getB(), interval2.getB()));
    }
}

