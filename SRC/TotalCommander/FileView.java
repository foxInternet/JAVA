package TotalCommander;

public class FileView {
private String nameFile;
private String typeFile;
private String sizeFile;
private String dataFile;

FileView(String n,String t,String s,String d){
	nameFile=n;
	typeFile=t;
	sizeFile=s;
	dataFile=d;
}
public String getName() {
	return nameFile;
}
public String getType() {
	return typeFile;
}
public String getSize() {
	return sizeFile;
}
public String getData() {
	return dataFile;
}
public void setType(String t) {
	typeFile=t;
}
}
