package net.findeasily.website.service;

import org.springframework.stereotype.Service;

import net.findeasily.website.repository.UserRepository;
import net.findeasily.website.repository.PropertyRepository;
/**
 * @author Hao Zhou
 * @since 2018-08-08
 */
@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;
    @Autowired
    public PropertyService(){
        
    }
}
