package ST0851_Programmazione.Lezioni;

public class ArrayShuffle {

    public static void PrintArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            StdOut.print(nums[i] + " ");
        }
        StdOut.println();
    }

    public static void ArrayShuffle(int[] nums) {
        int N = nums.length;
        for (int i = 0; i < N; i++) {
            int r = i + (int) (Math.random() * (N - i));
            int t = nums[i];
            nums[i] = nums[r];
            nums[r] = t;
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        PrintArray(nums);
        ArrayShuffle(nums);
        PrintArray(nums);
    }

}
