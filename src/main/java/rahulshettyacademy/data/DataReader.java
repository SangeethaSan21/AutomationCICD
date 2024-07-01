package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
public List<HashMap<String, String>> getJsonDataToMap() throws IOException 
{
	//read json to string
//	String JsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\main\\java\\rahulshettyacademy\\data\\purchaseOrder.json"));
	String JsonContent = FileUtils.readFileToString(new File(System.getProperty("User.dir") + "//src//main//java//rahulshettyacademy//data//urchaseOrder.json"),StandardCharsets.UTF_8);
	
	//Convert string Hashmap usinf Jackson databind
	
	ObjectMapper mapper = new ObjectMapper();
	List<HashMap<String,String>> data = mapper.readValue(JsonContent,new TypeReference <List<HashMap<String,String>>>(){
		
	});
	return data;
}
}
