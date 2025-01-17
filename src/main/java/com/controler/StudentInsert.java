package com.controler;

import com.db.Students;
import com.model.StudentsDao;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
//import javax.servlet.*;
//import javax.servlet.annotation.*;
//import javax.servlet.http.*;
//
//import oes.db.Students;
//import oes.model.StudentsDao;


@WebServlet("/oes.controller.StudentInsert")
public class StudentInsert extends HttpServlet {
    private static final long serialVersionUID = 1L;


    public StudentInsert() {
        super();
        // TODO Auto-generated constructor stub
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        String username = request.getParameter("uname");
        String password = request.getParameter("pass");
        String name = request.getParameter("name");

        Students st = new Students();
        st.setName(name);
        st.setPassword(password);
        st.setUsername(username);

        boolean usernameExists = StudentsDao.isUserNameExists(username);
        boolean isStringName = StudentsDao.isNameString(name);
        boolean isPasswordLength = StudentsDao.isPasswordLength(password);

        if (usernameExists) {
            String msg2 = "username already exists";
            response.sendRedirect("AddStudent.jsp?msg2=" + msg2);
        } else if (!isStringName) {
            String msg3 = "name  must contains only characters.";
            response.sendRedirect("AddStudent.jsp?msg3=" + msg3);
        } else if (!isPasswordLength) {
            String msg4 = "Password length must ge betweeen 6 and 10 characters.";
            response.sendRedirect("AddStudent.jsp?msg4=" + msg4);
        } else {
            boolean status = StudentsDao.insertStudent(st);
            if (status) {
                String msg1 = "Student Added";
                response.sendRedirect("AddStudent.jsp?msg1=" + msg1);
            } else {
                String msg2 = "Student not Added";
                response.sendRedirect("AddStudent.jsp?msg2=" + msg2);
            }
        }
    }
}
