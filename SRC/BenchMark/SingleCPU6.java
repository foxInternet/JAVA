package BenchMark;

import javax.swing.JTextField;
import java.util.Arrays;
import java.util.stream.*;
import java.util.OptionalDouble;

public class SingleCPU6 implements SingleMultiCPU{
	private JTextField jtf;
	private double[] copy_a_double,orig_a_double;
	private double min,max;
	
	SingleCPU6(double[] copy,JTextField j){
		orig_a_double=copy;
		copy_a_double=Arrays.copyOf(orig_a_double, orig_a_double.length);
		jtf=j;
		System.out.println("\n----------------------------\nОригинальный массив double_copy6: ");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", copy_a_double[i]);
		
		new RUNNER(this,jtf,6);
	}
	public long Compute() { //В этом методе не только четные возведем в корень а нечетные в кубический - но и найдем в потоке МИН и МАК значение
		long t1=System.currentTimeMillis();
		long t1nano=System.nanoTime();
		
		DoubleStream ds=Arrays.stream(copy_a_double);
		OptionalDouble minStream=ds.min();
		if(minStream.isPresent()) 
			min=minStream.getAsDouble();
		//вычислили максимальное значение
		ds=Arrays.stream(copy_a_double);
		OptionalDouble maxStream=ds.max();
		if(maxStream.isPresent()) 
			max=maxStream.getAsDouble();
		System.out.print("\nMIN="+min+" MAX="+max+"\n");
		for(int i=0;i<copy_a_double.length;i++) {
			if(i%2==0)
				copy_a_double[i]=Math.sqrt(copy_a_double[i]);
			else
				copy_a_double[i]=Math.cbrt(copy_a_double[i]);	
		}
		//вычислили Минимальное значение

		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
		
		System.out.println("\nЧасть преобразованное последовательности с точностью до 3-х знаков после запятой и МИН="+min+" МАКС="+max+" (Test 6)");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", copy_a_double[i]);
		System.out.println("\n*****************\nВремя Одноядерного процессора = "+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд\\");
		RUNNER.nano=(t2nano-t1nano);
		copy_a_double=null;
		copy_a_double=Arrays.copyOf(orig_a_double, orig_a_double.length);
		return (t2-t1);
	}
}

