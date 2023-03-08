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
		System.out.println("\n----------------------------\n������������ ������ ll_stream_long: ");
		for(int i=0;i<10;i++)
			System.out.print(ll_long.get(i)+" ");
		
		for(Long i:ll_long)
			if(i==0L)
				countZero++;
		System.out.println(" ������� �������� : "+countZero);
		new RUNNER(this,jtf,8);
	}
public long Compute() {
	/* 2 �������� reduce
	 * 		Optional<T> reduce ( B inaryOperator<T> ���������� )
			� reduce(T ��������_������������ , BinaryOperator<T> ����������) 
	 */
	System.out.println("\n----------------------------\n���������� � ������ reduce() � ������� ������ Stream<Long>: (Test 8)");
	long t1=System.currentTimeMillis();
	long t1nano=System.nanoTime(); 
	//�������� ����� ���� �������� ������� 
	//Optional<Long> streamLongSum=ll_long.stream().reduce( (a, b) -> (long)(a+b) );
	long streamLongSum=ll_long.stream().reduce(0L, (a, b) -> (long)(a+b) );
	//if(streamLongSum.isPresent()) //��� ��� ������� �� ������� int - ��������� long ���
		System.out.printf("�����  ��������  ������� LinkedList<Long> ll_long = %,d",streamLongSum);
	//�������� ������������ ���� �������� �� ������ � ������� 3 ������� (������ ������. 1 - ����� ��������. 0 - ����� ����������)
	Stream<Double> ROOTprod=ll_long.stream().map( (i) ->  Math.sqrt(Math.sqrt(i)) ); //��������� ��� � ���������� �����
	//LongStream cbrtROOTprod=ll_long.stream().mapToLong( (i) -> {
	//												if(i==0)
	//													return 1;
	//												return (long)Math.sqrt(Math.sqrt(i));
	//												});
	//Optional<Long> longProduct=ROOTprod.reduce((a, b) -> {
	double doubleProduct=ROOTprod.reduce(1.0,(a, b) -> ((a*b)/1_00)+Math.PI);
	// <R, �> R collect(Collector<? super �,�,R> �������_����������)
	// interface Collector<T,�,R>
	// static <�> Collector<T,?,List<T>> t oList()
	// static <�> Collector<T,?,Set<T>> toSet() 
	// � ������ ������� ���������� �� ������ � ������ ArrayList � � ��������� TreeSet
	Stream<Long> ROOTprod2=ll_long.stream().map( (i) -> (long)Math.sqrt(i) );
	Set<Long> mySet=ROOTprod2.collect(Collectors.toSet());  //��������� ��������
	TreeSet<Long> ts=new TreeSet<>(mySet);
	
	ROOTprod2=ll_long.stream().map( (i) -> (long)Math.sqrt(i) );
	List<Long> myList=ROOTprod2.collect(Collectors.toList());//��������� ��������
	ArrayList<Long> al=new ArrayList<>(myList);
	
	long t2=System.currentTimeMillis();
	long t2nano=System.nanoTime();
	System.out.printf("\n������������  ���������� ������ �� ������� ((a*b)/1_00)+Math.PI  ������ Stream<Double> = %,f", doubleProduct);
	System.out.println("\n�������� ArrayList<> ����� ��������������� �� ������: ");
		for(int i=0;i<40;i++) 
			System.out.print(al.get(i)+" ");
	System.out.println("\n�������� TreeSet<> ����� ��������������� �� ������: ");
		Iterator<Long> it=ts.iterator(); //descendingIterator() - �������� ������
		int c=0;
		while(it.hasNext()) {
			if(c>=0 && c<40)
				System.out.print(it.next()+" ");
			c++;
		}
	System.out.print("\n����� ����������� �� ���������� � ������� ������ Stream<Integer>:"+(t2-t1)+" ����������\\("+(t2nano-t1nano)+")����������\\");
	RUNNER.nano=(t2nano-t1nano);
	
	return (t2-t1);
	}
	
}

