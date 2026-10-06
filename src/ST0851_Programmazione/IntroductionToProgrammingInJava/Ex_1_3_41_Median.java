import Progetti_Personali._RvLib;

public class Ex_1_3_41_Median {

    public static void PrintArray(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        int[] nums = new int[] {8, 5, 3, 6, 2, 4, 9, 1, 7};
        /*
        for (int ii = 0; ii < nums.length; ii++) {
            nums[ii] = _RvLib.getIntInput(args, ii);
        }
         */
        PrintArray(nums);
        _RvLib.intBubbleSort(nums);
        PrintArray(nums);
        System.out.println("Median is " + nums[nums.length/2]);
    }

}
