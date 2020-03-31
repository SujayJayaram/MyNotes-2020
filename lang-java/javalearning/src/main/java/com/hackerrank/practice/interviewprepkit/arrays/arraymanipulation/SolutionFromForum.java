package com.hackerrank.practice.interviewprepkit.arrays.arraymanipulation;

import java.util.*;

public class SolutionFromForum {

    // https://www.hackerrank.com/challenges/crush/forum?h_l=interview&playlist_slugs%5B%5D=interview-preparation-kit&playlist_slugs%5B%5D=arrays
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int m = scan.nextInt();

        // This will be the "difference array". The entry arr[i]=k indicates that arr[i] is exactly k units larger than arr[i-1]
        long[] arr = new long[n];

        int lower;
        int upper;
        long sum;

        for(int i=0;i<n;i++)
            arr[i]=0;

        for(int i=0;i<m;i++){
            lower=scan.nextInt();
            upper=scan.nextInt();
            sum=scan.nextInt();
            arr[lower-1]+=sum; // It' a 1-indexed array so minus 1 corrects this
            if(upper<n)
                arr[upper]-=sum; // Record a downwards delta AFTER the upper bound.
        }

        long max=0;
        long temp=0;

        // Iterate through the lis playing out the deltas
        for(int i=0;i<n;i++){
            temp += arr[i];
            if(temp> max)
                max=temp;
        }

        System.out.println(max);
    }
}
