package BenchMark;

import java.util.LinkedList;
import java.util.Optional;
import java.util.stream.Stream;

import javax.swing.JTextField;

public class SingleCPU8 implements SingleMultiCPU{
	private JTextField jtf;
	private LinkedList<Integer> ll_stream_int;
	private LinkedList<Long> ll_long=new LinkedList<Long>();
	SingleCPU8(LinkedList<Integer> ll,JTextField j){
		ll_stream_int=ll;
		ll_stream_int.forEach( (x) -> ll_long.add((long)x));
		jtf=j;
		System.out.println("\n----------------------------\nОригинальный массив ll_stream_long: ");
		for(int i=0;i<10;i++)
			System.out.print(ll_long.get(i)+" ");	
		
		new RUNNER(this,jtf,8);
	}
public long Compute() {
	/* 2 ВАРИАНТА reduce
	 * 		Optional<T> reduce ( B inaryOperator<T> накопитель )
			Т reduce(T значение_идентичности , BinaryOperator<T> накопитель) 
	 */
	System.out.println("\n----------------------------\nВычисления в потоке reduce() с помощью потока Stream<Long>: (Test 8)");
	long t1=System.currentTimeMillis();
	long t1nano=System.nanoTime(); 
	//ВЫЧИСЛИМ СУММУ ВСЕХ ЗНАЧЕНИЙ МАССИВА 
	Optional<Long> streamLongSum=ll_long.stream().reduce( (a, b) -> (long)(a+b) );
	if(streamLongSum.isPresent()) //так как выходит за пределы int - применяем long тип
		System.out.printf("Сумма  значений  массива LinkedList<Long> ll_long = %,d",streamLongSum.get());
	//ВЫЧИСЛИМ ПРОИЗВЕДЕНИЕ ВСЕХ ЗНАЧЕНИЙ Кубических корней МАССИВА (Другой способ. 1 - когда умножают. 0 - когда складывают)
	Stream<Long> cbrtROOTprod=ll_long.stream().map( (i) -> {
		if((long)Math.cbrt(i)==0)
			return 1L;
		else
			return (long)Math.cbrt(i); //Отобразим все в кубические корни
	});
	long longProduct=cbrtROOTprod.reduce(1L,(a, b) -> a*b);
		System.out.printf("\nПроизведение кубических корней массива LinkedList<Long> ll_long = %,d", longProduct);
	long t2=System.currentTimeMillis();
	long t2nano=System.nanoTime();
	System.out.print("\nВремя потраченное на сортировку с помощью потока Stream<Integer>:"+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд\\");
	RUNNER.nano=(t2nano-t1nano);
	
	return (t2-t1);
	}
	
}

