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
		System.out.println("\n----------------------------\n������������ ������ ll_stream_double: ");
		for(int i=0;i<10;i++)
			System.out.format("|%.3f|", ll_stream_double.get(i));
		
		new RUNNER(this,jtf,9);
	}
public long Compute() {
	System.out.println("\n----------------------------\n���������� � ������ reduce() � ������� ������ Stream<Double>: (Test 9)");
	long t1=System.currentTimeMillis();
	long t1nano=System.nanoTime();
	//�������� ����� ���� �������� ������� 
		Optional<Double> streamDoubleSum=ll_stream_double.stream().reduce( (a, b) -> (a+b) );
		if(streamDoubleSum.isPresent()) //��� ��� ������� �� ������� int - ��������� long ���
			System.out.printf("�����  ��������   ������� LinkedList<Double> ll_stream_double = %,.6f",streamDoubleSum.get());
		//�������� ����� ���� �������� ���������� ������ ������� (������ ������. 1 - ����� ��������. 0 - ����� ����������)
		double doubleProduct=ll_stream_double.stream().reduce(0.0, (a, b) -> 
																			(a+Math.cbrt(b)) );
			System.out.printf("\n����� �������� ���������� ������  ������� LinkedList<Double> ll_stream_double = %,.6f", doubleProduct);
	long t2=System.currentTimeMillis();
	long t2nano=System.nanoTime();
	System.out.print("\n����� ����������� �� ���������� � ������� ������ Stream<Double>:"+(t2-t1)+" ����������\\("+(t2nano-t1nano)+")����������\\");
	RUNNER.nano=(t2nano-t1nano);

	return (t2-t1);
	}	
}