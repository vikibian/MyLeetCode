public class test {
    public static void main(String[] args) {

    }

    public static void sort(int[] numArray, int num,int count,int gap){
        if (count+1<numArray.length){
            numArray[count] = numArray[count] + gap;
            if (numArray[count+1] - numArray[count] == gap){
                count++;
                sort(numArray,num,count,gap);
            }else {
                return;
            }
        }
    }
}
