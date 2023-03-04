package BenchMark;

import java.util.Arrays;
import java.util.stream.IntStream;

import javax.swing.JTextField;

public class MultiCPU13 implements SingleMultiCPU{
	private JTextField jtf;
	private int[] copy_a,orig_a;
	private int c;
	MultiCPU13(int[] copy,JTextField j){
		orig_a=copy;
		copy_a=Arrays.copyOf(orig_a, orig_a.length);
		jtf=j;
		System.out.println("\n----------------------------\nОригинальный массив copy_a13: ");
		for(int i=0;i<10;i++)
			System.out.print(copy_a[i]+" ");
		
		new RUNNER(this,jtf,13);
	}
public long Compute() {
	c=0;
	System.out.println("\n----------------------------\nОтсортированнная последовательность с помощью Arrays.parallelSort (Test 13)");
	long t1=System.currentTimeMillis();
	long t1nano=System.nanoTime();	
	
	Arrays.parallelSort(copy_a);				//Сортируем
	copy_a=Arrays.copyOf(orig_a, orig_a.length);//заново заполняем прежним порядком
	Arrays.parallelSort(copy_a);				//опять сортируем
	IntStream is=Arrays.stream(copy_a).parallel();
	is.forEachOrdered( (y) -> y=(int) Math.sqrt(y)+(int)Math.cbrt(y) );
	
	long t2=System.currentTimeMillis();
	long t2nano=System.nanoTime();
	is=Arrays.stream(copy_a).parallel();
	is.forEachOrdered( (y) -> {
		y=(int) Math.sqrt(y)+(int)Math.cbrt(y);
		c++;
		if(c>=50_000 && c<50_010)
			System.out.print(y+" ");
		});
	//for(int i=50_000;i<50_010;i++)
	//	System.out.print(copy_a[i]+" ");
	System.out.print("\nВремя потраченное на сортировку с помощью Arrays.parallelSort:"+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд\\");
	RUNNER.nano=(t2nano-t1nano);
	copy_a=null;
	copy_a=Arrays.copyOf(orig_a, orig_a.length);
	return (t2-t1);
}
	
}
