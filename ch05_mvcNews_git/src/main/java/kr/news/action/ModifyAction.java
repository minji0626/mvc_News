package kr.news.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.news.dao.NewsDAO;
import kr.news.vo.NewsVO;
import kr.util.FileUtil;

public class ModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		NewsVO newsVO = new NewsVO();
		newsVO.setNum(Integer.parseInt(request.getParameter("num")));
		newsVO.setTitle(request.getParameter("title"));
		newsVO.setWriter(request.getParameter("writer"));
		newsVO.setPasswd(request.getParameter("passwd"));
		newsVO.setEmail(request.getParameter("email"));
		newsVO.setArticle(request.getParameter("article"));
		newsVO.setFilename(FileUtil.createFile(request,"filename"));

		NewsDAO dao = NewsDAO.getInstance();
		NewsVO db_board = dao.getNews(newsVO.getNum());
		
		boolean check = false;
		if(db_board != null) { // 게시물이 존재할 때 비밀번호 확인
			check = db_board.isCheckedPassword(newsVO.getPasswd());
		}
		
		if(check) { // 비밀번호가 일치할 때 update 메소드 실행
			dao.update(newsVO);
			
			// 상세페이지로 이동하기 위해 글 번호를 저장
			request.setAttribute("num", newsVO.getNum());
		} 
		request.setAttribute("check", check);
		
		return "/WEB-INF/views/modify.jsp";
	}

}
