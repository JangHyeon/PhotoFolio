package com.photofolio.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.photofolio.DTO.Article;
import com.photofolio.DTO.Emblem;
import com.photofolio.DTO.Image;
import com.photofolio.DTO.Member;
import com.photofolio.DTO.Reply;
import com.photofolio.Util.ConnectionHelper;

public class BoardDAO {
	private static BoardDAO instance = new BoardDAO();

	private BoardDAO() {
	}// SingleTone

	public static BoardDAO getInstance() {
		return instance;
	}

	// DB연결 (CRUD)작업
	// 초기화 : {}, static{}
	static DataSource ds;

	Connection conn = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;

	static {// Static 초기화 블럭 - driver로드
		InitialContext ctx;
		try {
			ctx = new InitialContext();
			ds = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql");
			System.out.println("DataSource 생성 성공");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	// 글쓰기
	public int boardWrite(Article dto) {
		int result = 0;
		try {
			conn = ds.getConnection();
			conn.setAutoCommit(false);
			String sql = "insert into article(id,subject,content,secret,thumbnail) values(?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getSubject());
			pstmt.setString(3, dto.getContent());
			pstmt.setInt(4, dto.getSecret());
			pstmt.setString(5, dto.getThumbnail());

			if (pstmt.executeUpdate() > 0) {
				conn.commit();
				conn.setAutoCommit(true);
				pstmt = conn
						.prepareStatement("select max(idx) as idx from article");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					result = rs.getInt("idx");
				}
			} else {
				System.out.println("rollback-------");
				conn.rollback();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}

	// 이미지 등록
	public int imageWrite(Image dto) {
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = "insert into image(idx,ref,subject,content,ori_name,file_name,file_size,tag) values(?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getIdx());
			pstmt.setInt(2, dto.getRef());
			pstmt.setString(3, dto.getSubject());
			pstmt.setString(4, dto.getContent());
			pstmt.setString(5, dto.getOri_name());
			pstmt.setString(6, dto.getFile_name());
			pstmt.setInt(7, dto.getFile_size());
			pstmt.setString(8, dto.getTag());

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}

	// 조회수 +1
	public void updataCount(int idx) {
		try {
			conn = ds.getConnection();

			// 조회수 갱신
			pstmt = conn
					.prepareStatement("update article set acount=acount+1 where idx=?");
			pstmt.setInt(1, idx);
			pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
	}

	// 게시물 정보
	public Article articleView(int idx) {
		Article dto = new Article();
		try {
			conn = ds.getConnection();

			// 글 정보 출력
			pstmt = conn
					.prepareStatement("select idx, id, subject, content, writedate, acount, alike, report, secret from article where idx=?");
			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setIdx(rs.getInt("idx"));
				dto.setId(rs.getString("id"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setWritedate(rs.getTimestamp("writedate"));
				dto.setCount(rs.getInt("acount"));
				dto.setLike(rs.getInt("alike"));
				dto.setReport(rs.getInt("report"));
				dto.setSecret(rs.getInt("secret"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return dto;
	}

	// 게시물 이미지 정보
	public List<Image> imageView(int idx) {
		ArrayList<Image> imageList = new ArrayList<Image>();
		try {
			conn = ds.getConnection();
			// 이미지 정보 출력
			pstmt = conn
					.prepareStatement("select ref, file_name, subject, content, tag from image where idx=? order by ref asc");
			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Image dto = new Image();
				dto.setRef(rs.getInt("ref"));
				dto.setFile_name(rs.getString("file_name"));
				dto.setSubject(rs.getString("subject"));
				dto.setContent(rs.getString("content"));
				dto.setTag(rs.getString("tag"));
				imageList.add(dto);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return imageList;
	}

	// 게시물 등록자 정보
	public Member memberView(String id) {
		Member dto = new Member();
		try {
			conn = ds.getConnection();
			// 등록자 정보 출력
			pstmt = conn
					.prepareStatement("select id, pwd, lvl, nickname, email, phone, address, profileimg from member where id=?");
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				dto.setId(rs.getString("id"));
				dto.setPwd(rs.getString("pwd"));
				dto.setLevel(rs.getInt("lvl"));
				dto.setNickname(rs.getString("nickname"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone(rs.getString("phone"));
				dto.setAddress(rs.getString("address"));
				dto.setProfileimg(rs.getString("profileimg"));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return dto;
	}

	// 댓글수
	public int articleReplyCount(int idx) {
		int result = 0;
		try {
			conn = ds.getConnection();
			// 이미지 정보 출력
			pstmt = conn
					.prepareStatement("select count(*) as count from reply where idx=?");
			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();
			if (rs.next()) {
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}

	// 댓글 리스트
	public List<Reply> articleReplyList(int idx) {
		List<Reply> replyList = new ArrayList<Reply>();
		try {
			conn = ds.getConnection();
			// 댓글 정보 출력
			pstmt = conn
					.prepareStatement("select reply_idx,r.id as id, m.nickname as nickname, m.profileimg, writedate, content, report "
							+ "from reply r join member m on r.id = m.id where idx=? order by writedate desc");
			pstmt.setInt(1, idx);

			rs = pstmt.executeQuery();
			while (rs.next()) {
				Reply dto = new Reply();
				dto.setReply_idx(rs.getInt("reply_idx"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setProfileimg(rs.getString("profileimg"));
				dto.setWritedate(rs.getTimestamp("writedate"));
				dto.setContent(rs.getString("content"));
				dto.setReport(rs.getInt("report"));
				replyList.add(dto);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return replyList;
	}

	// 댓글쓰기
	public int replyWrite(Reply dto) {
		int reply_idx = 0;
		try {
			conn = ds.getConnection();
			String sql = "insert into reply(idx,id,content) values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, dto.getIdx());
			pstmt.setString(2, dto.getId());
			pstmt.setString(3, dto.getContent());

			if (pstmt.executeUpdate() == 1) {
				pstmt = conn
						.prepareStatement("select max(reply_idx) as reply_idx from reply");
				rs = pstmt.executeQuery();
				if (rs.next()) {
					reply_idx = rs.getInt("reply_idx");
				}
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return reply_idx;
	}

	// 댓글삭제
	public int replyDelete(int reply_idx) {
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = "delete from reply where reply_idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, reply_idx);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}

	// 댓글 신고 +1
	public int replyReport(int reply_idx) {
		int result = 0;
		try {
			conn = ds.getConnection();

			// 신고수 갱신
			pstmt = conn
					.prepareStatement("update reply set report=report+1 where reply_idx=?");
			pstmt.setInt(1, reply_idx);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}

	// 댓글 신고 +1
	public int articleReport(int idx) {
		int result = 0;
		try {
			conn = ds.getConnection();

			// 신고수 갱신
			pstmt = conn
					.prepareStatement("update article set report=report+1 where idx=?");
			pstmt.setInt(1, idx);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}

	// 게시물삭제
	public int articleDelete(int idx) {
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = "delete from article where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}

	// 게시물 좋아요
	public int articleLike(int idx) {
		int result = 0;
		try {
			conn = ds.getConnection();
			String sql = "update article set alike=alike+1 where idx=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx);

			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}

	// 랜덤 idx 구하기
	public int randomArticle() {
		int idx = 0;
		try {
			conn = ds.getConnection();
			String sql = "select idx from article where secret=0 ORDER BY RAND() LIMIT 1";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				idx = rs.getInt("idx");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return idx;
	}
	
	
	//리스트 게시물 갯수
	public int articleCount(String searchKey, int emblem){
		int result =0;
		try {
			conn = ds.getConnection();
			String sql = "SELECT count(idx) as count from article where secret=0 and (content like ? or subject like ?)";
			//if(emblem!=0) sql+="emblem = "+emblem+" AND ";
			//sql+=searchType+" like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKey+"%");
			pstmt.setString(2, "%"+searchKey+"%");
			rs =pstmt.executeQuery();
		    
			if(rs.next()){
				result = rs.getInt("count");
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally{
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return result;
	}
	
	//엠블럼 리스트
	public List<Emblem> emblemList(){
		ArrayList<Emblem> clist = new ArrayList<Emblem>();
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement("select emblem_no, emblem, eimg from emblem");
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				Emblem dto = new Emblem();
				dto.setEmblem_no(rs.getInt("emblem_no"));
				dto.setEmblem(rs.getString("emblem"));
				dto.setEimg(rs.getString("eimg"));
				clist.add(dto);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally{
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return clist;
	}

	
	//목록
	public List<Article> articleList(String searchKey, int emblem, int pageNum, int pageSize){
		ArrayList<Article> list = new ArrayList<Article>();
		try {			
			int start = pageNum * pageSize - (pageSize -1); 
			int end = pageNum * pageSize;
			
			conn = ds.getConnection();
			String sql = "select a.idx as idx, a.id as id, nickname, profileimg, subject, thumbnail, acount, alike, reply_count from "+
					"(select a.idx, id, subject, content, thumbnail, a.acount, alike, COALESCE(re.recount,0) as reply_count from article a "+ 
					"left join (select count(reply_idx) as recount, idx from reply group by idx) re on a.idx=re.idx) a "+
					"inner join member m on m.id=a.id where subject like ? or content like ? order by idx desc limit ?,?";
			/*if(emblem!=0){
				sql+="WHERE C.category="+pageDTO.getCategory();
			}*/
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+searchKey+"%");
			pstmt.setString(2, "%"+searchKey+"%");
			pstmt.setInt(3, start);
			pstmt.setInt(4, end);
			
			rs =pstmt.executeQuery();
		    
			while(rs.next()){
				Article dto = new Article();
				dto.setIdx(rs.getInt("idx"));
				dto.setId(rs.getString("id"));
				dto.setNickname(rs.getString("nickname"));
				dto.setSubject(rs.getString("subject"));
				dto.setProfileimg(rs.getString("profileimg"));
				dto.setThumbnail(rs.getString("thumbnail"));
				dto.setCount(rs.getInt("acount"));
				dto.setLike(rs.getInt("alike"));
				dto.setReply(rs.getInt("reply_count"));
				list.add(dto);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}finally{
			ConnectionHelper.close(rs);
			ConnectionHelper.close(pstmt);
			ConnectionHelper.close(conn);
		}
		return list;
	}
}
