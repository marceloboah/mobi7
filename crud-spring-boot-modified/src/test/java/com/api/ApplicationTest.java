package com.api;

import static org.hamcrest.Matchers.equalTo;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import javax.persistence.EntityManager;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.api.business.PoisBusinessObject;
import com.api.controller.JsonReaderController;
import com.api.controller.PoisController;
import com.api.repository.PoisRepository;

@RunWith(SpringRunner.class)
@SpringBootTest 
@AutoConfigureMockMvc
public class ApplicationTest {
	
	//classe para testes unitários usando @Autowired 
	
	private static final Logger log = LoggerFactory.getLogger(ApplicationTest.class);
	
	@Autowired
	private JsonReaderController jsonReaderController;
	
	@Autowired
	private PoisController poisController;
	
	@Autowired
	private  PoisBusinessObject poisBusinessObject;
	
	@Autowired
	private PoisRepository productRepository;
	
	@Autowired
	private MockMvc mvc;
	
	@Mock
	private EntityManager manager;
	
	@Before
    public void setUp() throws Exception {
		
		MockitoAnnotations.initMocks(this);
		manager = Mockito.mock(EntityManager.class);
	    productRepository.deleteAll();
    }
	
	@After
	public void getTesteHttp() throws Exception {
		log.info("getTesteHttp");
	            mvc.perform(MockMvcRequestBuilders.get("/api/teste").accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().string(equalTo("Teste")));
		
	}
	
	
	


	
	
}
