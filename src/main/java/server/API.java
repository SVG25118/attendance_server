package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class API
 */
@WebServlet("/API")
public class API extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		PrintWriter output = response.getWriter();
		
		String course = request.getParameter("course");
		String uid = request.getParameter("uid");
		String loc = request.getParameter("loc");
		String desc = request.getParameter("desc");
		
		APIResponse apiResponse = new APIResponse();
		
		// Pre-validation of parameters
		if (command == null) {
			apiResponse.addResponse("Command is a mandatory parameter.", "FAILURE");
		}
		
		// Execute command
		if (!apiResponse.hasError()) {
			if ("query".equals(command)) {
				try	{
					if (DataStore.query(course, uid)) {
						apiResponse.addResponse("Device found in checkin list.", "FAILURE");
					} else {
						apiResponse.addResponse("Device not found in checkin list.", "SUCCESS");
					}
				} catch (Exception e) {
					apiResponse.addResponse("Unable to access group data.", "FAILURE");
				}
			} else if ("addCourse".equals(command)) {
				String tempCourse = request.getParameter("course");
				try {
					if((loc==null)||(desc==null)) {						
						DataStore.addCourse(tempCourse);
					} else {						
						DataStore.addCourse(tempCourse,loc,desc);
					}
					apiResponse.addResponse("Course added successfully.", "SUCCESS");
				} catch (Exception e) {
					apiResponse.addResponse("Unable to add new course.", "FAILURE");
				}				
			} else if ("removeCourse".equals(command)) {
				String tempCourse = request.getParameter("course");
				try {
					boolean result = DataStore.removeCourse(tempCourse);
					if(result) {
						apiResponse.addResponse("Course removed successfully.", "SUCCESS");
					} else {
						apiResponse.addResponse("Unable to remove course.", "FAILURE");
					}					
				} catch (Exception e) {
					apiResponse.addResponse("Unable to remove course.", "FAILURE");
				}				
			} else if ("addQuestion".equals(command)) {
				String question = request.getParameter("question");
				try	{
					DataStore.addQuestion(course, question);
					apiResponse.addResponse("Question added successfully.", "SUCCESS");
				} catch (Exception e) {
					apiResponse.addResponse("Unable to access group data.", "FAILURE");
				}				
			} else if ("addAnswer".equals(command)) {
				String question = request.getParameter("question");
				String answer = request.getParameter("answer");
				try	{
					DataStore.addAnswer(course,uid,question,answer);
					apiResponse.addResponse("Answer added successfully.", "SUCCESS");
				} catch (Exception e) {
					apiResponse.addResponse("Unable to access group data.", "FAILURE");
				}				
			} else if ("checkin".equals(command)) {
				String key = request.getParameter("key");
				try	{
					Checkin checkin = new Checkin(new Student(uid), key);
					DataStore.checkin(course, checkin);
					apiResponse.addResponse("Check in successful.", "SUCCESS");
				} catch (Exception e) {
					apiResponse.addResponse("Unable to access group data.", "FAILURE");
				}				
			} else if ("export".equals(command)) {
				try {
					if(uid != null) {
						apiResponse.addResponse(DataStore.getKey(course, uid), "SUCCESS");
					} else {
						apiResponse.addResponse(DataStore.getDeviceList(course), "SUCCESS");
					}
				} catch (Exception e) {
					apiResponse.addResponse("Unable to access group data.", "FAILURE");
				}				
			} else if ("exportCourses".equals(command)) {
				try {
					List<String> courseList = new ArrayList<String>();
					for (Map.Entry<String,Course> c : DataStore.getCourses()) {
						courseList.add(c.getValue().getName());
					}
					apiResponse.addResponse(courseList.toString(), "SUCCESS");
				} catch (Exception e) {
					apiResponse.addResponse("Unable to access group data.", "FAILURE");
				}				
			} else if ("exportCourseInfo".equals(command)) {
				try {
					if(course!=null) {
						List<String> courseInfoList = DataStore.getCourseInfo(course);
						
						apiResponse.addResponse(courseInfoList.toString(), "SUCCESS");
					} else {
						apiResponse.addResponse("No course provided.", "FAILURE");					
					}
				} catch (Exception e) {
					apiResponse.addResponse("Unable to access course data.", "FAILURE");
				}				
			} else if ("remove".equals(command)) {
				try {
					if ("*".equals(uid)) {
						DataStore.reset(course);
						apiResponse.addResponse("The list has been cleared", "SUCCESS");
					} else {
						if (DataStore.query(course, uid)) {
							DataStore.remove(course, uid);
							apiResponse.addResponse("UID removed from check-in list", "SUCCESS");
						} else {
							apiResponse.addResponse("UID not found in list", "FAILURE");
						}
					}
				} catch (Exception e) {
					apiResponse.addResponse("Unable to access group data.", "FAILURE");
				}				
			} else {
				apiResponse.addResponse("Unknown command provided: " + command + ".", "FAILURE");
			}
		}
		
		output.print(apiResponse.toJSON());
		
		log(request, apiResponse);
	}
	
	private void log(HttpServletRequest request, APIResponse response) {
		String logString = "";
		
		logString = request.getRemoteAddr();
		logString += ": ";
		logString += response.toJSON().replaceAll("\n", "");
		
		System.out.println(logString);
	}

	private class APIResponse {
		String response = null;
		String code = "";
		
		public void addResponse(String response, String code) {
			this.code = code;
			
			if (this.response == null) {
				this.response = response;
			} else {
				this.response += " " + response;
			}
		}
		
		public boolean hasError() {
			return ("FAILURE".equals(code));
		}
		
		public String toJSON() {
			String json = "{\n";
			json += "   \"apiResponse\": {\n";
			json += "      \"code\": \"" + code + "\"\n";
			json += "      \"response\": \"" + response + "\"\n";
			json += "   }\n";
			json += "}\n\n";
			
			return json;
		}
	}
}
