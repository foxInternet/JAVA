package BenchMark;

import javax.swing.JTextField;
import java.util.*;

public class SingleCPU5 implements SingleMultiCPU{
	private JTextField jtf;
	private double[] copy_a_double,orig_a_double;
	SingleCPU5(double[] copy,JTextField j){
		orig_a_double=copy;
		copy_a_double=Arrays.copyOf(orig_a_double, orig_a_double.length);
		jtf=j;
		System.out.println("\n----------------------------\nОригинальный массив double_copy5: ");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", copy_a_double[i]);
		
		new RUNNER(this,jtf,5);
	}
	public long Compute() {
		long t1=System.currentTimeMillis();
		long t1nano=System.nanoTime();
		for(int i=0;i<copy_a_double.length;i++) {
			if(i%2==0)
				copy_a_double[i]=Math.sqrt(copy_a_double[i]);
			else
				copy_a_double[i]=Math.cbrt(copy_a_double[i]);	
		}
		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
		
		System.out.println("\nЧасть преобразованное последовательности с точностью до 3-х знаков после запятой (Test 5)");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", copy_a_double[i]);
		System.out.println("\n*****************\nВремя Одноядерного процессора = "+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд\\");
		RUNNER.nano=(t2nano-t1nano);
		copy_a_double=null;
		copy_a_double=Arrays.copyOf(orig_a_double, orig_a_double.length);
		return (t2-t1);
	}
}
