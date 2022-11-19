package org.zerock.mapper;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.domain.BoardVO;
import org.zerock.domain.Criteria;

import lombok.Setter;
import lombok.extern.log4j.Log4j2;


//현재 테스트 코드가 스프링을 실행하는 역할을 할 것 표시
@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
//Java Config
//지정된 클래스나 문자열을 이용해서 필요한 객체들을 스프링 내에 객체로 등록함
@ContextConfiguration(classes = {org.zerock.config.RootConfig.class})
// 로그 기록
@Log4j2
public class BoardMapperTests {
	
	// 해당 인스턴스 변수가 스프링으로 부터 자동으로 주입해 달라는 표시
	@Setter(onMethod_ = @Autowired)
	private BoardMapper mapper;
	
	//@Test
	//public void testGetList() {
	//	mapper.getList().forEach(board -> log.info(board));
	//}
	
	// JUnit에서 테스트 대상을 표시 하는 어노테이션 입니다.
	@Test
	public void testInsert() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		mapper.insert(board);
		
		log.info(board);
		
	}
	
	@Test
	public void testInsertSelectKey() {
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글 select key");
		board.setContent("새로 작성하는 내용 select key");
		board.setWriter("newbie");
		
		mapper.insertSelectKey(board);
		
		log.info(board);
	}
	
	
	@Test
	public void testDelete() {
		log.info("testDelete");
		
		log.info("DELETE DISCOUNT:" + mapper.delete(3L));
		
	}
	
	@Test
	public void testUpdate() {
		log.info("testUpdate시작");

		BoardVO board = new BoardVO();
		// 실행전 존재하는 번호인지 확인 할 것
		board.setBno(5L);
		board.setTitle("수정된 제목");
		board.setContent("수정된 내용");
		board.setWriter("user00");
		
		int count = mapper.update(board);
		log.info("UPDATE COUNT: " + count);
	}
	

	@Test
	public void testSearch() {

		log.info("testSearch시작");
		Criteria cri = new Criteria();
		cri.setKeyword("새로");
		cri.setType("TC");
		
		
		log.info("testSearch cri : " + cri);

		List<BoardVO> list = mapper.getListWithPaging(cri);
		
		list.forEach(board -> log.info(board));
		
	}
	
}
