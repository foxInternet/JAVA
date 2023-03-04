package BenchMark;

import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import javax.swing.JTextField;

public class MultiCPU20 implements SingleMultiCPU{
	private JTextField jtf;
	private int[] a,orig_a;
	
	MultiCPU20(int[] a,JTextField j){
		orig_a=a;
		this.a=Arrays.copyOf(orig_a, orig_a.length);
		jtf=j;
		//первые 10
		System.out.println("----------------------------\nИсходная последовательность: ");
		for(int i=0;i<10;i++)
			System.out.print(a[i]+" ");
		//А теперь просчитаем среднее время выполнения после 10 запусков метода Compute()
		
		new RUNNER(this,jtf,20);
	}

public	long Compute() {	
//запустим сортировку Методом Пузырька
		System.out.println("\n----------------------------\nОтсортированная последовательность методом Fork/Join framework QuickSort. Метод Хоара: (Test 20)");
		ForkJoinPool fjp=new ForkJoinPool();
		MultiCore10 task=new MultiCore10(a,0,a.length-1);
//подсчет времени выполнения алгоритма ПУЗЫРЬКА
		long t1=System.currentTimeMillis();
		long t1nano=System.nanoTime();
//**************Тут сортировка методом Хоара****************
		fjp.invoke(task);
//**************/Тут сортировка методом Хоара****************		
		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
		for(int i=50_000;i<50_010;i++)
			System.out.print(a[i]+" ");
		System.out.println("\nВремя потраченное на метод пузырька использя Fork/Join framework в мили-секундах: "+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд");
		RUNNER.nano=(t2nano-t1nano);
		a=null;
		a=Arrays.copyOf(orig_a, orig_a.length);
		return (t2-t1);
	}//end Compute
}