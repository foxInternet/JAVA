package BenchMark;

import java.util.Collections;
import java.util.LinkedList;
import javax.swing.JTextField;

public class SingleCPU2 implements SingleMultiCPU{
	
	private JTextField jtf;
	private LinkedList<Integer> orig_ll,ll;
	SingleCPU2(LinkedList<Integer> ll,JTextField j){
		orig_ll=ll;
		this.ll=new LinkedList<Integer>(orig_ll);
		jtf=j;
		System.out.println("\n----------------------------\nОригинальный массив ll ");
		for(int i=0;i<10;i++)
			System.out.print(ll.get(i)+" ");
		
		new RUNNER(this,jtf,2);
	}
	public long Compute() {
		//Отсортируем с помощью Collections.sort
		System.out.println("\n----------------------------\nnВычисления в Collections.swap: (Test 2)");
		long t1=System.currentTimeMillis();
		long t1nano=System.nanoTime();
		//Collections.sort(ll,Integer::compare);  
		for(int i=0;i<ll.size();i++)
			if( i<ll.size()-1 )
				Collections.swap(ll, i, i+1);
		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
		for(int i=50_000;i<50_010;i++)
			System.out.print(ll.get(i)+" ");
		System.out.print("\nВремя потраченное на вычисления в Collections.swap:"+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд\\");
		RUNNER.nano=(t2nano-t1nano);
		this.ll=new LinkedList<Integer>(orig_ll);
		return (t2-t1);
	}
}
