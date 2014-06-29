package com.photofolio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.photofolio.DTO.Article;
import com.photofolio.DTO.Challenge;
import com.photofolio.DTO.Emblem;
import com.photofolio.DTO.Member;
import com.photofolio.Util.ConnectionHelper;

public class AdminDao {

	Connection conn = null;
	DataSource ds;
	PreparedStatement pstmt;
	ResultSet rs;

	public AdminDao() {
		try {
			Context init = new InitialContext();
			ds = (DataSource) init.lookup("java:comp/env/jdbc/mysql");
			conn = ds.getConnection();
			System.out.println("Connection Success!!!");
		} catch (Exception e) {
			System.out.println("Failure!!!");
			e.printStackTrace();
		}
	}

	public int selectadmin(String id, String pwd) { // 1. 관리자 로그인
		int result = 0;
		try {
			pstmt = conn
					.prepareStatement("select id,pwd from member where id='admin'");
			rs = pstmt.executeQuery();
			System.out.println("select query success");

			if (rs.next()) {
				System.out.println(pwd);
				if (id.equals(rs.getString(1))) {
					if (pwd.equals(rs.getString(2))) {
						result = 1;
						// System.out.println("로그인성공");
					} else {
						result = -1;
						// System.out.println("비밀번호가 다릅니다.");
					}
				} else {
					result = -2;
					// System.out.println("아이디가 없습니다.");
				}
			}
		} catch (Exception e) {

		} finally {

			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}

	public List memberManagerMain() { // 2.1 memberManager List 불러오기

		List<Member> list = new ArrayList<Member>();
		try {
			pstmt = conn.prepareStatement("select nickname, id from member");
			rs = pstmt.executeQuery();
			System.out.println("select query success");
			while (rs.next()) {
				Member member = new Member();
				member.setNickname(rs.getString(1));
				member.setId(rs.getString(2));
				list.add(member);
				// System.out.println(rs.getString(1) + " / " +
				// rs.getString(2));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return null;
	}

	public List memberView(String id) { // 2.2 memberManager List 불러오기

		List<Member> memberList = new ArrayList<Member>();
		try {
			pstmt = conn
					.prepareStatement("select id,pwd,lvl,nickname,email,phone,address,profileimg,memo from member where id=?");
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			System.out.println("select query success");
			while (rs.next()) {
				Member member = new Member();
				member.setId(rs.getString(1));
				member.setPwd(rs.getString(2));
				member.setLevel(rs.getInt(3));
				member.setNickname(rs.getString(4));
				member.setEmail(rs.getString(5));
				member.setPhone(rs.getString(6));
				member.setAddress(rs.getString(7));
				member.setProfileimg(rs.getString(8));
				member.setMemo(rs.getString(9));

				memberList.add(member);

			}
			return memberList;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return null;
	}

	public int memberDelete(String id) { // 2.1 memberManager 삭제하기
		try {
			pstmt = conn
					.prepareStatement("delete from image where idx=(select idx from article where id=?)");
			pstmt.setString(1, id);
			int row = pstmt.executeUpdate();
			System.out.println("delete query success1");

			pstmt = conn
					.prepareStatement("delete from reply where idx=(select idx from article where id=?)");
			pstmt.setString(1, id);
			row = pstmt.executeUpdate();
			System.out.println("delete query success2");

			pstmt = conn.prepareStatement("delete from article where id=?");
			pstmt.setString(1, id);
			row = pstmt.executeUpdate();
			System.out.println("delete query success3");

			pstmt = conn.prepareStatement("delete from notice where id=?");
			pstmt.setString(1, id);
			row = pstmt.executeUpdate();
			System.out.println("delete query success4");

			pstmt = conn.prepareStatement("delete from interest where id=?");
			pstmt.setString(1, id);
			row = pstmt.executeUpdate();
			System.out.println("delete query success5");

			pstmt = conn.prepareStatement("delete from creator where id=?");
			pstmt.setString(1, id);
			row = pstmt.executeUpdate();
			System.out.println("delete query success6");

			pstmt = conn.prepareStatement("delete from member where id=?");
			pstmt.setString(1, id);
			row = pstmt.executeUpdate();
			System.out.println("delete query success7");

			if (row > 0) {
				System.out.println("회원 등급 삭제완료");
			} else {

				System.out.println("회원 등급 삭제미완료ㅠ");
			}
			return 1;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return -1;
	}

	public List memberLevelManagerMain() { // 3. memberLevelManager List 불러오기

		List<Member> memberList = new ArrayList<Member>();

		try {
			// pstmt
			// =conn.prepareStatement("select m.nickname,m.id,m.lvl,a.alike  from member m join article a on m.id = a.id");
			pstmt = conn
					.prepareStatement("select nickname, id, lvl  from member where lvl=0");
			rs = pstmt.executeQuery();
			System.out.println("select query success");
			while (rs.next()) {
				Member member = new Member();
				member.setNickname(rs.getString(1));
				member.setId(rs.getString(2));
				member.setLevel(rs.getInt(3));
				// member.setLike(rs.getInt(4)); 좋아요 보류★

				memberList.add(member);
			}
			return memberList;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return null;
	}

	public List creatorLevelManagerMain() { // 3.1. creatorLevelManager List
											// 불러오기

		List<Member> creatorList = new ArrayList<Member>();

		try {

			// pstmt
			// =conn.prepareStatement("select m.nickname,m.id,m.lvl,a.alike  from member m join article a on m.id = a.id");
			pstmt = conn
					.prepareStatement("select nickname, id, lvl  from member where lvl=1");
			rs = pstmt.executeQuery();
			System.out.println("select query success");
			while (rs.next()) {
				Member member = new Member();
				member.setNickname(rs.getString(1));
				member.setId(rs.getString(2));
				member.setLevel(rs.getInt(3));
				// member.setLike(rs.getInt(4)); 좋아요 보류★
				creatorList.add(member);
			}
			return creatorList;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return null;
	}

	public int memberLevelUp(String id) { // 3.2 memberLevelUp 시키기

		try {
			pstmt = conn.prepareStatement("update member set lvl=1 where id=?");
			pstmt.setString(1, id);
			int row = pstmt.executeUpdate();
			System.out.println("update query success");

			pstmt = conn.prepareStatement("insert into creator(id) values(?)");
			pstmt.setString(1, id);
			row = pstmt.executeUpdate();
			System.out.println("insert query success");
			if (row > 0) {
				System.out.println("일반회원 등급 수정완료");
			} else {

				System.out.println("일반회원 등급 수정미완료ㅠ");
			}
			return 1;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return -1;
	}

	public int creatorLevelDown(String id) { // 3.3 creatorLevelDown 시키기

		try {
			pstmt = conn.prepareStatement("update member set lvl=0 where id=?");
			pstmt.setString(1, id);
			int row = pstmt.executeUpdate();
			System.out.println("update query success");

			pstmt = conn.prepareStatement("delete from creator where id=?");
			pstmt.setString(1, id);
			row = pstmt.executeUpdate();
			System.out.println("delete query success");

			if (row > 0) {
				System.out.println("크리에이터 등급 수정완료");
			} else {

				System.out.println("크리에이터 등급 수정미완료ㅠ");
			}
			return 1;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return -1;
	}

	public List challengeList() { // 5 챌린지 리스트 조회

		List<Challenge> challengeList = new ArrayList<Challenge>();

		try {

			pstmt = conn
					.prepareStatement("select subject,emblem_no from challenge");
			rs = pstmt.executeQuery();
			System.out.println("select query success");
			while (rs.next()) {
				Challenge challenge = new Challenge();
				challenge.setSubject(rs.getString(1));
				challenge.setEmblem_no(rs.getInt(2));
				challengeList.add(challenge);
			}
			return challengeList;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return null;
	}

	public int challengeWrite(String subject, String content, String startdate,
			String enddate, String emblem) { // 4.1 challengeWrite 시키기

		try {
			// 먼저 앰블럼 생성
			pstmt = conn
					.prepareStatement("insert into emblem(emblem,eimg) values(?,1)"); // 사진은
																						// 일단
																						// 보류★
			pstmt.setString(1, emblem);
			int row = pstmt.executeUpdate();
			System.out.println("insert query success1");

			// 생성된 앰블럼의 emblem_no와 동일한 챌린지 생성
			// ★챌린지가 등록되면 activation 이 1(오픈)으로 등록된다
			pstmt = conn
					.prepareStatement("insert into challenge(emblem_no,subject,content,startdate,enddate,activation) values((select emblem_no from emblem where emblem=?),?,?,?,?,1)");
			pstmt.setString(1, subject);
			pstmt.setString(2, subject);
			pstmt.setString(3, content);
			pstmt.setString(4, startdate);
			pstmt.setString(5, enddate);

			row = pstmt.executeUpdate();
			System.out.println("insert query success2");

			if (row > 0) {
				System.out.println("챌린지 등록 완료");
			} else {

				System.out.println("챌린지 등록 미완료ㅠ");
			}
			return 1;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return -1;
	}

	public List challengeView(int emblem_no) { // 5.2 챌린지 보기

		List<Challenge> challengeView = new ArrayList<Challenge>();
		try {
			// select
			// c.emblem_no,c.subject,c.content,c.startdate,c.enddate,c.writedate,c.activation,
			// e.emblem from challenge c join emblem e on
			// c.emblem_no=e.emblem_no where c.subject=?
			pstmt = conn
					.prepareStatement("select c.emblem_no,c.subject,c.content,c.startdate,c.enddate,c.writedate,c.activation, e.emblem from challenge c join emblem e on c.emblem_no=e.emblem_no where c.emblem_no=?");
			pstmt.setInt(1, emblem_no);
			rs = pstmt.executeQuery();
			System.out.println("select query success");
			while (rs.next()) {

				Challenge challenge = new Challenge();
				challenge.setEmblem_no(rs.getInt(1));
				challenge.setSubject(rs.getString(2));
				challenge.setContent(rs.getString(3));
				challenge.setStartdate(rs.getDate(4));
				challenge.setEnddate(rs.getDate(5));
				challenge.setWritedate(rs.getDate(6));
				challenge.setActivation(rs.getInt(7));
				challenge.setEmblem(rs.getString(8));

				challengeView.add(challenge);
			}
			return challengeView;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return null;
	}

	public int challengeModifyOk(String subject, String content,
			String startdate, String enddate, int emblem_no, String emblem) { // 5.3
																				// challenge
																				// 수정
		try {
			// 먼저 앰블럼 수정
			pstmt = conn
					.prepareStatement("update emblem set emblem = ?,eimg=0 where emblem_no=?");
			// 사진은일단 보류★
			pstmt.setString(1, emblem);
			pstmt.setInt(2, emblem_no);
			int row = pstmt.executeUpdate();
			System.out.println("update query success1");

			// 챌린지 수정
			pstmt = conn
					.prepareStatement("update challenge set subject=?,content=?,startdate=?,enddate=?,activation=0 where emblem_no=?");
			pstmt.setString(1, subject);
			pstmt.setString(2, content);
			pstmt.setString(3, startdate);
			pstmt.setString(4, enddate);
			pstmt.setInt(5, emblem_no);

			row = pstmt.executeUpdate();
			System.out.println("update query success2");

			if (row > 0) {
				System.out.println("챌린지 수정 완료");
			} else {

				System.out.println("챌린지 수정 미완료ㅠ");
			}
			return 1;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return -1;
	}

	public int challengeDelete(int emblem_no) { // 5.4 challenge 삭제하기
		try {
			/*
			 * int num = 0; pstmt = conn .prepareStatement(
			 * "select emblem_no from challenge where getEmblem_no=?");
			 * pstmt.setString(1, emblem_no); rs = pstmt.executeQuery();
			 * System.out.println("select query success"); if (rs.next()) { num=
			 * rs.getInt(1); // 번호 넘겨준다 }
			 */

			pstmt = conn
					.prepareStatement("delete from challenge where emblem_no=?");
			pstmt.setInt(1, emblem_no);
			int row = pstmt.executeUpdate();
			System.out.println("delete query success1");

			/*
			 * pstmt = conn
			 * .prepareStatement("delete from emblem where emblem_no=" + num);
			 * row = pstmt.executeUpdate();
			 * System.out.println("delete query success2");
			 */

			if (row > 0) {
				System.out.println("챌린지 삭제완료");
			} else {

				System.out.println("챌린지 삭제미완료ㅠ");
			}
			return 1;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return -1;
	}

	public List emblemManagerMain() { // 6.1 emblem List 불러오기

		List<Emblem> emblemList = new ArrayList<Emblem>();
		try {
			pstmt = conn
					.prepareStatement("select emblem_no,emblem,eimg from emblem");
			rs = pstmt.executeQuery();
			System.out.println("select query success");
			while (rs.next()) {
				Emblem emblem = new Emblem();
				emblem.setEmblem_no(rs.getInt(1));
				emblem.setEmblem(rs.getString(2));
				emblem.setEimg(rs.getString(3));
				emblemList.add(emblem);

			}
			return emblemList;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return null;
	}

	public List emblemModifyView(int emblem_no) { // 6.2 emblem 수정뷰 불러오기

		List<Emblem> emblemList = new ArrayList<Emblem>();
		try {
			pstmt = conn
					.prepareStatement("select emblem_no,emblem,eimg from emblem where emblem_no=?");
			pstmt.setInt(1, emblem_no);
			rs = pstmt.executeQuery();
			System.out.println("select query success");
			while (rs.next()) {
				Emblem emblem = new Emblem();
				emblem.setEmblem_no(rs.getInt(1));
				emblem.setEmblem(rs.getString(2));
				emblem.setEimg(rs.getString(3));
				emblemList.add(emblem);

			}
			return emblemList;
		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return null;
	}

	public int emblemModifyOk(int emblem_no, String emblem, String eimg) { // 6.3
																			// emblem수정
		try {
			// 앰블럼 수정
			pstmt = conn
					.prepareStatement("update emblem set emblem = ?,eimg=0 where emblem_no=?");
			// 사진은일단 보류★
			pstmt.setString(1, emblem);
			pstmt.setInt(2, emblem_no);
			int row = pstmt.executeUpdate();
			System.out.println("update query success");

			if (row > 0) {
				System.out.println("앰블럼 수정 완료");
			} else {

				System.out.println("앰블럼 수정 미완료ㅠ");
			}
			return 1;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return -1;
	}

	public int emblemDelete(int emblem_no) { // 6.4 앰블럼 삭제하기
		try {

			pstmt = conn
					.prepareStatement("delete from emblem where emblem_no=?");
			pstmt.setInt(1, emblem_no);
			int row = pstmt.executeUpdate();
			System.out.println("emblem delete query success");

			if (row > 0) {
				System.out.println("앰블럼 삭제완료");
			} else {

				System.out.println("앰블럼 삭제미완료ㅠ");
			}
			return 1;

		} catch (Exception e) {
			e.printStackTrace();

		} finally {
			// ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return -1;
	}

}