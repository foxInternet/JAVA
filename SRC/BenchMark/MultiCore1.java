package BenchMark;
import java.util.concurrent.*;

public class MultiCore1 extends RecursiveAction{
	private static final long serialVersionUID = -4691702118210558157L;
	private int seqThresHold=25_000;
	private int[] a;
	private int start,end;
	
	MultiCore1(int[] a,int s,int e){
		this.a=a;
		start=s;
		end=e;
	}
	@Override
	//������ int[100_000] �������� �� 4 �����. �������� ����� �� ����������� - ������ ����� �� �������� �����������
	protected void compute() {
		if( (end-start)<=seqThresHold) {
			if(start==0 || start==50_000){
				int tmp;
				for(int i=start;i<(end-1);i++)//������ ��������� ������� �� �����������
					for(int j=(i+1);j<end;j++) {
						if(a[j]<a[i]) {
							tmp=a[i];
							a[i]=a[j];
							a[j]=tmp;
						}//end if
					}//end for j
			}else if(start==25_000 || start==75_000) {
				int tmp;
				for(int i=start;i<(end-1);i++)//������ ��������� �� ��������
					for(int j=(i+1);j<end;j++) {
						if(a[j]>a[i]) {
							tmp=a[i];
							a[i]=a[j];
							a[j]=tmp;
						}//end if
					}//end for j
			}
		}else {
				int middle=(start+end)/2;
				//����������� ���� ��� 4-� ������� ����������� - ��������!!!
				invokeAll(new MultiCore1(a,start,middle),new MultiCore1(a,middle,end));
			}
		}//end compute
}