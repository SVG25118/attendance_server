package server;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

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
		
		String group = request.getParameter("group");
		String uid = request.getParameter("uid");
		
		APIResponse apiResponse = new APIResponse();
		
		// Pre-validation of parameters
		if (command == null)
		{
			apiResponse.addResponse("Command is a mandatory parameter.", "ERROR");
		}
		
		if (group == null)
		{
			apiResponse.addResponse("Group is a mandatory parameter.", "ERROR");
		}
		
		// Execute command
		if (!apiResponse.hasError())
		{
			if ("query".equals(command))
			{
				try
				{
					if (DataStore.query(group, uid))
					{
						apiResponse.addResponse("Device found in checkin list.", "ERROR");
					}
					else
					{
						apiResponse.addResponse("Device not found in checkin list.", "SUCCESS");
					}
				}
				catch (Exception e)
				{
					apiResponse.addResponse("Unable to access group data.", "ERROR");
				}
			}
			else if ("checkin".equals(command))
			{
				String key = request.getParameter("key");
				try
				{
					Checkin checkin = new Checkin(uid, key);
					DataStore.checkin(group, checkin);
					apiResponse.addResponse("Check in successful.", "SUCCESS");
				}
				catch (Exception e)
				{
					apiResponse.addResponse("Unable to access group data.", "ERROR");
				}				
			}
			else if ("export".equals(command))
			{
				try
				{
					if(uid != null) {
						apiResponse.addResponse(DataStore.getKey(group, uid), "SUCCESS");
					} else {
						apiResponse.addResponse(DataStore.getDeviceList(group), "SUCCESS");
					}
				}
				catch (Exception e)
				{
					apiResponse.addResponse("Unable to access group data.", "ERROR");
				}				
			}
			else if ("remove".equals(command))
			{
				try
				{
					if ("*".equals(uid))
					{
						DataStore.reset(group);
						apiResponse.addResponse("The list has been cleared", "SUCCESS");
					}
					else
					{
						if (DataStore.query(group, uid))
						{
							DataStore.remove(group, uid);
							apiResponse.addResponse("UID removed from check-in list", "SUCCESS");
						}
						else
						{
							apiResponse.addResponse("UID not found in list", "ERROR");
						}
					}
				}
				catch (Exception e)
				{
					apiResponse.addResponse("Unable to access group data.", "ERROR");
				}				
			}
			else
			{
				apiResponse.addResponse("Unknown command provided: " + command + ".", "ERROR");
			}
		}
		
		output.print(apiResponse.toJSON());
		
		log(request, apiResponse);
	}
	
	private void log(HttpServletRequest request, APIResponse response)
	{
		String logString = "";
		
		logString = request.getRemoteAddr();
		logString += ": ";
		logString += response.toJSON().replaceAll("\n", "");
		
		System.out.println(logString);
	}

	private class APIResponse {
		String response = null;
		String code = "";
		
		public void addResponse(String response, String code)
		{
			this.code = code;
			
			if (this.response == null)
			{
				this.response = response;
			}
			else
			{
				this.response += " " + response;
			}
		}
		
		public boolean hasError()
		{
			return ("ERROR".equals(code));
		}
		
		public String toJSON()
		{
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
