package BenchMark;

public class scores {
	static private long[] milisec;
	static private long[] nanosec;
	
	static{
		milisec=new long[20];
		for(int i=0;i<milisec.length;i++)
			milisec[i]=0L;
		nanosec=new long[20];
		for(int i=0;i<nanosec.length;i++)
			nanosec[i]=0L;
	}
	
	static long get_milisec(int i) {return milisec[i-1];}
	static long get_nanosec(int i) {return nanosec[i-1];}
	static long[] get_mlsec() {return milisec;}
	static long[] get_nsec() {return nanosec;}
	static void set_milisec(int i,long val) {
		milisec[i-1]=val;
	}
	static void set_nanosec(int i,long val) {
		nanosec[i-1]=val;
	}
}
