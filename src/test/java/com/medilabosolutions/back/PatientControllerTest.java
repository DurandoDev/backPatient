package com.medilabosolutions.back;

import com.medilabosolutions.back.model.Patient;
import com.medilabosolutions.back.repository.PatientRepo;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import java.time.LocalDate;
import org.springframework.test.web.servlet.MvcResult;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.properties")
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class PatientControllerTest {

	@Autowired
	private PatientRepo patientRepo;

	@Autowired
	MockMvc mockMvc;

	Patient p = new Patient();

	Integer patientId;

	@BeforeEach
	public void setup() throws Exception {
		Patient p = new Patient();
		p.setFirstname("toto");
		p.setName("totoName");
		p.setAddress("totoAddress");
		p.setGender("M");
		p.setDateOfBirth(LocalDate.parse("2001-01-01"));
		p.setPhoneNumber("1111-222-333");

		MvcResult result = mockMvc.perform(post("/Patients")
						.content("{\"firstname\":\"" + p.getFirstname() + "\"," +
								"\"name\":\"" + p.getName() + "\"," +
								"\"dateOfBirth\":\"" + p.getDateOfBirth() + "\"," +
								"\"gender\":\"" + p.getGender() + "\"," +
								"\"address\":\"" + p.getAddress() + "\"," +
								"\"phoneNumber\":\"" + p.getPhoneNumber() + "\"}")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated())
				.andReturn();

		String location = result.getResponse().getHeader("Location");
		String[] segments = location.split("/");
		patientId = Integer.parseInt(segments[segments.length - 1]);
	}

	@AfterEach
	public void deleteSetup() throws Exception {
		patientRepo.delete(p);
	}

	@AfterAll
	public void deleteTestData() throws Exception {
		patientRepo.deleteAll();
	}

	@Test
	public void listPatientTest() throws Exception {
		mockMvc.perform(get("/Patients")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content()
						.contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$[*].name", hasItem("totoName")));
	}

	@Test
	public void addPatientTest() throws Exception {
		mockMvc.perform(post("/Patients")
						.content("{\"firstname\":\"tata\"," +
								"\"name\":\"tataName\"," +
								"\"dateOfBirth\":\"2001-01-01\"," +
								"\"gender\":\"F\"," +
								"\"address\":\"tataAddress\"," +
								"\"phoneNumber\":\"9999-111-222\"}")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());
	}

	@Test
	public void deletePatientTest() throws Exception {
		Patient newPatient = new Patient(9999,"tutu","tutuName",LocalDate.parse("2001-01-01"),"M","tutuAddress","999-999-999");
		mockMvc.perform(delete("/Patients/{id}", 9999))
				.andExpect(status().isOk());
	}

	@Test
	public void updatePatientTest() throws Exception {
		mockMvc.perform(put("/Patients/{id}",patientId)
						.content("{\"firstname\":\"totoUpdated\"," +
								"\"name\":\"totoNameUpdated\"," +
								"\"dateOfBirth\":\"2001-01-01\"," +
								"\"gender\":\"M\"," +
								"\"address\":\"totoAddressUpdated\"," +
								"\"phoneNumber\":\"1111-222-333\"}")
						.contentType(MediaType.APPLICATION_JSON)
						.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk());
	}
}
