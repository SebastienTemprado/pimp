package fr.stemprado.apps.pimp.it;

import static fr.stemprado.apps.pimp.test.matchers.ErrorMatcher.errors;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.Arrays;
import java.util.Locale;

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
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.context.MessageSource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import fr.stemprado.apps.pimp.Pimp;
import fr.stemprado.apps.pimp.beans.dtos.UserDTO;
import fr.stemprado.apps.pimp.constants.Properties;
import fr.stemprado.apps.pimp.constants.Views;
import fr.stemprado.apps.pimp.constants.api.AuthenticationApi;
import fr.stemprado.apps.pimp.services.constants.api.UserApi;
import fr.stemprado.apps.pimp.test.builders.UserDTOBuilder;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT, classes={Pimp.class})
public class AuthenticationIT {
	
	@Autowired
	private TestRestTemplate restTemplate;
	
    private MockMvc mockMvc;

    @Value(Properties.Rest.RESOURCES_URL_BASE)
	private String REST_RESOURCES_URL;
    
    @Autowired
    private WebApplicationContext context;
    
    @Autowired
	private MessageSource messageSource;
    
    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }
    
    @Test
	public void signupFormValidationError() throws Exception {
		UserDTO userDTO = UserDTOBuilder.init().username("1johndoe").lastname("d").firstname("j").build();
		
		mockMvc.perform(post(AuthenticationApi.SIGN_UP)
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
						).andExpect(view().name(Views.Authentication.SIGN_UP_FORM));
	}
	
	@Test
	public void wrongPasswordConfirmationInSignupForm() throws Exception {
		UserDTO userDTO = UserDTOBuilder.init().password("ab123456").passwordConfirmation("ab123457").build();
		
		mockMvc.perform(post(AuthenticationApi.SIGN_UP)
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
								new BasicNameValuePair("username", userDTO.getUsername()),
								new BasicNameValuePair("password", userDTO.getPassword()),
								new BasicNameValuePair("passwordConfirmation", userDTO.getPasswordConfirmation()),
								new BasicNameValuePair("lastname", userDTO.getLastname()),
								new BasicNameValuePair("firstname", userDTO.getFirstname()),
								new BasicNameValuePair("email", userDTO.getEmail())
							)))).locale(Locale.ENGLISH)
						).andExpect(model().attributeHasFieldErrors("userDTO","passwordConfirmation")
						).andExpect(errors().hasError("userDTO", messageSource.getMessage("passwordConfirmation-matching-error", null, Locale.ENGLISH))
						).andExpect(view().name(Views.Authentication.SIGN_UP_FORM));
	}
	
	@Test
	public void signupFormOK() throws Exception {
		UserDTO userDTO = UserDTOBuilder.init().build();
		
		final String unEncodedPassword = userDTO.getPassword();
		mockMvc.perform(post(AuthenticationApi.SIGN_UP)
							.contentType(MediaType.APPLICATION_FORM_URLENCODED)
							.content(EntityUtils.toString(new UrlEncodedFormEntity(Arrays.asList(
								new BasicNameValuePair("username", userDTO.getUsername()),
								new BasicNameValuePair("password", unEncodedPassword),
								new BasicNameValuePair("passwordConfirmation", userDTO.getPasswordConfirmation()),
								new BasicNameValuePair("lastname", userDTO.getLastname()),
								new BasicNameValuePair("firstname", userDTO.getFirstname()),
								new BasicNameValuePair("email", userDTO.getEmail())
							))))
						).andExpect(model().hasNoErrors()
						).andExpect(view().name(Views.Authentication.LOGIN));

		UserDTO result = restTemplate.getForObject(UserApi.GET_USER, UserDTO.class, userDTO.getUsername());
		assertThat(result.getUsername()).isEqualTo(userDTO.getUsername());
		assertThat(result.getPassword()).isNotEqualTo(unEncodedPassword);
		assertThat(result.getLastname()).isEqualTo(userDTO.getLastname());
		assertThat(result.getFirstname()).isEqualTo(userDTO.getFirstname());
		assertThat(result.getEmail()).isEqualTo(userDTO.getEmail());
		
	}
}
