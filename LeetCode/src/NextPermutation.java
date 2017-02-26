public class NextPermutation {
    public void nextPermutation(int[] nums) {
        int i = nums.length - 1;
        for (; i > 0; i--) {
            if (nums[i] > nums[i - 1]) {
                int offset = 0;
                while (i + offset + 1 < nums.length
                        && nums[i] >= nums[i + offset + 1]
                        && nums[i + offset + 1] > nums[i - 1]) {
                    offset++;
                }
                int tmp = nums[i + offset];
                nums[i + offset] = nums[i - 1];
                nums[i - 1] = tmp;
                break;
            }
        }
        int stop = (nums.length - i) / 2;
        for (int j = 0; j < stop; j++) {
            int tmp = nums[i + j];
            nums[i + j] = nums[nums.length - 1 - j];
            nums[nums.length - 1 - j] = tmp;
        }
    }

    public static void main(String... args) {
        NextPermutation np = new NextPermutation();
        int[] nums = { 4, 2, 4, 4, 3 };
        np.nextPermutation(nums);
        for (int d : nums)
            System.out.println(d);
    }
}
