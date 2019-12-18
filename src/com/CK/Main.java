package com.CK;

public class Main {

    public static void main(String[] args) {
	// write your code here
    }
}

class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        for (int i = 0; i < n; i++) {
            pq.offer(new int[]{startTime[i], endTime[i], profit[i]});
        }

        List<Integer> st = new ArrayList<>(), ed = new ArrayList<>(), prof = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int[] curr = pq.poll();
            st.add(curr[0]);
            ed.add(curr[1]);
            prof.add(curr[2]);
        }

        int dp[] = new int[n], p[] = new int[n];

        for (int i = 1; i < n; i++) {
            p[i] = Collections.binarySearch(ed, st.get(i));
            if (p[i] < 0) {
                p[i] = -(p[i] + 1) - 1;
            }
        }
        dp[0] = prof.get(0);
        for (int i = 1; i < n; i++) {
            dp[i] = Math.max(prof.get(i), dp[i - 1]);
            if (p[i] >= 0) {
                dp[i] = Math.max(dp[i], prof.get(i) + dp[p[i]]);
            }
        }
        return dp[n - 1];
    }

}

class Solution {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = startTime.length;
        int[][] jobs = new int[n][3];
        for (int i = 0; i < n; i++) {
            jobs[i] = new int[] {startTime[i], endTime[i], profit[i]};
        }
        Arrays.sort(jobs, (a, b)->a[1] - b[1]);
        TreeMap<Integer, Integer> dp = new TreeMap<>();
        dp.put(0, 0);
        for (int[] job : jobs) {
            int cur = dp.floorEntry(job[0]).getValue() + job[2];
            if (cur > dp.lastEntry().getValue())
                dp.put(job[1], cur);
        }
        return dp.lastEntry().getValue();
    }
}


