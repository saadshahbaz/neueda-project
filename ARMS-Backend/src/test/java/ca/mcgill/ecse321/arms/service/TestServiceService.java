package ca.mcgill.ecse321.arms.service;

import ca.mcgill.ecse321.arms.dao.ServiceRepository;
import ca.mcgill.ecse321.arms.model.Service;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

@ExtendWith(MockitoExtension.class)
public class TestServiceService {

    @Mock
    private ServiceRepository serviceRepository;

    @InjectMocks
    private ServiceService serviceService;

    private static final String SERVICENAME = "TestService";
    private static final int DURATION = 30;
    private static final int PRICE = 100;

    @BeforeEach
    public void setMockOutput() {

        // mock for findServiceByName
        lenient().when(serviceRepository.findServiceByName(anyString())).thenAnswer((InvocationOnMock invocation) -> {
            if (invocation.getArgument(0).equals(SERVICENAME)) {
                Service service = new Service();
                service.setName(SERVICENAME);
                service.setDuration(DURATION);
                service.setPrice(PRICE);
                return service;
            } else {
                return null;
            }
        });

    }
}
