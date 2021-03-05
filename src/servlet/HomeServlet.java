package servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class HomeServlet
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomeServlet() {
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

		//���N�G�X�g�̕����R�[�h�ݒ�
		request.setCharacterEncoding("UTF-8");

		//ArrayList��錾
		ArrayList<Object> arryCon = new ArrayList<Object>();


				//�f�[�^�x�[�X�ڑ�����
				//�f�[�^�x�[�X�I�u�W�F�N�g�̏�����
				Connection con = null;	//�R�l�N�V�����I�u�W�F�N�g(java.sql)
				Statement state = null;		//�X�e�[�g�����g�I�u�W�F�N�g(java.sql)
				ResultSet ret = null;			//���U���g�Z�b�g�I�u�W�F�N�g(java.sql)

				//�Z�b�V�����̐���
				HttpSession session = request.getSession();

				//�]����ʂ̐ݒ�
				String strDisp = "party.jsp";

				//�G���[�ԍ�
				int intErrNo = 0;
				//���b�Z�[�W
				String strMsg = "";

				try{
					//�f�[�^�x�[�X����
					//�@�h���C�o�ǂݍ���
					Class.forName("org.mariadb.jdbc.Driver");
					//�ڑ����
					String strDBinfo = "jdbc:mariadb://localhost/party";
					//�ڑ����[�U
					String strUser = "partyuser";
					//�p�X���[�h
					String strPasswd = "partyparty";
					//�A�R�l�N�V�����쐬(�h���C�o�}�l�[�W���I�u�W�F�N�g���g��)
					con = DriverManager.getConnection(strDBinfo, strUser, strPasswd);
					//�B�X�e�[�g�����g�쐬
					state = con.createStatement();


					//�f���Ń{�^����������
					if(request.getParameter("party")!=null){
						//from database
						String sql = "select * from party;";
						ret = state.executeQuery(sql);

						while(ret.next()) {
							arryCon.add(ret.getString("���e��"));
							String date = ret.getString("���e��");
							date = date.substring(0, 10);
							arryCon.add(date);
							arryCon.add(ret.getString("�p�[�e�B�[��"));
							arryCon.add(ret.getString("���e�E�ڍ�"));
						}

						//result.jsp��arryCon��]��
						request.setAttribute("DATA", arryCon);

					}//���e�����I���


				}//try�I��
				catch(SQLException e) {		//�f�[�^�x�[�X�֘A�̗�O������
					//�G���[�ԍ��ݒ�
					intErrNo = -2;
					//�G���[���b�Z�[�W
					strMsg = e.getMessage();

					System.out.println("Err:"+intErrNo+":"+strMsg);
				}
				catch(Exception e) {		//����ȊO�̗�O���ׂĂ�����
					//�G���[�ԍ��ݒ�
					intErrNo = -1;
					strMsg = e.getMessage();

					System.out.println("Err:"+intErrNo+":"+strMsg);
				}
				finally {		//���ʏ���
					//�f�[�^�x�[�X�̌㏈��(�K����邱��)
					//�E�N���[�Y����
					//���U���g�Z�b�g�̃N���[�Y
					if(ret!=null){
						try {
							ret.close();
						}
						catch (SQLException e){
							// TODO �����������ꂽ catch �u���b�N
							e.printStackTrace();
						}
					}
					//�X�e�[�g�����g�̃N���[�Y
					if(state!=null){
						try {
							state.close();
						}
						catch (SQLException e) {
							// TODO �����������ꂽ catch �u���b�N
							e.printStackTrace();
						}
					}
					//�R�l�N�V�����̃N���[�Y
					if(con!=null){
						try {
							con.close();
						}
						catch (SQLException e) {
							// TODO �����������ꂽ catch �u���b�N
							e.printStackTrace();
						}
					}
				}//finally�̏I���

				//�G���[�ԍ���]��
				request.setAttribute("ERRNO", intErrNo);
				//�G���[���b�Z�[�W�]��
				request.setAttribute("MSG", strMsg);

			//�]������
			RequestDispatcher disp = request.getRequestDispatcher("party.jsp");
			disp.forward(request, response);
	}

}
