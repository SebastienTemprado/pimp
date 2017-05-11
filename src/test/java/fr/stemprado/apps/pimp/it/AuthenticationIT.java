package fr.stemprado.apps.pimp.it;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;

import fr.stemprado.apps.pimp.beans.dtos.UserDTO;
import fr.stemprado.apps.pimp.controllers.HomeController;
import fr.stemprado.apps.pimp.test.builders.UserDTOBuilder;

//TODO IT test for DB?
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthenticationIT {
	
    private MockMvc mockMvc;
    
    @Value("${rest-resources-url}")
	private String REST_RESOURCES_URL;
	
	@Autowired
	private HomeController homeController;
	
	@MockBean
	private RestTemplate restTemplate;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
    }
    
    @Test
	public void signupFormValidationError() throws Exception {
		UserDTO userDTO = UserDTOBuilder.init().username("1johndoe").lastname("d").firstname("j").build();
		
		mockMvc.perform(post("/signup")
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
								new BasicNameValuePair("username", userDTO.getUsername()),
								new BasicNameValuePair("password", userDTO.getPassword()),
								new BasicNameValuePair("passwordConfirmation", userDTO.getPasswordConfirmation()),
								new BasicNameValuePair("lastname", userDTO.getLastname()),
								new BasicNameValuePair("firstname", userDTO.getFirstname()),
								new BasicNameValuePair("email", userDTO.getEmail())
							))))
						).andExpect(model().hasErrors()
						).andExpect(view().name("signupForm"));
	}
	
	@Test
	public void wrongPasswordConfirmationInSignupForm() throws Exception {
		UserDTO userDTO = UserDTOBuilder.init().password("ab123456").passwordConfirmation("ab123457").build();
		
		//TODO : test on password confirmation error message?
		mockMvc.perform(post("/signup")
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
								new BasicNameValuePair("username", userDTO.getUsername()),
								new BasicNameValuePair("password", userDTO.getPassword()),
								new BasicNameValuePair("passwordConfirmation", userDTO.getPasswordConfirmation()),
								new BasicNameValuePair("lastname", userDTO.getLastname()),
								new BasicNameValuePair("firstname", userDTO.getFirstname()),
								new BasicNameValuePair("email", userDTO.getEmail())
							))))
						).andExpect(model().attributeHasFieldErrors("userDTO","passwordConfirmation")
						).andExpect(view().name("signupForm"));
	}
	
	@Test
	public void signupFormOK() throws Exception {
		UserDTO userDTO = UserDTOBuilder.init().build();
		
		mockMvc.perform(post("/signup")
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
								new BasicNameValuePair("username", userDTO.getUsername()),
								new BasicNameValuePair("password", userDTO.getPassword()),
								new BasicNameValuePair("passwordConfirmation", userDTO.getPasswordConfirmation()),
								new BasicNameValuePair("lastname", userDTO.getLastname()),
								new BasicNameValuePair("firstname", userDTO.getFirstname()),
								new BasicNameValuePair("email", userDTO.getEmail())
							))))
						).andExpect(model().hasNoErrors()
						).andExpect(view().name("login"));
		
		//TODO argumentCaptor on userDTO
		verify(restTemplate).postForObject(eq(REST_RESOURCES_URL + "addUser"), any(UserDTO.class), eq(UserDTO.class));
	}
}
