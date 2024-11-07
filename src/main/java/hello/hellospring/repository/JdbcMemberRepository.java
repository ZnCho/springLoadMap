package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcMemberRepository implements MemberRepository {
	private final DataSource dataSource;
	public JdbcMemberRepository(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	@Override
	public Member save(Member member) {
		String sql = "insert into member(name) values(?)";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null; //결과를 받는 것
		try {
			conn = getConnection(); //커넥션을 가져옴
			pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS); //RETURN_GENERATED_KEYS = seq값을 받아오는 것

			pstmt.setString(1, member.getName()); //values(?)에 값을 넣음

			pstmt.executeUpdate();//실제 쿼리가 날라감
			rs = pstmt.getGeneratedKeys();//seq 값을 반환해줌

			if (rs.next()) {//값이 있으면
				member.setId(rs.getLong(1)); //값을 꺼내옴
			} else {
				throw new SQLException("id 조회 실패");
			}
			return member;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs); //연결된 자원을 끊음. 리소스 반환. 안 그러면 큰일남. db 커넥션이 계속 쌓이면 장애가 남.
		}
	}
	@Override
	public Optional<Member> findById(Long id) { //조회 쿼리
		String sql = "select * from member where id = ?";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, id);

			rs = pstmt.executeQuery(); //조회는 executeQuery를 사용함
			if(rs.next()) {
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setName(rs.getString("name"));
				return Optional.of(member);
			} else {
				return Optional.empty();
			}
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		} }
	@Override
	public List<Member> findAll() {
		String sql = "select * from member";

		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);

			rs = pstmt.executeQuery();
			List<Member> members = new ArrayList<>();
			while(rs.next()) {  Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setName(rs.getString("name"));
				members.add(member);
			}
			return members;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}
	@Override
	public Optional<Member> findByName(String name) {
		String sql = "select * from member where name = ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				Member member = new Member();
				member.setId(rs.getLong("id"));
				member.setName(rs.getString("name"));
				return Optional.of(member);
			}
			return Optional.empty();
		} catch (Exception e) {
			throw new IllegalStateException(e);
		} finally {
			close(conn, pstmt, rs);
		}
	}
	private Connection getConnection() { //db커넥션
		return DataSourceUtils.getConnection(dataSource); //DataSourceUtils를 통해서 커넥션을 해야 똑같은 커넥션을 유지함
	}
	private void close(Connection conn, PreparedStatement pstmt, ResultSet rs) { //연결 끊는 함수
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (pstmt != null) {
				pstmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				close(conn);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	private void close(Connection conn) throws SQLException {
		DataSourceUtils.releaseConnection(conn, dataSource); //닫을 때도 DataSourceUtils를 통해서 release함.
	}
}
