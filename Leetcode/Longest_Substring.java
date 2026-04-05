class Solution {
    public int lengthOfLongestSubstring(String s) {
        int[] map = new int[128];
        int left = 0, max = 0;

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            map[c]++;

            while (map[c] > 1) {
                map[s.charAt(left)]--;
                left++;
            }

            max = Math.max(max, right - left + 1);
        }

        return max;
    }
}
