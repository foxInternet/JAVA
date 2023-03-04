package BenchMark;

import java.util.concurrent.RecursiveAction;

public class MultiCore7 extends RecursiveAction{
	private static final long serialVersionUID = -1394615970019737439L;
	
	private final int seqThresHold=1_000_000;//Порог
	private int end,start;
	private double[] copy_a_double;
	private double min,max;
	MultiCore7(double[] d,int s,int e,double min_d,double max_d){
		copy_a_double=d;
		start=s;
		end=e;
		min=min_d;
		max=max_d;
	}
	@Override
	protected void compute() {
		if( (end-start)<=seqThresHold){
			for(int i=start;i<end;i++) {
				if(i%2==0)
					copy_a_double[i]=(Math.sqrt(copy_a_double[i])*Math.sqrt(min) )*( Math.sqrt(min*100_000)*Math.sqrt(min*100_000) );
				else
					copy_a_double[i]=( Math.cbrt(copy_a_double[i])*Math.cbrt(max) )*( Math.cbrt(max*1_000)*Math.cbrt(max*1_000) );	
			}
		}else {
			int middle=(start+end)/2;
			invokeAll(new MultiCore7(copy_a_double,start,middle,min,max),new MultiCore7(copy_a_double,middle,end,min,max));
		}
	}//end compute
}
