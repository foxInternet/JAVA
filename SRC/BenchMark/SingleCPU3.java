package BenchMark;

import java.util.Arrays;


import javax.swing.JTextField;

public class SingleCPU3 implements SingleMultiCPU{
	private JTextField jtf;
	private int[] copy_a,orig_a;
	SingleCPU3(int[] copy,JTextField j){
		orig_a=copy;
		copy_a=Arrays.copyOf(orig_a, orig_a.length);
		jtf=j;
		System.out.println("\n----------------------------\nОригинальный массив copy_a: ");
		for(int i=0;i<10;i++)
			System.out.print(copy_a[i]+" ");
		
		new RUNNER(this,jtf,3);
	}
	public long Compute() {
		System.out.println("\n----------------------------\nОтсортированнная последовательность с помощью Arrays.sort(copy_a) (Test 3)");
		long t1=System.currentTimeMillis();
		long t1nano=System.nanoTime();
		
		Arrays.sort(copy_a);				//Сортируем
		copy_a=Arrays.copyOf(orig_a, orig_a.length);//заново заполняем прежним порядком
		Arrays.sort(copy_a);				//опять сортируем
		for(int i=0;i<copy_a.length;i++)	//Посчитаем кв корни всех значений массива
			copy_a[i]= (int)Math.sqrt(copy_a[i])+(int)Math.cbrt(copy_a[i]);
		
		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
		for(int i=50_000;i<50_010;i++)
			System.out.print(copy_a[i]+" ");
		System.out.println("\n----------------------------\nВремя потраченное на сортировку с помощью Arrays.sort:"+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд.");
		RUNNER.nano=(t2nano-t1nano);
		copy_a=null;
		copy_a=Arrays.copyOf(orig_a, orig_a.length);
		return (t2-t1);
	}
}
