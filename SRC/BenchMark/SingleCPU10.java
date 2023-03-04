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
		//������ 10
		System.out.println("----------------------------\n�������� ������������������: ");
		for(int i=0;i<10;i++)
			System.out.print(a[i]+" ");
		//� ������ ���������� ������� ����� ���������� ����� 10 �������� ������ Compute()
		
		new RUNNER(this,jtf,10);
	}
private void quickSort(int[] a,int low,int high) {//������ �� 100_000 ���������. �������� �� 0 �� 1000
		if(a.length==0 || low>=high) //��������� ���� ������ ���� �� 1 �������� ��� ����� ��� ������ ������ ������
			return;
		//�������� ������� �������
		int middle=low+(high-low)/2;
		int border=a[middle];
		//��������� �� ���������� (�� ������ �� ���� > middle � < middle )� ������ �������
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
		//�������� ��� ���������� ����� � ������ �����
		if(low < j) quickSort(a,low,j);
		if(high > i) quickSort(a,i,high);
		
}
public	long Compute() {	
//�������� ���������� ������� ��������
		System.out.println("\n----------------------------\n��������������� ������������������ ������� QuickSort. ����� �����: (Test 10)");
//������� ������� ���������� ��������� ��������
		long t1=System.currentTimeMillis();
		long t1nano=System.nanoTime();
//**************��� ���������� ������� �����****************
		quickSort(a,0,(a.length-1) );
//**************/��� ���������� ������� �����****************		
		long t2=System.currentTimeMillis();
		long t2nano=System.nanoTime();
		for(int i=50_000;i<50_010;i++)
			System.out.print(a[i]+" ");
		System.out.println("\n����� ����������� �� ����� �������� � ����-��������: "+(t2-t1)+" ����������\\("+(t2nano-t1nano)+")����������");
		RUNNER.nano=(t2nano-t1nano);
		a=null;
		a=Arrays.copyOf(orig_a, orig_a.length);
		return (t2-t1);
	}//end Compute
}