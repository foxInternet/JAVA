package BenchMark;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import javax.swing.JTextField;

public class SingleCPU4 implements SingleMultiCPU{
	private JTextField jtf;
	private int[] copy_a;
	private int lambdaCount1=0;
	SingleCPU4(int[] copy,JTextField j){
		copy_a=copy;
		jtf=j;
		System.out.println("\n----------------------------\n������������ ������ copy_a4: ");
		for(int i=0;i<10;i++)
			System.out.print(copy_a[i]+" ");
		
		new RUNNER(this,jtf,4);
	}
public long Compute() {
	lambdaCount1=0;
	LinkedList<Integer> ll=new LinkedList<>();
	System.out.println("\n----------------------------\n���������������� ������������������ � ������� ������ Stream (IntStream - ��� ����������� ������� ������): (Test 4)");
	long t1=System.currentTimeMillis();
	long t1nano=System.nanoTime();				
	
	//**************�����!!!!*****������ �� ������ �������, �� ���� copy_a ��������� ����������!!!!!*******************
	long si=Arrays.stream(copy_a).sum();   					  //������������� ��������.���������� ���������. ����� �� �����������
	IntStream streamInt=Arrays.stream(copy_a).sorted();		  //�����������
	Stream<Integer> st=Arrays.stream(copy_a).boxed().sorted();//������������ �� ��������� � ������ Integer
	st.forEachOrdered( (i) -> ll.add(i) ); 					  //�������� ������ LinkedList
	
	long t2=System.currentTimeMillis();
	long t2nano=System.nanoTime();
	
	streamInt.forEachOrdered(									  //�������� ��������. ���� ��������� ���������� ���� ��������
			(n)->{
				if(lambdaCount1>=50_000 && lambdaCount1<50_010)
					System.out.print(n+" ");
				lambdaCount1++;
			}
					);
	
	System.out.print("\n����� ����������� �� ���������� � ������� Parallel ������ IntStream:"+(t2-t1)+" ����������\\("+(t2nano-t1nano)+")����������. SUM="+si);
	RUNNER.nano=(t2nano-t1nano);

	return (t2-t1);
}
	
}
