package com.project.SmartTicket_GoogleSheet;


	import com.google.api.client.auth.oauth2.Credential;
	import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
	import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
	import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
	import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
	import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
	import com.google.api.client.json.JsonFactory;
	import com.google.api.client.json.jackson2.JacksonFactory;
	import com.google.api.client.util.store.FileDataStoreFactory;
	import com.google.api.services.sheets.v4.Sheets;
	import com.google.api.services.sheets.v4.SheetsScopes;
	import com.google.api.services.sheets.v4.model.ValueRange;

	import java.io.File;
	import java.io.FileInputStream;
	import java.io.FileNotFoundException;
	import java.io.IOException;
	import java.io.InputStream;
	import java.io.InputStreamReader;
	import java.security.GeneralSecurityException;
	import java.util.Collections;
import java.util.Hashtable;
import java.util.List;
public class TestGoogleSheet {
	private static final String APPLICATION_NAME = "SmartTicketUpgrade";
	private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();
	private static final String TOKENS_DIRECTORY_PATH = "C:\\Users\\Vinod\\Credentials\\tokens";

	private static final List<String> SCOPES = Collections.singletonList(SheetsScopes.SPREADSHEETS_READONLY); //
	
	/*
	 * private static String ProjectPath = System.getProperty("user.dir"); private
	 * static final String CREDENTIALS_FILE_PATH =
	 * ProjectPath+"/client_secret.json";
	 */
	 
	private static final String CREDENTIALS_FILE_PATH = "/client_secret.json ";
	private static Credential getCredentials(final NetHttpTransport HTTP_TRANSPORT) throws IOException {
		File initialFile = new File("C:\\Users\\Vinod\\Credentials\\client_secret.json");
	    InputStream in = new FileInputStream(initialFile);
		if (in == null) {
			throw new FileNotFoundException("Resource not found: " + CREDENTIALS_FILE_PATH);
		}
		GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(JSON_FACTORY, new InputStreamReader(in));

		// Build flow and trigger user authorization request.
		GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
				clientSecrets, SCOPES)
						.setDataStoreFactory(new FileDataStoreFactory(new java.io.File(TOKENS_DIRECTORY_PATH)))
						.setAccessType("offline").build();
		LocalServerReceiver receiver = new LocalServerReceiver.Builder().setPort(8888).build();
		return new AuthorizationCodeInstalledApp(flow, receiver).authorize("user");
	}
	
	/*public static  Sheets getSheetsService() throws IOException{
		Credential credential = authorize();
		return new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).setApplicationName(APPLICATION_NAME).build();
	}*/
	public static List<List<Object>> GetData() throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range= "Login_data!A2:C";
		
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	public static List<List<Object>> Get_Data()throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range = "Manage_Tenants!A2:B";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	public static List<List<Object>> Get_Su()throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range = "Manage_SuperUser!A2:B";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	public static List<List<Object>> Get_ImportTicket()throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range = "Import_Ticket!A2:C";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	public static List<List<Object>> Get_ImportUser()throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range = "Import_User!A2:B";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	public static List<List<Object>> Get_ManageUser()throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range = "Manage_User!A2:D";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	public static List<List<Object>> Get_ManageUserField()throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range = "Manage_Userfield!A2:C";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	public static List<List<Object>> Get_ManageRoles()throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range = "Manage_Roles!A2:B";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	public static List<List<Object>> Get_CreateForms()throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range = "Create_Forms!A2:C";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	public static List<List<Object>> Get_ManageWorkflow()throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range = "Manage_workflow!A2:D";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	public static List<List<Object>> Get_ManageField()throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range = "Manage_Fields!A2:C";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	public static List<List<Object>> Get_ResponseTemplate()throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range = "Response_Template!A2:D";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	public static List<List<Object>> Get_APIConnectivity()throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range = "API_Connectivity!A2:F";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	public static List<List<Object>> Get_Ticket()throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range = "Ticket!A2:I";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	public static List<List<Object>> Get_Forgotpassword()throws GeneralSecurityException,IOException{
		final NetHttpTransport HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
		final String spreadsheetId = "1WcD-vJXI2z4wZ9TQ1tHiT8cmmrKgD8v3cqKB-cPctRo";
		final String range = "Forgotpassword!A2:I";
		Sheets service = new Sheets.Builder(HTTP_TRANSPORT, JSON_FACTORY, getCredentials(HTTP_TRANSPORT)).setApplicationName(APPLICATION_NAME).build();
		ValueRange response = service.spreadsheets().values().get(spreadsheetId, range).execute();
		List<List<Object>> values = response.getValues();
			return values;
	}
	
}
	/*public static void main(String[] args) throws IOException, GeneralSecurityException {
		/*
		 * public static List<List<Object>> getData(String range) throws IOException,
		 * GeneralSecurityException { // Build a new authorized API client service.
		 */
		/*List<List<Object>> values = GetData();
		if (values == null || values.isEmpty()) {
			System.out.println("No data found.");
		} else {
			System.out.println("Emailid, Password");
			for (List row : values) {
				// Print columns A and E, which correspond to indices 0 and 4.
				System.out.printf("%s, %s\n", row.get(0), row.get(1));
			}
			List<List<Object>> values = Get_Data();
			if(values == null || values.isEmpty()) {
		}
	}
}*/
