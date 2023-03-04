package BenchMark;

import java.util.LinkedList;
import javax.swing.JTextField;

public class MultiCPU19 implements SingleMultiCPU{
	private JTextField jtf;
	private LinkedList<Double> ll_stream_double;
	MultiCPU19(LinkedList<Double> ll,JTextField j){
		ll_stream_double=ll;
		jtf=j;
		System.out.println("\n----------------------------\n������������ ������ Parallel ll_stream_double: ");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", ll_stream_double.get(i));
		
		new RUNNER(this,jtf,19);
	}
public long Compute() {
	System.out.println("\n----------------------------\n���������� � ������������ ������ reduce � ������� ������ Parallel Stream<Double>.reduce(): (Test 19)");
	long t1=System.currentTimeMillis();
	long t1nano=System.nanoTime();
	//�������� ����� ���� �������� ������� 	
	double streamDoubleSum=ll_stream_double.parallelStream().reduce(0.0, 			//Identity 		
																   (a,b) -> (a+b),	//Accumulator
																   (a,b) -> (a+b));	//Combine
	
		System.out.printf("�����  ��������   ������� LinkedList<Double> ll_stream_double = %,.6f",streamDoubleSum);
	//�������� ����� ���� �������� ���������� ������ ������� (������ ������. 1 - ����� ��������. 0 - ����� ����������)
	double doubleProduct=ll_stream_double.parallelStream().reduce(0.0,
																   (a,b) -> 
																	   	(a+Math.cbrt(b)),
																   (a,b) -> a+b);
		System.out.printf("\n����� �������� ����������  ������  ������� LinkedList<Double> ll_stream_double = %,.6f", doubleProduct);
	long t2=System.currentTimeMillis();
	long t2nano=System.nanoTime();
	System.out.print("\n����� ����������� �� ���������� � ������� ������ Parallel Stream<Double>:"+(t2-t1)+" ����������\\("+(t2nano-t1nano)+")����������\\");
	RUNNER.nano=(t2nano-t1nano);
	
	return (t2-t1);
}	
}