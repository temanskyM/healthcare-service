package ru.netology.patient.service.medical;

import org.junit.Test;
import org.mockito.Mockito;
import ru.netology.patient.entity.BloodPressure;
import ru.netology.patient.entity.HealthInfo;
import ru.netology.patient.entity.PatientInfo;
import ru.netology.patient.repository.PatientInfoRepository;
import ru.netology.patient.service.alert.SendAlertService;
import ru.netology.patient.service.alert.SendAlertServiceImpl;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;

public class MedicalServiceImplTest {

    @Test
    public void checkBloodPressure_SendMessageIfNeedHelp() {
        //Given
        String id1 = "1";
        PatientInfo patientInfo =  new PatientInfo("Иван", "Петров", LocalDate.of(1980, 11, 26),
                new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80)));

        PatientInfoRepository patientInfoRepository = Mockito.mock((PatientInfoRepository.class));
        Mockito.when(patientInfoRepository.getById(id1)).thenReturn(patientInfo);

        SendAlertService alertService = Mockito.mock(SendAlertService.class);

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);

        //When
        BloodPressure currentPressure = new BloodPressure(60, 120);
        medicalService.checkBloodPressure(id1, currentPressure);

        //Then
        Mockito.verify(alertService, Mockito.times(1)).send("Warning, patient with id: null, need help");
    }    @Test
    public void checkBloodPressure_NoSendMessageIfNoNeedHelp() {
        //Given
        String id1 = "1";
        PatientInfo patientInfo =  new PatientInfo("Иван", "Петров", LocalDate.of(1980, 11, 26),
                new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80)));

        PatientInfoRepository patientInfoRepository = Mockito.mock((PatientInfoRepository.class));
        Mockito.when(patientInfoRepository.getById(id1)).thenReturn(patientInfo);

        SendAlertService alertService = Mockito.mock(SendAlertService.class);

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);

        //When
        BloodPressure currentPressure = new BloodPressure(120, 80);
        medicalService.checkBloodPressure(id1, currentPressure);

        //Then
        Mockito.verify(alertService, Mockito.times(0)).send("Warning, patient with id: null, need help");
    }

    @Test
    public void checkTemperature_SendMessageIfNeedHelp() {
        //Given
        String id1 = "1";
        PatientInfo patientInfo =  new PatientInfo("Иван", "Петров", LocalDate.of(1980, 11, 26),
                new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80)));

        PatientInfoRepository patientInfoRepository = Mockito.mock((PatientInfoRepository.class));
        Mockito.when(patientInfoRepository.getById(id1)).thenReturn(patientInfo);

        SendAlertService alertService = Mockito.mock(SendAlertService.class);

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);

        //When
        BigDecimal currentTemperature = new BigDecimal("39.9");
        medicalService.checkTemperature(id1, currentTemperature);

        //Then
        Mockito.verify(alertService, Mockito.times(1)).send("Warning, patient with id: null, need help");
    }

    @Test
    public void checkTemperature_NoSendMessageIfNoNeedHelp() {
        //Given
        String id1 = "1";
        PatientInfo patientInfo =  new PatientInfo("Иван", "Петров", LocalDate.of(1980, 11, 26),
                new HealthInfo(new BigDecimal("36.65"), new BloodPressure(120, 80)));

        PatientInfoRepository patientInfoRepository = Mockito.mock((PatientInfoRepository.class));
        Mockito.when(patientInfoRepository.getById(id1)).thenReturn(patientInfo);

        SendAlertService alertService = Mockito.mock(SendAlertService.class);

        MedicalService medicalService = new MedicalServiceImpl(patientInfoRepository, alertService);

        //When
        BigDecimal currentTemperature = new BigDecimal("36.6");
        medicalService.checkTemperature(id1, currentTemperature);

        //Then
        Mockito.verify(alertService, Mockito.times(0)).send("Warning, patient with id: null, need help");
    }
}