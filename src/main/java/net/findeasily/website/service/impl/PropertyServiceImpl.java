package net.findeasily.website.service.impl;

import net.findeasily.website.domain.Property;
import net.findeasily.website.mapper.PropertyMapper;
import net.findeasily.website.service.PropertyService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Hao Zhou
 * @since 2018-08-08
 */
@Service
public class PropertyServiceImpl extends ServiceImpl<PropertyMapper, Property> implements PropertyService {

}
