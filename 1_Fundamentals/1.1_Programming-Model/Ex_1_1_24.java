package z_1;

public class Ex_1_1_24 {
	
    private Ex_1_1_24() { }
    //a<b
    public static int indexOf(int a_,int b_) {
    	int a=a_;
    	int b=b_;
    	
    	while(!(b%a==0)){
    		int temp=a;
    		a=b%a;
    		b=temp;
    		indexOf(a,b);
    	}
    	return a;
    }

    public static void main(String[] args) { 
    	System.out.println(indexOf(1111111,1234567));
    }
}
