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
	
	private String FileUrl = "D:\\����\\File\\������";
	
	private String[] mk = new String[1024];
	
	private String[] Url = new String[1024];
	
	StringBuilder sb = new StringBuilder();
	
	StringBuilder sb2 = new StringBuilder();
	
	StringBuilder sbStr = new StringBuilder();
	
	//��ȡ�ļ�Ŀ¼
	public void temp(){
		File fi = new File(FileUrl);
		
		File[] tempList = fi.listFiles();
		
		for (int i = 0; i < tempList.length; i++) {
			
			if (tempList[i].isDirectory()) {
				
				String a = tempList[i].toString();
				
				this.Url[i] = a;
				
                //��ȡĳ���ļ����µ������ļ���
				mk[i] = a.substring(a.length()-8, a.length());
            }
        }
	}
	
	//λ����������
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
	//�Ա�ԭ�ļ�Ŀ¼��ʵ��Ŀ¼���
	public void OutputFileName(){
		
		temp();

		for(int j = 1;j<mk.length;j++){
			
			sbStr.delete(0, sbStr.length()+1);
			
			sbStr.append("00000"+j);
			
			String nameLeng = Zero(sbStr).toString();
			
			if(!nameLeng.equals(mk[j-1])){
				System.out.println("��ƥ���ļ�"+nameLeng+"---"+mk[j-1]);
				break;
			}
		}
		
	}
	
	
	//�Զ��޸��ļ���
	public void FileFunction(){
		
		//��ȡ��Ŀ¼��ȫ��·�� 
		 temp();
		 
		 //����д�����ļ���
		 StringBuilder NewFileName = new StringBuilder();
		 
		 //����д����ļ���
		 StringBuilder OldFileName = new StringBuilder();
		 
		 //ѭ������Ŀ¼��ȫ���ļ���
		 for(int i =0;i<this.Url.length;i++){
				
			 //��ȡ·��
			 File ClassMov = new File(""+Url[i]);
			 
			 //��ȡ·���µ��ļ�Ŀ¼
			 String[] ClassName = ClassMov.list();
			 
			 //ѭ����ȡԴ�ļ������ļ���
				for (int j = 0;j<ClassName.length;j++) {
					
						//�����һ�δ������ļ���
						this.sbStr.delete(0, sbStr.length()+1);
						
						//д���ļ���
						this.sbStr.append("0"+(j+1));
						
						//�����һ�δ��������ļ���
						NewFileName.delete(0, NewFileName.length()+1);
						
						//д�����ļ��������ò���λ������
						NewFileName.append(Zero(this.sbStr).toString());
						
						//�����һ�λ�ȡ���ľ��ļ���
						OldFileName.delete(0, OldFileName.length()+1);
						
						//д����ļ���
						OldFileName.append(ClassName[j].toString());
						
						//��ȡ���ļ�
						File mlo = new File(ClassMov,OldFileName.toString());
						
						//������
						File ml = new File(ClassMov,NewFileName+".png");
						mlo.renameTo(ml);
						
						//�����������ļ�
						System.out.println(ml.toString().substring(ml.toString().length()-12, ml.toString().length()));
						
						//�Ƿ����һ��ͼ
						if(j==(ClassName.length-1)){
							System.out.println(Url[i]+"������");
						}
		        }
				
		 }
		 
		 
	}
}