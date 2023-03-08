package BenchMark;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

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
	Stream<Double> ROOTprod=ll_long.parallelStream().map( (i) -> Math.sqrt(Math.sqrt(i)) ); //Отобразим все в кубические корни
	//LongStream cbrtROOTprod=ll_long.parallelStream().mapToLong( (i) -> {
	//															if(i==0)
	//																return 1;
	//															return (long)Math.sqrt(Math.sqrt(i));
	//															}); //Отобразим все в кубические корни
	//long longProduct=cbrtROOTprod.reduce((a,b) -> {1L,(a,b) -> a*b, (a,b) -> a*b );
	double doubleProduct=ROOTprod.reduce(1.0,(a,b) ->  ((a*b)/1_00)+Math.PI, (a,b) -> a*b );	
	// <R, А> R collect(Collector<? super Т,А,R> функция_накопления)
		// interface Collector<T,А,R>
		// static <Т> Collector<T,?,List<T>> t oList()
		// static <Т> Collector<T,?,Set<T>> toSet() 
		// А теперь сделаем накопление из потока в Списке ArrayList и в Множестве TreeSet
		Stream <Long> ROOTprod2=ll_long.parallelStream().map( (i) -> (long)Math.sqrt(i) );
		Set<Long> mySet=ROOTprod2.collect(Collectors.toSet());  //финальная операция
		TreeSet<Long> ts=new TreeSet<>(mySet);
		
		ROOTprod2=ll_long.parallelStream().map( (i) -> (long)Math.sqrt(i) );
		List<Long> myList=ROOTprod2.collect(Collectors.toList());//финальная операция
		ArrayList<Long> al=new ArrayList<>(myList);
		
		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
		System.out.printf("\nПроизведение  квадратных корней по формуле ((a*b)/1_00)+Math.PI  потока Stream<Double> = %,f", doubleProduct);
		System.out.println("\nЗначения ArrayList<> после аккумулирования из потока: ");
			for(int i=0;i<40;i++) 
				System.out.print(al.get(i)+" ");
		System.out.println("\nЗначения TreeSet<> после аккумулирования из потока: ");
			Iterator<Long> it=ts.iterator(); //descendingIterator() - Обратный отсчет
			int c=0;
			while(it.hasNext()) {
				if(c>=0 && c<40)
					System.out.print(it.next()+" ");
				c++;
			}
	System.out.print("\nВремя потраченное на сортировку с помощью потока Parallel Stream<Integer>:"+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд\\");
	RUNNER.nano=(t2nano-t1nano);
	
	return (t2-t1);
}
	
}
