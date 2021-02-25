
public class AS1 {
    public static void main(String[] args) throws Exception{

        int repeatTime = 10;
        int unit = 5000;//unit for each change
        int upLimit = 100000;
        int [] arr;
        double beginTime;
        double endTime;
        double timeUsed;
        int currentLength;
        double []timeRecordMerge = new double[10];
        double []timeRecordInsertion = new double[10];
        double averageTimeMerge=0;
        double averageTimeInsertion = 0;

        for(currentLength = unit; currentLength<=upLimit; currentLength+=unit){
            //change the length of the array to get different running time
            for(int j = 0; j< repeatTime; j++){
                //repeat and get average value to reduce chance 
                arr = new int [currentLength];
                for(int i = 0; i< currentLength; i++){
                    arr[i] = (int)(Math.random()*1000);
                }
        
                beginTime = System.currentTimeMillis();
                mergeSort(arr,0,arr.length-1);
                endTime = System.currentTimeMillis();
                timeUsed = endTime-beginTime;
                timeRecordMerge[j] = timeUsed;//record time used in the array to get sum and calculate average value eventually
                
               
                for(int i = 0; i< currentLength; i++){
                    arr[i] = (int)(Math.random()*1000);
                }
                
                beginTime = System.currentTimeMillis();
                insertionSort(arr);
                endTime = System.currentTimeMillis();
                timeUsed = endTime-beginTime;
                timeRecordInsertion[j] = timeUsed;
            }
            for(int i = 0; i< repeatTime; i++){
                averageTimeMerge+=timeRecordMerge[i]; 
                averageTimeInsertion+=timeRecordInsertion[i];//get sum
            }

            averageTimeMerge = averageTimeMerge/repeatTime;//get average time of merge sort
            averageTimeInsertion = averageTimeInsertion/repeatTime;//get average time of insertion sort
            System.out.printf("%s\t%s\t%s\n%10d\t%10f\t%10f\n","ArrayLength","MergeSort","InsertionSort",currentLength,averageTimeMerge,averageTimeInsertion);
        }
        
    }


    public static void insertionSort(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            int key = arr[i];
            int j = i - 1;
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1; 
            }
            arr[j + 1] = key;
        }
    }

    public static void merge(int[] arr, int beg, int mid, int end) {
        int[] temp1 = new int[mid - beg + 1];
        int[] temp2 = new int[end - mid];
        System.arraycopy(arr, beg, temp1, 0, mid - beg + 1);
        System.arraycopy(arr, mid + 1, temp2, 0, end - mid);
        int key1 = 0;
        int key2 = 0;
        for (int i = beg; i < end + 1; i++) {
            if (key1 == temp1.length && key2 == temp2.length) {
                break;
            }
            if (key1 == temp1.length) {
                arr[i] = temp2[key2];
                key2++;
                continue;
            }
            if (key2 == temp2.length) {
                arr[i] = temp1[key1];
                key1++;
                continue;
            }
            if (temp1[key1] <= temp2[key2]) {
                arr[i] = temp1[key1];
                key1++;
            } else {
                arr[i] = temp2[key2];
                key2++;
            }
        }
    }

    public static void mergeSort(int[] arr, int beg, int end) {
        if (beg != end) {
            int middle = (beg + end) / 2;

            mergeSort(arr, beg, middle);
            mergeSort(arr, middle + 1, end);

            merge(arr, beg, middle, end);
        }
    }
}