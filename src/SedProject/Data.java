package SedProject;
 import com.csvreader.CsvReader;
 import com.csvreader.CsvWriter;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class Data {
	public ArrayList S = new ArrayList();
	public ArrayList<String []> Edges = new ArrayList<String[]>();
	public int [] node_w = new int [11];
	
	public File sqFile = new File("bhData\\dataresult.txt");
	public File forceFile = new File("bhData\\new_data.txt");
	/* 读取数据 */
	
	public void readSTxt() {
		//IO流尝试读取文件
		try(BufferedReader br = new BufferedReader(
								new InputStreamReader(
								new FileInputStream(sqFile)))) {
	        String lineTxt = null;

	        while ((lineTxt = br.readLine()) != null) {
	        	//数据以逗号分隔
	            String[] names = lineTxt.split(", ");
	            ArrayList temps = new ArrayList();
	            for(int i = 0 ; i < names.length ; i++) {
	            	temps.add(Integer.parseInt(names[i]));
	            }
	            S.add(temps);
	        }
	        System.out.print(S);
	    } catch (Exception e) {
	        System.err.println("read errors :" + e);
	    }
	}
	
	public void readData() {
    	try {
    		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(forceFile)));
    		String lineTxt = null;
    		while((lineTxt = br.readLine()) != null) {
    			String[] names = lineTxt.split(" ");

    			String[] tempEdge = {(names[0]),(names[1])};
    			Edges.add(tempEdge);
    		}
    		br.close();
    		System.out.println(Edges);
    	}catch(Exception e) {
    		System.err.println("read errors :" + e);
    	}
    }
		
	public void readForceTxt() {
		try {
			String force_item[];
			BufferedReader reader = new BufferedReader(new FileReader(forceFile));
			String line = null;
			int lines = 0;
			while((line=reader.readLine())!=null){
				lines++;
				force_item = line.split(" ");//根据逗号切分
				String last = force_item[force_item.length-1];
				int s = Integer.parseInt(force_item[0]);//转化为数值
				int t = Integer.parseInt(force_item[1]);
				if(s >= 0 && s < 404) node_w[1]++; if(t >= 0 && t < 404) node_w[1]++;
				if(s >= 404 && s < 808) node_w[2]++; if(t >= 404 && t < 808) node_w[2]++;
				if(s >= 808 && s < 1212) node_w[3]++; if(t >= 808 && t < 1212) node_w[3]++;
				if(s >= 1212 && s < 1616) node_w[4]++; if(t >= 1212 && t < 1616) node_w[4]++;
				if(s >= 1616 && s < 2020) node_w[5]++; if(t >= 1616 && t < 2020) node_w[5]++;
				if(s >= 2020 && s < 2424) node_w[6]++; if(t >= 2020 && t < 2424) node_w[6]++;
				if(s >= 2424 && s < 2828) node_w[7]++; if(t >= 2424 && t < 2828) node_w[7]++;
				if(s >= 2828 && s < 3232) node_w[8]++; if(t >= 2828 && t < 3232) node_w[8]++;
				if(s >= 3232 && s < 3636) node_w[9]++; if(t >= 3232 && t < 3636) node_w[9]++;
				if(s >= 3636 && s < 4040) node_w[10]++; if(t >= 3636 && t < 4040) node_w[10]++;
			}
			reader.close();
			System.out.println(lines);
			for(int i = 1; i <= 10 ;i++)
				System.out.print(node_w[i]+"   ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
    
}
