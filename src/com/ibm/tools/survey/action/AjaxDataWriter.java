package com.ibm.tools.survey.action;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

public class AjaxDataWriter<T> {

	public void write(HttpServletResponse response, T data,
			Class<T> cls) {
		ServletOutputStream servletOutputStream = null;
		String jsonData=null;
		try {
			jsonData=new Gson().toJson(data, cls);
			servletOutputStream = response.getOutputStream();
			servletOutputStream.write(jsonData.getBytes());
			servletOutputStream.flush();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				servletOutputStream.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
