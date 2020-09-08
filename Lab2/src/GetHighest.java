public class GetHighest {
    public static int getMax(int[] inputArray){
        int maxValue = inputArray[0];
        for(int i=1;i < inputArray.length;i++){
            if(inputArray[i] > maxValue){
                maxValue = inputArray[i];
            }
        }
        return maxValue;
    }

    public static int getMaxIndex(int[] inputArray){
        int maxValue = inputArray[0];
        int maxIndex = 0;
        for(int i=1;i < inputArray.length;i++){
            if(inputArray[i] > maxValue){
                maxValue = inputArray[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
