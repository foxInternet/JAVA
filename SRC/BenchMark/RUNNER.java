package BenchMark;

import javax.swing.JTextField;

public class RUNNER{
	private JTextField jtf;
	private int numberTest;
	static long minTimeCPU=0,minTimeCPUnano=0,nano=0;
	private SingleMultiCPU obj; //ќбъект, нследуемый от интерфейса SingleMultiCPU
	private int count;
			
	public RUNNER(SingleMultiCPU ob,JTextField j,int num) {
		count=Array_Bases.pass;
		jtf=j;
		numberTest=num;
		obj=ob;
		RUN();
	}
	public void RUN() {
		long tmp=0;
		for(int i=0;i<count;i++) {
			if(i==0) {
				minTimeCPU=obj.Compute();
				tmp=minTimeCPU;
				minTimeCPUnano=nano;
			}else {
				tmp=obj.Compute();
				if(minTimeCPUnano > nano){
					minTimeCPU=tmp;
					minTimeCPUnano=nano;
					}
			}
		}
		flags.end=true;
		try {Thread.sleep(250);}catch(InterruptedException e) {e.printStackTrace();}
		if(numberTest<=10)
			jtf.setText("OK! "+String.valueOf(minTimeCPU)+" млсек // "+minTimeCPUnano+" нсек. --> 100%");
		else {//“”“ ѕќƒ—„»“ј≈ћ, какой процент состовл€ет значение двух€дерного режима, по сравнению с одно€дерным
			switch(numberTest) {
			case 11:
				jtf.setText("OK! "+String.valueOf(minTimeCPU)+" млсек // "+minTimeCPUnano+" нсек. --> "+(minTimeCPUnano*100)/scores.get_nanosec(1)+"%");
				break;
			case 12:
				jtf.setText("OK! "+String.valueOf(minTimeCPU)+" млсек // "+minTimeCPUnano+" нсек. --> "+(minTimeCPUnano*100)/scores.get_nanosec(2)+"%");
				break;
			case 13:
				jtf.setText("OK! "+String.valueOf(minTimeCPU)+" млсек // "+minTimeCPUnano+" нсек. --> "+(minTimeCPUnano*100)/scores.get_nanosec(3)+"%");
				break;
			case 14:
				jtf.setText("OK! "+String.valueOf(minTimeCPU)+" млсек // "+minTimeCPUnano+" нсек. --> "+(minTimeCPUnano*100)/scores.get_nanosec(4)+"%");
				break;
			case 15:
				jtf.setText("OK! "+String.valueOf(minTimeCPU)+" млсек // "+minTimeCPUnano+" нсек. --> "+(minTimeCPUnano*100)/scores.get_nanosec(5)+"%");
				break;
			case 16:
				jtf.setText("OK! "+String.valueOf(minTimeCPU)+" млсек // "+minTimeCPUnano+" нсек. --> "+(minTimeCPUnano*100)/scores.get_nanosec(6)+"%");
				break;
			case 17:
				jtf.setText("OK! "+String.valueOf(minTimeCPU)+" млсек // "+minTimeCPUnano+" нсек. --> "+(minTimeCPUnano*100)/scores.get_nanosec(7)+"%");
				break;
			case 18:
				jtf.setText("OK! "+String.valueOf(minTimeCPU)+" млсек // "+minTimeCPUnano+" нсек. --> "+(minTimeCPUnano*100)/scores.get_nanosec(8)+"%");
				break;
			case 19:
				jtf.setText("OK! "+String.valueOf(minTimeCPU)+" млсек // "+minTimeCPUnano+" нсек. --> "+(minTimeCPUnano*100)/scores.get_nanosec(9)+"%");
				break;
			case 20:
				jtf.setText("OK! "+String.valueOf(minTimeCPU)+" млсек // "+minTimeCPUnano+" нсек. --> "+(minTimeCPUnano*100)/scores.get_nanosec(10)+"%");
				break;
			}
			
		}
		scores.set_milisec(numberTest, minTimeCPU);
		scores.set_nanosec(numberTest, minTimeCPUnano);
	}
}
