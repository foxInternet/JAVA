package BenchMark;

import java.util.LinkedList;
import java.util.concurrent.ForkJoinPool;
import javax.swing.JTextField;

public class MultiCPU12 implements SingleMultiCPU{
	private JTextField jtf;
	private LinkedList<Integer> orig_ll,ll_stream_int;
	MultiCPU12(LinkedList<Integer> ll,JTextField j){
		orig_ll=ll;
		jtf=j;
		ll_stream_int=new LinkedList<>(orig_ll);
		System.out.println("\n----------------------------\nОригинальный массив Parallel ll_stream_int: ");
		for(int i=0;i<10;i++)
			System.out.print(ll_stream_int.get(i)+" ");
		
		new RUNNER(this,jtf,12);
	}
public long Compute() {
	ForkJoinPool fjp=new ForkJoinPool();
	MultiCore2 task=new MultiCore2(ll_stream_int,0,ll_stream_int.size());
	System.out.println("\n----------------------------\nВычисления в Collections.swap с помощью стратегии \"разделяй и властвуй\". Фреймворк Fork\\Join: (Test 12)");
	long t1=System.currentTimeMillis();
	long t1nano=System.nanoTime();
	//ВЫЧИСЛИМ СУММУ ВСЕХ ЗНАЧЕНИЙ МАССИВА 
		fjp.invoke(task);
	long t2=System.currentTimeMillis();
	long t2nano=System.nanoTime();
	for(int i=50_000;i<50_010;i++)
		System.out.print(ll_stream_int.get(i)+" ");
	System.out.print("\nВремя потраченное на вычисления в Сollections.swap:"+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд\\");
	RUNNER.nano=(t2nano-t1nano);
	
	return (t2-t1);
}
	
}
