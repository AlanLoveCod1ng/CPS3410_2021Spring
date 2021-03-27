import java.util.*;

class Solution {

    public static void main(String[] args) {
        
    }
    //dynamic programming
    // I use LinkedList here because I consider that the list need to add element, however
    //I can't copy the reference of linkedList directly, I need to copy it for new one, so
    //2d array here is also available and more efficient
    public static LinkedList<Integer> lengthOfLIS(int[] nums) {
        LinkedList<Integer>[] maxIncreSub= new LinkedList [nums.length];
        for (int i = 0; i < nums.length; i++) {
            if(i == 0){
                LinkedList<Integer> temp = new LinkedList<>();
                temp.addLast(nums[0]);
                maxIncreSub[0] = temp;
                continue;
            }
            LinkedList<Integer> preMaxSub = new LinkedList<>();
            int maxLength = 0;
            for(int j = 0; j < i; j++){   
                if(nums[j]<nums[i]&&maxIncreSub[j].size()>maxLength){
                    maxLength = maxIncreSub[j].size();
                    preMaxSub = maxIncreSub[j];
                }
            }
            preMaxSub = (LinkedList)preMaxSub.clone();
            preMaxSub.add(nums[i]);
            maxIncreSub[i] = preMaxSub;
        }
        
        int maxLength = 0;
        int maxIndex = 0;
        for(int i = 0; i< maxIncreSub.length; i++){
            if(maxIncreSub[i].size()>maxLength){
                maxLength = maxIncreSub[i].size();
                maxIndex = i;
            }
        }
        return maxIncreSub[maxIndex];
    }
    //greedy algorithm
    public static int[] lengthOfLIS1(int[] nums){
        int [][] maxIncSub = new int[nums.length][];
        int [] lastMinNum = new int[nums.length];
        for(int i = 0; i<lastMinNum.length; i++){
            lastMinNum[i] = Integer.MIN_VALUE;
        }
        int length = 1;
        maxIncSub[length]=new int[length];
        maxIncSub[length][0] = nums[0];
        lastMinNum[length]=nums[0];
        if(nums.length==0)return new int[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > lastMinNum[length]) {
                length++;
                lastMinNum[length] = nums[i];
                int [] temp = new int [length];
                System.arraycopy(maxIncSub[length-1], 0, temp, 0, length-1);
                temp[length-1] = nums[i];
                maxIncSub[length] = temp;
            } else {
                int l = 1, r = length, pos = 0;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (lastMinNum[mid] < nums[i]) {
                        pos = mid;
                        l = mid + 1;
                    } else {
                        r = mid - 1;
                    }
                }
                lastMinNum[pos + 1] = nums[i];
                maxIncSub[pos+1][pos]=nums[i];
            }
        }
        int maxLength = 0;
        for(int i = lastMinNum.length-1; i>-1; i--){
            if(lastMinNum[i]!=Integer.MIN_VALUE){
                maxLength = i;
                break;
            }
        }
        return maxIncSub[maxLength];
}
}
