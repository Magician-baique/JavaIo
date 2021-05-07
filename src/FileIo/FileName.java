package FileIo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FileName {

	public static void main(String[] args) {
		CxFileName cx = new CxFileName();
		cx.FileFunction();
	}
	
}

class CxFileName{
	
	private String FileUrl = "D:\\工作\\File\\黄有林";
	
	private String[] mk = new String[1024];
	
	private String[] Url = new String[1024];
	
	StringBuilder sb = new StringBuilder();
	
	StringBuilder sb2 = new StringBuilder();
	
	StringBuilder sbStr = new StringBuilder();
	
	//获取文件目录
	public void temp(){
		File fi = new File(FileUrl);
		
		File[] tempList = fi.listFiles();
		
		for (int i = 0; i < tempList.length; i++) {
			
			if (tempList[i].isDirectory()) {
				
				String a = tempList[i].toString();
				
				this.Url[i] = a;
				
                //读取某个文件夹下的所有文件夹
				mk[i] = a.substring(a.length()-8, a.length());
            }
        }
	}
	
	//位数不够补零
	public StringBuilder Zero(StringBuilder ZeroCount){
		
		this.sb.delete(0,this.sb.length()+1);
		
		this.sb.append(ZeroCount);
		
		this.sb2.delete(0,this.sb2.length()+1);
		
		if(this.sb.toString().length()<8){
			
			for(int k = 0;k<(5-this.sb.length());k++){
				this.sb.insert(0, "0");
			}
			this.sb2.append(this.sb.toString());
		}else{
			this.sb2.append(this.sb.toString());
		}
		return this.sb2;
		
	}
	//对比原文件目录与实际目录差别
	public void OutputFileName(){
		
		temp();

		for(int j = 1;j<mk.length;j++){
			
			sbStr.delete(0, sbStr.length()+1);
			
			sbStr.append("00000"+j);
			
			String nameLeng = Zero(sbStr).toString();
			
			if(!nameLeng.equals(mk[j-1])){
				System.out.println("不匹配文件"+nameLeng+"---"+mk[j-1]);
				break;
			}
		}
		
	}
	
	
	//自动修改文件名
	public void FileFunction(){
		
		//获取该目录下全部路径 
		 temp();
		 
		 //用于写入新文件名
		 StringBuilder NewFileName = new StringBuilder();
		 
		 //用于写入旧文件名
		 StringBuilder OldFileName = new StringBuilder();
		 
		 //循环更改目录下全部文件名
		 for(int i =0;i<this.Url.length;i++){
				
			 //读取路径
			 File ClassMov = new File(""+Url[i]);
			 
			 //读取路径下的文件目录
			 String[] ClassName = ClassMov.list();
			 
			 //循环读取源文件更改文件名
				for (int j = 0;j<ClassName.length;j++) {
					
						//清空上一次存留的文件名
						this.sbStr.delete(0, sbStr.length()+1);
						
						//写入文件名
						this.sbStr.append("0"+(j+1));
						
						//清空上一次存留的新文件名
						NewFileName.delete(0, NewFileName.length()+1);
						
						//写入新文件名并调用补零位数方法
						NewFileName.append(Zero(this.sbStr).toString());
						
						//清空上一次获取到的旧文件名
						OldFileName.delete(0, OldFileName.length()+1);
						
						//写入旧文件名
						OldFileName.append(ClassName[j].toString());
						
						//读取旧文件
						File mlo = new File(ClassMov,OldFileName.toString());
						
						//重命名
						File ml = new File(ClassMov,NewFileName+".png");
						mlo.renameTo(ml);
						
						//输出命名后的文件
						System.out.println(ml.toString().substring(ml.toString().length()-12, ml.toString().length()));
						
						//是否最后一张图
						if(j==(ClassName.length-1)){
							System.out.println(Url[i]+"输出完毕");
						}
		        }
				
		 }
		 
		 
	}
}