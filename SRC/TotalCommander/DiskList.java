package TotalCommander;
import java.io.*;
import javax.swing.filechooser.*;
//Класс получения списка дисков и информации о них
public class DiskList {

	private File[] diskList;
	private FileSystemView fsv=FileSystemView.getFileSystemView();
	private String DriveName;
	private String displayName;
	private String Description;
	private String[][] diskInfo;
	private String [] diskName;
	
	DiskList(){
		
		diskList=File.listRoots(); //Возвращает массив дисков
		diskInfo=new String[diskList.length][3];
		diskName=new String[diskList.length];
		for(int i=0;i<diskList.length;i++) {
			for(int j=0;j<1;j++) {
			DriveName=diskList[i].toString();
			Description=fsv.getSystemTypeDescription(diskList[i]);
			displayName=fsv.getSystemDisplayName(diskList[i]);
			
			diskInfo[i][j]= DriveName;
			diskInfo[i][j+1]=displayName;
			diskInfo[i][j+2]=Description;
			diskName[i]=displayName;
			System.out.println("Disk#"+i+" DriveName:"+diskInfo[i][0]+" displayName:"+diskInfo[i][1]+" Description: "+diskInfo[i][2]);
			}
			System.out.println("******************************************");
		}
	}//end Constructor 
	
public  String[][] getDiskInfo() {
	return diskInfo;
}
public String[] disName() {
	return diskName;
}
}//end class
