package BenchMark;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.DoubleStream;
import javax.swing.JTextField;

public class MultiCPU17 implements SingleMultiCPU{
	private JTextField jtf;
	private double[] copy_a_double,orig_a_double;
	private double min,max;
	MultiCPU17(double[] copy,JTextField j){
		orig_a_double=copy;
		copy_a_double=Arrays.copyOf(orig_a_double, orig_a_double.length);
		jtf=j;
		System.out.println("\n----------------------------\nќригинальный массив double_copy17: ");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", copy_a_double[i]);
		
		new RUNNER(this,jtf,17);
	}
	public long Compute() {
		ForkJoinPool fjp=new ForkJoinPool();
		//вычислили ћинимальное значение
		DoubleStream ds=Arrays.stream(copy_a_double).parallel();
		OptionalDouble minStream=ds.min();
		if(minStream.isPresent())
			min=minStream.getAsDouble();
		//вычислили максимальное значение
		ds=Arrays.stream(copy_a_double).parallel();
		OptionalDouble maxStream=ds.max();
		if(maxStream.isPresent())
			max=maxStream.getAsDouble();
		System.out.print("\nMIN="+min+" MAX="+max+"\n");
		MultiCore7 task=new MultiCore7(copy_a_double,0,copy_a_double.length,min,max);
		
		long t1=System.currentTimeMillis();
		long t1nano=System.nanoTime();

		fjp.invoke(task);
		
		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
//после окончани€ подсчета - выведем на экран первые 10 значений. при этом создав заново ѕараллеьный поток
		System.out.println("\n„асть преобразованное последовательности с точностью до 3-х знаков после зап€той (Test 17)");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", copy_a_double[i]);
		System.out.println("\n*****************\n¬рем€ ћногоядерного процессора = "+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд\\");
		RUNNER.nano=(t2nano-t1nano);
		copy_a_double=null;
		copy_a_double=Arrays.copyOf(orig_a_double, orig_a_double.length);
		return (t2-t1);
	}
}
