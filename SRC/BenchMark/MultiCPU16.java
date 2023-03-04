package BenchMark;

import java.util.Arrays;
import java.util.OptionalDouble;
import java.util.concurrent.ForkJoinPool;
import java.util.stream.DoubleStream;

import javax.swing.JTextField;

public class MultiCPU16 implements SingleMultiCPU{
	private JTextField jtf;
	private double[] copy_a_double,orig_a_double;
	private double min,max;
	MultiCPU16(double[] copy,JTextField j){
		orig_a_double=copy;
		copy_a_double=Arrays.copyOf(orig_a_double, orig_a_double.length);
		jtf=j;
		System.out.println("\n----------------------------\nќригинальный массив double_copy16: ");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", copy_a_double[i]);
		
		new RUNNER(this,jtf,16);
	}
	public long Compute() {
		
		ForkJoinPool fjp=new ForkJoinPool();
		MultiCore6 task=new MultiCore6(copy_a_double,0,copy_a_double.length);
		long t1=System.currentTimeMillis();
		long t1nano=System.nanoTime();
		
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
		fjp.invoke(task);
		
		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
		System.out.println("\n„асть преобразованное последовательности с точностью до 3-х знаков после зап€той (Test 16)");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", copy_a_double[i]);
		System.out.println("\n*****************\n¬рем€ ћногоядерного процессора = "+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд\\");
		RUNNER.nano=(t2nano-t1nano);
		copy_a_double=null;
		copy_a_double=Arrays.copyOf(orig_a_double, orig_a_double.length);
		return (t2-t1);
	}
}