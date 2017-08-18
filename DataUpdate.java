import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataUpdate {

	public static void main(String[] args){

		DataUpdate dataUpdate = new DataUpdate();

		try{

			dataUpdate.updateOracle();
		
		}catch(Exception e){
		
			e.printStackTrace();
		
		}

	}



public void updateOracle() throws Exception{

		//ユーザ名	
		String user = "SYS as sysdba";

		//パスワード
		String pass = "oracle";

		//サーバ名
		String servername = "127.0.0.1";

		//SID
		String sid = "orcl";

		Connection conn = null;
		Statement stmt = null;
		//ResultSet result = null;

		try{
			//ドライバクラスのロード
			Class.forName("oracle.jdbc.driver.OracleDriver");

			//Connectionの作成
			
			conn = DriverManager.getConnection 
		("jdbc:oracle:thin:@" + servername + ":1521:" + sid, user , pass);
			
			//Statementの作成
			stmt = conn.createStatement();

			//更新処理
			int record = stmt.executeUpdate("UPDATE 商品マスタ SET 仕入単価 = 100000 WHERE 商品ID = 1");

  			// コミット
			conn.commit();

			//返却値を表示
			System.out.println("更新行数は" + record + "行です。");

		}catch(ClassNotFoundException e){
			throw e;

		}catch(SQLException e){
			
			// ロールバック
			if(conn != null){
				conn.rollback();
			}

		}catch(Exception e){
			
			//ロールバック
			if(conn != null){
				conn.rollback();
			}
			throw e;

		}
		finally{
			//クローズ処理
			if(conn != null){
				conn.close();
				conn = null;
			}
			if(stmt != null){
				stmt.close();
				stmt = null;
			}
			
		}
		
	}
}