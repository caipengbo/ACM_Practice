package dp.seq.mulstates;


/**
 * Title: 198. 打家劫舍
 * Desc: 如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。给定一个代表每个房屋存放金额的非负整数数组，
 * 计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 * 740题  可以转化成 该题，进行一个计数排序（下标统计每个点数出现的次数），就和该题一模一样了
 * Created by Myth-Lab on 11/21/2019
 */
public class P198HouseRobber {
    // 每个房间是一个阶段，每个阶段有两种状态（0,1）
    // 阶段转移：dp[i][0]=max(dp[i-1][0], dp[i-1][1])
    // dp[i][1] = dp[i-1][0]+nums[i]
    // max = max(dp[i][0], dp[i][1])
    public int rob(int[] nums) {
        if (nums.length == 0) return 0;
        int[][] dp = new int[nums.length][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        int max = dp[0][1];
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);
            dp[i][1] = dp[i-1][0] + nums[i];
            max = Math.max(dp[i][0], dp[i][1]);
        }
        return max;
    }
    // 上面的思路转化而来：dp[n] = MAX(dp[n-1], dp[n-2] + num )
    // 理解为：是 n-1 房屋可盗窃的最大值（0），要么就是 n-2 房屋可盗窃的最大值加上当前房屋的值（1），二者之间取最大值
    public int rob2(int[] nums) {
        if (nums.length == 0) return 0;
        int[] dp = new int[nums.length+1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= nums.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }
        return dp[nums.length];
    }
    // 使用滚动数组优化
    // 只和前两个元素有关

    // 面试题 17.16. 按摩师（和本题一样） 两种状态
    public int massage(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int n = nums.length;
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = nums[0];
        // max = Math.max(nums[0], nums[1]);
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]);  // 本次不选，那么前一次可以选也可以不选
            dp[i][1] = dp[i-1][0] + nums[i];  // 本次选，那么前一次不能选
        }
        // for (int[] ele : dp) {
        //     System.out.println(ele[0] + " " + ele[1]);
        // }
        return Math.max(dp[n-1][0], dp[n-1][1]);
    }


    public static void main(String[] args) {
        P198HouseRobber p198 = new P198HouseRobber();
        int[] nums = {2,7,9,3,1};
        int[] nums2 = {1,9,1,9,1};
        System.out.println(p198.massage(nums2));
    }
}
