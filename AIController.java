package com.shopzone.ai;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/ai")
public class AIController {
	@Value("${openrouter.api_key}")
	private String apiKey;

@PostMapping("/chat")
public String chat(@RequestBody Map<String , String> request) {
 
 String userMessage = request.get("message");
 
if(userMessage == null || userMessage.trim().isEmpty()) {
 return "Please enter a message";
}
 String systemPrompt = """
              you are shopZone AI , a friendly shopping assistant. 
              Help Customers with product , shopping , budgets , 
              comparision and buying suggestion
              
              Be friendly , and conversational. 
              use emojies naturally , 
              Ask a Short question if you need more information.
              keep answer useful and easy understand.
              """;
 
 String url = "https://openrouter.ai/api/v1/chat/completions";

  	HttpHeaders headers = new HttpHeaders();
  			headers.setContentType(MediaType.APPLICATION_JSON);
  			
  headers.setBearerAuth(apiKey);
 
  Map<String , Object> systemMessage = 
  Map.of(
   "role" , "system" , 
  "content" ,  
  systemPrompt
);
  
 Map<String , Object> userMessageData = 
     Map.of(
  "role" , "user" , 
   "content" , 
   userMessage
 );
 
 Map<String , Object> requestBody = 
 Map.of(
"model" , "openrouter/free",
 "messages" , List.of(systemMessage , userMessageData) 
 );
HttpEntity<Map <String , Object>> entity = 
  new HttpEntity<>(requestBody , headers);
RestTemplate restTemplate = new RestTemplate();
 ResponseEntity<Map> response = restTemplate.exchange
(url, HttpMethod.POST, entity , Map.class);
 
  Map responseBody = response.getBody();
  if(responseBody == null) {
 return "Sorry , no response received";
 }
 
 List choices  = (List) responseBody.get("choices");

 if(choices ==  null || choices.isEmpty()) {
  return "Sorry , i couldn't generate a response";
 }
 Map firstChoice = (Map) choices.get(0);
Map messageData =  (Map) firstChoice.get("message");
 
if(messageData == null) {
return "Sorry , something went wrong";
}

Object content = messageData.get("content");

if (content == null) {
    return "Sorry, I received an empty response";
}

return content.toString();
}
}