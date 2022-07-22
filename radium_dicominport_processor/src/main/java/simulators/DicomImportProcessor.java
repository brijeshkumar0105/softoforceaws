package simulators;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.HashMap;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

public class DicomImportProcessor implements RequestStreamHandler 
{
	Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
    	LambdaLogger logger = context.getLogger();
        BufferedReader reader = new BufferedReader(new InputStreamReader(input));
        try
        {
          HashMap event = gson.fromJson(reader, HashMap.class);
          logger.log("STREAM TYPE: " + input.getClass().toString());
          logger.log("EVENT TYPE: " + event.getClass().toString());
        }
        catch (IllegalStateException | JsonSyntaxException exception)
        {
          logger.log(exception.toString());
        }
        finally
        {
          reader.close();
        }
    }
}