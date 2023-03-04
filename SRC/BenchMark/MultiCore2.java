package BenchMark;

import java.util.Collections;
import java.util.LinkedList;
import java.util.concurrent.RecursiveAction;

public class MultiCore2 extends RecursiveAction{
	private static final long serialVersionUID = -1707407472788675954L;
	private final int seqThresHold=10_000;//Порог
	private int end,start;
	private LinkedList<Integer> orig_ll;
	
	MultiCore2(LinkedList<Integer> ll,int s,int e){
		orig_ll=ll;
		start=s;
		end=e;
	}
	@Override
	protected void compute() {
		if( (end-start)<=seqThresHold){
		for(int i=start;i<end;i++) {
				if( i<orig_ll.size()-1)
					Collections.swap(orig_ll, i, i+1);
		}
		}else {
			int middle=(start+end)/2;
			invokeAll(new MultiCore2(orig_ll,start,middle),new MultiCore2(orig_ll,middle,end));
		}
	}//end compute
}