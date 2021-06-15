package main;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.DataBase;

/**
 * Servlet implementation class ReadDataSetFile
 */
@WebServlet("/ReadDataSetFile")
public class ReadDataSetFile extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReadDataSetFile() {
		super();
		// TODO Auto-generated constructor stub
	}
    public String dataClining(String str) {
    	str = str.replaceAll("\\.+", ".");
		str = str.replaceAll("don't", "do not");
		str = str.replaceAll("couldn't", "could not");
		str = str.replaceAll("shouldn't", "should not");
		str = str.replaceAll("wouldn't", "would not");
		str = str.replaceAll("isn't", "is not");
		str = str.replaceAll("needn't", "need not");
		str = str.replaceAll("Don't", "do not");
		str = str.replaceAll("dont", "do not");
		str = str.replaceAll("couldnt", "could not");
		str = str.replaceAll("shouldnt", "should not");
		str = str.replaceAll("wouldnt", "would not");
		str = str.replaceAll("isnt", "is not");
		str = str.replaceAll("neednt", "need not");
		str = str.replaceAll(",+", ",");
		str = str.replaceAll("-+", "-");
		str = str.replaceAll("'", "");
		str = str.replaceAll("\"", "");
		str = str.replaceAll("!", "");
		
		str = str.replaceAll(";", "");
		
		
		str = str.replaceAll("[()]", " ");
    	return str;
    }
	private TitleAbstractRead readFile(File f) {
		BufferedReader br;
		StringBuffer strApend = new StringBuffer();
		TitleAbstractRead fileReader = new TitleAbstractRead();
		try {
			br = new BufferedReader(new FileReader(f));
			String st;
			int lineNumber = 0;
			boolean abstractStatus = false;
			while ((st = br.readLine()) != null) {
				st=dataClining(st);
				if (lineNumber == 0) {
					fileReader.setTitle(st);
				//	System.out.print("title "+st);
				} else {
					if (abstractStatus) {
						strApend.append(st);
					}

					if (st.matches("ABSTRACT")|| st.matches("Abstract")) {
						abstractStatus = true;
					}
					
					

				}
				lineNumber += 1;
			}
		//	System.out.println("Abstract :"+strApend.toString());
			fileReader.setAbstract(strApend.toString());
			br.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileReader;
	}

	public void insertData(String title, String abstractName,int number) {
		Connection con = (Connection) DataBase.getConnection();
		try {
			String msql = "INSERT INTO data_set(title,abstract,file_number) VALUES(?,?,?)";

			PreparedStatement smt = con.prepareStatement(msql);
			// smt.setString(1,column);
			smt.setString(1, title);
			smt.setString(2, abstractName);
			smt.setInt(3, number);

			smt.executeUpdate();
			con.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext context = getServletContext();
		File folder = new File(context.getRealPath("/dataset/test"));
		int inc = 0;
		List<TitleAbstractRead> tileAbstractList=new ArrayList<>();
		File[] listOfFiles = folder.listFiles();
		for (File f : listOfFiles) {
			
			
			tileAbstractList.add(readFile( f));
		
		}
		
		for(TitleAbstractRead TitleAb :tileAbstractList) {
			inc+=1;
			response.getWriter().append("Title  " +TitleAb.getTitle()+"\n");
			//response.getWriter().append("Abstract  " +TitleAb.getAbstract()+"\n");
			insertData(TitleAb.getTitle(),TitleAb.getAbstract(),inc);
		}
		response.getWriter().append("total "+tileAbstractList.size());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

class TitleAbstractRead {
	public String title;
	public String Abstract;

	public void setTitle(String str) {
		this.title = str;

	}

	public void setAbstract(String str) {
		this.Abstract = str;
	}

	public String getTitle() {
		return title;

	}

	public String getAbstract() {
		return Abstract;
	}

}
