package BenchMark;

import java.util.LinkedList;

public class Array_Bases {
	//создадим сразу статичный массив и заполним его случайными числами от 0 до 100
	private int[] a=new int[100_000];
	private double[] double_a=new double[10_000_000];
	private LinkedList<Integer> ll=new LinkedList<>();
	private LinkedList<Double> ll_double=new LinkedList<>();
	static final int pass=10;//10 проходов
	
	Array_Bases() { 	
		for(int x=0;x<a.length;x++) {
			a[x]=( (int)(Math.random()*1000))+1;
			ll.add(a[x]);
		}
		for(int i=0;i<double_a.length;i++) 
			double_a[i]=(Math.random()*1000);
		
		for(int i=0;i<double_a.length;i++)
			ll_double.add(double_a[i]);
	}//end static
	int[] get_a() {return a;}
	double[] get_double() {return double_a;}
	LinkedList<Integer> get_ll(){return ll;}
	LinkedList<Double> get_ll_double(){return ll_double;}
	int get_pass() {return pass;}
}
