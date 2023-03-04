package BenchMark;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.JTextField;

public class MultiCPU14 implements SingleMultiCPU{
	private JTextField jtf;
	private int[] copy_a;
	private int lambdaCount1=0;
	MultiCPU14(int[] copy,JTextField j){
		copy_a=copy;
		jtf=j;
		System.out.println("\n----------------------------\nОригинальный массив copy_a14: ");
		for(int i=0;i<10;i++)
			System.out.print(copy_a[i]+" ");
		
		new RUNNER(this,jtf,14);
	}
public long Compute() {
	lambdaCount1=0;
	LinkedList<Integer> ll=new LinkedList<>();
	System.out.println("\n----------------------------\nОтсортированнная последовательность с помощью Parallel потока Stream (IntStream - для примитивных потоков данных) (Test 14): ");
	long t1=System.currentTimeMillis();
	long t1nano=System.nanoTime();		
	
	//**************ВАЖНО!!!!*****ПОТОКИ не меняют объекты, то есть copy_a останется неизменным!!!!!*******************
	long si=Arrays.stream(copy_a).parallel().sum();   					  //Промежуточная операция.Сохранение состояния. Сразу не выполняется
	IntStream streamInt=Arrays.stream(copy_a).parallel().sorted();		  //отсортируем
	Stream<Integer> st=Arrays.stream(copy_a).boxed().parallel().sorted();//Конвертируем из примитива в объект Integer
	st.forEachOrdered( (i) -> ll.add(i) ); 					  //Заполним массив LinkedList

	long t2=System.currentTimeMillis();
	long t2nano=System.nanoTime();
	streamInt.forEachOrdered(									  //Конечная операция. Идет финальное выполнение всех операций
			(n)->{
				if(lambdaCount1>=50_000 && lambdaCount1<50_010)
					System.out.print(n+" ");
				lambdaCount1++;
			}
					);
	System.out.print("\nВремя потраченное на сортировку с помощью Parallel потока IntStream:"+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд. SUM="+si);
	RUNNER.nano=(t2nano-t1nano);

	return (t2-t1);
}
	
}