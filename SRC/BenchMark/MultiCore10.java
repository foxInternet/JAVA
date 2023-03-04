package BenchMark;

import java.util.concurrent.RecursiveAction;
public class MultiCore10 extends RecursiveAction{

	private static final long serialVersionUID = -6122857378488059312L;
	private int[] a;
	private int low,high;
	MultiCore10(int[] a,int l,int h){
		this.a=a;
		low=l;
		high=h;
	}
	@Override
	protected void compute() {
				if(a.length==0 || low>=high) //��������� ���� ������ ���� �� 1 �������� ��� ����� ��� ������ ������ ������
					return;
				//�������� ������� �������
				int border=a[low+(high-low)/2];
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
				if(low < j && high > i) 
					invokeAll(new MultiCore10(a,low,j),new MultiCore10(a,i,high));
				else if(low < j) invokeAll(new MultiCore10(a,low,j) );
				else if(high > i) invokeAll(new MultiCore10(a,i,high) );
		
	}//end compute
}
