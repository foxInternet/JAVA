package BenchMark;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
//import java.util.stream.Stream;
import javax.swing.JTextField;

public class SingleCPU8 implements SingleMultiCPU{
	private JTextField jtf;
	private LinkedList<Integer> ll_stream_int;
	private LinkedList<Long> ll_long=new LinkedList<Long>();
	int countZero=0;
	SingleCPU8(LinkedList<Integer> ll,JTextField j){
		ll_stream_int=ll;
		ll_stream_int.forEach( (x) -> ll_long.add((long)x));
		jtf=j;
		System.out.println("\n----------------------------\nОригинальный массив ll_stream_long: ");
		for(int i=0;i<10;i++)
			System.out.print(ll_long.get(i)+" ");
		
		for(Long i:ll_long)
			if(i==0L)
				countZero++;
		System.out.println(" Нулевых значений : "+countZero);
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
	//Optional<Long> streamLongSum=ll_long.stream().reduce( (a, b) -> (long)(a+b) );
	long streamLongSum=ll_long.stream().reduce(0L, (a, b) -> (long)(a+b) );
	//if(streamLongSum.isPresent()) //так как выходит за пределы int - применяем long тип
		System.out.printf("Сумма  значений  массива LinkedList<Long> ll_long = %,d",streamLongSum);
	//ВЫЧИСЛИМ ПРОИЗВЕДЕНИЕ ВСЕХ ЗНАЧЕНИЙ кв корней в степени 3 МАССИВА (Другой способ. 1 - когда умножают. 0 - когда складывают)
	Stream<Double> ROOTprod=ll_long.stream().map( (i) ->  Math.sqrt(Math.sqrt(i)) ); //Отобразим все в кубические корни
	//LongStream cbrtROOTprod=ll_long.stream().mapToLong( (i) -> {
	//												if(i==0)
	//													return 1;
	//												return (long)Math.sqrt(Math.sqrt(i));
	//												});
	//Optional<Long> longProduct=ROOTprod.reduce((a, b) -> {
	double doubleProduct=ROOTprod.reduce(1.0,(a, b) -> ((a*b)/1_00)+Math.PI);
	// <R, А> R collect(Collector<? super Т,А,R> функция_накопления)
	// interface Collector<T,А,R>
	// static <Т> Collector<T,?,List<T>> t oList()
	// static <Т> Collector<T,?,Set<T>> toSet() 
	// А теперь сделаем накопление из потока в Списке ArrayList и в Множестве TreeSet
	Stream<Long> ROOTprod2=ll_long.stream().map( (i) -> (long)Math.sqrt(i) );
	Set<Long> mySet=ROOTprod2.collect(Collectors.toSet());  //финальная операция
	TreeSet<Long> ts=new TreeSet<>(mySet);
	
	ROOTprod2=ll_long.stream().map( (i) -> (long)Math.sqrt(i) );
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
	System.out.print("\nВремя потраченное на сортировку с помощью потока Stream<Integer>:"+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд\\");
	RUNNER.nano=(t2nano-t1nano);
	
	return (t2-t1);
	}
	
}

