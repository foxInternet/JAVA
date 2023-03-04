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
	private String str="\t\tОдноядерный режим: \n"
					 + "    1. Использован \"тяжелый\" для процессора \"Пузырьковый\" метод сортировки.\n"
					 + "    2. Вычисления в Collections.swap(List<Integer>,i,j);  В качестве Коллекции применен LinkedList<Integer>()\n"
					 + "    3. Сортировка в массиве примитивных типов Arrays.sort(int []); В качестве примитивов использовался массив int[] емкостью 100_000\n"
					 + "    4. Сортировка массива примитивных типов в потоке Stream. Использовался поток данных типа IntStream. Поток вызывается из Arrays\n"
					 + "    5. Математические вычисление, применяя класс Math в массиве с примитивными типами double[]\n"
					 + "    6. Математические вычисление, применяя класс Math в массиве с примитивными типами double[] + потоковые DoubleStream вычисления\n"
					 + "    7. Математические вычисление с более тяжелой формулой, применяя класс Math в массиве с примитивными типами double[] + потоковые DoubleStream вычисления\n"
					 + "    8. Вычисления в операции <свидения> reduce в потоке Stream, используя LinkedList<Long>\n"
					 + "    9. Вычисления в операции <свидения> reduce в потоке Stream, используя LinkedList<Double>\n"
					 + "    10.Быстрая сортировка QuickSort. Использован метод Хоара.\n"
					 + "\t\tМногоядерный режим.\n"
					 + "    11.Используется фреймворк Fork\\Join для параллельных вычислений для \"тяжелого\" \"Пузырькового\" метода сортировки.\n"
					 + "    12.Используется фреймворк Fork\\Join для параллельных вычислений. Вычисления в Collections.swap(List<Integer>,i,j);  В качестве Коллекции применен LinkedList<Integer>()\n"
					 + "    13.Параллельная ортировка в массиве примитивных типов Arrays.parallelSort(int[]) В качестве примитивов использовался массив int[] емкостью 100_000\n"
					 + "    14.Параллельная сортировка массива примитивных типов в потоке Stream. Использовался поток данных типа IntStream. Поток вызывается из Arrays\n"
					 + "    15.Используется фреймворк Fork\\Join для параллельных вычислений. Мат. вычисления в массиве с примитивными типами double[]\n"
					 + "    16.Используется фреймворк Fork\\Join для параллельных вычислений. Мат. вычисления в массиве с примитивными типами double[] + потоковые DoubleStream вычисления\n"
					 + "    17.Используется фреймворк Fork\\Join для параллельных вычислений. Мат. вычисления с более тяжелой формулой в параллельном потоке <stream.parallel()> с примитивными типами  double[] + потоковые DoubleStream вычисления\n"
					 + "    18.Вычисления в операции <свидения> reduce в параллельном потоке Stream, используя LinkedList<Long>\n"
					 + "    19.Вычисления в операции <свидения> reduce в потоке параллельном Stream, используя LinkedList<Double>\n"
					 + "    20.Используется фреймворк Fork\\Join  для параллельных вычислений. Параллельная быстрая сортировка QuickSort. Использован метод Хоара.";
	About(){
		GUI();	
	}
	private void GUI() {
		frameAbout=new JFrame("О программе...");
		frameAbout.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		exit=new JButton("выход");
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
