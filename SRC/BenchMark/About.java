package BenchMark;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;

public class About {
	private JFrame frameAbout;
	private JButton exit;
	private Dimension dim=Toolkit.getDefaultToolkit().getScreenSize();
	private int screenX=(int)dim.getWidth();
	private int screenY=(int)dim.getHeight();
	private final int sizeX=600;
	private final int sizeY=680;
	private int x=(screenX-sizeX)/2;
	private int y=(screenY-sizeY)/2;
	private JPanel panel;
	private JTextArea text;
	private String str="\t\t����������� �����: \n"
					 + "    1. ����������� \"�������\" ��� ���������� \"�����������\" ����� ����������.\n"
					 + "    2. ���������� � Collections.swap(List<Integer>,i,j);  � �������� ��������� �������� LinkedList<Integer>()\n"
					 + "    3. ���������� � ������� ����������� ����� Arrays.sort(int []); � �������� ���������� ������������� ������ int[] �������� 100_000\n"
					 + "    4. ���������� ������� ����������� ����� � ������ Stream. ������������� ����� ������ ���� IntStream. ����� ���������� �� Arrays\n"
					 + "    5. �������������� ����������, �������� ����� Math � ������� � ������������ ������ double[]\n"
					 + "    6. �������������� ����������, �������� ����� Math � ������� � ������������ ������ double[] + ��������� DoubleStream ����������\n"
					 + "    7. �������������� ���������� � ����� ������� ��������, �������� ����� Math � ������� � ������������ ������ double[] + ��������� DoubleStream ����������\n"
					 + "    8. ���������� � �������� <��������> reduce � ������ Stream, ��������� LinkedList<Long>\n"
					 + "    9. ���������� � �������� <��������> reduce � ������ Stream, ��������� LinkedList<Double>\n"
					 + "    10.������� ���������� QuickSort. ����������� ����� �����.\n"
					 + "\t\t������������ �����.\n"
					 + "    11.������������ ��������� Fork\\Join ��� ������������ ���������� ��� \"��������\" \"������������\" ������ ����������.\n"
					 + "    12.������������ ��������� Fork\\Join ��� ������������ ����������. ���������� � Collections.swap(List<Integer>,i,j);  � �������� ��������� �������� LinkedList<Integer>()\n"
					 + "    13.������������ ��������� � ������� ����������� ����� Arrays.parallelSort(int[]) � �������� ���������� ������������� ������ int[] �������� 100_000\n"
					 + "    14.������������ ���������� ������� ����������� ����� � ������ Stream. ������������� ����� ������ ���� IntStream. ����� ���������� �� Arrays\n"
					 + "    15.������������ ��������� Fork\\Join ��� ������������ ����������. ���. ���������� � ������� � ������������ ������ double[]\n"
					 + "    16.������������ ��������� Fork\\Join ��� ������������ ����������. ���. ���������� � ������� � ������������ ������ double[] + ��������� DoubleStream ����������\n"
					 + "    17.������������ ��������� Fork\\Join ��� ������������ ����������. ���. ���������� � ����� ������� �������� � ������������ ������ <stream.parallel()> � ������������ ������  double[] + ��������� DoubleStream ����������\n"
					 + "    18.���������� � �������� <��������> reduce � ������������ ������ Stream, ��������� LinkedList<Long>\n"
					 + "    19.���������� � �������� <��������> reduce � ������ ������������ Stream, ��������� LinkedList<Double>\n"
					 + "    20.������������ ��������� Fork\\Join  ��� ������������ ����������. ������������ ������� ���������� QuickSort. ����������� ����� �����.";
	About(){
		GUI();	
	}
	private void GUI() {
		frameAbout=new JFrame("� ���������...");
		frameAbout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		exit=new JButton("�����");
		exit.addActionListener( (ae) -> {
				frameAbout.dispose();
				frameAbout.setVisible(false);
				} );
		text=new JTextArea();
		text.setText(str);
		text.setLineWrap(true);
		text.setWrapStyleWord(true);
		
		panel=new JPanel(new BorderLayout());
		panel.add(BorderLayout.CENTER,text);
		panel.add(BorderLayout.SOUTH,exit);
		
		frameAbout.add(panel);
		frameAbout.setVisible(true);
		frameAbout.setSize(sizeX, sizeY);
		frameAbout.setResizable(false);
		frameAbout.setLocation(x, y);
	}
}
