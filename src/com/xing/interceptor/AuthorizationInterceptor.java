package com.xing.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xing.domain.User;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/** 
 * ����������ʵ��HandlerInterceptor�ӿ�
 * */ 
public class AuthorizationInterceptor  implements HandlerInterceptor {

	// ������"/loginForm"��"/login"����
	private static final String[] IGNORE_URI = {"/loginForm", "/login"};
	
	 /** 
     * �÷������������������֮��ִ�У� ��Ҫ����������������Դ�ģ�
     * �÷���Ҳֻ���ڵ�ǰInterceptor��preHandle�����ķ���ֵΪtrueʱ�Ż�ִ�С� 
     */  
	@Override
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception exception)
			throws Exception {
		System.out.println("AuthorizationInterceptor afterCompletion --> ");
		
	}
	/** 
     * �÷�������Controller�ķ�������֮��ִ�У� �����п��Զ�ModelAndView���в��� ��
     * �÷���Ҳֻ���ڵ�ǰInterceptor��preHandle�����ķ���ֵΪtrueʱ�Ż�ִ�С� 
     */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler, ModelAndView mv) throws Exception {
		System.out.println("AuthorizationInterceptor postHandle --> ");
		
	}

	 /** 
     * preHandle�����ǽ��д����������õģ��÷�������Controller����֮ǰ���е��ã�
     * �÷����ķ���ֵΪtrue�������Ż��������ִ�У��÷����ķ���ֵΪfalse��ʱ����������ͽ����ˡ� 
     */  
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object handler) throws Exception {
		System.out.println("AuthorizationInterceptor preHandle --> ");
		// flag���������ж��û��Ƿ��¼��Ĭ��Ϊfalse 
		boolean flag = false; 
		//��ȡ�����·�������ж�
		String servletPath = request.getServletPath();
		// �ж������Ƿ���Ҫ����
        for (String s : IGNORE_URI) {
            if (servletPath.contains(s)) {
                flag = true;
                break;
            }
        }
        // ��������
        if (!flag){
        	// 1.��ȡsession�е��û� 
        	User user = (User) request.getSession().getAttribute("user");
        	// 2.�ж��û��Ƿ��Ѿ���¼ 
        	if(user == null){
        		// ����û�û�е�¼����������ʾ��Ϣ����ת����¼ҳ��
        		 System.out.println("AuthorizationInterceptor��������");
        		 request.setAttribute("message", "���ȵ�¼�ٷ�����վ");
        		 request.getRequestDispatcher("loginForm").forward(request, response);
        	}else{
        		// ����û��Ѿ���¼������֤ͨ��������
        		 System.out.println("AuthorizationInterceptor��������");
        		 flag = true;
        	}
        }
        return flag;
		
	}

}
