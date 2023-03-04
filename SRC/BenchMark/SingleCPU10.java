package BenchMark;

import java.util.Arrays;

import javax.swing.JTextField;

public class SingleCPU10 implements SingleMultiCPU{
	private JTextField jtf;
	private int[] a,orig_a;
	
	SingleCPU10(int[] a,JTextField j){
		orig_a=a;
		this.a=Arrays.copyOf(orig_a, orig_a.length);
		jtf=j;
		//первые 10
		System.out.println("----------------------------\nИсходная последовательность: ");
		for(int i=0;i<10;i++)
			System.out.print(a[i]+" ");
		//А теперь просчитаем среднее время выполнения после 10 запусков метода Compute()
		
		new RUNNER(this,jtf,10);
	}
private void quickSort(int[] a,int low,int high) {//массив из 100_000 элементов. Значения от 0 до 1000
		if(a.length==0 || low>=high) //завершить если массив сост из 1 элемента или когда уже больше нечего делить
			return;
		//выбираем опорный элемент
		int middle=low+(high-low)/2;
		int border=a[middle];
		//разделяем на подмассивы (на массив со знач > middle и < middle )и меняем местами
		int i=low;
		int j=high;
		while(i<=j) {
			while(a[i]<border) i++;
			while(a[j]>border) j--;
			if(i<=j) {
				int swap=a[i];
				a[i]=a[j];
				a[j]=swap;
				i++;
				j--;
			}
		}
		//рекурсия для сортировки левой и правой части
		if(low < j) quickSort(a,low,j);
		if(high > i) quickSort(a,i,high);
		
}
public	long Compute() {	
//запустим сортировку Методом Пузырька
		System.out.println("\n----------------------------\nОтсортированная последовательность методом QuickSort. Метод Хоара: (Test 10)");
//подсчет времени выполнения алгоритма ПУЗЫРЬКА
		long t1=System.currentTimeMillis();
		long t1nano=System.nanoTime();
//**************Тут сортировка методом Хоара****************
		quickSort(a,0,(a.length-1) );
//**************/Тут сортировка методом Хоара****************		
		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
		for(int i=50_000;i<50_010;i++)
			System.out.print(a[i]+" ");
		System.out.println("\nВремя потраченное на метод пузырька в мили-секундах: "+(t2-t1)+" милисекунд\\("+(t2nano-t1nano)+")наносекунд");
		RUNNER.nano=(t2nano-t1nano);
		a=null;
		a=Arrays.copyOf(orig_a, orig_a.length);
		return (t2-t1);
	}//end Compute
}