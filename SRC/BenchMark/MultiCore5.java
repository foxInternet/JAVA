package BenchMark;

import java.util.concurrent.RecursiveAction;

public class MultiCore5 extends RecursiveAction{
	private static final long serialVersionUID = 5723190822255431768L;
	private final int seqThresHold=1_000_000;//Порог
	private int end,start;
	private double[] copy_a_double;
	MultiCore5(double[] d,int s,int e){
		copy_a_double=d;
		start=s;
		end=e;
	}
	@Override
	protected void compute() {
		if( (end-start)<=seqThresHold){
		for(int i=start;i<end;i++) {
			if(i%2==0)
				copy_a_double[i]=Math.sqrt(copy_a_double[i]);
			else
				copy_a_double[i]=Math.cbrt(copy_a_double[i]);	
		}
		}else {
			int middle=(start+end)/2;
			invokeAll(new MultiCore5(copy_a_double,start,middle),new MultiCore5(copy_a_double,middle,end));
		}
	}//end compute
}
