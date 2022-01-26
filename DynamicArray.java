import java.util.Arrays;

public class DynamicArray {

    private int[] array;
    private int size;

    public DynamicArray(int capacityOfInitialArray){

        if(capacityOfInitialArray < 0){

            throw new IllegalArgumentException("ERROR! DynamicArray() input is less than 0!");

        }

        array = new int[capacityOfInitialArray];
        size = 0;

    }

    public DynamicArray(){

        array = new int[3];

        size = 0;


    }

    public DynamicArray(DynamicArray dynamicArrayInput){

        if(dynamicArrayInput == null){

            throw new IllegalArgumentException("'" + dynamicArrayInput + "' is equal to 'null'!");

        }

        this.size = dynamicArrayInput.size;

        array = new int[dynamicArrayInput.array.length];

        for(int i = 0; i < array.length; i++){

            array[i] = dynamicArrayInput.array[i];

        }

    }

    public int getSize(){

        //System.out.println("getSize()");

        return size;

    }

    public int getCapacity(){

        //System.out.println("getCapacity()");

        return array.length;

    }

    public int[] getArray(){

        //System.out.println("getArray()");

        int[] arrayCopy = new int[array.length];

        for(int i = 0; i < array.length; i++){

            arrayCopy[i] = array[i];

        }

        return arrayCopy;

    }

    public int[] toArray(){

        //System.out.println("toArray()");

        int[] arrayCopy = new int[this.size];

        for(int i = 0; i < arrayCopy.length; i++){

            arrayCopy[i] = array[i];

        }

        return arrayCopy;

    }

    public void push(int inputInt){

        //System.out.println("push()");

        //int[] dummyArray = new int[];

        int arrayContent;

        int originalArrayLength = array.length;

        //for(int i = 0; i < array.length; i++){

            //for everything in the
            //dummyArray[i] = array[i];

        //}

        //arrayContent = dummyArray.length;

        //ifthe size of the occupied array is equal to the max capacioty of the array, then double the array capacity
        if(size == array.length) {

            int[] newArray = new int[array.length * 2];
            //size = size * 2;

            for(int i = 0; i < this.getSize(); i++){

                newArray[i] = this.array[i];

            }

            array = newArray;

        }

        size = size + 1;

        array[size - 1] = inputInt;

    }

    public int pop() throws RuntimeException{

        //System.out.println("pop()");

        int poppedInt;

        if(size == 0){

            throw new RuntimeException("The array '" + Arrays.toString(array) + "' is empty!");

        }

        //stores last element before decrementing size
        poppedInt = array[size - 1];

        //stores last element before decrementing size
        //poppedInt = array[size];

        //decrements size
        size = size - 1;



        //set array to downsizedArray
        //array =  downsizedArray;

        if(array.length >= size * 4){

            int[] downsizedArray = new int[array.length / 2];

            for(int i = 0; i < downsizedArray.length; i++) {

                downsizedArray[i] = array[i];
            }

            array = downsizedArray;

        }

        return poppedInt;

    }

    int get(int indexInput) throws IndexOutOfBoundsException{

        //System.out.println("get()");

        if(indexInput > size || indexInput < 0){

            throw new IndexOutOfBoundsException("'" + indexInput + "' is an illegal index! " +
                    "Enter an integer between 0 and " + size + "!");

        }

        return array[indexInput];

    }

    int indexOf(int searchNumber){

        //System.out.println("indexOf()");

        //searching for number in array
        for(int i = 0; i < size; i++){

            int numberInIndex = array[i];

            if(numberInIndex == searchNumber){

                return i;

            }

        }

        return -1;

    }

    void add(int indexInput, int inputInteger) throws IndexOutOfBoundsException{

        //System.out.println("add()");

        //throws IndexOutOfBoundsException
        if(indexInput > size || indexInput < 0){

            throw new IndexOutOfBoundsException( "'" + indexInput + "' is an illegal index! " +
                    "Enter an integer between 0 and " + size);

        }

        int[] newArray;

        if (size == array.length) {
            newArray = new int[array.length * 2];
        } else {
            newArray = new int[array.length];
        }

        for (int i = 0; i < indexInput; i++) {
            newArray[i] = array[i];
        }

        newArray[indexInput] = inputInteger;

        for (int i = indexInput; i < size; i++) {
            newArray[i + 1] = array[i];
        }

        size += 1;

        array = newArray;

        /*//initializes an array that is the same size as array
        int[] shiftedArray = new int[size * 2];

        //gets the integers that will be shifted from the addition of indexInput and saves them to shifted array
        for(int i = indexInput; i < size; i++){

            // -indexInput so that shiftedArray actually starts counting from 0 instead of from indexInput
            shiftedArray[i - indexInput] = array[i];

        }

        //what about if the index is equal to size?

        array[indexInput] = inputInteger;
        size = size + 1;

        //adds contents of shifted array to array
        for(int i = 0; i < shiftedArray.length; i++){

            //starts from the place right after (indexInput + 1) the added index (indexInput)
            //and adds the contents of shiftedArray to array
            array[indexInput + 1] = shiftedArray[i];

        }*/

    }

    int remove(int indexInput){

        //System.out.println("remove()");

        if(indexInput > size - 1 || indexInput < 0){

            throw new IndexOutOfBoundsException( "'" + indexInput + "' is an illegal index! " +
                    "Enter an integer between 0 and " + size);

        }

        int returnInteger = array[indexInput];

        int[] newArray;

        if (size * 4 <= array.length) {

            newArray = new int[array.length / 2];

        } else {

            newArray = new int[array.length];

        }

        for (int i = 0; i < indexInput; i++) {

            newArray[i] = array[i];

        }

        //newArray[indexInput] = inputInteger;

        for (int i = indexInput; i < size; i++) {

            newArray[i] = array[i + 1];

        }

        size -= 1;

        array = newArray;

        return returnInteger;

    }

    boolean isEmpty() {

        return size == 0;

    }

    int findMin() throws RuntimeException {

        if (isEmpty()) {

            throw new RuntimeException();

        }

        return Arrays.stream(Arrays.copyOfRange(array, 0, size)).reduce(array[0], Math::min);

    }

    int findMax() throws RuntimeException{

        if (isEmpty()) {

            throw new RuntimeException();

        }

        int max = array[0];

        return Arrays.stream(Arrays.copyOfRange(array, 0, size)).reduce(array[0], Math::max);

    }

    public String toString(){

        if (isEmpty()) {
            return "[ ]";
        }

        String returnString = "";

        returnString += "[";

        for (int i = 0; i < getSize(); i++) {

            if (i != 0) {

                returnString += ", ";

            }

            returnString += array[i];

        }

        returnString += "]";

        System.out.println(returnString);

        return returnString;

    }

    boolean equals(DynamicArray dynamicArrayInput){

        if(dynamicArrayInput.size != this.size){

            return false;

        }

        for (int i = 0; i < this.size; i++) {

            if (dynamicArrayInput.array[i] != this.array[i]) {

                return false;

            }

        }

        return true;

    }

}
