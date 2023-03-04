package BenchMark;

import javax.swing.JTextField;
import java.util.*;
public class SingleCPU1 implements SingleMultiCPU{

		private JTextField jtf;
		private int[] a,orig_a;
		
		SingleCPU1(int[] a,JTextField j){
			orig_a=a;
			jtf=j;
			this.a=Arrays.copyOf(orig_a, orig_a.length);
			//первые 10
			System.out.println("----------------------------\nИсходная последовательность: ");
			for(int i=0;i<10;i++)
				System.out.print(a[i]+" ");
			
			//А теперь просчитаем среднее время выполнения после 10 запусков метода Compute()
			new RUNNER(this,jtf,1);
		}
	public long Compute() {	
	//запустим сортировку Методом Пузырька
			System.out.println("\n----------------------------\nОтсортированная последовательность методом пузырька:(Test 1) ");
	//подсчет времени выполнения алгоритма ПУЗЫРЬКА / Половину отсортируем по возрастанию, а половину по убыванию
			int temp;
			long t1=System.currentTimeMillis();
			long t1nano=System.nanoTime();
			//разделим на 4 части и будем сортировать по очередно по убыванию и по возрастанию!!!!!!!
			//По убыванию часть 1
			for(int i=0;i<(a.length/4-1);i++)
				for(int j=(i+1);j<a.length/4;j++) { 
					if(a[j]<a[i]) {
						temp=a[i];
						a[i]=a[j];
						a[j]=temp;
					}//end if
				}//end for j
			//По возрастанию Часть 2
			for(int i=a.length/4;i<(a.length/2-1);i++)
				for(int j=(i+1);j<a.length/2;j++) { 
					if(a[j]>a[i]) {
						temp=a[i];
						a[i]=a[j];
						a[j]=temp;
					}//end if
				}//end for j
			//По убыванию Часть 3
			for(int i=a.length/2;i<(a.length*3/4-1);i++)
				for(int j=(i+1);j<a.length*3/4;j++) { 
					if(a[j]<a[i]) {
						temp=a[i];
						a[i]=a[j];
						a[j]=temp;
					}//end if
				}//end for j
			//По возрастанию Часть 4
			for(int i=a.length*3/4;i<(a.length-1);i++)
				for(int j=(i+1);j<a.length;j++) { 
					if(a[j]>a[i]) {
						temp=a[i];
						a[i]=a[j];
						a[j]=temp;
					}//end if
				}//end for j
			long t2=System.currentTimeMillis();
			long t2nano=System.nanoTime();
			for(int i=50_000;i<50_005;i++)
				System.out.print(a[i]+" ");
			for(int i=50_500;i<50_505;i++)
				System.out.print(a[i]+" ");
			
			System.out.println("\nВремя потраченное на метод пузырька в мили-секундах: "+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд");
			RUNNER.nano=(t2nano-t1nano);
			a=null;
			a=Arrays.copyOf(orig_a, orig_a.length);
			return (t2-t1);
		}//end Compute
}