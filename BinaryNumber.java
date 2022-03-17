/* 
   Assignment : 1
   Name: Sahil Mahendra Mody
   CWID: 20007262
   Course: CS-570-B
   */ 
package Data_Structures;
import java.util.*; 
import java.util.Arrays;


public class BinaryNumber {
    private int data[];
    private boolean overflow;
    //Creating binary number of given length consisting only zeros
    public BinaryNumber(int length){
        data=new int[length];
        for(int i=0;i<length-1;i++){
            data[i]=0;
        }
    }
    //creating binary number of given string
    public BinaryNumber(String str) {
        int length=str.length();
        data=new int[length];
        for ( int i=0;i<length;i++){
            if(Character.getNumericValue(str.charAt(i))==0 || Character.getNumericValue(str.charAt(i))==1){
                data[i]=Character.getNumericValue(str.charAt(i));
            }
            else{
                System.out.println("String is not binary");
            }
        }
    }
    //Length of binary number
    public int getLength(){
        return data.length;
    }  
    //Obtaining  a  digit  of  a  binary  number  given  an  index.
    public int getDigit(int index){
        if(index>data.length){
            System.out.println("Index out of bounds");
            return -1;
        }
        else{
            return data[index];
        }
    } 

    // shifting zeros
    public void shiftR(int amount){
        int[] x= Arrays.copyOf(data, (data.length+amount));
    	for(int i=0;i<x.length;i++) {
    		if(i<amount)
    			x[i]=0;
    		else
    			x[i]=data[i-amount];
    	}
    	data=x;
    }
    //adding binary numbers
    public void add(BinaryNumber aBinaryNumber) {
        if(getLength()!=aBinaryNumber.getLength()){
    		System.out.println("Length of the binary number should coincide");
        }
        else{
            int carry=0;
            for(int i=0;i<aBinaryNumber.getLength();i++){
                int add=getDigit(i)+aBinaryNumber.getDigit(i)+carry;
                if(add==1){
                    data[i]=add;
                    carry=0;
                }
                else if(add==2){
                    data[i]=0;
                    carry=1;
                }
                else if(add==3){
                    data[i]=1;
                    carry=1;
                }
                else{
                    data[i]=add;
                    carry=0;
                }
            }
            if(carry==1){
                overflow=true;
            }
            else{
                clearOverflow();
            }
        }
    }
    //converting a binary number to a string
    public String toString() {
        String str=new String();
        if(overflow){
            return ("Overflow");
        }
        else{
            for(int i=0;i<data.length;i++){
                str=str+String.valueOf(data[i]);
            }
        return str;
        }
    }
    //converting a binary number to a decimal
    public int toDecimal() {
        int decimal=0;
    	for(int i=data.length-1;i>=0;i--) {
    		decimal =(int) (decimal + data[i]*(Math.pow(2, i)));
    	}
    	return decimal;
    }
    //clearing overflow
    public void clearOverflow() {
    	overflow=false;
    }
    public static void main(String args[]) {
        //printing bin of zeros
		try {
			BinaryNumber Bin=new BinaryNumber(5);  
			System.out.println("Binary number of length having only zeros: " + Bin);
		}catch (Exception e) {
			System.out.println("Enter only positive integer value.");
		}
		// printing binary number in string
		try {
			BinaryNumber z= new BinaryNumber("10101");
			System.out.println("Binary number corresponding to given string: "+ z);
		}catch(Exception e) {
			System.out.println("Please enter valid String.");
		}
		//printing the length of bin	
		try{
			BinaryNumber length= new BinaryNumber("10000101");
			System.out.println("Length of bin is: "+length.getLength() );
		}catch(Exception e) {
			System.out.println("Enter only binary number.");
		}
		//checking the index of diggit		
		try{
			BinaryNumber digitindex=new BinaryNumber("1001010");
			System.out.println("Index of digit mentioned in binary number:"+digitindex.getDigit(4));
		}catch (Exception e) {
			System.out.println("Index out of bound");
		}
        //shifting zeros 
        try {
	    	BinaryNumber Shift= new BinaryNumber("101101");
	    	Shift.shiftR(3);
	    	System.out.println("Binary number after right shift operation: " +Shift);
	    }catch (Exception e) {
	    	System.out.println("Please enter positive number as amount to right shift the binary number.");
	    }
        //adding binary numbers
        try {
	    	BinaryNumber x= new BinaryNumber("10110");
	    	BinaryNumber y= new BinaryNumber("11100");
	    	
	    	x.add(y);
	    	System.out.println("Addition of two binary number is: " +x.toString());
            System.out.println("Decimal notation of addition: " +x.toDecimal());
	    	
	    }catch (Exception e) {
	    	System.out.println("Enter only binary number.");
	    }
        //converting binary number to decimal and printing it 
		try {
			BinaryNumber BinToDec= new BinaryNumber("1011");
			System.out.println("Decimal notation of given binary number: " +BinToDec.toDecimal());
		}catch (Exception e) {
				System.out.println("Enter only binary number.");
		} 
	    
	}
}

    

        

