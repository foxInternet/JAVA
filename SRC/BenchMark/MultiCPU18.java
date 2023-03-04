package BenchMark;

import java.util.LinkedList;
import javax.swing.JTextField;
import java.util.stream.*;

public class MultiCPU18 implements SingleMultiCPU{
	private JTextField jtf;
	private LinkedList<Integer> ll_stream_int;
	private LinkedList<Long> ll_long=new LinkedList<Long>();
	MultiCPU18(LinkedList<Integer> ll,JTextField j){
		ll_stream_int=ll;
		ll_stream_int.forEach((x) -> ll_long.add((long) x));
		jtf=j;
		System.out.println("\n----------------------------\nОригинальный массив Parallel ll_stream_long: ");
		for(int i=0;i<10;i++)
			System.out.print(ll_long.get(i)+" ");
		
		new RUNNER(this,jtf,18);
	}
public long Compute() {
	System.out.println("\n----------------------------\nВычисления в потоке reduce() с помощью потока Parallel Stream<Long>: (Test 18)");
	long t1=System.currentTimeMillis();
	long t1nano=System.nanoTime();
	//ВЫЧИСЛИМ СУММУ ВСЕХ ЗНАЧЕНИЙ МАССИВА 
	//Для правильного подсчета суммы в параллельном потоке надо считать по методу 
	//<U> U reduce (U значение_идентичности ,BiFunction<U ,? super Т, U>накопитель BinaryOperator<U> объединитель)
	long streamLongSum=ll_long.parallelStream().reduce(0L, 			//identity
													  (a,b) -> a+b, //accumulator
													  (a,b) -> a+b);//combine
		System.out.printf("Сумма  значений  массива LinkedList<Long> ll_long = %,d",streamLongSum);
	//ВЫЧИСЛИМ ПРОИЗВЕДЕНИЕ ВСЕХ ЗНАЧЕНИЙ Кубических корней МАССИВА (Другой способ. 1 - когда умножают. 0 - когда складывают)
	//****************ПРИ ПАРАЛЛЕЛЬНЫХ ВЫЧИСЛЕНИЯ ДАННЫЕ БЕРУТCЯ ДРУГИЕ***************
	Stream<Long> cbrtROOTprod=ll_long.parallelStream().map( (i) -> {
							if(i==0)
								i=1L;
							return (long)Math.cbrt(i); //Отобразим все в кубические корни
	});
	long longProduct=cbrtROOTprod.reduce(1L,           		   									   //identity
													(a,b) -> a*b,
													(a,b) -> a*b );    	   									  
	System.out.printf("\nПроизведение кубических корней массива LinkedList<Long> ll_long = %,d", longProduct);
	long t2=System.currentTimeMillis();
	long t2nano=System.nanoTime();
	System.out.print("\nВремя потраченное на сортировку с помощью потока Parallel Stream<Integer>:"+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд\\");
	RUNNER.nano=(t2nano-t1nano);
	
	return (t2-t1);
}
	
}
