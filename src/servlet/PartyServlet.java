package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PartyServlet
 */
@WebServlet("/PartyServlet")
public class PartyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public PartyServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//リクエストの文字コード設定
		request.setCharacterEncoding("UTF-8");

		//ArrayListを宣言
		ArrayList<Object> arryCon = new ArrayList<Object>();


				//データベース接続処理
				//データベースオブジェクトの初期化
				Connection con = null;	//コネクションオブジェクト(java.sql)
				Statement state = null;		//ステートメントオブジェクト(java.sql)
				ResultSet ret = null;			//リザルトセットオブジェクト(java.sql)

				//セッションの生成
				HttpSession session = request.getSession();

				//転送画面の設定
				String strDisp = "party.jsp";

						//エラー番号
						int intErrNo = 0;
						//メッセージ
						String strMsg = "";

						try{

							//データベース処理
							//①ドライバ読み込み
							Class.forName("org.mariadb.jdbc.Driver");
							//接続情報
							String strDBinfo = "jdbc:mariadb://localhost/party";
							//接続ユーザ
							String strUser = "partyuser";
							//パスワード
							String strPasswd = "partyparty";
							//②コネクション作成(ドライバマネージャオブジェクトを使う)
							con = DriverManager.getConnection(strDBinfo, strUser, strPasswd);
							//③ステートメント作成
							state = con.createStatement();


							//投稿ボタン押下処理
							if(request.getParameter("con")!=null){

								//投稿内容取得
								String strSHIMEI = request.getParameter("shimei");
								String strPARTY = request.getParameter("party");
								String strDETAIL = request.getParameter("detail");
								//	Timestamp datetime = new Timestamp(Calendar.getInstance().getTimeInMillis() - 1000*60*60*24);

								TimeZone tz = TimeZone.getTimeZone("Asia/Tokyo");
							    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd  HH:mm:ss");
							    Calendar cal = Calendar.getInstance(tz);
							    String date = sdf.format(cal.getTime());
							    //sdf.setTimeZone(tz);
							    //System.out.println(sdf.format(new Date()));

								//Timestamp datetime = new Timestamp(System.currentTimeMillis()-1000*60*60*24);

								//System.out.println(sdf.format(cal.getTime()));

								//投稿SQLを作る(insert命令)
								String sql =
										"insert into party values('" + strSHIMEI +
										"','" + date + "','"+strPARTY +"','"+strDETAIL +"');";
								//System.out.println(sql);

								//SQL実行(executeUpdateメソッド)
								state.executeUpdate(sql);

								//from database
								sql = "select * from party;";
								ret = state.executeQuery(sql);


								while(ret.next()) {
									arryCon.add(ret.getString("投稿者"));
									date = ret.getString("投稿日");
									date = date.substring(0, 10);
									arryCon.add(date);
									arryCon.add(ret.getString("パーティー名"));
									arryCon.add(ret.getString("内容・詳細"));
								}

								//result.jspにarryConを転送
								request.setAttribute("DATA", arryCon);

							}//投稿処理終わり
						}//try終了

						catch(SQLException e) {		//データベース関連の例外を処理
							//エラー番号設定
							intErrNo = -2;
							//エラーメッセージ
							strMsg = e.getMessage();

							System.out.println("Err:"+intErrNo+":"+strMsg);
						}

						catch(Exception e) {		//それ以外の例外すべてを処理
							//エラー番号設定
							intErrNo = -1;
							strMsg = e.getMessage();

							System.out.println("Err:"+intErrNo+":"+strMsg);
						}

						finally {		//共通処理
							//データベースの後処理(必ずやること)
							//⑥クローズ処理
							//リザルトセットのクローズ
							if(ret!=null){
								try {
									ret.close();
								} catch (SQLException e) {
									// TODO 自動生成された catch ブロック
									e.printStackTrace();
								}
							}
							//ステートメントのクローズ
							if(state!=null){
								try {
									state.close();
								} catch (SQLException e) {
									// TODO 自動生成された catch ブロック
									e.printStackTrace();
								}
							}
							//コネクションのクローズ
							if(con!=null){
								try {
									con.close();
								} catch (SQLException e) {
									// TODO 自動生成された catch ブロック
									e.printStackTrace();
								}
							}
						}//finallyの終わり

				//エラー番号を転送
				request.setAttribute("ERRNO", intErrNo);
				//エラーメッセージ転送
				request.setAttribute("MSG", strMsg);

		//転送処理
		RequestDispatcher disp = request.getRequestDispatcher("party.jsp");
		disp.forward(request, response);
	}

}
