package BenchMark;

import java.awt.Color;
import java.util.LinkedList;
import javax.swing.JTextField;

public class sequential_exec implements Runnable{
	private JTextField jtf;
	private Thread thrd;
	private statusThread thrdObj;
	private double[] double_a;
	private LinkedList<Integer> ll_int,ll_copy;
	private LinkedList<Integer> ll_stream_int=new LinkedList<>();
	private LinkedList<Double>  ll_stream_double,ll_stream_double_orig;
	private int[] a;
	
	sequential_exec(){
		Array_Bases obj=new Array_Bases();//��������� ������������ ��������
		
		a=obj.get_a();
		ll_int=obj.get_ll();
		ll_copy=new LinkedList<>(ll_int);
		double_a=obj.get_double();
		ll_int.stream().forEach( (x) -> ll_stream_int.add(x)); //������� ����� LinkedList<Integer> ll ����� �����
		//for(int i=0;i<ll_int.size();i++)
		//	ll_stream_int.add(ll_int.get(i));
		ll_stream_double_orig=obj.get_ll_double();
		ll_stream_double=new LinkedList<>(ll_stream_double_orig);
		thrdObj=new statusThread(jtf);
		thrd=new Thread(thrdObj,"����1");
	}
	public void run() {
//***************������ �������� ����#1 SingleCPU1****************
		flags.end=false;
		start.tf11.setForeground(Color.RED);
		thrdObj=new statusThread(start.tf11);
		thrd=new Thread(thrdObj,"����1");
		thrd.start();
		new SingleCPU1(a,start.tf11);
		start.tf11.setForeground(Color.green);
//***************������ �������� ����#2 SingleCPU2****************
		flags.end=false;
		start.tf12.setForeground(Color.RED);
		thrdObj=new statusThread(start.tf12);
		thrd=new Thread(thrdObj,"����2");
		thrd.start();
		new SingleCPU2(ll_copy,start.tf12);
		start.tf12.setForeground(Color.green);
//***************������ �������� ����#3 SingleCPU3****************
		flags.end=false;
		start.tf13.setForeground(Color.RED);
		thrdObj=new statusThread(start.tf13);
		thrd=new Thread(thrdObj,"����3");
		thrd.start();
		new SingleCPU3(a,start.tf13);
		start.tf13.setForeground(Color.green);
//***************������ �������� ����#4 SingleCPU4****************
		flags.end=false;
		start.tf14.setForeground(Color.RED);
		thrdObj=new statusThread(start.tf14);
		thrd=new Thread(thrdObj,"����4");
		thrd.start();
		new SingleCPU4(a,start.tf14);
		start.tf14.setForeground(Color.green);
//***************������ �������� ����#5 SingleCPU5****************
		flags.end=false;
		start.tf15.setForeground(Color.RED);
		thrdObj=new statusThread(start.tf15);
		thrd=new Thread(thrdObj,"����5");
		thrd.start();
		new SingleCPU5(double_a,start.tf15);
		start.tf15.setForeground(Color.green);
//***************������ �������� ����#6 SingleCPU6****************		
		flags.end=false;
		start.tf16.setForeground(Color.RED);
		thrdObj=new statusThread(start.tf16);
		thrd=new Thread(thrdObj,"����6");
		thrd.start();
		new SingleCPU6(double_a,start.tf16);
		start.tf16.setForeground(Color.green);
//****************************************************************
//***************������ �������� ����#7 SingleCPU6****************		
		flags.end=false;
		start.tf17.setForeground(Color.RED);
		thrdObj=new statusThread(start.tf17);
		thrd=new Thread(thrdObj,"����7");
		thrd.start();
		new SingleCPU7(double_a,start.tf17);
		start.tf17.setForeground(Color.green);
//****************************************************************
//***************������ �������� ����#8 SingleCPU8****************		
		flags.end=false;
		start.tf18.setForeground(Color.RED);
		thrdObj=new statusThread(start.tf18);
		thrd=new Thread(thrdObj,"����8");
		thrd.start();
		new SingleCPU8(ll_stream_int,start.tf18);
		start.tf18.setForeground(Color.green);
//****************************************************************
//***************������ �������� ����#9 SingleCPU9****************		
		flags.end=false;
		start.tf19.setForeground(Color.RED);
		thrdObj=new statusThread(start.tf19);
		thrd=new Thread(thrdObj,"����9");
		thrd.start();
		new SingleCPU9(ll_stream_double,start.tf19);
		start.tf19.setForeground(Color.green);
//****************************************************************
//***************������ �������� ����#10 SingleCPU10**************//����� �����		
		flags.end=false;
		start.tf20.setForeground(Color.RED);
		thrdObj=new statusThread(start.tf20);
		thrd=new Thread(thrdObj,"����10");
		thrd.start();
		new SingleCPU10(a,start.tf20);
		start.tf20.setForeground(Color.green);
//****************************************************************
//***************������ �������� ����#11 MultiCPU11**************//���������� ����� ��������		
		flags.end=false;
		start.tfm11.setForeground(Color.RED);
		thrdObj=new statusThread(start.tfm11);
		thrd=new Thread(thrdObj,"����11");
		thrd.start();
		new MultiCPU11(a,start.tfm11);
		start.tfm11.setForeground(Color.green);
		if(scores.get_milisec(11)<scores.get_milisec(1) || scores.get_nanosec(11)<scores.get_nanosec(1))
			start.tf11.setForeground(Color.red);
		else
			start.tfm11.setForeground(Color.red);
//****************************************************************
//***************������ �������� TECT12 MultiCPU12****************
		flags.end=false;
		start.tfm12.setForeground(Color.RED);
		thrdObj=new statusThread(start.tfm12);
		thrd=new Thread(thrdObj,"����12");
		thrd.start();
		new MultiCPU12(ll_stream_int,start.tfm12);
		start.tfm12.setForeground(Color.green);
		if(scores.get_milisec(12)<scores.get_milisec(2) || scores.get_nanosec(12)<scores.get_nanosec(2))
			start.tf12.setForeground(Color.red);
		else
			start.tfm12.setForeground(Color.red);
//***************������ �������� TECT13 MultiCPU13****************
		flags.end=false;
		start.tfm13.setForeground(Color.RED);
		thrdObj=new statusThread(start.tfm13);
		thrd=new Thread(thrdObj,"����13");
		thrd.start();
		new MultiCPU13(a,start.tfm13);
		start.tfm13.setForeground(Color.green);
		if(scores.get_milisec(13)<scores.get_milisec(3) || scores.get_nanosec(13)<scores.get_nanosec(3))
			start.tf13.setForeground(Color.red);
		else
			start.tfm13.setForeground(Color.red);
//***************������ �������� ����#14 MultiCPU14****************
		flags.end=false;
		start.tfm14.setForeground(Color.RED);
		thrdObj=new statusThread(start.tfm14);
		thrd=new Thread(thrdObj,"����14");
		thrd.start();
		new MultiCPU14(a,start.tfm14);
		start.tfm14.setForeground(Color.green);
		if(scores.get_milisec(14)<scores.get_milisec(4) || scores.get_nanosec(14)<scores.get_nanosec(4))
			start.tf14.setForeground(Color.red);
		else
			start.tfm14.setForeground(Color.red);
//***************������ �������� ����#15 MultiCPU15****************
		flags.end=false;
		start.tfm15.setForeground(Color.RED);
		thrdObj=new statusThread(start.tfm15);
		thrd=new Thread(thrdObj,"����15");
		thrd.start();
		new MultiCPU15(double_a,start.tfm15);
		start.tfm15.setForeground(Color.green);
		if(scores.get_milisec(15)<scores.get_milisec(5) || scores.get_nanosec(15)<scores.get_nanosec(5))
			start.tf15.setForeground(Color.red);
		else
			start.tfm15.setForeground(Color.red);
//****************************************************************
//***************������ �������� ����#16 MultiCPU16****************
		flags.end=false;
		start.tfm16.setForeground(Color.RED);
		thrdObj=new statusThread(start.tfm16);
		thrd=new Thread(thrdObj,"����16");
		thrd.start();
		new MultiCPU16(double_a,start.tfm16);
		start.tfm16.setForeground(Color.green);
		if(scores.get_milisec(16)<scores.get_milisec(6) || scores.get_nanosec(16)<scores.get_nanosec(6))
			start.tf16.setForeground(Color.red);
		else
			start.tfm16.setForeground(Color.red);
//****************************************************************
//***************������ �������� ����#17 MultiCPU17****************
		flags.end=false;
		start.tfm17.setForeground(Color.RED);
		thrdObj=new statusThread(start.tfm17);
		thrd=new Thread(thrdObj,"����17");
		thrd.start();
		new MultiCPU17(double_a,start.tfm17);
		start.tfm17.setForeground(Color.green);
		if(scores.get_milisec(17)<scores.get_milisec(7) || scores.get_nanosec(17)<scores.get_nanosec(7))
			start.tf17.setForeground(Color.red);
		else
			start.tfm17.setForeground(Color.red);
//****************************************************************
//***************������ �������� ����#18 MultiCPU18****************
		flags.end=false;
		start.tfm18.setForeground(Color.RED);
		thrdObj=new statusThread(start.tfm18);
		thrd=new Thread(thrdObj,"����18");
		thrd.start();
		new MultiCPU18(ll_copy,start.tfm18);
		start.tfm18.setForeground(Color.green);
		if(scores.get_milisec(18)<scores.get_milisec(8) || scores.get_nanosec(18)<scores.get_nanosec(8))
			start.tf18.setForeground(Color.red);
		else
			start.tfm18.setForeground(Color.red);
//****************************************************************
//***************������ �������� ����#19 MultiCPU19****************
		flags.end=false;
		start.tfm19.setForeground(Color.RED);
		thrdObj=new statusThread(start.tfm19);
		thrd=new Thread(thrdObj,"����19");
		thrd.start();
		ll_stream_double=new LinkedList<>(ll_stream_double_orig);
		new MultiCPU19(ll_stream_double,start.tfm19);
		start.tfm19.setForeground(Color.green);
		if(scores.get_milisec(19)<scores.get_milisec(9) || scores.get_nanosec(19)<scores.get_nanosec(9))
			start.tf19.setForeground(Color.red);
		else
			start.tfm19.setForeground(Color.red);
//****************************************************************
//***************������ �������� ����#20 MultiCPU20**************//����� �����	��������� Fork/Join	
		flags.end=false;
		start.tfm20.setForeground(Color.RED);
		thrdObj=new statusThread(start.tfm20);
		thrd=new Thread(thrdObj,"����20");
		thrd.start();
		new MultiCPU20(a,start.tfm20);
		start.tfm20.setForeground(Color.green);
		if(scores.get_milisec(20)<scores.get_milisec(10) || scores.get_nanosec(20)<scores.get_nanosec(10))
			start.tf20.setForeground(Color.red);
		else
			start.tfm20.setForeground(Color.red);
//****************************************************************
//*****************��� ����� ���������� � ����********************
		long[] mlsec=scores.get_mlsec();
		long[] nsec=scores.get_nsec();
		//DataBase(JFrame jf,long[] mls,long[] ns,String osN,String osAr,String ven,String mod, int cor,double mh,int totalCor,long totalRa,long freeRa){
		new DataBase(start.obj.frame,mlsec,nsec,start.osName,start.osArch,start.vendor,start.model,start.cores,start.mhz,start.totalCores,start.totalRAM,start.freeRAM);
		start.obj.listPCs(); //������� ������ JComboBox
		start.BtnStart.setEnabled(true);
	}
}
