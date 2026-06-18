package coding_test;

class Solution {
    static int answer = 0;

    public void dfs(int idx, int sum, int[] numbers, int target) {
        if(idx==numbers.length) {
            if (sum == target) answer++;
            return;
        }

        dfs(idx+1, sum + numbers[idx], numbers, target);
        dfs(idx+1, sum - numbers[idx], numbers, target);
    }
    public int solution(int[] numbers, int target) {
        dfs(0, 0, numbers, target);

        return answer;
    }
}