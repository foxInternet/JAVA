package BenchMark;

import java.util.LinkedList;
import java.util.Optional;
import javax.swing.JTextField;


public class SingleCPU9 implements SingleMultiCPU{
	private JTextField jtf;
	private LinkedList<Double> ll_stream_double;
	SingleCPU9(LinkedList<Double> ll,JTextField j){
		ll_stream_double=ll;
		jtf=j;
		System.out.println("\n----------------------------\nОригинальный массив ll_stream_double: ");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", ll_stream_double.get(i));
		
		new RUNNER(this,jtf,9);
	}
public long Compute() {
	System.out.println("\n----------------------------\nВычисления в потоке reduce() с помощью потока Stream<Double>: (Test 9)");
	long t1=System.currentTimeMillis();
	long t1nano=System.nanoTime();
	//ВЫЧИСЛИМ СУММУ ВСЕХ ЗНАЧЕНИЙ МАССИВА 
		Optional<Double> streamDoubleSum=ll_stream_double.stream().reduce( (a, b) -> (a+b) );
		if(streamDoubleSum.isPresent()) //так как выходит за пределы int - применяем long тип
			System.out.printf("Сумма  значений   массива LinkedList<Double> ll_stream_double = %,.6f",streamDoubleSum.get());
		//ВЫЧИСЛИМ СУММУ ВСЕХ ЗНАЧЕНИЙ Квадратных корней МАССИВА (Другой способ. 1 - когда умножают. 0 - когда складывают)
		double doubleProduct=ll_stream_double.stream().reduce(0.0, (a, b) -> 
																			(a+Math.cbrt(b)) );
			System.out.printf("\nСумма значений кубических корней  массива LinkedList<Double> ll_stream_double = %,.6f", doubleProduct);
	long t2=System.currentTimeMillis();
	long t2nano=System.nanoTime();
	System.out.print("\nВремя потраченное на сортировку с помощью потока Stream<Double>:"+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд\\");
	RUNNER.nano=(t2nano-t1nano);

	return (t2-t1);
	}	
}