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
			//������ 10
			System.out.println("----------------------------\n�������� ������������������: ");
			for(int i=0;i<10;i++)
				System.out.print(a[i]+" ");
			
			//� ������ ���������� ������� ����� ���������� ����� 10 �������� ������ Compute()
			new RUNNER(this,jtf,1);
		}
	public long Compute() {	
	//�������� ���������� ������� ��������
			System.out.println("\n----------------------------\n��������������� ������������������ ������� ��������:(Test 1) ");
	//������� ������� ���������� ��������� �������� / �������� ����������� �� �����������, � �������� �� ��������
			int temp;
			long t1=System.currentTimeMillis();
			long t1nano=System.nanoTime();
			//�������� �� 4 ����� � ����� ����������� �� �������� �� �������� � �� �����������!!!!!!!
			//�� �������� ����� 1
			for(int i=0;i<(a.length/4-1);i++)
				for(int j=(i+1);j<a.length/4;j++) { 
					if(a[j]<a[i]) {
						temp=a[i];
						a[i]=a[j];
						a[j]=temp;
					}//end if
				}//end for j
			//�� ����������� ����� 2
			for(int i=a.length/4;i<(a.length/2-1);i++)
				for(int j=(i+1);j<a.length/2;j++) { 
					if(a[j]>a[i]) {
						temp=a[i];
						a[i]=a[j];
						a[j]=temp;
					}//end if
				}//end for j
			//�� �������� ����� 3
			for(int i=a.length/2;i<(a.length*3/4-1);i++)
				for(int j=(i+1);j<a.length*3/4;j++) { 
					if(a[j]<a[i]) {
						temp=a[i];
						a[i]=a[j];
						a[j]=temp;
					}//end if
				}//end for j
			//�� ����������� ����� 4
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
			
			System.out.println("\n����� ����������� �� ����� �������� � ����-��������: "+(t2-t1)+" ����������\\("+(t2nano-t1nano)+")����������");
			RUNNER.nano=(t2nano-t1nano);
			a=null;
			a=Arrays.copyOf(orig_a, orig_a.length);
			return (t2-t1);
		}//end Compute
}