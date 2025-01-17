package com.controler;

import com.db.Instructions;
import com.model.InstructionsDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;


@WebServlet("/oes.controller.InstructionInsert")
public class InstructionInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;


    public InstructionInsert() {
        super();
        // TODO Auto-generated constructor stub
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		
		String instruction = request.getParameter("inst");
		
		Instructions ist = new Instructions();
		ist.setInstruction(instruction);
		
		boolean status = InstructionsDao.insertInstruction(ist);
		if(status)
		{
			String msg1 = "Instruction Added";
	    	response.sendRedirect("AddInstruction.jsp?msg1="+msg1);
		}
		else
		{
			String msg2 = "Instruction not Added";
	    	response.sendRedirect("AddInstruction.jsp?msg2="+msg2);
		}
		

	}



}
